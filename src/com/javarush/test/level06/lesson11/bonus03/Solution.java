package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(reader.readLine());
        int n2 = Integer.parseInt(reader.readLine());
        int n3 = Integer.parseInt(reader.readLine());
        int n4 = Integer.parseInt(reader.readLine());
        int n5 = Integer.parseInt(reader.readLine());
        int x;
        for (int i = 0; i < 4; i++) {
            if (n2<n1){
                x=n1;
                n1=n2;
                n2=x;
            }
            if (n3<n2) {
                x = n2;
                n2 = n3;
                n3 = x;
            }
            if (n4<n3) {
                x = n3;
                n3 = n4;
                n4 = x;
            }
            if (n5<n4) {
                x = n4;
                n4 = n5;
                n5 = x;
            }
        }
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);
    }
}
