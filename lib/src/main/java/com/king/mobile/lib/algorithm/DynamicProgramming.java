package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

import java.util.Arrays;

class DynamicProgramming {


    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。(不连续)
     * eg
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     */
    public static int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length < 2) return length;
        int[] dp = new int[length]; // 过程存储容器
        //  dp[i]是以nums[i]结尾的 前i个元素的最优解，dp[i] = dp[i-1] 的关系
        Arrays.fill(dp, 1);
        int res = dp[0]; // 初始值
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) { // 判断条件
                    dp[i] = Math.max(dp[j] + 1, dp[i]); // 状态方程
                }
            }
            res = Math.max(res, dp[i]); // 取最优解
            System.out.println(res);
        }
        return res;

    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     *      [-2,1, 1,4, 4,2,1,-5,4]
     * 输出: 6
     *
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    public int maxSubSum(int [] n){
        return 0;
    }


    /**
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *  
     * 示例 1：
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     * 示例 2：
     * 输入：strs = ["10", "0", "1"], m = 1, n = 1
     * 输出：2
     * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0) return 0;
        int[][] dp = new int[m + 1][n + 1];  // dp[i-1][j-1]
        for (String s : strs) {
            int count1 = 0, count0 = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            for (int i = m; i >= count0; i--) {
                for (int j = n; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i][j],dp[i-count0][j-count1]+1); //状态方程的怎么设计？
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     * @param n dp[i] 为到第 i个台阶需要的跳法。
     *          dp[i] = dp[i-1] + dp[i-2]
     */
    public static int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        int numWaysI = 1;
        int numWaysII = 1;
        for (int i = 2; i <= n; i++) {
            int tem = numWaysI + numWaysII;
            numWaysI = numWaysII;
            numWaysII = tem;
        }
        return numWaysII;
    }

    /**
     * 附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。
     * 你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。
     * 按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。
     * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
     * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么
     * 可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
     * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
     * 以这种方式布置书架，返回书架整体可能的最小高度。
     * <p>
     * 示例：
     * <p>
     * 输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
     * 输出：6
     * 解释：
     * 3 层书架的高度和为 1 + 3 + 2 = 6 。
     * <p>
     * dp[i] 表示第i本书之前的最优解 dw = books[i][0]  dh = books[i][1]
     * tem_w 表示当前累计宽度
     * tem_h 表示当前累计高度
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length; // 书的数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000 * 1000);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int tem_w = 0;
            int tem_h = 0;
            for (int j = i; j > 0; j--) {
                tem_w += books[j - 1][0];
                if (tem_w > shelf_width) // 最后一层
                    break;
                tem_h = Math.max(tem_h, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + tem_h);
            }
        }
        return dp[n + 1];
    }

    /**
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * 示例 1：
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     * 示例 2:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * @param n 分析 所剪段数 m 的取值范围 2 - n
     */
    public int cuttingRope(int n) {
        int[] dp = new int[n];
        for (int m = 2; m <= n; m++) {

        }


        return 0;
    }

    /**
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     * <p>
     * 示例：
     * <p>
     * 输入：
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出：3
     * 解释：
     * 长度最长的公共子数组是 [3, 2, 1] 。
     */
    public static int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int[][] dp = new int[A.length + 1][B.length + 1]; // 为什要加一
        int maxLength = 0;
        int lastA = 0;
        int lastB = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) { //
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        lastA = i;
                        lastB = j;
                    }
                    maxLength = Math.max(dp[i][j], maxLength); //dp[i][j] 表示A 0-i 与 B 0-j 的最长公共字串
                }
            }
        }
        PrintUtil.print("lastA=" + lastA);
        PrintUtil.print("lastB=" + lastB);
        return maxLength;
    }


    public static void main(String[] args) {
//        int result = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
//        int result = numWays(3);
//        System.out.println(result);
        int[] a = {1, 2, 3, 2, 1};
        int[] b = {3, 2, 1, 4, 7};
        PrintUtil.print(findLength(a, b));
    }


}
