package com.company.core.dao;

import com.company.api.DAO;
import com.company.core.api.AbstractFactory;
import com.company.core.entity.Contact;
import com.company.core.entity.Entity;
import com.company.core.entity.Letter;
import oracle.jdbc.proxy.annotation.Pre;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sophie on 25.03.2015.
 */
public abstract class DataBaseDAO<T extends Entity> implements DAO<T> {

    protected static final Logger logger = Logger.getLogger(DataBaseDAO.class);
    protected static ConnectionwithDB dataBase;

    public static ConnectionwithDB getDataBase() {
        return dataBase;
    }

    protected static PreparedStatement paramsStatement;

    protected DataBaseDAO() throws  RuntimeException{
        dataBase = ConnectionwithDB.getInstance();
        if (dataBase == null) {
            throw  new RuntimeException();
        }
    }
    @Override
    public void addEntity(T entity) {
        try {
            paramsStatement = getAddEntityQuery(entity);
            paramsStatement.executeUpdate();
            //костыль,но как по-другому - не знаю
            if (entity.getClass().equals(Letter.class)){
                Letter letter = (Letter) entity;

            }
        } catch (SQLException e) {
            logger.error("Can't add record ",e);
            e.printStackTrace();
        }
    }


    @Override
    public T findEntityById(String idEntity) {
        return null;
    }

    @Override
    public void updateEntity(T entity) {
        removeEntity(entity);
        addEntity(entity);
    }

    @Override
    public void removeEntity(T entity) {
        try {
            paramsStatement =  dataBase.connection.prepareStatement("DELETE FROM " + entity.getClass().getSimpleName() + " WHERE id = ?");
            paramsStatement.setString(1, entity.getId());
            paramsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
    }

    abstract protected PreparedStatement getAddEntityQuery(T entity) throws SQLException;
}
