package com.example.vitalsigns.model;

public class userInfo {
    public String username;
    public String emailAddress;
    public String mobileNo;
    public String password;
    public String dateOfBirth;
    public String height;
    public String weight;

    public userInfo(String username,String emailAddress,String mobileNo,String password,String dateOfBirth,String height,String weight){
        this.username = username;
        this.emailAddress = emailAddress;
        this.mobileNo = mobileNo;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
    }

}
