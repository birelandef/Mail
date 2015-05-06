package com.company.core.api;

import java.io.File;

/**
 * Created by Sophie on 29.03.2015.
 */
public interface Constants {
    public static final  String SEPARATOR  = File.separator;
    public static final String PATHTORESOURCES = System.getProperty("user.dir") + Constants.SEPARATOR +
            "resources" + Constants.SEPARATOR;
    public static final String QUERY_CREATE = "";
    public static final String QUERY_UPDATE = "";
    public static final String QUERY_DELETE = "";
    public static final String QUERY_FIND = "";

}
