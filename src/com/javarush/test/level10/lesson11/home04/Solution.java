package com.javarush.test.level10.lesson11.home04;

/* ������� ��������
������� �� ����� ������� �� �� ���� ������� Java, � ���� ������� �������� 40 ��� �� �������.
�������:
� �� ���� ������� Java, � ���� ������� ��������
 �� ���� ������� Java, � ���� ������� ��������
�� ���� ������� Java, � ���� ������� ��������
� ���� ������� Java, � ���� ������� ��������
 ���� ������� Java, � ���� ������� ��������
���� ������� Java, � ���� ������� ��������
�
*/

public class Solution
{
    public static void main(String[] args)
    {
        String s = "� �� ���� ������� Java, � ���� ������� ��������";
        for (int i = 0; i < 40; i++) {
            System.out.println(s.substring(i));
        }
//        char[] str=s.toCharArray();
//        for (int i = 0; i < 40; i++) {
//            for (int j = i; j <str.length ; j++)
//                System.out.print(str[j]);
//            System.out.println();
//        }
    }
}
