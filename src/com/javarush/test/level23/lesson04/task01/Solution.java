package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] res=new Solution[2];
        for (int i = 0; i < res.length; i++) {
            res[i]=new Solution();
            for (int j = 0; j < res[i].innerClasses.length; j++) {
                res[i].innerClasses[j]=res[i].new InnerClass();
            }
        }
        return res;
    }
}
