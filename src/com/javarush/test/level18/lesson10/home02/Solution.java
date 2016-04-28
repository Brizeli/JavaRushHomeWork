package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream input=new FileInputStream(args[0]);
        int count=0;
        int spaces=0;
        while (input.available()>0){
            if (input.read() ==' ') spaces++;
            count++;
        }
        input.close();
        System.out.printf("%.2f",(float)spaces/(float)count*100);
    }
}
