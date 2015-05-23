package com.company.core.dao;

import com.company.core.api.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * @author Sophie
 * @date 10.05.2015.
 */
public class ConnectionwithDB {

    private static final Logger logger = Logger.getLogger(ConnectionwithDB.class);

    private static ConnectionwithDB ourInstance = new ConnectionwithDB();

    public Connection connection;

    public static ConnectionwithDB getInstance() throws RuntimeException {
        return ourInstance;
    }

    public ConnectionwithDB() throws RuntimeException{
        try {
            Locale.setDefault(Locale.ENGLISH);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(Constants.URL, Constants.name, Constants.password);
            if (connection == null){
                throw new NullPointerException();
            }
        } catch (ClassNotFoundException e) {
            logger.error("Driver of DB don't found ", e);
            throw new RuntimeException();
        } catch (SQLException | NullPointerException e) {
            logger.error("Connection is not established ", e);
            throw new RuntimeException();
        }
    }
}