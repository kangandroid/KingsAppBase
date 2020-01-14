package com.king.player.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.king.mobile.component.Callback;
import com.king.player.db.VideoRepository;
import com.king.player.model.VideoInfo;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {
    private VideoRepository repository;
    private LiveData<List<VideoInfo>> localVideoList;
    private LiveData<List<VideoInfo>> remoteVideoList;


    public VideoViewModel(Application application) {
        super(application);
        repository = new VideoRepository(application);
        localVideoList = repository.getLocalVideo();
        remoteVideoList = repository.getRemoteVideo();
    }

    public LiveData<List<VideoInfo>> getLocalVideoList() {
        return localVideoList;
    }

    public LiveData<List<VideoInfo>> getRemoteVideoList() {
        return remoteVideoList;
    }

    public void loadLocalVideo() {
        repository.loadLocalVideo();
    }

    public void updateVideoInfo(VideoInfo videoInfo) {
        repository.updateVideoInfo(videoInfo);
    }

    public void insert(VideoInfo videoInfo, Callback callback) {
        repository.insert(videoInfo, callback);
    }

    public void delete(VideoInfo videoInfo) {
        repository.delete(videoInfo);
    }
}
