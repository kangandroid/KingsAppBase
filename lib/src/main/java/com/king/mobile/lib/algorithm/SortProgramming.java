package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class SortProgramming {

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int midIndex = start;// 占位
        for (int i = start + 1; i <= end; i++) { // 遍历
            if (arr[i] < arr[start]) { // 比标杆小的值
                arr[midIndex] = arr[i];// 移至坑位
                midIndex++; // 坑位前移
                for (int j = i; j > midIndex; j--) { // 再次留出坑位
                    arr[j] = arr[j - 1];
                }
            }
        }
        arr[midIndex] = arr[start]; // 填坑
        // 分治 坑位两侧
        quickSort(arr, start, midIndex - 1);
        quickSort(arr, midIndex + 1, end);
    }

    /**
     * 快速排序
     *
     * @param l   数组的开始index
     * @param r   数组的结束index
     * @param arr
     */
    private static void quick(int l, int r, int[] arr) {
        if (l >= r) return; // 跳出递归
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

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int i= 0, j= 0;
        while(i<pushA.length){
            if(pushA[i]!=popA[j]){
                stack.push(pushA[i++]);
            }else{
                i++;
                j++;
                while(!stack.empty() && stack.peek()== popA[j]){
                    stack.pop();
                    j++;
                }
            }
        }
        return stack.empty();
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
