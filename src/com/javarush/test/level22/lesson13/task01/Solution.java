package com.javarush.test.level22.lesson13.task01;

import java.util.Arrays;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer t=new StringTokenizer(query,delimiter);
        String[] res=new String[t.countTokens()];
        for (int i = 0; i < res.length; i++) {
            res[i]=t.nextToken();
        }
        return res;
    }
}
