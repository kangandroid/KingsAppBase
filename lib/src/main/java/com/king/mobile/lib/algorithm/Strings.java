package com.king.mobile.lib.algorithm;

import com.king.mobile.lib.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
            PrintUtil.print(max);
            PrintUtil.print(min);
            PrintUtil.print(mid);
            if (hasDupSubstring(mid, s)) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }

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

    ;

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
        if (s2.length() > s1.length()) return false;
        int[] next = next(s2);
        int tem = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(tem)){
                if(tem == s2.length()-1){
                    return true;
                }else {
                    tem++;
                }
            }else{
                tem = next[tem];// 回退
            }
        }
        return true;
    }

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
        PrintUtil.print("isSubString = "+ isSubString(a,b));
    }


}
