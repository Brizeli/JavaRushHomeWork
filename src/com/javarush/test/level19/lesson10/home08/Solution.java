package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть потоки. Не использовать try-with-resources

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(reverse(line));
        }
        input.close();
    }

    private static String reverse(String line) {
        String res = "";
        for (int i = line.length() - 1; i > -1; i--) {
            res += line.charAt(i);
        }
        return res;
    }

}
