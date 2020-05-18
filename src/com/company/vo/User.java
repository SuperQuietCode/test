package com.company.vo;

/**
 * @author ZhaoXin
 * @date 2020/4/22 14:44
 */
public class User {
    private Integer userId;
    private String userName;
    private String passWord;


    public User(Integer userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
