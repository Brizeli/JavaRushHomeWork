package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    private static Random random = new Random();

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ArrayList<Character> pswChars = new ArrayList<>();
        addChars(pswChars, '0', '9');
        addChars(pswChars, 'A', 'Z');
        addChars(pswChars, 'a', 'z');
        int size = pswChars.size();
        outputStream.write(pswChars.get(random.nextInt(10)));
        int indexUpper = 10 + random.nextInt('Z' - 'A');
        outputStream.write(pswChars.get(indexUpper));
        int indexLower = 10 + 1 + 'Z' - 'A' + random.nextInt('z' - 'a');
        outputStream.write(pswChars.get(indexLower));
        outputStream.write(pswChars.get(random.nextInt(10)));
        indexUpper = 10 + random.nextInt('Z' - 'A');
        outputStream.write(pswChars.get(indexUpper));
        indexLower = 10 + 1 + 'Z' - 'A' + random.nextInt('z' - 'a');
        outputStream.write(pswChars.get(indexLower));
        outputStream.write(pswChars.get(random.nextInt(10)));
        indexUpper = 10 + random.nextInt('Z' - 'A');
        outputStream.write(pswChars.get(indexUpper));
        return outputStream;
    }
    
    private static void addChars(ArrayList<Character> pswChars, char start, char end) {
        while (start <= end) {
            pswChars.add(start++);
        }
    }
}
