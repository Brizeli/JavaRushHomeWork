package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Arrays;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNumbers(Integer.MAX_VALUE)));
    }

    public static int[] getNumbers(int N) {
        int[] result = null;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= N && i > 0; i++) {
            if (i == sumOfPowersOfDigits(i, lengthOf(i))) {
                System.out.println(i);
                nums.add(i);
            }
        }
        result = new int[nums.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = nums.get(i);
        }
        return result;
    }

    private static int sumOfPowersOfDigits(int i, int power) {
        return i == 0 ? 0 : power(i % 10, power) + sumOfPowersOfDigits(i / 10, power);
    }

    private static int power(int i, int power) {
        return power == 1 ? i : i * power(i, power - 1);
    }

    private static int lengthOf(int i) {
        return i == 0 ? 0 : 1 + lengthOf(i / 10);
    }
}
