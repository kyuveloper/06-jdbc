package com.ohgiraffers.opez.model.vo;

public class UserVO {

    private int userCode;
    private String userName;
    private String userTier;

    public UserVO() {
    }

    public UserVO(String userName) {
        this.userName = userName;
    }

    public UserVO(int userCode, String userName, String userTier) {
        this.userCode = userCode;
        this.userName = userName;
        this.userTier = userTier;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "userCode=" + userCode +
                ", userName='" + userName + '\'' +
                ", userTier='" + userTier + '\'' +
                '}';
    }
}
