package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 修改密码
 */
public class PasswordApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/password";
    }

    /** 手机号（已登录可不传） */
    private String phone;
    /** 验证码 */
    private String code;
    /** 密码 */
    private String password;

    public PasswordApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PasswordApi setCode(String code) {
        this.code = code;
        return this;
    }

    public PasswordApi setPassword(String password) {
        this.password = password;
        return this;
    }
}