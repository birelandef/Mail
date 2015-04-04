package com.company.core.factory.entities;

import java.io.*;
import java.math.BigInteger;

/**
 * TODO javaDoc
 *
 * Created by Sophie on 18.03.2015.
 */
public abstract class Entity implements Serializable {

   // protected static final long serialVersionUID = 1L;
    protected final BigInteger id;

    public Entity() {
        this.id = BigInteger.valueOf(System.currentTimeMillis());
    };

    public BigInteger getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (!id.equals(entity.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

//    public Map<String,String> getFields() throws IllegalAccessException {
//        Map<String,String> fields = new HashMap<>();
//        Field[] publicFields = this.getClass().getFields();
//        for (Field field : publicFields) {
//            fields.put(field.getName(), (String) field.get(this));
//        }
//        return fields;
//    };

    /*
    *сериализация экземпляра сущности
    * @params out  поток определенного файла (различные файлы ля разлиных сущностей)
     */
//    public void serialized(FileOutputStream out) throws IOException {
//        try (ObjectOutputStream oout = new ObjectOutputStream(out)){
//            oout.writeObject(this);
//        }
//    }

//    public  <T extends Entity> T deserialized(FileInputStream in) throws IOException, ClassNotFoundException {
//        try (ObjectInputStream oin = new ObjectInputStream(in)){
//            T obj  = (T) oin.readObject();
//            return  obj;
//        }
//    }

//    protected final void writeObject(ObjectOutputStream out) throws IOException{
//        out.defaultWriteObject();
//    }
//
//    protected final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//    }
}
