package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 获取用户信息
 */
public class UserInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/info";
    }
}