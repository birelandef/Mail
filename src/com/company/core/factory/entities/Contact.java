package com.company.core.factory.entities;

/**
 *
 * Created by Sophie on 18.03.2015.
 */
public class Contact extends Entity {

    private String email;
    // TODO javaDoc
    private String firstName;
    // TODO javaDoc
    private String secondName;

    public Contact(String email, String firstName, String secondName) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
