package com.javarush.test.level26.lesson08.task01;

import java.util.concurrent.atomic.AtomicInteger;

/* Вежливость - это искусственно созданное хорошее настроение.
В классе Solution создайте public static класс IntegerHolder.
IntegerHolder должен быть для типа int, быть нитебезопасным и изменяемым.
В этом классе должны быть два public метода get и set
*/
public class Solution {
    public static void main(String[] args) {
    }
    public static class IntegerHolder{
        private final AtomicInteger anInt=new AtomicInteger();

        public synchronized int get() {
            return anInt.get();
        }

        public synchronized void set(int holder) {
            anInt.set(holder);
        }

    }
}
