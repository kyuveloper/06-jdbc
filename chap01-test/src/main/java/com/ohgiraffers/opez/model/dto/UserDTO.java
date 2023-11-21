package com.ohgiraffers.opez.model.dto;

public class UserDTO {

    private String userName;
    private String userTier;

    public UserDTO() {
    }

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public UserDTO(String userName, String userTier) {
        this.userName = userName;
        this.userTier = userTier;
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
        return "MenuDTO{" +
                "userName='" + userName + '\'' +
                ", userTier='" + userTier + '\'' +
                '}';
    }
}
