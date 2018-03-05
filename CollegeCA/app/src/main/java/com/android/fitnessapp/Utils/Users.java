package com.android.fitnessapp.utils;

/**
 */

public class Users{

    public int ID;
    public String name;
    public String email;
    public String weight;
    public String height;
    public String BMI;

    public Users(int ID, String name, String email, String weight, String height, String BMI) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
    }



    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public Users(int ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
