package com.javarush.test.level08.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* ����� �������� � ��������� ����� ����������������
������: ��������� ����������, ����� ����� (�������) ���� � ���� � ��������� �������.
����� ������: ��������� ������ �������� �� � �������� �����, � � ��������:
������ �����:
������
�������
����
�������
������
����������

������

������ ������:
����������
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        HashMap<String,String> addresses = new HashMap<String,String>();
        while (true)
        {
            String city= reader.readLine();
            if (city.isEmpty()) break;
            String family = reader.readLine();
            addresses.put(city, family);
        }

        //read home number
        String cityread = reader.readLine();

        if (addresses.containsKey(cityread))
        {
            String familySecondName = addresses.get(cityread);
            System.out.println(familySecondName);
        }
    }
}
