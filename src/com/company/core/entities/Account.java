package com.company.core.entities;

import java.util.GregorianCalendar;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Account extends Person {
    public static String  serialFileName = String.valueOf(Account.class);

    private String login;
    private String password;

    public Account(String firstName, String secondName, GregorianCalendar birthDay, String gender, String login, String password) {
        super(firstName, secondName, birthDay, gender);
        this.login = login;
        this.password = password;
    }

    public Account() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
