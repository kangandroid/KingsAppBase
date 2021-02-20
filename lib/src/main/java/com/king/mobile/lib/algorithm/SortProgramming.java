package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

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
     * @param array   数组
     * @param start   数组的开始index
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
                for (int j = i; j> pivot ; j--) {
                    array[j] = array[j-1];
                }
            }
        }
        array[pivot] = pivotV;
        quickSort(array,start,pivot-1);
        quickSort(array,pivot+1,end);
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

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new  ArrayList();
        if(matrix==null|| matrix.length== 0 || matrix[0].length==0){
            return list;
        }
        int top  = 0;
        int left = 0;
        int bottom = matrix.length-1;
        int right = matrix[0].length-1;
        while(true){
            for(int i = left;i<=right;i++){
                list.add(matrix[top][i]);
            }
            top++;
            if(top>bottom)break;
            for(int i = top; i<=bottom; i++){
                list.add(matrix[i][right]);
            }
            right--;
            if(right<left)break;
            for(int i = right;i>=left;i--){
                list.add(matrix[bottom][i]);
            }
            bottom--;
            if(bottom<top)break;
            for(int i=bottom; i>=top; i--){
                list.add(matrix[i][left]);
            }
            left++;
            if(left<right)break;
        }
        return list;
    }



    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(k==0||input==null||input.length==0)return list;
        if(k >= input.length){
            for (Integer integer: input) {
                list.add(integer);
            }
            return list;
        }
        for(int i= 0; i<k; i++){

        }
        return list;

    }
}
