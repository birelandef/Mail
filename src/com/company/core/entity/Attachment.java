package com.company.core.entity;

/**
 * The entity of the attached file
 *
 * @author Sofia Ruban
 */
public class Attachment extends Entity {

    /**
     * The name of the attached file
     */
    private String name;
    /**
     * The contents of the file
     */
    private byte[] file;

    public Attachment(String name, byte[] file) {
        this.name = name;
        this.file = file;
    }

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
}
