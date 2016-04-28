package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(args[0]));
        PrintStream output=new PrintStream(new FileOutputStream(args[1]));
        String line;
        while ((line=input.readLine())!=null){
            String[] words=line.split(" ");
            for (String word:words)
                if (word.matches(".*\\d+.*"))
                    output.print(word+" ");
        }
        input.close();
        output.close();
    }
}
