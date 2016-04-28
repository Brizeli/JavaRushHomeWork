package com.javarush.test.level19.lesson08.task01;

/* Ридер обертка
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна преобразовывать весь текст в заглавные буквы
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток.
Вывести модифицированную строку в консоль.
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console= System.out;
        PrintStream out = new PrintStream(new UppercaseOutputStream());
        System.setOut(out);
        testString.printSomething();
        System.setOut(console);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }

    private static class UppercaseOutputStream extends OutputStream {
        OutputStream out= System.out;

        @Override
        public void write(int b) throws IOException {
            char B= Character.toUpperCase((char)b);
            out.write(B);
        }
    }
}
