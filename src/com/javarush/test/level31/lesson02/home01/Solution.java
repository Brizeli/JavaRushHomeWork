package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    private static List<File> filesToSaveList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File sourceFolder = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        scanFolder(sourceFolder, resultFileAbsolutePath);
        File newResultFile = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
        resultFileAbsolutePath.renameTo(newResultFile);
        Collections.sort(filesToSaveList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        saveContentsTo(newResultFile);
    }

    private static void saveContentsTo(File newResultFile) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(newResultFile));
        for (File file : filesToSaveList) {
            BufferedReader input = new BufferedReader(new FileReader(file));
            while (input.ready()) {
                output.write(input.readLine());
                output.newLine();
            }
            input.close();
        }
        output.close();
    }
    
    private static void scanFolder(File file, File resultFile) {
        if (file.equals(resultFile)) {
            return;
        }
        if (file.isDirectory()) {
            for (File subFile : file.listFiles())
                scanFolder(subFile, resultFile);
            file.delete();
        } else {
            if (file.length() > 50) file.delete();
            else filesToSaveList.add(file);
        }
    }
    
    
}
