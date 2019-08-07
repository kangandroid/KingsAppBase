package com.king.mobile.component;

public class ComponentInfo {
    public String group;
    public String name;
    public Class componentClass;
    public Type type;

    public enum Type {
        COMPONENT_TYPE_ACTIVITY,
        COMPONENT_TYPE_FRAGMENT,
        COMPONENT_TYPE_VIEW,
        COMPONENT_TYPE_FUNCTION;
    }
}
