package com.king.player.db;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.king.mobile.component.Callback;
import com.king.mobile.util.Executor;
import com.king.mobile.util.Loker;
import com.king.player.datasource.AssetDataSource;
import com.king.player.datasource.LocalVideoSource;
import com.king.player.datasource.VideoDao;
import com.king.player.model.VideoInfo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoRepository {
    private VideoDao videoDao;
    private LiveData<List<VideoInfo>> mVideos;
    private LiveData<List<VideoInfo>> remoteVideos;
    private LiveData<List<VideoInfo>> liveTV;
    private Application app;

    public VideoRepository(Application application) {
        app = application;
        AppDatabase db = AppDatabase.getDatabase(application);
        videoDao = db.videoDao();
        mVideos = videoDao.findLocal();
        remoteVideos = videoDao.findRemote();
        liveTV = videoDao.findAllTV();
    }

    public LiveData<List<VideoInfo>> getLocalVideo() {
        return mVideos;
    }

    public LiveData<List<VideoInfo>> getRemoteVideo() {
        return remoteVideos;
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

    public Single<Boolean> insert(VideoInfo videoInfo) {
        Loker.d("----------insert--------------");
        return Single.just(videoInfo)
                .subscribeOn(Schedulers.newThread())
                .map(this::insertVideoSync)
                .observeOn(AndroidSchedulers.mainThread());

    }

    private Boolean insertVideoSync(VideoInfo videoInfo) {
        Loker.d("----------insertVideoSync--------------");
        VideoInfo video = videoDao.findOneByUrl(videoInfo.url);
        if (video == null) {
            videoDao.insert(videoInfo);
            return true;
        } else {
            return false;
        }
    }

    public void delete(VideoInfo videoInfo) {
        Executor.getInstance().execute(() -> videoDao.deleteVideo(videoInfo));
    }

    public void loadLocalVideo() {
        Executor.getInstance().execute(() -> {
            Context context = app.getApplicationContext();
            List<VideoInfo> videoList = new LocalVideoSource(context).getVideoList();
            List<VideoInfo> local = videoDao.findLocalList();
            videoList.removeAll(local);
            if (videoList.size() > 0) {
                videoDao.insertAll(videoList);
            }
        });
    }

    public void loadLiveTv() {
        Executor.getInstance().execute(() -> {
            Context context = app.getApplicationContext();
            videoDao.deleteLiveTv();
            List<VideoInfo> videoInfos = new AssetDataSource(context).getVideos();
            if (videoInfos != null && videoInfos.size() > 0) {
                videoDao.insertAll(videoInfos);
            }
        });
    }

    public void updateVideoInfo(VideoInfo videoInfo) {
        Executor.getInstance().execute(() -> videoDao.update(videoInfo));
    }


    public LiveData<List<VideoInfo>> getLiveTV() {
        return liveTV;
    }
}
