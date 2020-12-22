package com.king.player.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.king.player.db.VideoCollectionRepository;
import com.king.player.video.VideoCollection;

import java.util.List;

public class VideoCollectionViewModel extends AndroidViewModel {
    private VideoCollectionRepository mRepository;
    private final LiveData<List<VideoCollection>> remotelVideo;

    public VideoCollectionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new VideoCollectionRepository(application);
        remotelVideo = mRepository.getCollections();
    }

    public LiveData<List<VideoCollection>> getVideoList() {
        return remotelVideo;
    }

}
