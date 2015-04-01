package com.company.core.tools;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sophie on 18.03.2015.
 */
public class Generator implements Serializable, Constants{

    protected static final long serialVersionUID = Long.valueOf("6100508228625604496");// 1L;
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

    /*
    *сериализация экземпляра сущности
    * @params out  поток определенного файла (различные файлы ля разлиных сущностей)
     */
    public void serialized(FileOutputStream out) throws IOException {
        try (ObjectOutputStream oout = new ObjectOutputStream(out)){
            oout.writeObject(this);
        }
    }

    public  <T extends Generator> T deserialized(FileInputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream oin = new ObjectInputStream(in)){
            T obj  = (T) oin.readObject();
            return  obj;
        }
    }

//    protected final void writeObject(ObjectOutputStream out) throws IOException{
//        out.defaultWriteObject();
//    }
//
//    protected final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//    }
}
