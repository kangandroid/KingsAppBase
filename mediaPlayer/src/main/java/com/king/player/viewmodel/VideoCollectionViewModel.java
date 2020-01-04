package com.king.player.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.util.DBUtil;

import com.king.mobile.util.Executor;
import com.king.player.db.VideoCollectionRepository;
import com.king.player.db.VideoRepository;
import com.king.player.model.VideoCollection;
import com.king.player.model.VideoInfo;

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
