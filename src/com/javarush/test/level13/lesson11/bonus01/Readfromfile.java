package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Readfromfile
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader fileread=new BufferedReader(new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine()));
        String s;
        ArrayList list=new ArrayList();
        while ((s=fileread.readLine())!=null) {
            int next = Integer.parseInt(s);
            if (next%2==0) list.add(next);
        }
        fileread.close();
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
