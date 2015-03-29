package com.company.core.dao;


import com.company.core.tools.Constants;
import com.company.core.tools.Generator;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Sophie on 23.03.2015.
 */
public interface DAO<T extends Generator> extends Constants {

    /*
    * создание map сущности со всеми экземплярами данной сущности. Содержимое map записывается в переменную
    * Map<BigInteger, String> mapEntity,  в файл или таблицу базы данных.
    * @param map - map с парами <название поля, значение>
    * @return id созданной сущности
    */
    public BigInteger create(T entity) throws IllegalAccessException, IOException;

    /*
    * обновление экземпляра сущности под определенным идентификатором. Производится поиск экземпляра по идентификатору в
    * mapEntity,  в файле или в таблице базы данных и записываются текущие значения полей из реального экземпляра
    * (поиск этого экземпляра по названию переменной?)
    * @param id  - идентификатор обновляемого экземпляра сущности
    */
    public void update(T entity) throws IllegalAccessException, IOException;
    /*
    * удаление экземпляра сущности по идентификатору
    * @param id  идентификатор удаляемого экземпляра сущности
    * @return true, если такой экземпляр был удален, и false иначе
    */
    public boolean delete(BigInteger id);
    /*
    * Поиск экземпляра сущности по идентификатору
    * @param id  идентификатор искомого экземпляра сущности
    * @return название переменной(объекта) найденного экземпляра сущности или null, если экземпляра сущности под таким идентификатором не существует
    */
    public T read(BigInteger id);
}
