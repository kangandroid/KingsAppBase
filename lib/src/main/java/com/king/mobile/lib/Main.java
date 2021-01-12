package com.king.mobile.lib;

import com.king.mobile.lib.thread.ProductAndConsumer;
import com.king.mobile.lib.util.PrintUtil;


public class Main {
    public static void main(String[] args) {
        ProductAndConsumer productAndConsumer = new ProductAndConsumer();
        productAndConsumer.startProduce();
        try {
            Thread thread = Thread.currentThread();
            thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productAndConsumer.startConsume();
    }

    static int hash(Object key) {
        int h;
        if (key == null) {
            return 0;
        }
        h = key.hashCode();
        PrintUtil.printB(h);
        int cont = h >>> 16;
        return h ^ cont;
    }
}