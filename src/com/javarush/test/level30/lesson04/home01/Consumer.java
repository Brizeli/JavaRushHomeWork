package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Processing " + queue.take());
            }
        } catch (InterruptedException e) {
        }
    }
}
