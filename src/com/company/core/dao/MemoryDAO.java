package com.company.core.dao;

import com.company.core.api.DAO;
import com.company.core.factory.entities.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Sophie on 23.03.2015.
 */
public class MemoryDAO<T extends Entity> implements DAO<T> {

    private static final Logger log = Logger.getLogger(MemoryDAO.class.toString());

    protected static Map<BigInteger, Person> persons = new HashMap<>();
    protected static Map<BigInteger, Account> accounts = new HashMap<>();
    protected static Map<BigInteger, Contact> contacts = new HashMap<>();
    protected static Map<BigInteger, Folder> folders = new HashMap<>();
    protected static Map<BigInteger, Letter> letters = new HashMap<>();
    protected static Map<BigInteger, Attachment> attachments = new HashMap<>();

    /**
     *
     * @param entity the instance of entity
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public BigInteger create(T entity) {
        if (entity.getClass().isAssignableFrom(Person.class)) {
            persons.put(entity.getId(), (Person) entity);
        }
        if (entity.getClass().isAssignableFrom(Account.class)) {
            accounts.put(entity.getId(), (Account) entity);
        }
        if (entity.getClass().isAssignableFrom(Contact.class)) {
            contacts.put(entity.getId(), (Contact) entity);
        }
        if (entity.getClass().isAssignableFrom(Folder.class)) {
            folders.put(entity.getId(), (Folder) entity);
        }
        if (entity.getClass().isAssignableFrom(Letter.class)) {
            letters.put(entity.getId(), (Letter) entity);
        }
        if (entity.getClass().isAssignableFrom(Attachment.class)) {
            attachments.put(entity.getId(), (Attachment) entity);
        }
        return entity.getId();
    }

    /*
       * обновление экземпляра сущности под определенным идентификатором. Производится поиск экземпляра по идентификатору в
       * mapEntity и записываются текущие значения полей из текущего  экземпляра
       * (поиск этого экземпляра по id?)
       * @param id  - идентификатор обновляемого экземпляра сущности
       */
    @Override
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException {
        T entity = findById(id);
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (parameters.containsKey(field.getName())) {
                //TODO field?
                field.set(field, parameters.get(field.getName()));
            }
        }
    }

    @Override
    public boolean delete (BigInteger id) {
        T entity = findById(id);
        if (entity.getClass().isAssignableFrom(Person.class)) {
            persons.remove(entity.getId());
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
    @SuppressWarnings("unchecked")
    public T findById(BigInteger id) {
        Map<BigInteger, Entity> allEntities = new HashMap<>();
        allEntities.putAll(persons);
        allEntities.putAll(accounts);
        allEntities.putAll(contacts);
        allEntities.putAll(folders);
        allEntities.putAll(letters);
        allEntities.putAll(attachments);
        return (T) allEntities.get(id);
    }
}
