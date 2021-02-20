package com.king.mobile.component;

import android.app.Activity;
import android.os.Bundle;
import android.util.ArrayMap;

import java.util.ArrayList;

public class ActivityModuleManager extends ModuleManager {

    public void initModules(Bundle saveInstance, Activity activity, ArrayMap<String, ArrayList<Integer>> modules) {
        if(activity==null||modules==null)return;
//        moduleConfig(modules);

    }
}
