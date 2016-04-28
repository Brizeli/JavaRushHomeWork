package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int n1=Integer.parseInt(reader.readLine());
        int n2=Integer.parseInt(reader.readLine());
        int n3=Integer.parseInt(reader.readLine());
        if ((n1<n2 && n1>n3)||(n1>n2&&n1<n3)) System.out.println(n1);
        else if ((n2<n1&&n2>n3)||(n2>n1&&n2<n3)) System.out.println(n2);
        else System.out.println(n3);
    }
}
