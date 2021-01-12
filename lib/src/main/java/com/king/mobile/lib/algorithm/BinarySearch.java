package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

class BinarySearch {
    /**
     * @param array  升序数组
     * @param target 查找目标
     * @return target 在array 重的位置，不存在 返回-1。
     */
    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        if (array[start] > target || array[end] < target) {
            return -1;
        }
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] > target) {
                end = middle;
            } else {
                start = middle;
            }
        }
        return -1;
    }

    static void callSelf() {
        String[] strings = new String[100];
        callSelf();
    }

    public static void main(String[] args) {
//        int[][] array = {
//                {1, 2, 8, 9},
//                {2, 4, 9, 12},
//                {4, 7, 10, 13},
//                {6, 8, 11, 15}
//        };
//        try {
//            callSelf();
//        } catch (StackOverflowError e) {
//            System.out.println("StackOverflowError --------- catched "+e.toString());
//        }
//        int[] ints = {0, 1, 2, 4, 5, 6, 7, 8, 9};
//        System.out.println(search(ints, 3));
        ;
        PrintUtil.print(replaceSpace(new StringBuffer("We Are Happy")));
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递
     * 增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
     * true
     */
    public static boolean findIn2D(int target, int[][] array) {
        int xStart = 0;
        int yStart = array[0].length - 1;
        while (xStart < array.length && yStart >= 0) {
            if (target == array[xStart][yStart]) {
                return true;
            } else if (target > array[xStart][yStart]) {
                xStart++;
            } else {
                yStart--;
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public static String replaceSpace(StringBuffer str) {
        for (int i = 0; i <str.length()-1; i++) {
            if(str.charAt(i)==' '){
                str.replace(i,i+1,"%20");
            }
        }
        return str.toString();
    }

}
