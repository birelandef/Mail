package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.dao.DBHelper;
import com.company.core.entity.*;
import com.company.core.factory.EntityFactoryImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 19.05.2015.
 * READY
 */
public class AttachmentDAOTest {

    private static Connection conn = null;
    private static EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
    private static AttachmentDAO attachmentDAO = new AttachmentDAO();
    private static FolderDAO folderDAO = new FolderDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static LetterDAO letterDAO = new LetterDAO();
    private static Person person = new Person("duma@mail.ru", "password","Sanya","Saharov",
            Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
    private  static Account account = null;
    private static Folder folder =  null;
    private static Letter letter = null;
    private static Attachment  attachment = null;

    @BeforeClass
    public static void setUp() throws Exception {
        conn = DBHelper.getConnection();
        account = factory.createAccount("example@yandex.ru", "12345", "yandex", "rambler",person.getId());
        folder = factory.createFolder(account.getId(), "newFolder3", null, person.getId(), true, "root folder");

        ArrayList<String> toWhom = new ArrayList<>();
        toWhom.add("mymail@yandex.ru");
        toWhom.add("mymail@ramber.ru");
        ArrayList<String> copy = new ArrayList<>();
        copy.add("mymail1@yandex.ru");
        copy.add("mymail1@ramber.ru");

        letter = factory.createLetter(person.getId(), folder.getId(), account.getId(), true, "newaddresseed@gmail.com",toWhom, copy, "news",
                "Call me as soon as you can.", null, new Date(1455555555));
        attachment = factory.createAttachment("picture", new byte[2],letter.getId(), folder.getId(), account.getId(), person.getId());
        personDAO.addEntity(person);
        accountDAO.addEntity(account);
        folderDAO.addEntity(folder);
        letterDAO.addEntity(letter);
    }

    @AfterClass
    public static void  tearDown() throws Exception {
        letterDAO.removeEntity(letter);
        folderDAO.removeEntity(folder);
        accountDAO.removeEntity(account);
        personDAO.removeEntity(person);
    }

//    @Ignore
    @Test
    public void testGetAllEntity() throws Exception {
        Collection<Attachment> allEntity = attachmentDAO.getAllEntity();
        Iterator<Attachment> iterator = allEntity.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM ATTACHMENT");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
    }
//    @Ignore
    @Test
    public void testAddEntity() throws Exception {
        attachmentDAO.addEntity(attachment);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("SELECT * FROM ATTACHMENT WHERE id =  '" + attachment.getId() + "'");
            assertEquals("Add new attachment", 1, statement.executeUpdate());
        }finally {
            statement.close();
            attachmentDAO.removeEntity(attachment);
        }
    }

//    @Ignore
    @Test
    public void testFindEntityById() throws Exception {
        attachmentDAO.addEntity(attachment);
        Attachment cont = attachmentDAO.findEntityById(attachment.getId());
        assertNotNull("Entity is found", cont);
        attachmentDAO.removeEntity(attachment);
    }
//    @Ignore
    @Test
    public void testUpdateEntity() throws Exception {
        attachmentDAO.addEntity(attachment);
        String newName = "Second File";
        attachment.setName(newName);
        attachmentDAO.updateEntity(attachment);
        PreparedStatement statement = conn.prepareStatement("SELECT NAME FROM ATTACHMENT WHERE id = '" + attachment.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update attachment", resultSet.getString("NAME"), newName);
        attachmentDAO.removeEntity(attachment);
    }

//    @Ignore
    @Test
    public void testRemoveEntity() throws Exception {
        attachmentDAO.addEntity(attachment);
        attachmentDAO.removeEntity(attachment);
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("SELECT * FROM ATTACHMENT WHERE id = '" + attachment.getId() + "'");
            assertEquals("Remove attachment", 0, statement.executeUpdate());
        } finally {
            statement.close();
        }
    }
}