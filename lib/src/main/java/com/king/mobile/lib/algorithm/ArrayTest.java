package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


class ArrayTest {

    /**
     * 最长公共子子数组长度
     */
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        int result = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    /**
     *
     */
    public int maxLetters(int[][] letters) {
        Arrays.sort(letters, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int n = letters.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int[] h = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            int[] l = letters[i];
            int width = l[0];
            int height = l[1];
            h[i] = height;
        }
        return max;
    }

    public int[] inorderTraversal (BinaryTree.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // write code here
        inorderTraversal(list, root);
        int [] result = new int [list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;

    }

    void inorderTraversal(List<Integer> list, BinaryTree.TreeNode node){
        if(node == null) return;
        inorderTraversal(list, node.left);
        list.add(node.val);
        inorderTraversal(list, node.right);
    }

    public int[] twoSum (int[] numbers, int target) {
        int n = numbers.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int key = numbers[i];
            if(map.containsKey(target - key)){
                return new int[]{map.get(target - key)+1,i+1};
            }
            map.put(key,i);
        }
        return new int[]{0, 0};

        //        for(int i = 0; i< n-1; i++){
//            for(int j = i+1; i< n; i++){
//                if(numbers[i] + numbers[j] == target){
//                    return new int[]{i + 1, j + 1};
//                }
//            }
//        }
//        return new int[]{0, 0};
    }

    public String solve (String str) {
        return new StringBuilder(str).reverse().toString();
        // write code here
    }
}
