package com.king.mobile.kottest

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode

public class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        openStrictMode()
    }

    override fun onCreate() {
        super.onCreate()
        registerFrameInspector()
//        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
//            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//                println(activity.componentName.shortClassName)
//            }
//
//            override fun onActivityStarted(activity: Activity) {
//
//            }
//
//            override fun onActivityResumed(activity: Activity) {
//
//            }
//
//            override fun onActivityPaused(activity: Activity) {
//
//            }
//
//            override fun onActivityStopped(activity: Activity) {
//
//            }
//
//            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
//
//            }
//
//            override fun onActivityDestroyed(activity: Activity) {
//
//            }
//
//        })

    }

    private fun registerFrameInspector(){
//        mainLooper.setMessageLogging(FrameInspectorPrinter())
    }

    private fun openStrictMode() {
//        val threadPolicy = StrictMode.ThreadPolicy.Builder()
//                .penaltyDialog()
//                .detectAll()
//                .build()
//        StrictMode.setThreadPolicy(threadPolicy)
//        val vmPolicy = StrictMode.VmPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .build()
//        StrictMode.setVmPolicy(vmPolicy)
        StrictMode.enableDefaults()
    }
}