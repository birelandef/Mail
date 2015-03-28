package com.company.core.entities;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.GregorianCalendar;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Contact extends Person {
    private InternetAddress internetAddress;

    public Contact() {
    }

    public Contact(String firstName, String secondName, GregorianCalendar birthDay, String gender, InternetAddress InternetAddress) {
        super(firstName, secondName, birthDay, gender);
        this.internetAddress = InternetAddress;
    }

    public InternetAddress getInternetAddress() {
        return internetAddress;
    }

    public void setInternetAddress(String address) throws AddressException {
        this.internetAddress = new InternetAddress(address);
    }
}
