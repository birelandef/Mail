package com.company.core.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The entity of the letter
 *
 * @author Sofia Ruban
 */
public class Letter extends Entity {

    /**
     * The person identifier
     */
    private String person;
    /**
     * The identifier of the folder containing the message
     */
    private String idFolder;
    /**
     * Flag view the letter
     */
    private boolean seen;
    /**
     * Email address of the mailbox from which the mail is sent
     */
    private String fromWhom;
    /**
     * A list of electronic mailbox address of the recipients of the letter
     */
    private List<String> toWhom = new ArrayList<String>();
    /**
     * A list of email addresses mailboxes of recipients of copies of the letter
     */
    private ArrayList<String> copy = new ArrayList<String>();
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
    private List<Attachment> attachments = new ArrayList<Attachment>();
    /**
     * Date receive or send message
     */
    private Date date;

    public Letter(String person, String idFolder, boolean seen, String fromWhom, List<String> toWhom,
                  ArrayList<String> copy, String subject, String message, List<Attachment> attachments, Date date) {
        super();
        this.person = person;
        this.idFolder = idFolder;
        this.seen = seen;
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.copy = copy;
        this.subject = subject;
        this.message = message;
        this.attachments = attachments;
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
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

    public ArrayList<String> getCopy() {
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
}
