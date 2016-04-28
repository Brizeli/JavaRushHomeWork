package com.javarush.test.level26.lesson02.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static void main(String[] args) {
        Integer[] array = {5, 6, 8, 9, 6, 3, 1, 4, 2, 3,7};
        double median = getMedian(array);
        sort(array);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            System.out.print(Math.abs(median-array[i])+",");
        }
    }
    //[5,6,8,9,6,3,1,4,2,3] -> [1,2,3,3,4, 5,6,6,8,9]:4.5
    public static Integer[] sort(Integer[] array) {
        final double median=getMedian(array);
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double do1=Math.abs(o1-median);
                double do2=Math.abs(o2-median);
                double res=do1-do2;
                return res==0?o1-o2: (int) res;
            }
        });
        return array;
    }

    private static double getMedian(Integer[] array) {
        Arrays.sort(array);
        int length = array.length;
        return length%2==0?(array[length/2]+array[length/2-1])/2.0:array[length/2];
    }


}
