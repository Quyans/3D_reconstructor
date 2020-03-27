package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

import java.io.File;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 上传图片
 */
public class UpdateImageApi implements IRequestApi {

    @Override
    public String getApi() {
        return "update/image";
    }

    /** 图片文件 */
    private File image;

    public UpdateImageApi setImage(File image) {
        this.image = image;
        return this;
    }
}