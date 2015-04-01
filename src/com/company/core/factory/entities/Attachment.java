package com.company.core.factory.entities;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Attachment extends Entity {

    private String name;
    private int size;
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


