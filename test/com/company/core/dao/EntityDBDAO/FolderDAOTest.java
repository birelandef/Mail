package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DBHelper;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Folder;
import com.company.core.entity.Person;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.*;

import java.net.ConnectException;
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
 * READY
 */
public class FolderDAOTest {

    private static Connection conn = null;
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private static FolderDAO folderDAO = new FolderDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static Person person = null;
    private static Account account = null;
    private static Folder folder = null;

    @BeforeClass
    public static void setUp() throws Exception {
        conn = DBHelper.getConnection();
        person = factory.createPerson("duma@mail.ru", "password", "Sanya", "Saharov",
                Gender.MAN, new Date(1), "Russia", "Moscow", "", new HashMap<String, Account>(), new HashMap<String, Contact>());
        account = factory.createAccount("example@yandex.ru", "12345", "yandex", "rambler", person.getId());
        folder = factory.createFolder(account.getId(), "newFolder", null, person.getId(), true, "root folder");
        personDAO.addEntity(person);
        accountDAO.addEntity(account);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        accountDAO.removeEntity(account);
        personDAO.removeEntity(person);
    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        folderDAO.addEntity(folder);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM FOLDER WHERE id='" + folder.getId() + "'");
        assertNotNull("Add folder", statement);
        folderDAO.removeEntity(folder);
    }

//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        folderDAO.addEntity(folder);
        Folder fol = folderDAO.findEntityById(folder.getId());
        assertNotNull("Entity is found",fol);
        folderDAO.removeEntity(fol);
    }

//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        folderDAO.addEntity(folder);
        String newName = "newFolder2";
        folder.setName(newName);
        folderDAO.updateEntity(folder);
        PreparedStatement statement = conn.prepareStatement("SELECT NAME FROM Folder WHERE id = '" + folder.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update Folder", resultSet.getString("NAME"), newName);
        folderDAO.removeEntity(folder);
    }
//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Folder> allEntity = folderDAO.getAllEntity();
        Iterator<Folder> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM folder");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        folderDAO.addEntity(folder);
        folderDAO.removeEntity(folder);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM folder WHERE id = '" + folder.getId() + "'");
        assertEquals("Remove folder", 0, statement.executeUpdate());
    }
}