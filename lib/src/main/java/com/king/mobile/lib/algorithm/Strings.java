package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class Strings {
    /**
     * 给定字符串 S，求最长重复子串长度，如果不存在重复子串就返回 0
     * 输入：”aabcaabdaab”，输出：3
     * 输入：”aaaaa”，输出：4
     */
    static int lengthOfDoubleSubstringLength(String s) {
        if (s == null || s.length() < 2) return 0;
        int max = s.length() - 1;
        int min = 0;
        while (max > min + 1) {
            int mid = (max + min) / 2;
            if (hasDupSubstring(mid, s)) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }

    /**
     * @param l
     * @param s
     * @return
     */
    static public boolean hasDupSubstring(int l, String s) {
        HashSet<String> subs = new HashSet<>();
        for (int i = 0; i < s.length() - l; i++) {
            String substring = s.substring(i, i + l);
            if (!subs.add(substring)) {
                return true;
            }
        }
        return false;
    }

    static String lengthOfDoubleSubstring(String s) {
        if (s == null || s.length() < 2) return "";
        String maxSub = "";
        int max = s.length() - 1;
        int min = 0;
        while (max > min + 1) {
            int mid = min + (max - min) / 2;
            String sub = getDupSubstring(mid, s);
            PrintUtil.print(sub);
            if (sub.equals("")) {
                max = mid;
            } else {
                min = mid;
                maxSub = sub;
            }
        }
        String sub = getDupSubstring(max, s);
        if (sub.equals("")) {
            return maxSub;
        } else {
            return sub;
        }

    }

    private static String getDupSubstring(int len, String s) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - len + 1; i++) {
            String sub = s.substring(i, i + len);
            if (!set.add(sub)) {
                PrintUtil.print(sub);
                return sub;
            }
        }
        return "";
    }


    public ArrayList<String> Permutation(String str) {
        List<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            PermutationHelper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }
        return (ArrayList) res;
    }

    public void PermutationHelper(char[] cs, int i, List<String> list) {
        if (i == cs.length - 1) {
            String val = String.valueOf(cs);
            if (!list.contains(val))
                list.add(val);
        } else {
            for (int j = i; j < cs.length; j++) {
                swap(cs, i, j);
                PermutationHelper(cs, i + 1, list);
                swap(cs, i, j);
            }
        }
    }

    public void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        int[] max = new int[array.length];
        int len = array.length;
        int HalfNum = 0;
        int maxCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int vi : array) {
            if (map.containsKey(vi)) {
                int count = map.get(vi);
                if (++count > maxCount) {
                    maxCount = count;
                    HalfNum = vi;
                }
                map.replace(vi, count);
            } else {
                map.put(vi, 1);
                maxCount = Math.max(1, maxCount);
            }
        }
        if (maxCount > len / 2) {
            return HalfNum;
        } else {
            return 0;
        }
    }

    public int GetNumberOfK(int[] array, int k) {
        int lbound = 0, rbound = 0;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        lbound = l;
        l = 0;
        r = array.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] <= k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        rbound = l;
        return rbound - lbound;
    }

    public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int ret = 0;
        for (int i : array) {
            ret = ret ^ i;
        }
        ret &= (-ret);
        for (int i : array) {
            if ((i & ret) != 0) {
                num1[0] ^= i;
            } else {
                num2[0] ^= i;
            }
        }
    }

    /**
     * KMP 算法
     * 给定字符串S1和S2，在S1中寻找是否包含S2
     *
     * @param s1
     * @param s2 模式串
     */
    public static boolean isSubString(String s1, String s2) {
        // 当s2长度大于s1 false
        if (s2.length() > s1.length()) return false;
        //
        int[] next = next(s2);
        int tem = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(tem)) {
                if (tem == s2.length() - 1) {
                    return true;
                } else {
                    tem++;
                }
            } else {
                tem = next[tem];// 回退
            }
        }
        return true;
    }

    /**
     * 通过模式串 创建 next数组
     */
    public static int[] next(String s2) {
        int length = s2.length();
        int[] next = new int[length];
        int tem = 0;
        next[0] = 0;
        for (int i = 1; i < length; i++) {
            if (s2.charAt(i) == s2.charAt(tem)) {
                next[i] = next[i - 1] + 1;
                tem++;
            } else {
                next[i] = 0;
                tem = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String a = "abcdabcabcabc";
        String b = "abcw";
        PrintUtil.print("isSubString = " + isSubString(a, b));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int[] hash = new int[128];
        Arrays.fill(hash, -1);
        // 最长不重复子串长度
        int maxLen = 0;
        // 不重复子串的开始位置
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int hashIndex = s.charAt(i);
            // 更新新开始角标
            start = Math.max(start, hash[hashIndex] + 1);
            maxLen = Math.max(maxLen, i - start + 1);
            hash[hashIndex] = i;
        }
        return maxLen;
    }

    /**
     * 三角的路径最小值
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] minList = new int[n + 1];
        Arrays.fill(minList, 0);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minList[j] = Math.min(minList[j], minList[j + 1]) + triangle.get(i).get(j);
            }
        }
        return minList[0];
    }

    /**
     * 列举所以有效括号序列
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        String str = "()";
        Set<String> iSet = new HashSet<>();
        iSet.add(str);
        int in = 1;
        while (in < n) {
            Set<String> temSet = new HashSet<>();
            Iterator<String> iterator = iSet.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                for (int i = 0; i <= next.length(); i++) {
                    StringBuffer buffer = new StringBuffer(next);
                    if (i < next.length()) {
                        buffer.insert(i, str);
                    } else {
                        buffer.append(str);
                    }
                    temSet.add(buffer.toString());
                }
            }
            iSet = temSet;
            in++;
        }
        return new ArrayList<>(iSet);
    }


    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int index = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n - k; i++) {
            int a = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                if (a == nums[j]) {
                    return true;
                }
            }
        }

        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            if (set.contains(a)) {
                return true;
            }
            set.add(a);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
