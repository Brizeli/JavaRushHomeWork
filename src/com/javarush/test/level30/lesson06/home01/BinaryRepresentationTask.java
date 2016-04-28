package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        int a = i%2;
        int b = i/2;
        String res = String.valueOf(a);
        return b > 0 ? new BinaryRepresentationTask(b).fork().join() + res : res;
    }
}
