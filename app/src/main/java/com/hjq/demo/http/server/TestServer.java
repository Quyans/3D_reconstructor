package com.hjq.demo.http.server;

/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 测试环境
 */
public class TestServer extends ReleaseServer {

    @Override
    public String getHost() {
        return "https://www.baidu.com/";
    }
}