package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 退出登录
 */
public class LogoutApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/logout";
    }
}