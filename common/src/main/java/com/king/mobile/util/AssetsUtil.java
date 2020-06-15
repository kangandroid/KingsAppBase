package com.king.mobile.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;

public class AssetsUtil {

    public static String readText(Context context, String path) throws IOException {
        AssetManager assets = context.getAssets();
        return FileIOUtil.readText(assets.open(path));
    }

    public static void copyFile(Context context, String file, String targetDir) throws IOException {
        AssetManager assets = context.getAssets();
        FileIOUtil.copyFile(assets.open(file), targetDir);
    }


}
