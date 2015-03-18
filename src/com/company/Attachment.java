package com.company;

import java.io.File;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Attachment {
    private String name;
    private int size;
    private File file;

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

    public void setSize(int size) {
        this.size = size;
    }
}
