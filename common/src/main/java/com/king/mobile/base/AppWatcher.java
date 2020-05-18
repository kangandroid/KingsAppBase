package com.king.mobile.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.king.mobile.util.Loker;

import java.util.Iterator;
import java.util.LinkedList;


final public class AppWatcher implements Application.ActivityLifecycleCallbacks {
    private int startedActivityCount = 0;
    private boolean isOnBack = true;
    private LinkedList<AppStateChangeListener> listeners;
    private AppWatcher(){
        listeners = new LinkedList<>();
    }

    public static AppWatcher getInstance() {
        return InstanceHolder.appWatcher;
    }

    static class InstanceHolder {
        private static final AppWatcher appWatcher = new AppWatcher();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        startedActivityCount++;
        if(isOnBack){
            Iterator<AppStateChangeListener> iterator = listeners.iterator();
            while (iterator.hasNext()){
                AppStateChangeListener listener = iterator.next();
                listener.onShow(activity);
            }
        }
        isOnBack = false;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        startedActivityCount--;
        if (startedActivityCount == 0) {
            isOnBack = true;
            Iterator<AppStateChangeListener> iterator = listeners.iterator();
            while (iterator.hasNext()){
                AppStateChangeListener listener = iterator.next();
                listener.onHide(activity);
            }
            Loker.d("--------------应用已切换到后台");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public void addListener(AppStateChangeListener listener){
        listeners.add(listener);
    }

    public boolean removeLister(AppStateChangeListener listener){
        return listeners.remove(listener);
    }

    interface AppStateChangeListener {

        /**
         * 应用已切换到后台
         * @param activity
         */
        void onHide(Activity activity);

        /**
         * 应用已切换到前台
         * @param activity
         */
        void onShow(Activity activity);
    }
}
