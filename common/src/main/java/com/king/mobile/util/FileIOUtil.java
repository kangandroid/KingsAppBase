package com.king.mobile.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FileIOUtil {

    private static int sBufferSize = 524288; // 512k 默认缓存数组大小

    public static InputStream getInputStream() {

        return null;
    }

    public static String readText(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte data[] = new byte[sBufferSize];
        while (bis.read(data) != -1) {
            stringBuffer.append(new String(data, StandardCharsets.UTF_8));
        }
        inputStream.close();
        return stringBuffer.toString();
    }

    public static Byte[] readByteArray(InputStream inputStream) {
        return null;
    }

    public static BufferedOutputStream getOutputStream(File file) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        return bos;
    }

    public static void writeText(String text) {

    }

    public static void writeFile(String target) {


    }

    public static void copyFile(InputStream is, String targetDir) throws IOException {
        File file = new File(targetDir);
        BufferedInputStream bis = new BufferedInputStream(is);
        OutputStream os = getOutputStream(file);
        byte data[] = new byte[sBufferSize];
        while (bis.read(data) != -1) {
            os.write(data);
            os.flush();
        }
        os.close();
    }
}
