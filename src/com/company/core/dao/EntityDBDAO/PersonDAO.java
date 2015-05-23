package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import com.company.core.factory.EntityFactoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sophie
 * @date 14.05.2015.
 */
public class PersonDAO  extends DataBaseDAO<Person>{
    @Override
    protected PreparedStatement getAddEntityQuery(Person entity) throws SQLException {
        PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO PERSON VALUES (?,?,?,?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getLogin());
        paramsStatement.setString(3, entity.getPassword());
        paramsStatement.setString(4, entity.getName());
        paramsStatement.setString(5, entity.getSurname());
        paramsStatement.setString(6, entity.getGender().toString());
        paramsStatement.setString(7, entity.getCountry());
        paramsStatement.setString(8, entity.getCity());
        paramsStatement.setString(9, entity.getInfo());
        paramsStatement.setDate(10,  entity.getBirthday());
        //TODO Person_Contact и Account
//        PreparedStatement preparedStatement = dataBase.connection.prepareStatement("INSERT INTO PERSON VALUES (?,?,?,?,?,?,?,?,?,?)");
        return paramsStatement;
    }
//TODO возможно, это нужно переписать
    @Override
    public Collection<Person> getAllEntity(Class<Person> entityClass) {
        Collection<Person> collection = new ArrayList<>();
        Map<String, Account> mapAccount = new HashMap<>();
        Map<String, Contact> mapContact = new HashMap<>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
           while (resultSet.next()) {
               EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
               PreparedStatement statementAccount = dataBase.connection.prepareStatement("SELECT *  FROM ACCOUNT WHERE IDPERSON = " + resultSet.getString("id"));
               PreparedStatement statementContact = dataBase.connection.prepareStatement("SELECT *  FROM PERSON_CONTACT JOIN CONTACT " +
                       "ON (PERSON_CONTACT.IDCONTACT = CONTACT.ID); " + " WHERE PERSON_CONTACT.IDPERSON = " + resultSet.getString("id"));
               ResultSet accounts = statementAccount.executeQuery();
               ResultSet contacts = statementContact.executeQuery();
               while(accounts.next()){
                   Account account = factory.createAccount(accounts.getString("email"), accounts.getString("password"),
                           accounts.getString("outgoingMailServer"),accounts.getString("incomingMailServer"),
                           accounts.getString("idPerson"));
                   account.setId(accounts.getString("ID"));
                   mapAccount.put(account.getId(), account);
               }
               while(contacts.next()){
                   Contact contact = factory.createContact(accounts.getString("email"), accounts.getString("name"),
                           accounts.getString("surname"));
                   contact.setId(accounts.getString("ID"));
                   mapContact.put(contact.getId(), contact);
               }
               //TODO приведение типов Gender
                Person person = factory.createPerson(resultSet.getString("login"), resultSet.getString("password"),
                        resultSet.getString("Name"), resultSet.getString("Surname"), Gender.MAN/*resultSet.getString("Genger")*/,
                        resultSet.getDate("Birthday"), resultSet.getString("Country"),resultSet.getString("City"),
                        resultSet.getString("Info"), mapAccount, mapContact);
                person.setId(resultSet.getString("id"));
                collection.add(person);
                mapAccount.clear();
                mapContact.clear();
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }
}
