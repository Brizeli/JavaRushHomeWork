package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/

import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console= System.out;
        System.setOut(new WrapOut(console));
        testString.printSomething();
        System.setOut(console);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }

    private static class WrapOut extends PrintStream {
        static int counter=0;
        @Override
        public void println(String x) {
            super.println(x);
            counter++;
            if (counter==2){
                super.println("JavaRush - курсы Java онлайн");
                counter=0;
            }
        }

        public WrapOut(OutputStream out) {
            super(out);
        }
    }
}
