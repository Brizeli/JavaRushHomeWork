package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.*;
import com.javarush.test.level26.lesson15.big01.exception.*;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        int amount = 0;
        Map<Integer, Integer> withdrawAmountMap;
        while (true) {
            String input = ConsoleHelper.readString();
            try {
                amount = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (amount < 0) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(amount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try {
                withdrawAmountMap = manipulator.withdrawAmount(amount);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),amount,currencyCode));
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
        for (Map.Entry<Integer, Integer> entry : withdrawAmountMap.entrySet())
            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
    }
}
