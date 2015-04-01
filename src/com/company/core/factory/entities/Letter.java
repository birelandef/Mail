package com.company.core.factory.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Letter extends Entity {

    private Date dateFrom;
    private String emailRecipient;
    private List<String> listSender;
    private String subject;
    private List<String> carbonCopy;
    private List<String> blindCarbonCopy;
    private String message;
    private List<Attachment> listAttachment = new ArrayList<>();
    private BigInteger idFolder;
    private boolean isRead;

    public Letter(Date dateFrom, String emailRecipient, List<String> listSender, String subject, List<String> carbonCopy,
                  List<String> blindCarbonCopy, String message, List<Attachment> listAttachment, BigInteger idFolder,
                  boolean isRead) {
        super();
        this.dateFrom = dateFrom;
        this.emailRecipient = emailRecipient;
        this.listSender = listSender;
        this.subject = subject;
        this.carbonCopy = carbonCopy;
        this.blindCarbonCopy = blindCarbonCopy;
        this.message = message;
        this.listAttachment = listAttachment;
        this.idFolder = idFolder;
        this.isRead = isRead;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    public List<String> getListSender() {
        return listSender;
    }

    public void setListSender(List<String> listSender) {
        this.listSender = listSender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(List<String> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public List<String> getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
        this.blindCarbonCopy = blindCarbonCopy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attachment> getListAttachment() {
        return listAttachment;
    }

    public void setListAttachment(List<Attachment> listAttachment) {
        this.listAttachment = listAttachment;
    }

    public BigInteger getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(BigInteger idFolder) {
        this.idFolder = idFolder;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}

