package com.king.player.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.annotation.RequiresPermission;

import com.king.player.model.VideoInfo;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.List;

public class LocalVideoSource {

    public static List<VideoInfo> getVideoList(Context context) {
        String[] mediaColumns = {
                MediaStore.Video.Media._ID, // id
                MediaStore.Video.Media.DATA, // Uri
                MediaStore.Video.Media.TITLE, // 名称
                MediaStore.Video.Media.MIME_TYPE, // 类型
                MediaStore.Video.Media.SIZE, // 大小
                MediaStore.Video.Media.DATE_ADDED}; // 文件名
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, null);
        List<VideoInfo> videoList = new ArrayList<>();
        if (cursor == null || cursor.getCount() == 0) {
            return videoList;
        }
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String data = cursor.getString(1);
                String name = cursor.getString(2);
                String type = cursor.getString(3);
                long size = cursor.getLong(4); // byte
                long addDate = cursor.getLong(5); // s unix
                VideoInfo info = new VideoInfo();
                info.name = name;
                info.localId = id;
                info.url = data;
                info.type = type;
                info.size = size;
                info.createTime = System.currentTimeMillis()/1000;
                info.dateAdded = addDate;
                info.dateModified = addDate;
                videoList.add(info);
            } while (cursor.moveToNext());
        }
        return videoList;
    }

}
