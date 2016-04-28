package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String outFileName=null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, byte[]> fileNames=new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String[] partsO1 = ((String) o1).split("part");
                String[] partsO2 = ((String) o2).split("part");
                int n1 = Integer.parseInt(partsO1[partsO1.length - 1]);
                int n2 = Integer.parseInt(partsO2[partsO2.length - 1]);
                return n1-n2;
            }
        });
        String line;
        while (!(line=reader.readLine()).equals("end")){
            if (outFileName == null) outFileName=line.split(".part")[0];
            FileInputStream input=new FileInputStream(line);
            byte[] b=new byte[input.available()];
            input.read(b);
            fileNames.put(line,b);
            input.close();
        }
        reader.close();
        FileOutputStream output=new FileOutputStream(outFileName);
        int i=0;
        for (Map.Entry<String, byte[]> entry:fileNames.entrySet()){
            output.write(entry.getValue());
        }
        output.close();
    }
}
