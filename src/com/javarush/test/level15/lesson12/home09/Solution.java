package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String s=reader.readLine();
        String[] pars=s.substring(s.indexOf('?')+1).split("&");
        String objVal=null;
        for (int i = 0; i < pars.length; i++) {
            String[] par=pars[i].split("=");
            if (par[0].equals("obj")) objVal=par[1];
            System.out.print(par[0]);
            if (i<pars.length-1) System.out.print(" ");
        }
        System.out.println();
        if (objVal!=null){
            try{
                alert(Double.parseDouble(objVal));
            }
            catch (NumberFormatException e){
                alert(objVal);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
