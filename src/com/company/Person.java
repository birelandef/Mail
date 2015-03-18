package com.company;

import java.util.Date;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Person {
    protected String firstName;
    protected String secondName;
    protected Date birthDay;
    protected String gender;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getSecondName() {
        return secondName;
    }


}
