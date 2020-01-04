package com.king.player.db;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.king.mobile.component.Callback;
import com.king.mobile.util.Executor;
import com.king.player.datasource.LocalVideoSource;
import com.king.player.datasource.VideoDao;
import com.king.player.model.VideoInfo;

import java.util.List;

public class VideoRepository {
    private VideoDao videoDao;
    private LiveData<List<VideoInfo>> mVideos;
    private LiveData<List<VideoInfo>> remoteVideoss;
    private Application app;

    public VideoRepository(Application application) {
        app = application;
        AppDatabase db = AppDatabase.getDatabase(application);
        videoDao = db.videoDao();
        mVideos = videoDao.findLocal();
        remoteVideoss = videoDao.findRemote();
    }

    public LiveData<List<VideoInfo>> getLocalVideo() {
        return mVideos;
    }

    public LiveData<List<VideoInfo>> getRemoteVideo() {
        return remoteVideoss;
    }

    public void insert(VideoInfo videoInfo, final Callback callback) {
        Executor.getInstance().execute(() -> {
            VideoInfo video = videoDao.findOneByUrl(videoInfo.url);
            if (video == null) {
                videoDao.insert(videoInfo);
                if (callback != null) {
                    callback.onResult(true);
                }
            } else {
                if (callback != null) {
                    callback.onError(false);
                }
            }

        });
    }

    public void delete(VideoInfo videoInfo) {
        Executor.getInstance().execute(() -> videoDao.deleteVideo(videoInfo));
    }

    public void loadLocalVideo() {
        Executor.getInstance().execute(() -> {
            Context context = app.getApplicationContext();
            List<VideoInfo> videoList = LocalVideoSource.getVideoList(context);
            List<VideoInfo> local = videoDao.findLocalList();
            videoList.removeAll(local);
            if (videoList.size() > 0) {
                videoDao.insertAll(videoList);
            }
        });
    }

    public void updateVideoInfo(VideoInfo videoInfo) {
        Executor.getInstance().execute(() -> videoDao.update(videoInfo));
    }


}
