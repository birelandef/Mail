package com.company.core;

import com.company.core.entities.*;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sophie on 18.03.2015.
 */
public interface AbstractFactory {
    public Account createAccount(String firstName, String secondName, GregorianCalendar birthDay, String gender, String login, String password);
    public Attachment createAttachment(String name, int size, File file);
    public Contact createContact(String firstName, String secondName, GregorianCalendar birthDay, String gender, InternetAddress InternetAddress);
    public Folder createFolder(String name, BigInteger idParent, BigInteger idAccount, String fullName);
    public Letter createLetter(Date dateFrom,
                               Address recipient,
                               List<Address> sender,
                               String subject,
                               List<Address> carbonCopy,
                               List<Address> blindCarbonCopy,
                               String message,
                               List<Attachment> listAttachment,
                               BigInteger idFolder,
                               boolean isRead);
    public Person createPerson(String firstName, String secondName, GregorianCalendar birthDay, String gender);

//    public <T extends Generator> T create();

}
