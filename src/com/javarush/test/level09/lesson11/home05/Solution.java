package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* ������� � ��������� �����
�������� ���������, ������� ������ � ���������� ������ ������.
��������� ������ ������� �� ����� ��� ������:
1. ������ ������ �������� ������ ������� �����
2. ������ - ������ ��������� ����� � ����� ���������� �� �������� ������.
����� ��������� ��������.

������ �����:
���� ���� ����.
������ ������:
� � � � � �
� � � � � � .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String text= reader.readLine().replace(" ","");
        ArrayList<Character> vowls=new ArrayList<Character>();
        ArrayList<Character> notvowls=new ArrayList<Character>();
        for (int i=0; i<text.length();i++) {
            if (isVowel(text.charAt(i))) vowls.add(text.charAt(i));
            else notvowls.add(text.charAt(i));
        }
        for (int i=0; i<vowls.size();i++) {
            System.out.print(i < vowls.size() - 1 ? vowls.get(i) + " " : vowls.get(i));
        }
        System.out.println();
        for (int i=0; i<notvowls.size();i++) {
            System.out.print(i<notvowls.size()-1?notvowls.get(i) + " ":notvowls.get(i));
        }
    }


    public static char[] vowels = new char[]{'�', '�', '�', '�', '�', '�', '�', '�', '�', '�'};

    //����� ���������, ������� �� �����
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //�������� ������ � ������ ������� - �� ��������� � �������� ������

        for (char d : vowels)   //���� ����� ������� �������
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
