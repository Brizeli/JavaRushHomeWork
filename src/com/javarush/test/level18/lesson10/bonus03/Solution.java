package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        int id = Integer.parseInt(args[1]);
        ArrayList<String> lines = getLines(fileName);
        PrintStream output = new PrintStream(new FileOutputStream(fileName));
        for (String line : lines) {
            int curId = Integer.parseInt(line.substring(0, 8).trim());
            if (curId == id) {
                if (args[0].equals("-u"))
                    line = String.format("%-8d%-30s%-8s%-4s", id, args[2], args[3], args[4]);
                if (args[0].equals("-d"))
                    continue;
            }
            output.println(line);
        }
        output.close();
    }

    private static ArrayList<String> getLines(String fileName) throws IOException {
        ArrayList<String> res = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = input.readLine()) != null) res.add(line);
        input.close();
        return res;
    }
}
