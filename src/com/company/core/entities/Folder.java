package com.company.core.entities;

import com.company.core.tools.Generator;

import java.math.BigInteger;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Folder extends Generator {
    private String name;
    private BigInteger idParent;
    private BigInteger idAccount;
    private String fullName;

    public Folder(String name, BigInteger idParent, BigInteger idAccount, String fullName) {
        this.name = name;
        this.idParent = idParent;
        this.idAccount = idAccount;
        this.fullName = fullName;
    }

    public Folder() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
