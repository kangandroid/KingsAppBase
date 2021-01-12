package com.king.mobile.lib.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Strings {
    /**
     * 给定字符串 S，求最长重复子串长度，如果不存在重复子串就返回 0
     * 输入：”aabcaabdaab”，输出：3
     * 输入：”aaaaa”，输出：4
     */
    int lengthOfDoubleSubstring(String s) {
        if (s == null || s.length() < 2) return 0;
        int max = s.length();
        for (int i = max - 1; i > 0; i--) {
            if (s.substring(0, i));

        }

        return max;
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
        int [] max = new int [array.length];
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

}
