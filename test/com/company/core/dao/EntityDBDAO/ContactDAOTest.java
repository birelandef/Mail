package com.company.core.dao.EntityDBDAO;

import com.company.core.entity.Contact;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 14.05.2015.
 * READY
 */
public class ContactDAOTest {
    private static  ContactDAO contactDAO;
    private static Contact contact;

    @BeforeClass
    public static void setUp() throws Exception {
        contactDAO = new ContactDAO();
        contact = new Contact("email@yandex.ru", "Ksusha", "Sedova");
    }

    @AfterClass
    public static void tearDown() throws Exception {
//        contactDAO.removeEntity(contact);
    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        contactDAO.addEntity(contact);
        PreparedStatement statement = contactDAO.getDataBase().connection.prepareStatement("SELECT * FROM CONTACT WHERE id = '" + contact.getId() + "'");
        assertEquals("Add new contact", 1, statement.executeUpdate());
    }

    @Test
    @Ignore
    public void testGetAllEntity() throws Exception {
        Collection<Contact> allEntity = contactDAO.getAllEntity(Contact.class);
        PreparedStatement statement = contactDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM CONTACT");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());

        Iterator<Contact> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Ignore
    @Test
    public void testFindEntityById() throws Exception {

    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        String newName = "Nina";
        contact.setName(newName);
        contactDAO.updateEntity(contact);
        PreparedStatement statement = contactDAO.getDataBase().connection.prepareStatement("SELECT NAME FROM CONTACT WHERE id = '" + contact.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update contact", resultSet.getString("NAME"),newName );
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        contactDAO.removeEntity(contact);
        PreparedStatement statement = contactDAO.getDataBase().connection.prepareStatement("SELECT * FROM CONTACT WHERE id = '" + contact.getId() + "'");
        assertEquals("Remove contact", 0, statement.executeUpdate());
    }
}