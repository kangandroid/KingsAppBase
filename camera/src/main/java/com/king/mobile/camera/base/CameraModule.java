package com.king.mobile.camera.base;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.king.mobile.component.Component;
import com.king.mobile.component.ComponentInfo;
import com.king.mobile.component.ComponentModule;

import java.util.Map;

public class CameraModule implements ComponentModule {

    @Override
    public Map<String, ComponentInfo> getComponent() {
        return null;
    }

    @Override
    public void registerComponent(ComponentInfo component) {

    }

    @Override
    public String getModuleName() {
        return "Camera";
    }

    @Override
    public View getView(String componentName) {
        return null;
    }

    @Override
    public void invokeFunction(String componentName, String params) {

    }

    @Override
    public void openActivity(String componentName, String params) {

    }

    @Override
    public Fragment getFragment(String componentName, String params) {
        return null;
    }
}
