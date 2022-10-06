package com.WAM.model;

public class Client {
    private String clientName;
    private String phoneNo;
    private String sportType;
    private String fieldType;
    private String date;
    private String startTime;
    private String finishTime;
    private String DP;

    public Client(String clientName, String phoneNo, String sportType, String fieldType, String date, String startTime, String finishTime, String DP) {
        this.clientName = clientName;
        this.phoneNo = phoneNo;
        this.sportType = sportType;
        this.fieldType = fieldType;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.DP = DP;
    }

    public Client() {

    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }
}
