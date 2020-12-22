package com.king.mobile.lib.algorithm;

import java.util.Arrays;

class SortProgramming {

    public static void quickSort(int[] arr) {
        quick(0, arr.length - 1, arr);
    }

    private static void quick(int l, int r, int[] arr) {
        if (l >= r) return;
        int bIndex = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < arr[l]) {
                int tem = arr[i];
                for (int j = i; j > bIndex; j--) {
                    arr[j] = arr[j - 1];
                    System.out.println(Arrays.toString(arr));
                }
                arr[bIndex] = tem;
                bIndex++;
            }
        }
        quick(l, bIndex - 1, arr);
        quick(bIndex + 1, r, arr);
    }

    public static void mergeSort(int[] arr) {
        int[] merge = merge(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(merge));
    }

    private static int[] merge(int[] arr, int start, int end) {
        if (end == start) {
            int[] ints = {arr[start]};
            System.out.println(Arrays.toString(ints));
            return ints;
        } else {
            int mid = (start + end) / 2;
            int[] sortLeft = merge(arr, start, mid);
            int[] sortRight = merge(arr, mid + 1, end);
            return mergeSortArr(sortLeft, sortRight);
        }
    }

    private static int[] mergeSortArr(int[] sorArr1, int[] sorArr2) {
        int len = sorArr1.length + sorArr2.length;
        int[] result = new int[len];
        int x = 0, y = 0;
        for (int i = 0; i < len; i++) {
            if (x >= sorArr1.length) {
                result[i] = sorArr2[y++];
            } else if (y >= sorArr2.length) {
                result[i] = sorArr1[x++];
            } else if (sorArr1[x] < sorArr2[y]) {
                result[i] = sorArr1[x++];
            } else {
                result[i] = sorArr2[y++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 5, 7, 4, 8};
        mergeSort(arr);
    }
}
