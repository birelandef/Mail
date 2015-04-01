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
public class EntitiesFactory implements AbstractFactory {

    private static volatile EntitiesFactory instance;

    private EntitiesFactory(){}

    public static EntitiesFactory getInstance() {
        EntitiesFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (EntitiesFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EntitiesFactory();
                }
            }
        }
        return localInstance;
    }

    public Account createAccount (){
        return new Account();
    }

    @Override
    public Account createAccount(String firstName, String secondName, GregorianCalendar birthDay, String gender, String login, String password) {
        return new Account(firstName,secondName,birthDay,gender,login,password);
    }

    @Override
    public Attachment createAttachment(String name, int size, File file) {
        return new Attachment (name,size,file);
    }

    @Override
    public Contact createContact(String firstName, String secondName, GregorianCalendar birthDay, String gender, InternetAddress internetAddress) {
        return new Contact(firstName,secondName,birthDay,gender,internetAddress);
    }

    @Override
    public Folder createFolder(String name, BigInteger idParent, BigInteger idAccount, String fullName) {
        return new Folder(name, idParent, idAccount,fullName) ;
    }

    @Override
    public Letter createLetter(Date dateFrom,
                               Address recipient,
                               List<Address> sender,
                               String subject,
                               List<Address> carbonCopy,
                               List<Address> blindCarbonCopy,
                               String message,
                               List<Attachment> listAttachment,
                               BigInteger idFolder,
                               boolean isRead) {
        return new Letter(dateFrom,recipient,sender,subject,carbonCopy,blindCarbonCopy, message,listAttachment,idFolder,isRead);
    }

    @Override
    public Person createPerson(String firstName, String secondName, GregorianCalendar birthDay, String gender) {
        return new Person(firstName,secondName,birthDay, gender) ;
    }

//    public <T extends Generator> T create(){
//        T obj = new T();
//        return
//    }
}
