package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        isDateOdd("MAY 1 2013");
    }

    public static boolean isDateOdd(String date)
    {
        Date startofyear=new Date();
        Date date1=new Date(date);
        startofyear.setMonth(0);
        startofyear.setDate(1);
        return ((date1.getTime()-startofyear.getTime())/(24 * 60 * 60 * 1000)%2==0)?true:false;
    }
}
