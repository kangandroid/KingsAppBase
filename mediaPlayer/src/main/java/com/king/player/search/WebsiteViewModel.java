package com.king.player.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.king.mobile.util.Loker;
import com.king.player.db.AppDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WebsiteViewModel extends AndroidViewModel {

    public static WebsiteViewModel getInstance(@NonNull Application application) {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(WebsiteViewModel.class);
    }


    private final LiveData<WebSiteInfo> defaultWebsite;
    private final WebSiteDao webSiteDao;
    private final LiveData<List<WebSiteInfo>> allWebSite;

    public WebsiteViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        webSiteDao = db.webSiteDao();
        allWebSite = webSiteDao.getAll();
        defaultWebsite = webSiteDao.getDefault();
    }

    public LiveData<List<WebSiteInfo>> getAllWebSite() {
        return allWebSite;
    }

    public Disposable add(WebSiteInfo webSiteInfo, Consumer<Long> onSuccess, Consumer onError) {
        if (onError == null) {
            onError = throwable -> {
            };
        }
        if (onSuccess == null) {
            onSuccess = l -> {
            };
        }
        return webSiteDao.insert(webSiteInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, onError);
    }

    public Disposable delete(WebSiteInfo item) {
        return webSiteDao.delete(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Disposable update(WebSiteInfo webSiteInfo, Consumer<Integer> onSuccess, Consumer onError) {
        if (onError == null) {
            onError = throwable -> {
            };
        }
        if (onSuccess == null) {
            onSuccess = l -> {
            };
        }
        return webSiteDao.update(webSiteInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, onError);
    }

    public Disposable setDefault(WebSiteInfo item, Consumer<Integer> onSuccess, Consumer onError) {
        if (onError == null) {
            onError = throwable -> {
            };
        }
        if (onSuccess == null) {
            onSuccess = l -> {
            };
        }
        if (item.isDefault == 0) item.isDefault = 1;
        return webSiteDao.cancelDefault()
                .flatMapSingle(l -> webSiteDao.update(item))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, onError);

    }

    public LiveData<WebSiteInfo> getDefault() {
        return defaultWebsite;
    }

}
