package com.company.core.api;

import java.io.File;

/**
 * Created by Sophie on 29.03.2015.
 */
public interface Constants {
    public static final  String SEPARATOR  = File.separator;
    public static final String PATHTORESOURCES = System.getProperty("user.dir") + Constants.SEPARATOR +
            "resources" + Constants.SEPARATOR;
}
