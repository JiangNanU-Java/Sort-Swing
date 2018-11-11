package com.ten.demo.dataExchange;

//用户 对象
public class User {
    private String userName;
    private char[] Password;

    //构造方法
    public User() {
        this("null", null);
    }

    public User(String username, char[] password) {
        this.userName = username;
        this.Password = password;
    }

    //getter setter方法
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return Password;
    }

    public void setPassword(char[] password) {
        Password = password;
    }
}
