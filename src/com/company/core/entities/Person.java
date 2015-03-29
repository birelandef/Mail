package com.company.core.entities;

import com.company.core.tools.Generator;

import java.util.GregorianCalendar;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Person extends Generator{
    protected String firstName;
    protected String secondName;
    protected GregorianCalendar birthDay;
    protected String gender;

    public Person(String firstName, String secondName, GregorianCalendar birthDay, String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public Person() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDay(GregorianCalendar birthDay) {
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

    public GregorianCalendar getBirthDay() {return birthDay;}

    public String getSecondName() {
        return secondName;
    }

}
