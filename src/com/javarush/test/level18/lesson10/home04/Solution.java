package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        FileInputStream input1=new FileInputStream(file1);
        FileInputStream input2=new FileInputStream(file2);
        int file2size = input2.available();
        int file1size = input1.available();
        byte[] content=new byte[file1size + file2size];
        input2.read(content);
        input1.read(content,file2size,file1size);
        input1.close();
        input2.close();
        FileOutputStream output=new FileOutputStream(file1);
        output.write(content);
        output.close();
    }
}
