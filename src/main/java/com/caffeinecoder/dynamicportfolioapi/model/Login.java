package com.caffeinecoder.dynamicportfolioapi.model;

public class Login {

    String emailId;
    String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Login(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }
}
