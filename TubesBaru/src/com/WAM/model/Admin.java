package com.WAM.model;

public class Admin {
    private String adminName;
    private String username;
    private String password;
    private String phoneNo;

    public Admin(String adminName, String username, String password, String phoneNo) {
        this.adminName = adminName;
        this.username = username;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public Admin(){

    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
