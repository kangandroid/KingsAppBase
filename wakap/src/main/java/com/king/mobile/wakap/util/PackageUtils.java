package com.king.mobile.wakap.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.king.mobile.base.BaseApplication;
import com.king.mobile.wakap.model.AppInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageUtils {
    public static final String TAG = "PackageUtils";
    private static List<AppInfo> appInfoList;
    private static Map<String, AppInfo> apps = new HashMap<>();

    public static List<AppInfo> getAllInstallPages(Context context) {
        if (appInfoList == null) {
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> packs = pm.getInstalledPackages(0);
            appInfoList = new ArrayList<AppInfo>();
            for (PackageInfo packageInfo : packs) {
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    //三方应用
                    AppInfo appInfo = new AppInfo();
                    appInfo.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                    appInfo.packageName = packageInfo.packageName;
                    appInfo.version = new StringBuilder(String.valueOf(packageInfo.versionName)).toString();
                    appInfo.icon = packageInfo.applicationInfo.loadIcon(pm);
                    appInfo.launchIntent = pm.getLaunchIntentForPackage(appInfo.packageName);
                    Log.d(TAG, appInfo.appName + "是 ：三方应用");
                    apps.put(packageInfo.packageName, appInfo);
                    appInfoList.add(appInfo);
                }
            }
        }
        return appInfoList;
    }

    public static AppInfo getAppInfo(String packageName) {
        if (apps.size() == 0) {
            getAllInstallPages(BaseApplication.getContext());
        }
        return apps.get(packageName);
    }

}
