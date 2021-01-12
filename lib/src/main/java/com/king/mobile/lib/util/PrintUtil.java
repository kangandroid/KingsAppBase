package com.king.mobile.lib.util;

import java.util.Arrays;

public class PrintUtil {
    public static void printArray(int[] args) {
        System.out.print("{");
        System.out.print(Arrays.toString(args));
        System.out.print("}");

    }

    public static void printB(int abc) {
        System.out.println(Integer.toBinaryString(abc));
    }

    public static void print(int abc) {
        System.out.println(abc);
    }

    public static void print(String abc) {
        System.out.println(abc);
    }
}
