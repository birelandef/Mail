package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DBHelper;
import com.company.core.entity.*;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.company.core.dao.DBHelper.freeResources;
import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class LetterDAOTest {

    private static Connection conn = null;
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private  static LetterDAO letterDAO = new LetterDAO();
    private static FolderDAO folderDAO = new FolderDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static Person person = null;
    private static Account account = null;
    private static Folder folder = null;
    private static Letter letter = null;

    @BeforeClass
    public static void setUp() throws Exception {
        conn = DBHelper.getConnection();
        person = factory.createPerson("rim@mail.ru", "password", "Sanya", "Saharov",
                Gender.MAN, new Date(1), "Russia", "Moscow", "", new HashMap<String, Account>(), new HashMap<String, Contact>());
        account = factory.createAccount("example@yandex.ru", "12345", "yandex", "rambler", person.getId());
        folder = factory.createFolder(account.getId(), "newFolder3", null, person.getId(), true, "root folder");

        ArrayList<String> toWhom = new ArrayList<>();
        toWhom.add("mymail@yandex.ru");
        toWhom.add("mymail@ramber.ru");
        ArrayList<String> copy = new ArrayList<>();
        copy.add("mymail1@yandex.ru");
        copy.add("mymail1@ramber.ru");
        letter = factory.createLetter(person.getId(), folder.getId(), account.getId(), true, "newaddresseed@gmail.com",toWhom, copy, "news",
                "Call me as soon as you can.", null, new Date(1455555555));
        personDAO.addEntity(person);
        accountDAO.addEntity(account);
        folderDAO.addEntity(folder);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        letterDAO.addEntity(letter);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("SELECT * FROM LETTER WHERE id =  '" + letter.getId() + "'");
            assertEquals("Add new account", 1, statement.executeUpdate());
        }finally {
            statement.close();
            letterDAO.removeEntity(letter);
        }
    }
//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Letter> allEntity = letterDAO.getAllEntity();
        Iterator<Letter> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM LETTER");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }

//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        letterDAO.addEntity(letter);
        Letter l = letterDAO.findEntityById(letter.getId());
        assertNotNull("Entity is found",l);
        letterDAO.removeEntity(l);
    }

//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        letterDAO.addEntity(letter);
        String newSubject = "WARNING";
        letter.setSubject(newSubject);
        letterDAO.updateEntity(letter);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement("SELECT SUBJECT FROM LETTER WHERE id = '" + letter.getId() + "'");
            resultSet = statement.executeQuery();
            resultSet.next();
            assertEquals("Update letter", resultSet.getString("SUBJECT"), newSubject);
        }finally {
            freeResources(statement, resultSet);
            letterDAO.removeEntity(letter);
        }
    }
//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        letterDAO.addEntity(letter);
        letterDAO.removeEntity(letter);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("SELECT * FROM LETTER WHERE id = '" + letter.getId() + "'");
            assertEquals("Remove letter", 0, statement.executeUpdate());
        } finally {
            statement.close();
        }
    }
}