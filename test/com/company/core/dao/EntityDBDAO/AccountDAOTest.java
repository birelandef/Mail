package com.company.core.dao.EntityDBDAO;

import com.company.api.EntityFactoryInterface;
import com.company.api.Gender;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.entity.Person;
import org.junit.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 14.05.2015.
 * READY
 */
public class AccountDAOTest {

//    private static  ContactDAO contactDAO;
    private  static AccountDAO accountDAO;
    private static PersonDAO personDAO;
    private  static Person person;
    private static Account account;

    @BeforeClass
    public static void setUp() throws Exception {
        accountDAO = new AccountDAO();
        personDAO = new PersonDAO();
        person = new Person("duma@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
        account = new Account("example@yandex.ru", "12345", "yandex", "rambler",person.getId());
        personDAO.addEntity(person);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        accountDAO.removeEntity(account);
        personDAO.removeEntity(person);
    }

    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Account> allEntity = accountDAO.getAllEntity(Account.class);
        PreparedStatement statement = accountDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM ACCOUNT");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }
//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        accountDAO.addEntity(account);
        PreparedStatement statement = accountDAO.getDataBase().connection.prepareStatement("SELECT * FROM ACCOUNT WHERE id = '" + account.getId() + "'");
        assertEquals("Add new account", 1, statement.executeUpdate());
    }

    @Test
    public void testFindEntityById() throws Exception {

    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        String newIncoming= "google";
        account.setIncomingMailServer(newIncoming);
        accountDAO.updateEntity(account);
        PreparedStatement statement = accountDAO.getDataBase().connection.prepareStatement("SELECT INCOMINGMAILSERVER FROM ACCOUNT WHERE id = '" + account.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update account", resultSet.getString("INCOMINGMAILSERVER"), newIncoming);
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        accountDAO.removeEntity(account);
        PreparedStatement statement = accountDAO.getDataBase().connection.prepareStatement("SELECT * FROM account WHERE id = '" + account.getId() + "'");
        assertEquals("Remove account", 0, statement.executeUpdate());
    }
}