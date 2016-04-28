package com.javarush.test.level09.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* ������ �� ����������
������: ������������ ������ � ���������� ������ ���� (� �����). ����� ������� � ������������ �������, ����� - � ���������.
������ �����:
�����
1
���
3
������
2
0
�����
������ ������:
�����
3
���
2
�����
1
0
������
*/

public class StringsAndNumbersSort
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        ArrayList<Integer> numIndexes=new ArrayList<Integer>();
        ArrayList<Integer> strIndexes=new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) numIndexes.add(i);
            else strIndexes.add(i);
        }
        for (int i = 0; i < numIndexes.size()-1; i++) {
            for (int j = i+1; j <numIndexes.size() ; j++) {
                int a=Integer.parseInt(array[numIndexes.get(i)]);
                int b=Integer.parseInt(array[numIndexes.get(j)]);
                if (b>a){
                    array[numIndexes.get(i)]=Integer.toString(b);
                    array[numIndexes.get(j)]=Integer.toString(a);
                }
            }
        }
        for (int i = 0; i < strIndexes.size()-1; i++) {
            for (int j = i+1; j <strIndexes.size() ; j++) {
                String a=array[strIndexes.get(i)];
                String b=array[strIndexes.get(j)];
                if (isGreaterThen(a,b)){
                    array[strIndexes.get(i)]=b;
                    array[strIndexes.get(j)]=a;
                }
            }
        }
    }

    //����� ��� ��������� �����: '�' ������ ��� 'b'
    public static boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) > 0;
    }


    //������ - ��� �� ����� ���� �����?
    public static boolean isNumber(String s)
    {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if ((i != 0 && c == '-') //���� '-' ������ ������
                    || (!Character.isDigit(c) && c != '-') ) // �� ����� � �� ���������� � '-'
            {
                return false;
            }
        }
        return true;
    }
}
