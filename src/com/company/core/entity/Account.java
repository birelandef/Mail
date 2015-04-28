package com.company.core.entity;

/**
 * The entity of the account of the mailbox
 *
 * @author Sofia Ruban
 */
public class Account extends Entity {

    /**
     * Email address of the user's mailbox
     */
    private String email;

    /**
     * The password from the user's mailbox
     */
    private String password;

    /**
     * Outgoing mail server
     */
    private String outgoingMailServer;

    /**
     * Incoming mail server
     */
    private String incomingMailServer;

    public Account(String email, String password, String outgoingMailServer, String incomingMailServer) {
        super();
        this.email = email;
        this.password = password;
        this.outgoingMailServer = outgoingMailServer;
        this.incomingMailServer = incomingMailServer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutgoingMailServer() {
        return outgoingMailServer;
    }

    public void setOutgoingMailServer(String outgoingMailServer) {
        this.outgoingMailServer = outgoingMailServer;
    }

    public String getIncomingMailServer() {
        return incomingMailServer;
    }

    public void setIncomingMailServer(String incomingMailServer) {
        this.incomingMailServer = incomingMailServer;
    }
}
