package com.king.mobile.cptprocessor;

import com.king.mobile.component.Component;
import javax.lang.model.element.TypeElement;

class ComponentAnnotatedClass {
    TypeElement annotatedClassElement;
    String name;
    private String type;
    private String params;

    ComponentAnnotatedClass(TypeElement classElement) throws IllegalArgumentException {
        this.annotatedClassElement = classElement;
        Component component = classElement.getAnnotation(Component.class);
        name = component.name();
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(
                    String.format("id() in @%s for class %s is null or empty! that's not allowed",
                            Component.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        type = component.type();
        if (StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException(
                    String.format("type() in @%s for class %s is null or empty! that's not allowed",
                            Component.class.getSimpleName(), classElement.getQualifiedName().toString()));
        } else if (!StringUtils.isComponentType(type)) {
            throw new IllegalArgumentException(
                    String.format("The value @%s is  unsupported by type() in @%s for class %s !",
                            type, Component.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        params = component.params();
        if (!StringUtils.isEmpty(params) && !StringUtils.isJsonString(params)) {
            throw new IllegalArgumentException(
                    String.format("params() in @%s for class %s must be JSON string!",
                            Component.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }

    }
}
