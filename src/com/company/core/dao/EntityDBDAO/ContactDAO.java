package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Contact;
import com.company.core.factory.EntityFactoryImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Sophie
 * @date 14.05.2015.
 */
public class ContactDAO extends DataBaseDAO<Contact>{

    @Override
    public Collection<Contact> getAllEntity(Class<Contact> entityClass) {
        Collection<Contact> collection = new ArrayList<Contact>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Contact contact = factory.createContact(resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surname"));
                contact.setId(resultSet.getString("id"));
                collection.add(contact);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }

    protected PreparedStatement getAddEntityQuery(Contact entity) throws SQLException {
            PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO CONTACT VALUES (?,?,?,?)");
            paramsStatement.setString(1, entity.getId());
            paramsStatement.setString(2, entity.getEmail());
            paramsStatement.setString(3, entity.getName());
            paramsStatement.setString(4, entity.getSurname());
            return paramsStatement;
    }
}
