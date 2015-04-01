package com.company.core.factory.entities;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * TODO javaDoc
 * Created by Sophie on 18.03.2015.
 */
public class Person extends Entity{
    // TODO javaDoc
    private String firstName;
    // TODO javaDoc
    private String secondName;
    // TODO javaDoc подумать над
    private GregorianCalendar birthDay;
    // TODO javaDoc
    private String gender;
    private List<Account> accounts = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();

    public Person(String firstName, String secondName, GregorianCalendar birthDay, String gender, List<Account> accounts,
                  List<Contact> contacts) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.contacts = contacts;
        this.accounts = accounts;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts.addAll(accounts);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }
}
