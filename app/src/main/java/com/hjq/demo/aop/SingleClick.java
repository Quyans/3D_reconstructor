package com.hjq.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 防重复点击注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {

    /**
     * 快速点击的间隔
     */
    long value() default 1000;
}