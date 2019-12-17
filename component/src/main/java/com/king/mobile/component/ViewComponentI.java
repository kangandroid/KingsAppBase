package com.king.mobile.component;

import android.content.Context;
import android.view.View;

interface ViewComponentI extends ComponentI {
    View getView(Context context, String params, Callback callback);
}
