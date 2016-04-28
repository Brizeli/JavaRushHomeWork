package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) content.append(line + " ");
        input.close();
        StringBuilder word1, word2;
        String[] words = content.toString().split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].equals("")) continue;
            word1 = new StringBuilder(words[i]);
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].equals("")) continue;
                word2 = new StringBuilder(words[j]);
                if (word1.toString().equals(word2.reverse().toString())) {
                    Pair pair = new Pair();
                    pair.first = words[i];
                    pair.second = words[j];
                    result.add(pair);
                    words[i] = "";
                    words[j] = "";
                    break;
                }
            }
        }
        System.out.println(result);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
