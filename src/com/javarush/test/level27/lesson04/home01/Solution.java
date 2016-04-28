package com.javarush.test.level27.lesson04.home01;

/* Модификаторы и дедлоки
Расставьте модификаторы так, чтобы при работе с этим кодом появился дедлок
*/
public class Solution {
    public static void main(String[] args) {
        final Solution s= new Solution();
        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run() {
                    s.getData();
                }
            }.start();
        }
    }
    public synchronized Object getData() {
        return new ThreadDeadlock().getData();
    }
}
