package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        FileInputStream input=new FileInputStream(reader.readLine());
        FileOutputStream output=new FileOutputStream(reader.readLine());
        byte[] buffer = new byte[input.available()];
        input.read(buffer);
        for (int i = buffer.length-1; i>=0 ; i--) {
            output.write(buffer[i]);
        }
        reader.close();
        input.close();
        output.close();
    }
}
