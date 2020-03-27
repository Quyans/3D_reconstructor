package com.hjq.demo.other;

import com.hjq.demo.BuildConfig;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : App 配置管理类
 */
public final class AppConfig {

    /**
     * 当前是否为 Debug 模式
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 获取当前应用的包名
     */
    public static String getPackageName() {
        return BuildConfig.APPLICATION_ID;
    }

    /**
     * 获取当前应用的版本名
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取当前应用的版本码
     */
    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    /**
     * 获取当前应用的渠道名
     */
    public static String getProductFlavors() {
        return BuildConfig.FLAVOR;
    }

    /**
     * 获取 BuglyId
     */
    public static String getBuglyId() {
        return BuildConfig.BUGLY_ID;
    }
}