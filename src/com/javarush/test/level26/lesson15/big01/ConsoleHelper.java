package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res=ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String input = reader.readLine();
            if (input.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
            return input;
        } catch (IOException e) {
        }
        return null;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        Operation operation;
        writeMessage("1. "+res.getString("operation.INFO"));
        writeMessage("2. "+res.getString("operation.DEPOSIT"));
        writeMessage("3. "+res.getString("operation.WITHDRAW"));
        writeMessage("4. "+res.getString("operation.EXIT"));
        while (true) {
            try {
                int selection = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(selection);
                break;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String input = "";
        while (true) {
            input = readString();
            if (input.length() == 3) break;
            writeMessage(res.getString("invalid.data"));
        }
        return input.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
        while (true) {
            result = readString().split(" ");
            if (areTwoPositiveIntegers(result)) break;
            writeMessage(res.getString("invalid.data"));
        }
        return result;
    }

    private static boolean areTwoPositiveIntegers(String[] res) {
        if (res.length != 2) return false;
        for (String s : res) {
            if (!s.matches("[0-9]+")) return false;
        }
        return true;
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
