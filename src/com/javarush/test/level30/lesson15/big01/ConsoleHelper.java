package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString(){
        String line = null;
        while (true) {
            try {
                line = reader.readLine();
                break;
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return line;
    }
    public static int readInt(){
        int res;
        while (true){
            try{
                res=Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e){
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
        return res;
    }
}
