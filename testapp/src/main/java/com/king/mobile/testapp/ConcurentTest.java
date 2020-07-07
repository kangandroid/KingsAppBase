package com.king.mobile.testapp;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ConcurentTest {
    int a = 0;

    public void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                add(10);
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                add(10);
            }
        });
        threadA.start();
        threadB.start();

        Callable<String> callable = new Callable<String>(){

            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };
        try {
            Future<String> future = new Future<String>() {
                @Override
                public boolean cancel(boolean mayInterruptIfRunning) {
                    return false;
                }

                @Override
                public boolean isCancelled() {
                    return false;
                }

                @Override
                public boolean isDone() {
                    return false;
                }

                @Override
                public String get() throws ExecutionException, InterruptedException {
                    return null;
                }

                @Override
                public String get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                    return null;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void add(int time) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < time; i++) {
            if(a==5) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            a++;
        }
    }



}
