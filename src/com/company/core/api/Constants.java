package com.company.core.api;

import java.io.File;

/**
 * Created by Sophie on 29.03.2015.
 */
public interface Constants {
    String SEPARATOR  = File.separator;
    String PATHTORESOURCES = System.getProperty("user.dir") + Constants.SEPARATOR +
            "resources" + Constants.SEPARATOR;
    String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    String nameDB = "Mail";
    String name = "SOPHIE";
    String password  = "SOPHIE";
    String QUERY_ADD = "INSERT INTO ? VALUE ?";
    String QUERY_UPDATE = "UPDATE ? SET ? WHERE ?";
    String QUERY_DELETE = "DELETE FROM ? WHERE ?";
    String QUERY_FIND = "";

}
