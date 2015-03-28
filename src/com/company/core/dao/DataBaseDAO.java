package com.company.core.dao;

import com.company.core.tools.Generator;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Sophie on 25.03.2015.
 */
public class DataBaseDAO implements DAO {
    @Override
    public void create(Map<String, String> map) {

    }

    @Override
    public void update(Generator entity) {

    }

    @Override
    public boolean remove(BigInteger id) {
        //TODO заглушка
        return false;
    }

    @Override
    public Map<String,String> findByID(BigInteger id) {
        return null;
    }
}
