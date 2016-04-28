package com.javarush.test.level19.lesson10.home03;

import java.io.*;
import java.text.*;
import java.util.*;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();
    static DateFormat df = new SimpleDateFormat("dd MM yyyy");

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = input.readLine()) != null) {
            String[] data = line.split("(?<=\\D) (?=\\d)");
            PEOPLE.add(new Person(data[0], df.parse(data[1])));
        }
        input.close();
//        for (Person person : PEOPLE) System.out.println(person.getName() + "#" + df.format(person.getBirthday()));
    }

}
