package com.company.core.dao;

import com.company.core.tools.Generator;

import java.math.BigInteger;

/**
 * Created by Sophie on 25.03.2015.
 */
public class DataBaseDAO<T extends Generator> implements DAO<T> {
    @Override
    public BigInteger create(Generator entity) {
        // TODO
        return  null;
    }

    @Override
    public void update(Generator entity) {

    }

    @Override
    public boolean delete(BigInteger id) {
        //TODO заглушка
        return false;
    }

    @Override
    public T read(BigInteger id) {
        return null;
    }
}
