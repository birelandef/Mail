package com.company.core.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * The entity of the letter
 *
 * @author Sofia Ruban
 */
public class Letter extends Entity {

    /**
     * The idPerson identifier
     */
    private String idPerson;
    /**
     * The identifier of the folder containing the message
     */
    private String idFolder;
    /**
     * Flag view the letter
     */
    private boolean isSeen;
    /**
     * Email address of the mailbox from which the mail is sent
     */
    private String fromWhom;
    /**
     * A list of electronic mailbox address of the recipients of the letter
     */
    //TODO в схеме?
    private List<String> toWhom = new ArrayList<String>();
    /**
     * A list of email addresses mailboxes of recipients of copies of the letter
     */
    //TODO в схеме?
    private List<String> copy = new ArrayList<String>();
    /**
     * Message subject
     */
    private String subject;
    /**
     * The body of the message
     */
    private String message;
    /**
     * The list of files attached to the message
     */
    //TODO в схеме?
    private List<Attachment> attachments = new ArrayList<>();
    /**
     * Date receive or send message
     */
    private Date date;

    public Letter(String idPerson, String idFolder, boolean isSeen, String fromWhom, List<String> toWhom,
                  List<String> copy, String subject, String message, List<Attachment> attachments, Date date) {
        super();
        this.idPerson = idPerson;
        this.idFolder = idFolder;
        this.isSeen = isSeen;
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.copy = copy;
        this.subject = subject;
        this.message = message;
        this.attachments = attachments;
        this.date = date;
    }

    public String getidPerson() {
        return idPerson;
    }

    public void setidPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setisSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public List<String> getToWhom() {
        return toWhom;
    }

    public void setToWhom(List<String> toWhom) {
        this.toWhom.addAll(toWhom);
    }

    public List<String> getCopy() {
        return copy;
    }

    public void setCopy(ArrayList<String> copy) {
        this.copy = copy;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments.addAll(attachments);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id='" + getId() + '\'' +
                ", idPerson='" + idPerson + '\'' +
                ", idFolder='" + idFolder + '\'' +
                ", isSeen=" + isSeen +
                ", fromWhom='" + fromWhom + '\'' +
                ", toWhom=" + toWhom +
                ", copy=" + copy +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", attachments=" + attachments +
                ", date=" + date +
                '}';
    }
}
