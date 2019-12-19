package com.king.mobile.component;


import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    Map<String, ComponentModule> moduleMap;


    private ComponentManager() {
        moduleMap = new HashMap<>();
    }

    public static ComponentManager getInstance() {
        return InstanceHolder.instance;
    }

    public static View getViewComponet(@NonNull String moduleName, @NonNull String componentName, @NonNull Context context) {
       return getInstance().getView(moduleName, componentName,context);
    }

    private static class InstanceHolder {
        private static ComponentManager instance = new ComponentManager();
    }

    public View getView(@NonNull String moduleName, @NonNull String componentName, @NonNull Context context) {
        if (moduleMap.containsKey(moduleName)) {
            ComponentModule componentModule = moduleMap.get(moduleName);
            return componentModule != null ? componentModule.getView(componentName) : null;
        } else {
            TextView textView = new TextView(context);
            textView.setText(String.format("Module%s是没有注册", moduleName));
            return textView;
        }
    }

    public void invokeFunction(String moduleName, String componentName,String params){
        if (moduleMap.containsKey(moduleName)) {
            ComponentModule componentModule = moduleMap.get(moduleName);
            if (componentModule!=null){
                componentModule.invokeFunction(componentName,params);
            }
        } else {
            ToastUtils.showLong(String.format("Module%是没有注册", moduleName));
        }
    }


    public void registerModule(ComponentModule module) {
        if (moduleMap.containsValue(module)) {
            return;
        }
        moduleMap.put(module.getModuleName(), module);
    }
}
