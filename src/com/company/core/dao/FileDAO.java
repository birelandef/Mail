package com.company.core.dao;

import com.company.core.EntitiesFactory;
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

    File serialFile;

    @Override
    public BigInteger create(Generator entity) throws IOException {
        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
        serialFile.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(serialFile, true);
        entity.serialized(outputStream);
        return entity.getId();
    }
    /*
    * для того, чтобы обновить данные о любом экземпляре сущности, перезапишем файл полностью
     */
    @Override
    public void update(Generator entity) throws IOException {
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
    public T read(BigInteger id) {
        //EntitiesFactory factory = EntitiesFactory.getInstance();
        T obj = new T();
        try {
            obj.deserialized(new FileInputStream(serialFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
