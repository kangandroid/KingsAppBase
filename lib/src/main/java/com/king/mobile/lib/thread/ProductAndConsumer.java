package com.king.mobile.lib.thread;

import com.king.mobile.lib.util.PrintUtil;

import java.util.ArrayList;
import java.util.List;


public class ProductAndConsumer {
    private List<Integer> buffer = new ArrayList<>();

    public void startProduce() {
        Producer producerA = new Producer(buffer);
        Producer producerB = new Producer(buffer);
        new Thread(producerA).start();
        new Thread(producerB).start();
    }

    public void startConsume() {
        Consumer consumerA = new Consumer(buffer);
        Consumer consumerB = new Consumer(buffer);
        new Thread(consumerA).start();
        new Thread(consumerB).start();
    }


    static class Producer implements Runnable {
        private List<Integer> container;

        Producer(List<Integer> container) {
            this.container = container;
        }

        public void produce() {
            synchronized (container) {
                while (true) {
                    try {
                        container.wait(100);
                        int size = container.size();
                        if (!(size < 1000)) break;
                        container.add(size);
                        PrintUtil.print(Thread.currentThread().getName() + "+++++++++produce====" + size);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        @Override
        public void run() {
            produce();
        }
    }

    static class Consumer implements Runnable {
        List<Integer> container;

        Consumer(List<Integer> container) {
            this.container = container;
        }

        public void consume() {
            synchronized (container) {
                while (!container.isEmpty()) {
                    try {
                        container.wait(100);
                        PrintUtil.print(Thread.currentThread().getName() + "---------consume====" + container.remove(0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ;


        @Override
        public void run() {
            consume();
        }
    }

}
