package com.company;

import javax.mail.Address;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Letter {
    private Date dateFrom;
    private Address recipient;
    private List<Address> sender;
    private String subject;
    private List<Address> carbonCopy;
    private List<Address> blindCarbonCopy;
    private String message;
    private List<Attachment> listAttachment;
    private BigInteger idFolder;
    private boolean isRead;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Address getRecipient() {
        return recipient;
    }

    public void setRecipient(Address recipient) {
        this.recipient = recipient;
    }

    public List<Address> getSender() {
        return sender;
    }

    public void setSender(List<Address> sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Address> getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(List<Address> carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public List<Address> getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    public void setBlindCarbonCopy(List<Address> blindCarbonCopy) {
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
