package com.company.core.api;


import com.company.core.factory.entities.Entity;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/** This interface designed for access to encapsulated data
 * instances of entities stored in memory of computer,  files or database.
 * It has methods, which allow to create, update, delete and find instances by identifier
 * Created by Sophie on 23.03.2015.
 */
public interface DAO<T extends Entity> {

    /**
     * Create a new instance of the entity in an appropriate Map, file or table of DataBase
     * @param entity a new instance of the entity
     * @return  id of created instance
     * @throws IOException output error
     */

    public BigInteger create(T entity) throws IOException;

    /**
     * Update instance of the entity at a certain identifier. A search for an instance identifier in
     * MapEntity, in a file or in a database table and writes the current values of the real instance fields
     * @param id id of updated instances
     * @param parameters Map, containing the pair field-value of instance
     * @throws IllegalAccessException if this Field object is enforcing Java language access control
     * and the underlying field is either inaccessible or final.
     */
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException;

    /**
     * Delete the instance of the entity by identifier
     * @param id id of deleted instance
     * @return true, if deletion was successfully, and false otherwise
     */
    public boolean delete(BigInteger id);

    /**
     * Search the instance of the entity by identifier.
     * @param  id id of needful instance
     * @return Delete the instance of the entity(which has appropriated type T extends Entity))
     */
    public T findById(BigInteger id);
}
