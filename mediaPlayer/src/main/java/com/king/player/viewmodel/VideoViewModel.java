package com.king.player.viewmodel;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.king.mobile.util.Executor;
import com.king.mobile.util.FileUtils;
import com.king.player.App;
import com.king.player.Utils.DBUtil;
import com.king.player.model.VideoInfo;

import java.util.List;

public class VideoViewModel extends ViewModel {
    private MutableLiveData<List<VideoInfo>> videoList;

    public MutableLiveData<List<VideoInfo>> getVideoList() {
        if (videoList == null) {
            videoList = new MutableLiveData<>();
            loadVideoList();
        }
        return videoList;
    }

    private void loadVideoList() {
        Executor.getInstance().excute(
                () -> {
                    List<VideoInfo> all = DBUtil.getDB().videoDao().getAll();
                    videoList.postValue(all);
                }
        );
    }

    public void addVideo(Intent resultData) {
        Uri uri = resultData.getData();
        Log.i("KK", "Uri: " + uri.toString());
        String path = FileUtils.getRealFilePath(App.getContext(), uri);
        Log.i("KK", "path: " + path);
        final VideoInfo videoInfo = new VideoInfo();
        videoInfo.createTime = System.currentTimeMillis() / 1000;
        videoInfo.name = path.substring(path.lastIndexOf('/') + 1);
        videoInfo.url = uri.toString();
        Executor.getInstance().excute(
                () -> {
                    DBUtil.getDB().videoDao().insert(videoInfo);
                    loadVideoList();
                }
        );
    }
}
