package com.king.mobile.wakap;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.king.mobile.wakap.model.AppDatabase;
import com.king.mobile.wakap.model.Task;
import com.king.mobile.wakap.model.TaskDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TaskViewModel extends AndroidViewModel {


    private final LiveData<List<Task>> listLiveData;
    private final TaskDao taskDao;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        listLiveData = taskDao.getAll();

    }

    public LiveData<List<Task>> getTasks() {
        return listLiveData;
    }

    public void insert(Task task) {
        taskDao.addTask(task);
    }

    public void delete(Task task) {
        Observable.just(task)
                .map((data) -> {
                    taskDao.deleteTask(data);
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
