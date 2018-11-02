package com.example.android.bloodbank;

public class Donor {
    // fields
    private String Name;
    private String ID;
    private String Gender;
    private String Age;
    private String Date;
    private String weight;
    private String Hospital;
    private String City;

    // constructors
    public Donor() {
    }

    public Donor(String Name, String ID, String Gender, String Age, String Date, String weight, String Hospital, String City) {
        this.Name = Name;
        this.ID = ID;
        this.Gender = Gender;
        this.Age = Age;
        this.Date = Date;
        this.Hospital = Hospital;
        this.City = City;
    }

    // properties
    public void setDName(String Name) {
        this.Name = Name;
    }

    public String getDName() {
        return this.Name;
    }

    public void setDID(String ID) {
        this.ID = ID;
    }

    public String getDID() {
        return this.ID;
    }

    public void setDGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDGender() {
        return this.Gender;
    }

    public void setDAge(String Age) {
        this.Age = Age;
    }

    public String getDAge() {
        return this.Age;
    }

    public void setDDate(String Date) {
        this.Date = Date;
    }

    public String getDDate() {
        return this.Date;
    }

    public void setDweight(String Hospital) {
        this.weight = weight;
    }

    public String getDweight() {
        return this.weight;
    }


    public void setDHospital(String Hospital) {
        this.Hospital = Hospital;
    }

    public String getDHospital() {
        return this.Hospital;
    }

    public void setDCity(String City) {
        this.City = City;
    }

    public String getDCity() {
        return this.City;
    }
}
