package com.company.core.dao.EntityDBDAO;

import com.company.api.Gender;
import com.company.core.entity.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

    private static AttachmentDAO attachmentDAO = new AttachmentDAO();
    private static FolderDAO folderDAO = new FolderDAO();
    private static PersonDAO personDAO = new PersonDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static LetterDAO letterDAO = new LetterDAO();
    private static Person person = new Person("duma@mail.ru", "password","Sanya","Saharov",
            Gender.MAN, new Date(1),"Russia", "Moscow","", new HashMap<String,Account>(), new HashMap<String, Contact>());
    private  static Account account = new Account("example@yandex.ru", "12345", "yandex", "rambler",person.getId());
    private static Folder folder =  new Folder(account.getId(),"newFolder3", null, person.getId(),true,"root folder");
    private static Letter letter = new Letter(person.getId(), folder.getId(), true,"" , new ArrayList<String>(),
            new ArrayList<String>(), "news", "Call me as soon as you can.", null, new Date(2));;
    private  static Attachment attachment  = new Attachment("picture", new byte[2],letter.getId(), folder.getId(), account.getId(), person.getId());;

    @BeforeClass
    public static void setUp() throws Exception {
        personDAO.addEntity(person);
        accountDAO.addEntity(account);
        folderDAO.addEntity(folder);
        letterDAO.addEntity(letter);
    }

//    @AfterClass
//    public static void  tearDown() throws Exception {
//        attachmentDAO.removeEntity(attachment);
//        letterDAO.removeEntity(letter);
//        folderDAO.removeEntity(folder);
//        accountDAO.removeEntity(account);
//        personDAO.removeEntity(person);
//    }

//    @Test
//    public void testGetAllEntity() throws Exception {
//        Collection<Attachment> allEntity = attachmentDAO.getAllEntity(Attachment.class);
//        PreparedStatement statement = attachmentDAO.getDataBase().connection.prepareStatement("SELECT COUNT(*) FROM ATTACHMENT");
//        ResultSet resultSet = statement.executeQuery();
//        resultSet.next();
//        assertEquals("Get All Entity", resultSet.getInt(1), allEntity.size());
//
////        Iterator<Attachment> iterator = allEntity.iterator();
////        while (iterator.hasNext()){
////            System.out.println(iterator.next());
////        }
//    }

    @Test
    public void testAddEntity() throws Exception {
        attachmentDAO.addEntity(attachment);
        PreparedStatement statement = attachmentDAO.getDataBase().connection.prepareStatement("SELECT * FROM ATTACHMENT WHERE id =  '" + attachment.getId() + "'");
        assertEquals("Add new attachment", 1, statement.executeUpdate());
    }

    @Test
    public void testFindEntityById() throws Exception {

    }

    @Test
    public void testUpdateEntity() throws Exception {
        String newName = "Second File";
        attachment.setName(newName);
        attachmentDAO.updateEntity(attachment);
        PreparedStatement statement = attachmentDAO.getDataBase().connection.prepareStatement("SELECT NAME FROM ATTACHMENT WHERE id = '" + attachment.getId() + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        assertEquals("Update attachment", resultSet.getString("NAME"),newName );

    }

//    @Test
//    public void testRemoveEntity() throws Exception {
//        attachmentDAO.removeEntity(attachment);
//        PreparedStatement statement = attachmentDAO.getDataBase().connection.prepareStatement("SELECT * FROM ATTACHMENT WHERE id = '" + attachment.getId() + "'");
//        assertEquals("Remove attachment", 0, statement.executeUpdate());
//    }
}