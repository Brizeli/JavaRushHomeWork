package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id,
найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (!args[0].equals("-c")) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line, lastLine = null;
        while ((line = input.readLine()) != null) lastLine = line;
        input.close();
        int id = Integer.parseInt(lastLine.substring(0, 8));
        PrintStream output=new PrintStream(new FileOutputStream(fileName,true));
        output.printf("%-8d%-30s%-8s%-4s\n",++id,args[1],args[2],args[3]);
        output.close();
    }
}
