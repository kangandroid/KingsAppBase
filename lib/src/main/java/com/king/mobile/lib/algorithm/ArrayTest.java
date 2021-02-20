package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

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
                if (A[i-1] == B[j-1]) {
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
}
