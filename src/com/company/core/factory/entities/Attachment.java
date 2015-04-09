package com.company.core.factory.entities;
/**
 * This class represents the file which attached to letter.
 * It allows to change this file and get file's size and name.
 */


/**
 * Created by Sophie on 18.03.2015.
 */
public class Attachment extends Entity {

    //name of attached file
    private String name;

    //size of attached file
    private int size;

    //bytes representation of attached file
    private byte[] file;

    public Attachment(String name, int size, byte[] file) {
        this.name = name;
        this.size = size;
        this.file = file;
    }

    public Attachment() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) { this.size = size;}
}


