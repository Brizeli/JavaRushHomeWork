package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fr = new FileReader(reader.readLine());
        FileWriter fw = new FileWriter(reader.readLine());
        reader.close();
        StringBuffer contents=new StringBuffer();
        while (fr.ready()) contents.append((char)fr.read());
        String[] words=contents.toString().split("\\s");
        for (String word:words){
            try{
                Integer.parseInt(word);
                fw.write(word+" ");
            }
            catch (Exception e){}
        }
        fr.close();
        fw.close();
    }
}
