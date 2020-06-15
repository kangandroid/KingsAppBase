package com.king.player.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.king.mobile.util.Executor;
import com.king.player.datasource.VideoCollectionDao;
import com.king.player.model.VideoCollection;

import java.util.List;

public class VideoCollectionRepository {
    private VideoCollectionDao collectionDao;
    private LiveData<List<VideoCollection>> collections;

    private Application app;

    public VideoCollectionRepository(Application application) {
        app = application;
        AppDatabase db = AppDatabase.getDatabase(application);
        collectionDao = db.videoCollectionDao();
        collections = collectionDao.getAll();
    }

    public LiveData<List<VideoCollection>> getCollections() {
        return collections;
    }


    public void insert(VideoCollection VideoCollection) {
        Executor.getInstance().execute(() -> collectionDao.insert(VideoCollection));
    }

    public void delete(VideoCollection VideoCollection) {
        Executor.getInstance().execute(() -> collectionDao.delete(VideoCollection));
    }


    public void updateVideoCollection(VideoCollection VideoCollection) {
        Executor.getInstance().execute(() -> collectionDao.update(VideoCollection));
    }
}
