package com.hjq.demo.mojo;

public class User {
    private String nickname;
    private String phone;

    public User(){
        nickname = "";
        phone = "";
    }

    public User(String nickname,String phone){
        this.nickname = nickname;
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
