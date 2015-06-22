package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DBHelper;
import com.company.core.entity.Contact;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.*;

import java.sql.Connection;
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

    private static Connection conn = null;
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private static  ContactDAO contactDAO = new ContactDAO();
    private static Contact contact;

    @BeforeClass
    public static void setUp() throws Exception {
        conn = DBHelper.getConnection();
        contact = factory.createContact("cool@dotnet.com", "Dwayne", "Johnson");
    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        contactDAO.addEntity(contact);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM CONTACT WHERE id = '" + contact.getId() + "'");
        assertEquals("Add new contact", 1, statement.executeUpdate());
        contactDAO.removeEntity(contact);
    }

//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Contact> allEntity = contactDAO.getAllEntity();

        Iterator<Contact> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM CONTACT");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }

//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        contactDAO.addEntity(contact);
        Contact cont = contactDAO.findEntityById(contact.getId());
        assertNotNull("Entity is found", cont);
        contactDAO.removeEntity(contact);
    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        contactDAO.addEntity(contact);
        String newName = "Vin";
        contact.setName(newName);
        contactDAO.updateEntity(contact);
        PreparedStatement statement = conn.prepareStatement("SELECT NAME FROM CONTACT WHERE id = '" + contact.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update contact", resultSet.getString("NAME"),newName );
        contactDAO.removeEntity(contact);
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        contactDAO.addEntity(contact);
        contactDAO.removeEntity(contact);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM CONTACT WHERE id = '" + contact.getId() + "'");
        assertEquals("Remove contact", 0, statement.executeUpdate());
    }
}