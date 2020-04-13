package com.hjq.demo.http.request;


/**
 *    author : 曲延松
 *    time   : 2020/01/11
 *    desc   : 请求的api
 */
public class RequestApi {
    public String Login = "/login";
    public String HasLoggedIn = "/hasLoggedIn";
    public String Logout = "/logout";
    public String Register = "/register";

    public String User = "/user";
    public String Model = "/model";
    public String CreateModel = "/model/create";
    private String DownloadModel = "/models/";

    public String getDownloadModel() {
        return DownloadModel;
    }

    public void setDownloadModel(String modelID) {
        this.DownloadModel =this.DownloadModel+ modelID + "/model/";
    }
}
