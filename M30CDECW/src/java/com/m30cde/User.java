package com.m30cde;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
public class User {
    private int userID;
    private String userType;
    private String userName;
    private String userPassword;

    public User(int i, String t, String n, String p) {
        userID = i;
        userType = t;
        userName = n;
        userPassword = p;
    }

    public void setUserID(int i) {
        userID = i;
    }

    public void setUserType(String t) {
        userType = t;
    }

    public void setUserName(String n) {
        userName = n;
    }

    public void setUserPassword(String p) {
        userPassword = p;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
