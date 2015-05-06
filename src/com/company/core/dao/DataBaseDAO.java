package com.company.core.dao;

import com.company.core.api.DAO;
import com.company.core.factory.entities.Entity;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Sophie on 25.03.2015.
 */
class DataBaseDAO<T extends Entity> implements DAO<T> {

    private static final Logger logger = Logger.getLogger(DataBaseDAO.class);
    private final Connection connection;

    DataBaseDAO(String database, String nameDB, String password) throws RuntimeException {
        try {
            connection = connect(database,nameDB, password);
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

    @Override
    public BigInteger create(T entity) throws IOException {
        return null;
    }

    @Override
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException{

    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public T findById(BigInteger id) {
        return null;
    }


    public Connection connect(String database, String name, String password) throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:" + database;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(url, name, password);
        return connection;
    }
}
