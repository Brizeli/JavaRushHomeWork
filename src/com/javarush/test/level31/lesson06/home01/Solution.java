package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.
Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip
Файлы внутри test.zip:
a.txt
b.txt
После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt
Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {

    private static final Map<String, byte[]> zipEntryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        saveZipEntriesToMap(args[1]);
        //adding new entry to a map
        String fileName = args[0].substring(args[0].lastIndexOf("/") + 1);
        if (zipEntryMap.containsKey(fileName)) fileName="new/" + fileName;
        zipEntryMap.put(fileName, getFileBytes(args[0]));

        retrieveAllEntriesToArchive(args[1]);
    }

    private static void saveZipEntriesToMap(String zipFilePath) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;
            while ((count = zis.read(buffer)) != -1) baos.write(buffer, 0, count);
            zipEntryMap.put(entry.getName(), baos.toByteArray());
            baos.close();
        }
        zis.close();
    }

    private static byte[] getFileBytes(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] fileBytes = new byte[fis.available()];
        fis.read(fileBytes);
        fis.close();
        return fileBytes;
    }

    private static void retrieveAllEntriesToArchive(String zipFilePath) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
        for (Map.Entry<String, byte[]> entry : zipEntryMap.entrySet()) {
            zos.putNextEntry(new ZipEntry(entry.getKey()));
            zos.write(entry.getValue());
            zos.closeEntry();
        }
        zos.close();
    }
}