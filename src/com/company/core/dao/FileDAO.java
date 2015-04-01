package com.company.core.dao;

import com.company.core.tools.Generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Sophie on 25.03.2015.
 */
public class FileDAO<T extends Generator>  implements DAO<T> {

    private  static File serialFile;
    private Class tClass;

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


    @Override
    public BigInteger create(T entity) throws IOException {
        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
        System.out.println("name " + serialFile.getName());
        tClass = entity.getClass();
        serialFile.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(serialFile, true);//true для дописывания файла
        entity.serialized(outputStream);
        outputStream.close();
        return entity.getId();
    }
    /*
    * для того, чтобы обновить данные о любом экземпляре сущности, перезапишем файл полностью
     */
    @Override
    public void update(T entity) throws IOException {
        if (read(entity.getId()) != null){
            delete(entity.getId());
        }
        create(entity);
    }

    @Override
    public boolean delete(BigInteger id) {
        //TODO заглушка
        return false;
    }

    @Override
    public T read(BigInteger id)  {
        T obj = null;
        try {
            FileInputStream fis = new FileInputStream(serialFile);
            obj = (T) tClass.newInstance();
           while (obj.getId() != id){
                obj = obj.deserialized(fis);
            }
            fis.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
