package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class PersonDAOTest {

    private static PersonDAO personDAO;
    private static Person person;


    @BeforeClass
    public static void setUp() throws Exception {
        personDAO = new PersonDAO();
        person = new Person("duma@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
    }

    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        personDAO.addEntity(person);
        PreparedStatement statement = personDAO.getDataBase().connection.prepareStatement("SELECT * FROM PERSON WHERE id = '" + person.getId() + "'");
        assertEquals("Add new person", 1, statement.executeUpdate());
    }

    @Ignore
    @Test
    //TODO проблема с Person_*
    public void testGetAllEntity() throws Exception {
        Collection<Person> allEntity = personDAO.getAllEntity(Person.class);
        PreparedStatement statement = personDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM PERSON");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }

    @Test
    public void testFindEntityById() throws Exception {

    }
    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        String newCity = "Bladimir";
        person.setCity(newCity);
        personDAO.updateEntity(person);
        PreparedStatement statement = personDAO.getDataBase().connection.prepareStatement("SELECT City FROM PERSON WHERE id = '" + person.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update contact", resultSet.getString("CITY"), newCity);
    }

    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        personDAO.removeEntity(person);
        PreparedStatement statement = personDAO.getDataBase().connection.prepareStatement("SELECT * FROM PERSON WHERE id = '" + person.getId() + "'");
        assertEquals("Remove contact", 0, statement.executeUpdate());
    }
}