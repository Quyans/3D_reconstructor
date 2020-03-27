package com.hjq.demo.http.server;

import com.hjq.http.config.IRequestServer;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 正式环境
 */
public class ReleaseServer implements IRequestServer {

    @Override
    public String getHost() {
        return "https://www.baidu.com/";
    }

    @Override
    public String getPath() {
        return "api/";
    }
}