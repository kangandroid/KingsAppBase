package com.king.mobile.cptprocessor;

import com.king.mobile.component.Component;

import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str == "" || str.replaceAll(" ", "") == "";
    }

    public static boolean isJsonString(String str) {
        JSONParser jsonParser = new JSONParser(str, Global.instance(), true);
        Object parse = jsonParser.parse();
        return true;
    }

    public static boolean isComponentType(String type) {
        return Component.TYPE_ACTIVITY.equals(type)
                || Component.TYPE_FRAGMENT.equals(type)
                || Component.TYPE_VIEW.equals(type)
                || Component.TYPE_FUNCTION.equals(type);
    }

}
