package com.king.mobile.testapp.utils;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class TestUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        LogUtil.print("crash",e.getMessage());
        final Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        Throwable cause = e;
        while (cause!=null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final  String stackTrace = writer.toString();
        printWriter.close();
    }
}
