package com.king.mobile.base;

import android.app.Application;
import android.content.Context;
import android.util.SparseIntArray;

import androidx.core.app.FrameMetricsAggregator;

import com.king.mobile.util.ScreenAdapter;

public class BaseApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();
        ScreenAdapter.init(750);
        registerActivityLifecycleCallbacks(AppWatcher.getInstance());
    }
//
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        Resources res = base.getResources();
//        final Configuration configuration = res.getConfiguration();
//        configuration.setToDefaults();
//        configuration.densityDpi = 1;
//        DisplayMetrics displayMetrics = res.getDisplayMetrics();
//        displayMetrics.scaledDensity = 1;
//        res.updateConfiguration(configuration, displayMetrics);
//        final Context newContext = base.createConfigurationContext(configuration);
//        super.attachBaseContext(newContext);
//    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        FrameMetricsAggregator frameMetricsAggregator = new FrameMetricsAggregator();
//        frameMetricsAggregator.add(activity);
//        frameMetricsAggregator.remove(activity);
        SparseIntArray[] metrics = frameMetricsAggregator.getMetrics();
        frameMetricsAggregator.reset();
        frameMetricsAggregator.stop();
        for (SparseIntArray metric : metrics) {
            int i = metric.get(1);
        }
    }
}
