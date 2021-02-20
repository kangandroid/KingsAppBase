package com.king.mobile.component;

import android.content.res.Configuration;
import android.util.ArrayMap;

import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<String> moduleNames = new ArrayList<>();

    protected ArrayMap<String,BaseModule> moduleMap = new ArrayMap<>();

    public List<String> getModuleNames() {
        return moduleNames;
    }

    public void moduleConfig( List<String> moduleNames){
        this.moduleNames = moduleNames;
    }

    public BaseModule getModuleByName(String name){
        if(!moduleNames.isEmpty()){
            return moduleMap.get(name);
        }
        return null;
    }

    public void onResume(){
        for(BaseModule module: moduleMap.values()){
            module.onResume();
        }
    }

    public void onPause(){
        for(BaseModule module: moduleMap.values()){
            module.onPause();
        }
    }


    public void onDestroy() {
        for(BaseModule module: moduleMap.values()){
            module.onDestroy();
        }
    }

    public void onStop() {
        for(BaseModule module: moduleMap.values()){
            module.onStop();
        }
    }
    public void onOrientationChanged(Configuration newConfig) {
        for(BaseModule module: moduleMap.values()){
            module.onOrientationChanged(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
        }
    }

}
