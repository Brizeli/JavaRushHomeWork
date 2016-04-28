package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Solution {
    private static final long SEED = 123456;
    private static final Random gen = new Random(SEED);

    public static void main(String[] args) throws IOException {
        boolean cifer=false;
        if (args[0].equals("-e")) cifer=true;
        else if (args[0].equals("-d")) cifer=false;
        else return;
        FileInputStream input = new FileInputStream(args[1]);
        FileOutputStream output = new FileOutputStream(args[2]);
        byte[] b = new byte[input.available()];
        input.read(b);
        output.write(cifer(b, cifer));
        input.close();
        output.close();
    }

    private static byte[] cifer(byte[] b, boolean cifer) {
        byte[] res = new byte[b.length];
        for (int i = 0; i < res.length; i++) {
            int incr = gen.nextInt();//validator waits for constant
            res[i] = (byte) (cifer ? b[i] + incr : b[i] - incr);
        }
        return res;
    }
}
