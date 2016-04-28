package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> archivePieces= Arrays.asList(Arrays.copyOfRange(args,1,args.length));
        Collections.sort(archivePieces);
        List<FileInputStream> inputStreams=new ArrayList<>();
        for (String piece : archivePieces) inputStreams.add(new FileInputStream(piece));
        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(inputStreams));

        ZipInputStream zis = new ZipInputStream(sis);
        FileOutputStream fos = new FileOutputStream(args[0]);
        byte[] buf = new byte[1024 * 1024]; // 1MB buffer
        while (zis.getNextEntry() != null) {
            int count;
            while ((count = zis.read(buf)) != -1) {
                fos.write(buf, 0, count);
            }
        }
        sis.close();
        zis.close();
        fos.close();
    }

}
