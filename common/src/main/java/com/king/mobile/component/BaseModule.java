package com.king.mobile.component;

import android.os.Bundle;


public abstract class BaseModule implements ComponentModule {

    public abstract boolean init(ModuleContext context, Bundle extend);

    public abstract void onSaveInstanceState(Bundle outState);

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();

    public abstract void onOrientationChanged(boolean isLandscape);

    public abstract void onDestroy();


}
