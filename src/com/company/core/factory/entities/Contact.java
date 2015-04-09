package com.company.core.factory.entities;

/**
 * This class represents the entity which contains the information about certain
 * contact. The contact store full name about person and only one email address.
 *
 */

/**
 * Created by Sophie on 18.03.2015.
 */
public class Contact extends Entity {

    // email
    private String email;
    // first name of person(who has this email)
    private String firstName;
    // second name of person(who has this email)
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
