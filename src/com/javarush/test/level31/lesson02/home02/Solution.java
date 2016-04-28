package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        for (String path: getFileTree("D:\\Docs\\Dropbox\\J\\JavaRushHomeWork"))
            System.out.println(path);
    }
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> dirQueue=new ArrayDeque<>();
        List<String> res=new ArrayList<>();
        for (File file:new File(root).listFiles()){
            if (file.isDirectory()) dirQueue.add(file);
            else res.add(file.getAbsolutePath());
        }
        while (!dirQueue.isEmpty()){
            File dir=dirQueue.remove();
            for (File file:dir.listFiles()){
                if (file.isDirectory()) dirQueue.add(file);
                else res.add(file.getAbsolutePath());
            }
        }
        return res;

    }
}
