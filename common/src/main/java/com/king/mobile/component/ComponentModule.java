package com.king.mobile.component;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.Map;

public interface ComponentModule {
    /**
     * 获取已注册的组件
     *
     * @return
     */
    Map<String, ComponentInfo> getComponent();

    /**
     * 注册将要共享的module中
     *
     * @param component
     */
    void registerComponent(ComponentInfo component);

    /**
     * 获得module的名称
     *
     * @return
     */
    String getModuleName();

    View getView(String componentName);

    void invokeFunction(String componentName, String params);

    void openActivity(String componentName, String params);

    Fragment getFragment(String componentName, String params);
}
