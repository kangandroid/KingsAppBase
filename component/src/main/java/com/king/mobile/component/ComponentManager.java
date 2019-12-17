package com.king.mobile.component;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    Map<String, FragmentComponentI> fragmentComponents;
    Map<String, ActivityComponentI> activityComponents;
    Map<String, ViewComponentI> viewComponents;
    Map<String, FunctionComponentI> functionComponents;

    private ComponentManager() {
        fragmentComponents = new HashMap<>();
        activityComponents = new HashMap<>();
        viewComponents = new HashMap<>();
        functionComponents = new HashMap<>();
    }

    public static ComponentManager getInstance() {
        return InstanceHolder.instance;
    }

    public static void startActivity(String name, String params) {
        ComponentManager manager = getInstance();
        if (manager.activityComponents.containsKey(name)) {
            ActivityComponentI activityComponent = manager.activityComponents.get(name);
            if (activityComponent != null) {
                activityComponent.startActivity(params);
            }
        }

    }

    public static View getView(@NonNull Context context, @NonNull String name, String params, Callback callback) {
        ComponentManager manager = getInstance();
        if (manager.viewComponents.containsKey(name)) {
            ViewComponentI viewComponent = manager.viewComponents.get(name);
            return viewComponent != null ? viewComponent.getView(context, params, callback) : null;
        } else {
            TextView textView = new TextView(context);
            textView.setText(String.format("View @%s 是没有注册", name));
            return textView;
        }

    }

    private static class InstanceHolder {
        private static ComponentManager instance = new ComponentManager();
    }


    public static void register(@NonNull ComponentI component) {
        ComponentManager manager = getInstance();
        if (component instanceof ActivityComponentI) {
            String name = component.getName();
            manager.activityComponents.put(name, (ActivityComponentI) component);
        } else if (component instanceof ViewComponentI) {
            String name = component.getName();
            manager.viewComponents.put(name, (ViewComponentI) component);
        } else if (component instanceof FragmentComponentI) {
            String name = component.getName();
            manager.fragmentComponents.put(name, (FragmentComponentI) component);
        } else if (component instanceof FunctionComponentI) {
            String name = component.getName();
            manager.functionComponents.put(name, (FunctionComponentI) component);
        } else {
            throw new RuntimeException("Component type not support!");
        }
    }

}

