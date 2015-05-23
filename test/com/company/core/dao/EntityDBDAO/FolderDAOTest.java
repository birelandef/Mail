package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Folder;
import com.company.core.entity.Person;
import org.junit.*;
import org.junit.runners.model.Statement;

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
 * READY
 */
public class FolderDAOTest {


    private static FolderDAO folderDAO;
    private static AccountDAO accountDAO;
    private static PersonDAO personDAO;
    private static Person person;
    private static Account account;
    private static Folder folder;

    @BeforeClass
    public static void setUp() throws Exception {
        folderDAO = new FolderDAO();
        accountDAO = new AccountDAO();
        personDAO = new PersonDAO();
        person = new Person("duma@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
        personDAO.addEntity(person);
        account = new Account("example@yandex.ru", "12345", "yandex", "rambler",person.getId());
        accountDAO.addEntity(account);
        folder = new Folder(account.getId(),"newFolder", null, person.getId(),true,"root folder");
    }
    @Ignore
    @AfterClass
    public static void tearDown() throws Exception {
//        accountDAO.removeEntity(account);
//        personDAO.removeEntity(person);
    }


    @Test
    public void testAddEntity() throws Exception {
        folderDAO.addEntity(folder);
        PreparedStatement statement = folderDAO.getDataBase().connection.prepareStatement("SELECT * FROM FOLDER WHERE id=" + folder.getId());
        assertNotNull("Add folder", statement);
    }

    @Ignore
    @Test
    public void testFindEntityById() throws Exception {

    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        String newName = "newFolder2";
        folder.setName(newName);
        folderDAO.updateEntity(folder);
        PreparedStatement statement = folderDAO.getDataBase().connection.prepareStatement("SELECT NAME FROM Folder WHERE id = '" + folder.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update Folder", resultSet.getString("NAME"), newName);
    }
    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Folder> allEntity = folderDAO.getAllEntity(Folder.class);
        PreparedStatement statement = folderDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM folder");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
        Iterator<Folder> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        folderDAO.removeEntity(folder);
        PreparedStatement statement = folderDAO.getDataBase().connection.prepareStatement("SELECT * FROM folder WHERE id = '" + folder.getId() + "'");
        assertEquals("Remove folder", 0, statement.executeUpdate());
    }
}