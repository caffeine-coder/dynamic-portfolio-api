package com.caffeinecoder.dynamicportfolioapi.model;

public class UserSignUp {
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;

    public UserSignUp(String emailId, String password, String firstName, String lastName) {
        this.emailId = emailId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserSignUp{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
