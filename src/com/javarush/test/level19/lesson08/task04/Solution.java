package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testString.printSomething();
        String exercise = outputStream.toString().trim();
        String[] members = exercise.split("\\s");
        int op1 = Integer.parseInt(members[0]);
        int oper = members[1].charAt(0);//43-+ 45-- *-42
        int op2 = Integer.parseInt(members[2]);
        int result = 0;
        switch (oper) {
            case (43):
                result = op1 + op2;
                break;
            case (45):
                result = op1 - op2;
                break;
            case (42):
                result = op1 * op2;
                break;
        }
        System.setOut(consoleStream);
        System.out.println(exercise + (" " + result));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 * 6 = ");
        }
    }
}

