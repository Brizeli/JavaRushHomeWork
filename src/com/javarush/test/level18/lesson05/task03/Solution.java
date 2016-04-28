package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
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
        FileOutputStream output1=new FileOutputStream(reader.readLine());
        FileOutputStream output2=new FileOutputStream(reader.readLine());
        byte[] buffer = new byte[input.available()];
        int size=buffer.length-buffer.length/2;
        input.read(buffer);
        output1.write(buffer,0,size);
        output2.write(buffer,size,buffer.length-size);
        output1.close();
        output2.close();
        input.close();
        reader.close();
    }
}
