package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream input=new FileInputStream(args[0]);
        int count=0;
        while (input.available()>0){
            int c = input.read();
            if (c >64&&c<91 || c>96 && c<123) count++;
        }
        input.close();
        System.out.println(count);
    }
}
