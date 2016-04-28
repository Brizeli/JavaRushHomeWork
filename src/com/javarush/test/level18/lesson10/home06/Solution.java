package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream input = new FileInputStream(args[0]);
        Map<Character, Integer> map = new TreeMap<>();
        while (input.available()>0){
            int c = input.read();
            Integer count=map.get((char)c);
            if (count==null) count=0;
            map.put((char) c, ++count);
        }
        input.close();
        for (Map.Entry<Character,Integer> entry:map.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());
    }
}
