package com.king.player;


import com.king.mobile.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(new AppWatcher());
    }


}
