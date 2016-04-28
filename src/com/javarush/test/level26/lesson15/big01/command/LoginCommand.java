package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards=ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res=ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            String number = ConsoleHelper.readString();
            String pinCode = ConsoleHelper.readString();
            if (isValid(number,12) && isValid(pinCode,4)) {
                if (validCreditCards.containsKey(number) && pinCode.equals(validCreditCards.getString(number))){
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),number));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),number));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }else ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
    }

    private boolean isValid(String number, int length) {
        if (number.length()!=length) return false;
        if (!number.matches("\\d+")) return false;
        return true;
    }
}
