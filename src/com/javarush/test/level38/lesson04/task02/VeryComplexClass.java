package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class VeryComplexClass {
    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsNullPointerException();
    }
    public void methodThrowsClassCastException() {
        Object x=new Integer(2);
        System.out.println((String)x);
        //напишите тут ваш код
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        System.out.println(s.length());
        //напишите тут ваш код
    }
}
