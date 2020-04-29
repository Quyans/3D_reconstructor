package com.hjq.demo.other;

import com.hjq.demo.mojo.User;


/**
 * 这个类是软件的全局变量
 */
public class GlobalData {
    //登录状态
    private static User user = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        GlobalData.user = user;
    }
}
