package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader=new FileReader(reader.readLine());
        reader.close();
        StringBuffer contents=new StringBuffer("");
        while (fileReader.ready()) contents.append((char)fileReader.read());
        fileReader.close();
        String[] words = contents.toString().split("\\W+");
        int count=0;
        for (String word : words)
            if ("world".equals(word))
                count++;
        System.out.println(count);
    }
}
