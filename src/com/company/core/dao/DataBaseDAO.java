package com.company.core.dao;

import com.company.core.api.DAO;
import com.company.core.factory.entities.Entity;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Sophie on 25.03.2015.
 */
public class DataBaseDAO<T extends Entity> implements DAO<T> {

    @Override
    public BigInteger create(T entity) throws IllegalAccessException, IOException {
        return null;
    }

    @Override
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException, IOException {

    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public T findById(BigInteger id) {
        return null;
    }
}
