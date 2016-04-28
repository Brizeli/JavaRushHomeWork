package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream file1 = new FileOutputStream(reader.readLine());
        FileInputStream file2 = new FileInputStream(reader.readLine());
        FileInputStream file3 = new FileInputStream(reader.readLine());
        copyBytes(file1,file2);
        copyBytes(file1,file3);
        file1.close();
        file2.close();
        file3.close();
        reader.close();
    }

    private static void copyBytes(FileOutputStream output, FileInputStream input) throws Exception {
        while(input.available()>0) output.write(input.read());
    }
}
