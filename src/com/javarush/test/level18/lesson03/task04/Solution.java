package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream input=new FileInputStream(reader.readLine());
        Map<Integer,Integer> bytes=new HashMap<>();
        while (input.available()>0){
            int key = input.read();
            Integer count=bytes.get(key);
            if (count==null) count=0;
            bytes.put(key,++count);
        }
        ArrayList<Integer> values = new ArrayList<>(bytes.values());
        int minRepeat=values.get(0);
        for (Integer val:values) if (val<minRepeat) minRepeat=val;
        for (Map.Entry<Integer,Integer> entry:bytes.entrySet()){
            if (entry.getValue()==minRepeat) System.out.print(entry.getKey()+" ");
        }
        input.close();
    }
}
