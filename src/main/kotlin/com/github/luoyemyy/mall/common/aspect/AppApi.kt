package com.github.luoyemyy.mall.common.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppApi {

    int pathId();// manager 10000+; applet 20000+;

    boolean auth() default true;
}
