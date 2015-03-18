package com.company;

import java.math.BigInteger;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Folder {
    private String name;
    private BigInteger idParent;
    private BigInteger idAccount;
    private String fullName;

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
