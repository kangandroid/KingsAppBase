package com.king.mobile.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityComponent<T extends Activity> implements ActivityComponentI {
    String name;
    Context mContext;
    public ActivityComponent(Context context, String name){
        this.name = name;
        mContext = context;
    }

    @Override
    public void startActivity(String params) {
//
//        Intent intent = new Intent(mContext, T.get);
//        mContext.startActivity(intent);
    }

    @Override
    public void register() {
        ComponentManager.register(this);
    }

    @Override
    public String getName() {
        return name;
    }
}
