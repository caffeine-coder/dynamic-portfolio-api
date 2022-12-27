package com.caffeinecoder.dynamicportfolioapi.model;

public class Persons {

    int personId;
    String firstName;
    String lastName;
    String city;
    String address;

    public Persons(int personId, String firstName, String lastName, String city, String address) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
    }

    public int getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
