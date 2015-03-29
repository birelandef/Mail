package com.company.core.entities;

import com.company.core.tools.Generator;

import java.io.File;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Attachment extends Generator {

    public static String  serialFileName = String.valueOf(Attachment.class);

    private String name;
    private int size;
    private File file;

    public Attachment(String name, int size, File file) {
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) { this.size = size;}
}


