package com.company.core.dao;

import com.company.core.tools.Generator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sophie on 23.03.2015.
 */
public class MemoryDAO <T extends Generator> implements DAO<T> {
    private Map<BigInteger,  Map<String,String>> mapEntities = new HashMap<>();

    @Override
    /*
    * создание map - сущность со всеми полями. Содержимое map записывается в переменную
    * Map<BigInteger, String> mapEntity
    * @param map - map с парами <название поля, значение>
    */
    public BigInteger create(T entity) throws IllegalAccessException {
        Map<String, String> map = null;
            map = entity.getFields();
            BigInteger index = new BigInteger(map.get("id"));
            map.remove("id");
            mapEntities.put(index, map);
            return index;
    }

    /*
       * обновление экземпляра сущности под определенным идентификатором. Производится поиск экземпляра по идентификатору в
       * mapEntity и записываются текущие значения полей из текущего  экземпляра
       * (поиск этого экземпляра по id?)
       * @param id  - идентификатор обновляемого экземпляра сущности
       */
    @Override
    public void update(T entity) throws IllegalAccessException {
        if (read(entity.getId()) != null) {
            delete(entity.getId());
        }
            create(entity);
    }

    @Override
    public boolean delete (BigInteger id) {
        if (mapEntities.containsKey(id)){
            mapEntities.remove(id);
            return true;
        }
        return false;
    }

    /*
    * Поиск экземпляра сущности по идентификатору
    * @param id  идентификатор искомого экземпляра сущности
    * @return map с <поле, значение> найденного экземпляра сущности или null, если экземпляра сущности
    * под таким идентификатором не существует
    */
    @Override
    //TODO
    public T read(BigInteger id) {
        for (Map.Entry<BigInteger, Map<String,String>> entry: mapEntities.entrySet()){
            if (entry.getKey() == id) {

                return null ; //entry.getValue();
            }
        }
        return null;
    }
}
