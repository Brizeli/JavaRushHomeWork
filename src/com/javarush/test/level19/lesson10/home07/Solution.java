package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(args[0]));
        PrintStream output = new PrintStream(new FileOutputStream(args[1]));
        String line;
        String res = "";
        while ((line = input.readLine()) != null) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (word.length() > 6)
                    res+=word + ",";
            }
        }
        output.print(res.substring(0,res.length()-1));
        input.close();
        output.close();
    }
}
