package com.javarush.test.level25.lesson09.task03;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    public static void main(String[] args) {
        new Thread() {
            Solution eh = new Solution();
            {setUncaughtExceptionHandler(eh);}
            @Override
            public void run() {
                eh.uncaughtException(this,new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
            }
        }.start();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        print(e);
    }

    private void print(Throwable e) {
        if (e.getCause()!=null) print(e.getCause());
        System.out.println(e);
    }
}
