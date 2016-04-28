package com.javarush.test.level32.lesson02.task01;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) throws Exception {
//        args=new String[]{"D:\\pathToTest\\file.txt","100","text"};
        RandomAccessFile raf=new RandomAccessFile(args[0],"rw");
        raf.skipBytes(Integer.parseInt(args[1]));
        raf.writeBytes(args[2]);
        raf.close();
    }
}
