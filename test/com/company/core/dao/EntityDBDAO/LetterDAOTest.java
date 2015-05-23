package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.entity.*;
import org.junit.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class LetterDAOTest {

    private  static LetterDAO letterDAO = new LetterDAO();
    private static FolderDAO folderDAO = new FolderDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static Person person;
    private static Account account;
    private static Folder folder;
    private static Letter letter;

    @BeforeClass
    public static void setUp() throws Exception {
        person = new Person("duma@mail.ru", "password","Sanya","Saharov",
                Gender.MAN, new Date(1),"Russia", "Moscow"," ", new HashMap<String,Account>(), new HashMap<String, Contact>());
        account = new Account("example@yandex.ru", "12345", "yandex", "rambler",person.getId());
        folder = new Folder(account.getId(),"newFolder3", null, person.getId(),true,"root folder");
        letter = new Letter(person.getId(), folder.getId(), true,"" , new ArrayList<String>(),
                new ArrayList<String>(), "news", "Call me as soon as you can.", null, new Date(2));
        personDAO.addEntity(person);
        accountDAO.addEntity(account);
        folderDAO.addEntity(folder);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        letterDAO.addEntity(letter);
//        System.out.print(letter.getId());
        PreparedStatement statement = letterDAO.getDataBase().connection.prepareStatement("SELECT * FROM LETTER WHERE id =  '" +letter.getId() + "'");
        assertEquals("Add new letter", 1, statement.executeUpdate());
    }
//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Letter> allEntity = letterDAO.getAllEntity(Letter.class);
        PreparedStatement statement = letterDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM LETTER");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
        Iterator<Letter> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getToWhom());
        }
    }

    @Ignore
    @Test
    public void testFindEntityById() throws Exception {

    }
    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        String newSubject = "WARNING";
        letter.setSubject(newSubject);
        letterDAO.updateEntity(letter);
        PreparedStatement statement = letterDAO.getDataBase().connection.prepareStatement("SELECT SUBJECT FROM LETTER WHERE id = '" + letter.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update letter", resultSet.getString("SUBJECT"), newSubject);
    }
    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        letterDAO.removeEntity(letter);
        PreparedStatement statement = letterDAO.getDataBase().connection.prepareStatement("SELECT * FROM LETTER WHERE id = '" + letter.getId() + "'");
        assertEquals("Remove letter", 0, statement.executeUpdate());
    }
}