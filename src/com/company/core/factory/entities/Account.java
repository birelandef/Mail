package com.company.core.factory.entities;

/**
 * This class represents abstract image of account that is a specified e-mail address.
 * The address has characterized an appropriate incoming and outgoing server.
 * Access to internal contents has carried out with login and password.
 /*
 /**
 * Created by Sophie on 18.03.2015.
 */
public class Account extends Entity {
    /**
     * login of email
     */
    private String login;
    /**
     * user's password for certain login
     */
    private String password;
    /**
     * server for incoming letters
     */
    private String incomingServer;
    /*
    * port of incoming server
     */
    private int incomingServerPort;
    /*
    * server for outgoing letters
     */
    private String outgoingServer;
    /*
    * port for outgoing server
     */
    private int outgoingServerPort;

    public Account(String login, String password, String incomingServer, int incomingServerPort, String outgoingServer,
                   int outgoingServerPort) {
        super();
        this.login = login;
        this.password = password;
        this.incomingServer = incomingServer;
        this.incomingServerPort = incomingServerPort;
        this.outgoingServer = outgoingServer;
        this.outgoingServerPort = outgoingServerPort;
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

    public String getIncomingServer() {
        return incomingServer;
    }

    public void setIncomingServer(String incomingServer) {
        this.incomingServer = incomingServer;
    }

    public int getIncomingServerPort() {
        return incomingServerPort;
    }

    public void setIncomingServerPort(int incomingServerPort) {
        this.incomingServerPort = incomingServerPort;
    }

    public String getOutgoingServer() {
        return outgoingServer;
    }

    public void setOutgoingServer(String outgoingServer) {
        this.outgoingServer = outgoingServer;
    }

    public int getOutgoingServerPort() {
        return outgoingServerPort;
    }

    public void setOutgoingServerPort(int outgoingServerPort) {
        this.outgoingServerPort = outgoingServerPort;
    }
}
