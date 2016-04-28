package com.javarush.test.level28.lesson06.home01;

public class MyThread extends Thread {
    private static int priority=0;
    public MyThread() {
        setPriority(getCyclePriority());
    }

    private int getCyclePriority() {
        int pri = priority++%Thread.MAX_PRIORITY + 1;
        ThreadGroup group = currentThread().getThreadGroup();
        if (group !=null && pri>group.getMaxPriority())
            pri=group.getMaxPriority();
        return pri;
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(getCyclePriority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority(getCyclePriority());

    }

    public MyThread(String name) {
        super(name);
        setPriority(getCyclePriority());

    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority(getCyclePriority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(getCyclePriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority(getCyclePriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority(getCyclePriority());
    }
}
