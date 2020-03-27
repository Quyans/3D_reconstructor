package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 修改手机
 */
public class PhoneApi implements IRequestApi {

    @Override
    public String getApi() {
        return "user/phone";
    }

    /** 旧手机号验证码（没有绑定情况下可不传） */
    private String preCode;

    /** 新手机号 */
    private String phone;
    /** 新手机号验证码 */
    private String code;

    public PhoneApi setPreCode(String preCode) {
        this.preCode = preCode;
        return this;
    }

    public PhoneApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PhoneApi setCode(String code) {
        this.code = code;
        return this;
    }
}