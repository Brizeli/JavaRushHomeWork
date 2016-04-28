package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = reader.readLine()).equals("exit")) {
            new ReadThread(line).start();
        }
        reader.close();
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                FileInputStream input = new FileInputStream(fileName);
                TreeMap<Integer,Integer> map=new TreeMap<>();
                while (input.available() > 0) {
                    int key = input.read();
                    Integer count = map.get(key);
                    if (count==null) count=0;
                    map.put(key,++count);
                }
                input.close();
                int max=0;
                int search=0;
                for (Map.Entry<Integer,Integer> entry:map.entrySet()){
                    if (entry.getValue()>max){
                        max=entry.getValue();
                        search=entry.getKey();
                    }
                }
                resultMap.put(fileName,search);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
