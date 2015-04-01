package com.company.core.dao;

import com.company.core.factory.entities.Entity;

import java.io.*;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Sophie on 25.03.2015.
 */
public class FileDAO<T extends Entity> extends MemoryDAO<T> {

    private  static File serialFile;
    private Class tClass;

    public FileDAO() {

    }

    @Override
    public BigInteger create(T entity) {
        BigInteger id = super.create(entity);
        serialized();
        return id;
    }

    @Override
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException {

    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public T findById(BigInteger id) {
        return null;
    }

    public void serialized(FileOutputStream out) throws IOException {
        try (ObjectOutputStream oout = new ObjectOutputStream(out)){
            oout.writeObject(this);
        }
    }

    public  <T extends Entity> T deserialized(FileInputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream oin = new ObjectInputStream(in)){
            T obj  = (T) oin.readObject();
            return  obj;
        }
    }


//    public FileDAO() {
//        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
//        System.out.println("name " + serialFile.getName());
//        tClass = entity.getClass();
//        try {
//            serialFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    @Override
//    public BigInteger create(T entity) throws IOException {
//        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
//        System.out.println("name " + serialFile.getName());
//        tClass = entity.getClass();
//        serialFile.createNewFile();
//
//        FileOutputStream outputStream = new FileOutputStream(serialFile, true);//true для дописывания файла
//        entity.serialized(outputStream);
//        outputStream.close();
//        return entity.getId();
//    }
//    /*
//    * для того, чтобы обновить данные о любом экземпляре сущности, перезапишем файл полностью
//     */
//    @Override
//    public void update(T entity) throws IOException {
//        if (read(entity.getId()) != null){
//            delete(entity.getId());
//        }
//        create(entity);
//    }
//
//    @Override
//    public boolean delete(BigInteger id) {
//        //TODO заглушка
//        return false;
//    }
//
//    @Override
//    public T read(BigInteger id)  {
//        T obj = null;
//        try {
//            FileInputStream fis = new FileInputStream(serialFile);
//            obj = (T) tClass.newInstance();
//           while (obj.getId() != id){
//                obj = obj.deserialized(fis);
//            }
//            fis.close();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }

}
