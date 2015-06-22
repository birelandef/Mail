package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DBHelper;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import com.company.core.factory.EntityFactoryImpl;
import static com.company.core.dao.DBHelper.*;
import org.junit.*;

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
 * @date 14.05.2015.
 * READY
 */
public class AccountDAOTest {

    private static Connection conn = DBHelper.getConnection();
    private static PreparedStatement statement = null;
    private static AccountDAO accountDAO = new AccountDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private static Person person;


    @BeforeClass
    public static void setUp() throws Exception {
        //TODO так же лучше, чем при объ€влении?
        person = factory.createPerson("rim@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
        personDAO.addEntity(person);
    }

    @AfterClass
    public static void tearDown() throws Exception {

        personDAO.removeEntity(person);
    }

//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Account> allEntity = accountDAO.getAllEntity();
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement("SELECT COUNT(*) FROM ACCOUNT");
            resultSet = statement.executeQuery();
            resultSet.next();
            assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
        }finally {
            freeResources(statement, resultSet);
        }
    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        Account account = factory.createAccount("account@yandex.ru", "12345", "yandex", "rambler", person.getId());
        accountDAO.addEntity(account);
        try {
            statement = conn.prepareStatement("SELECT * FROM ACCOUNT WHERE id = '" + account.getId() + "'");
            assertEquals("Add new account", 1, statement.executeUpdate());
        }finally {
            statement.close();
        }
    }
//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        Account account = factory.createAccount("account@yandex.ru", "12345", "yandex", "rambler", person.getId());
        accountDAO.addEntity(account);
        Account acc = accountDAO.findEntityById(account.getId());
        assertNotNull("Entity is found", acc);
    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        Account account = factory.createAccount("account@yandex.ru", "12345", "yandex", "rambler", person.getId());
        accountDAO.addEntity(account);
        String newIncoming = "kubana4";
        account.setIncomingMailServer(newIncoming);
        accountDAO.updateEntity(account);
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement("SELECT INCOMINGMAILSERVER FROM ACCOUNT WHERE id = '" + account.getId() + "'");
            resultSet = statement.executeQuery();
            resultSet.next();
            assertEquals("Update account", resultSet.getString("INCOMINGMAILSERVER"), newIncoming);
        }finally {
            freeResources(statement, resultSet);
        }
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        Account account = factory.createAccount("account@yandex.ru", "12345", "yandex", "rambler", person.getId());
        accountDAO.addEntity(account);
        accountDAO.removeEntity(account);
        try {
            statement = conn.prepareStatement("SELECT * FROM account WHERE id = '" + account.getId() + "'");
            assertEquals("Remove account", 0, statement.executeUpdate());
        } finally {
            statement.close();
        }
    }
}