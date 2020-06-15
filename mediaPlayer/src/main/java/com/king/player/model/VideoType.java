package com.king.player.model;

import android.util.SparseArray;

public class VideoType {

    private static SparseArray<String> allType;
    private static String[] typeAarry = {"未分类", "电视剧", "电视直播", "网络直播", "短视频", "电影"};

    {
        allType = new SparseArray();
        allType.append(0, "未分类");
        allType.append(1, "电视剧");
        allType.append(2, "电视直播");
        allType.append(3, "网络直播");
        allType.append(4, "短视频");
        allType.append(5, "电影");
    }

    public static String getTypeName(int type) {
        return allType.get(type, "未知类型");
    }

    public static SparseArray getAllType() {
        return allType;
    }

    public static String[] getTypeArray() {
        return typeAarry;
    }
}
