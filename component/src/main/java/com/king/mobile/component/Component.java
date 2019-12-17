package com.king.mobile.component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String TYPE_ACTIVITY = "activity";
    String TYPE_VIEW = "view";
    String TYPE_FUNCTION = "function";
    String TYPE_FRAGMENT = "fragment";

    String name() default "";

    String type() default TYPE_ACTIVITY;

    String params() default "{}";
}
