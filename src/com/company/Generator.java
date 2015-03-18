package com.company;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Generator {
    private static Generator ourInstance = new Generator();


    public static Generator getInstance() {
        return ourInstance;
    }

    private Generator() {

    }
}
