package com.king.mobile.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {
    public static String getPublicDir(Context context) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        return externalStorageDirectory.getAbsolutePath();
    }


    public static String getPrivateFileDir(Context context) {
        File dir = context.getFilesDir();
        return dir.getAbsolutePath();
    }
}
