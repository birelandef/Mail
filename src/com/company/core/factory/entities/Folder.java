package com.company.core.factory.entities;

import java.math.BigInteger;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Folder extends Entity {

    private String name;
    private BigInteger idPerson;
    private BigInteger idParent;
    private BigInteger idAccount;
    private String description;

    public Folder(String name, BigInteger idPerson, BigInteger idParent, BigInteger idAccount, String description) {
        super();
        this.name = name;
        this.idPerson = idPerson;
        this.idParent = idParent;
        this.idAccount = idAccount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getIdParent() {
        return idParent;
    }

    public void setIdParent(BigInteger idParent) {
        this.idParent = idParent;
    }

    public BigInteger getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(BigInteger idAccount) {
        this.idAccount = idAccount;
    }

    public BigInteger getIdPerson() {
        return idPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}