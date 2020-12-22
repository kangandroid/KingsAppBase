package com.king.player;


import com.king.mobile.base.BaseApplication;
import com.king.player.util.DispUtility;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        DispUtility.disabledDisplayDpiChange(getResources());
        super.onCreate();
    }


}
