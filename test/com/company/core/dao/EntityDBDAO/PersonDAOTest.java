package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DBHelper;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class PersonDAOTest {

    private static Connection conn = DBHelper.getConnection();
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private static final PersonDAO personDAO = new PersonDAO();
    private static Person person = null;
    @BeforeClass
    public static void setUp() throws Exception {
        person = factory.createPerson("rim@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        personDAO.addEntity(person);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM PERSON WHERE id = '" + person.getId() + "'");
        assertEquals("Add new person", 1, statement.executeUpdate());
        personDAO.removeEntity(person);
    }

//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Person> allEntity = personDAO.getAllEntity();
//        Iterator<Person> iter = allEntity.iterator();
//        while (iter.hasNext()){
//            System.out.println(iter.next());
//        }
        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM PERSON");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }

    @Test
    public void testFindEntityById() throws Exception {
        personDAO.addEntity(person);
        Person per = personDAO.findEntityById(person.getId());
        assertNotNull("Entity isn't found", per);
        personDAO.removeEntity(person);

    }

//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        personDAO.addEntity(person);
        String newCity = "MoscowCity";
        person.setCity(newCity);
        personDAO.updateEntity(person);
        PreparedStatement statement = conn.prepareStatement("SELECT City FROM PERSON WHERE id = '" + person.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update contact", resultSet.getString("CITY"), newCity);
        personDAO.removeEntity(person);
    }

//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        personDAO.addEntity(person);
        personDAO.removeEntity(person);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM PERSON WHERE id = '" + person.getId() + "'");
        System.out.println(statement.executeUpdate());
        assertEquals("Remove contact", 0, statement.executeUpdate());
    }
}