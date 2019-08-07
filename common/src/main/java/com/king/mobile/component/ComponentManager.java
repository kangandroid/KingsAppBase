package com.king.mobile.component;


import java.util.List;
import java.util.Map;

public class ComponentManager {


    private ComponentManager() {
    }

    public static ComponentManager getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static ComponentManager instance = new ComponentManager();
    }

    public void getComponentByName(String componentName){

    }
}
