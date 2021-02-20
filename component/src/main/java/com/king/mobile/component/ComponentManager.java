package com.king.mobile.component;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    private ComponentManager() {

    }

    static class InstanceHolder {
        static ComponentManager instance = new ComponentManager();
    }

    public static ComponentManager getInstance() {
        return InstanceHolder.instance;
    }

    Map<String,Class> component = new HashMap<>();


    public void addComponent(String name, Class clazz){
        if(name == null||name.equals("")){
            throw new RuntimeException("component name can but be null!");
        }
        component.put(name,clazz);
    }

    public void getComponent(String name){
        if(name == null||name.equals("")){
            throw new RuntimeException("component name can but be null!");
        }
        Class aClass = component.get(name);
    }


}
