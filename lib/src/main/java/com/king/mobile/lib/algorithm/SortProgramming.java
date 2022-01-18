package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

class SortProgramming {

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] merge = merge(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(merge));
    }

    /**
     * @param arr
     * @param start
     * @param end   时间复杂度O(n Log n)
     * @return
     */
    private static int[] merge(int[] arr, int start, int end) {
        if (end == start) { //不可再分时 跳出递归
            return new int[]{arr[start]};
        } else { // 递归 分治 合并结果
            int mid = (start + end) / 2;
            int[] sortLeft = merge(arr, start, mid);
            int[] sortRight = merge(arr, mid + 1, end);
            return mergeSortArr(sortLeft, sortRight);
        }
    }

    /**
     * 合并有序数组
     *
     * @param sorArr1
     * @param sorArr2
     * @return
     */
    private static int[] mergeSortArr(int[] sorArr1, int[] sorArr2) {
        int len = sorArr1.length + sorArr2.length;
        int[] result = new int[len];
        int x = 0, y = 0;
        for (int i = 0; i < len; i++) { //
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

    private static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;// 无需排序
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean ordered = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                    ordered = false;
                }
            }
            if (ordered) break;
        }
        return arr;
    }

    /**
     * 快速排序
     *
     * @param array 数组
     * @param start 数组的开始index
     * @param end   数组的结束index
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) return;
        int pivot = start;
        int pivotV = array[start];
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivotV) {
                array[pivot] = array[i];
                pivot++;
                for (int j = i; j > pivot; j--) {
                    array[j] = array[j - 1];
                }
            }
        }
        array[pivot] = pivotV;
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 5, 7, 4, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString((arr)));

    }


    private static int[] bubbleSort1(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean ordered = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                    ordered = false;
                }
            }
            if (ordered) break;
        }
        return arr;
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) break;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if (right < left) break;
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);
            }
            bottom--;
            if (bottom < top) break;
            for (int i = bottom; i >= top; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            if (left < right) break;
        }
        return list;
    }


    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (k == 0 || input == null || input.length == 0) return list;
        if (k >= input.length) {
            for (Integer integer : input) {
                list.add(integer);
            }
            return list;
        }
        for (int i = 0; i < k; i++) {

        }
        return list;

    }


    /**
     * 排序算法
     * <p>
     * 冒泡排序
     * 插入排序
     * 快速排序
     * 归并排序
     * 堆排序
     * 选择排序
     * 希尔排序
     * <p>
     * 计数排序
     * 桶排序
     * 基数排序
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int n = arr.length;
        // 冒泡排序 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
        // 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成
        // 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
        for (int i = 0; i < n - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 需要交换
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                    sorted = false;
                }
            }
            if (sorted) break;
        }

        // 选择排序 :首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素
        // ，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
        // 交换值的次数会多
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int tem = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tem;
                }
            }

        }
        // 或者每一次只交换一次。
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (arr[i] > arr[minIndex]) {
                int tem = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tem;
            }
        }

        // 插入排序: 算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，
        // 在已排序序列中从后向前扫描，找到相应位置并插入。
        for (int i = 1; i < n; i++) {
            int tem = arr[i];
            // 0 -- i-1 为有序数列 将第 i 个元素插入到 有序数列中
            int preIndex = i - 1;
            while (preIndex > 0 && tem >= arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = tem;
        }
        // 希尔排序 ，第一个突破O(n2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。
        // 希尔排序又叫缩小增量排序。


        // 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
        // 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
        int[] sorted = mergeSort1(arr);

        // 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
        // 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
        quickSort1(arr, 0, n);

    }

    private void quickSort1(int[] arr, int from, int to) {
        if (from >= to - 1) return;
        int poi = from;
        int poV = arr[poi];
        for (int i = from + 1; i < to; i++) {
            if (poV > arr[i]) {
                int tem = arr[i];
                for (int j = i; j > poi; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[poi] = tem;
                poi++;
            }
        }
        quickSort1(arr, from, poi);
        quickSort1(arr, poi + 1, to);
    }


    private int[] mergeSort1(int[] arr) {
        int n = arr.length;
        if (n < 2) return arr;
        int m = n / 2;
        int[] a = mergeSort1(Arrays.copyOfRange(arr, 0, m));
        int[] b = mergeSort1(Arrays.copyOfRange(arr, m, n));
        return mergeSortArr(a, b);
    }


}
