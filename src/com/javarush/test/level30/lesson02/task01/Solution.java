package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int radix = 10;
        if (s.charAt(0) == '0') {
            int cut = 1;
            char c = Character.toLowerCase(s.charAt(1));
            if (c == 'x') {
                radix = 16;
                cut++;
            } else if (c == 'b') {
                radix = 2;
                cut++;
            } else radix = 8;
            s = s.substring(cut);
        }
        return String.valueOf(Integer.parseInt(s, radix));
    }
}
