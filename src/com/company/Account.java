package com.company;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Account extends Person {
    private String login;
    private String password;
    private String additionalAccount;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAdditionalAccount() {
        return additionalAccount;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdditionalAccount(String additionalAccount) {
        this.additionalAccount = additionalAccount;
    }
}
