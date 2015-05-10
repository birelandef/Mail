package com.company.core.dao;

import com.company.api.DAO;
import com.company.core.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Sophie on 25.03.2015.
 */
class DataBaseDAO<T extends Entity> implements DAO<T> {

    private static final Logger logger = Logger.getLogger(DataBaseDAO.class);
    private PreparedStatement paramsStatement = null;
    private static ConnectionwithDB dataBase;

    DataBaseDAO() throws  RuntimeException{
        dataBase = ConnectionwithDB.getInstance();
        if (dataBase == null) {
            throw  new RuntimeException();
        }
    }

    @Override
    public void addEntity(T entity) {
        try {
            paramsStatement = dataBase.connection.prepareStatement("INSERT INTO ? VALUE ?");
            paramsStatement.setString((int)1, entity.getClass().getSimpleName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<T> getAllEntity(Class<T> entityClass) {
        return null;
    }

    @Override
    public T findEntityById(String idEntity) {
        return null;
    }

    @Override
    public void updateEntity(T entity) {}

    @Override
    public void removeEntity(T entity) {}
}
