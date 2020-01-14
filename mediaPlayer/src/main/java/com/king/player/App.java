package com.king.player;


import android.app.Activity;
import android.os.Bundle;

import com.king.mobile.base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(new AppWatcher());
    }


}
