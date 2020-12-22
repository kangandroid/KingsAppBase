package com.king.player.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.king.mobile.component.Callback;
import com.king.player.db.VideoRepository;
import com.king.player.video.VideoInfo;

import java.util.List;

import io.reactivex.Single;

public class VideoViewModel extends AndroidViewModel {
    private VideoRepository repository;
    private LiveData<List<VideoInfo>> localVideoList;
    private LiveData<List<VideoInfo>> remoteVideoList;
    private LiveData<List<VideoInfo>> liveTVList;


    public VideoViewModel(Application application) {
        super(application);
        repository = new VideoRepository(application);
        localVideoList = repository.getLocalVideo();
        remoteVideoList = repository.getRemoteVideo();
        liveTVList = repository.getLiveTV();
    }

    public LiveData<List<VideoInfo>> getLocalVideoList() {
        return localVideoList;
    }

    public LiveData<List<VideoInfo>> getRemoteVideoList() {
        return remoteVideoList;
    }

    public LiveData<List<VideoInfo>> getLiveTVList() {
        return liveTVList;
    }

    public void loadLocalVideo() {
        repository.loadLocalVideo();
    }

    public void loadLiveTv() {
        repository.loadLiveTv();
    }

    public void updateVideoInfo(VideoInfo videoInfo) {
        repository.updateVideoInfo(videoInfo);
    }

    public void insert(VideoInfo videoInfo, Callback callback) {
        repository.insert(videoInfo, callback);
    }

    public Single<Boolean> insert(VideoInfo videoInfo) {
        return repository.insert(videoInfo);
    }

    public void delete(VideoInfo videoInfo) {
        repository.delete(videoInfo);
    }
}
