package com.king.mobile.component;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

public enum CptType {
    ACTIVITY(Activity.class),
    FRAGMENT(Fragment.class),
    VIEW(View.class),
    FUNCTION(Runnable.class);
    CptType(Class clazz) {

    }
}
