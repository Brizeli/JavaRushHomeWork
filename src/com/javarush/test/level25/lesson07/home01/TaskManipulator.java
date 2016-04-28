package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread t;

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(t.getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}
    }

    @Override
    public void start(String threadName) {
        t = new Thread(null, this, threadName);
        t.start();
    }

    @Override
    public void stop() {
        t.interrupt();
    }
}
