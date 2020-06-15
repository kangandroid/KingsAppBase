package com.king.mobile.util;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONUtil {

    public static <T> List<T> parseArray(String json, Class<T> tClass) {
        List<T> ts = JSON.parseArray(json, tClass);
        return ts;
    }

    public static <T> T parse(String json) {
        T ts = (T) JSON.parse(json);
        return ts;
    }

    public static boolean isJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isJsonArray(String json) {
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
