package com.javarush.test.level30.lesson08.task01;

/* Найдем число 2 в максимальной степени
Реализуйте логику метода maxPowerOf2,
который должен возвращать число 2 в максимальной степени, которое получается поместить в переданное число
Аргументом maxPowerOf2 может быть только положительное число
Используйте только операции: 1)побитовые сдвиги, 2) присваивание, 3) побитовое ИЛИ
Не оставляйте комментарии
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(maxPowerOf2(140_000));   //131072
        System.out.println(maxPowerOf2(1026));      //1024
        System.out.println(maxPowerOf2(17));        //16
        System.out.println(maxPowerOf2(1<<15));
    }

    public static int maxPowerOf2(int x) {
        System.out.printf("%18s\n", b(x));
        System.out.printf("%18s\n", b(x >> 1));
        System.out.printf("%18s\n", b(x | x >> 1));
        x |= x >> 1;
        System.out.println();
        System.out.printf("%18s\n", b(x));
        System.out.printf("%18s\n", b(x >> 2));
        System.out.printf("%18s\n", b(x | x >> 2));
        x |= x >> 2;
        System.out.println();
        System.out.printf("%18s\n", b(x));
        System.out.printf("%18s\n", b(x >> 4));
        System.out.printf("%18s\n", b(x | x >> 4));
        x |= x >> 4;
        System.out.println();
        System.out.printf("%18s\n", b(x));
        System.out.printf("%18s\n", b(x >> 8));
        System.out.printf("%18s\n", b(x | x >> 8));
        x |= x >> 8;
        x |= x >> 16;
        System.out.println((x+1)>>1);
        return x-(x>>1);
    }

    private static String b(int i) {
        return Integer.toBinaryString(i);
    }
}
