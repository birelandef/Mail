package com.company.core.tools;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Generator implements Serializable {

    protected final BigInteger id;

    public Generator() {
        this.id = BigInteger.valueOf(System.currentTimeMillis());
    };

    public BigInteger getId() {
        return id;
    }

    public Map<String,String> getFields() throws IllegalAccessException {
        Map<String,String> fields = new HashMap<>();
        Field[] publicFields = this.getClass().getFields();
        for (Field field : publicFields) {
            fields.put(field.getName(), (String) field.get(this));
        }
        return fields;
    };


}
