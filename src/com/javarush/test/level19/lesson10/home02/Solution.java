package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader input=new BufferedReader(new FileReader(args[0]));
        Map<String,Double> res=new HashMap<>();
        String line;
        while((line=input.readLine())!=null){
            String[] pair= line.split(" ");
            String name=pair[0];
            Double value=Double.parseDouble(pair[1]);
            Double prevValue=res.get(name);
            if (prevValue!=null) value+=prevValue;
            res.put(name,value);
        }
        input.close();
        Double max = Collections.max(res.values());
        for (Map.Entry<String,Double> entry:res.entrySet()){
            if (entry.getValue()==max)System.out.println(entry.getKey());
        }
    }
}
