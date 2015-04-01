package com.company.core;

import com.company.core.factory.entities.*;
import org.junit.Test;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import static com.company.core.EntitiesFactory.getInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * EntitiesFactory Tester.
 *
 * @author Sophie
 * @version 1.0
 */
public class EntitiesFactoryTest {

    //TODO проблемы с инициализацией factory и account внутри тестовых методов, пока каждый раз [объявляются/создаются] заново
    public EntitiesFactory factory;
    public Account account;

    /**
    *
    * Method: getInstance()
    *
    */
    @Test
    public  void testGetInstance() throws Exception {
        factory = getInstance();
        assertNotNull("Фабрика сущностей не создана", factory);
    }


    /**
    *
    * Method: createAccount(String firstName, String secondName, Date birthDay, String gender, String login, String password)
    *
    */
    @Test
    public void testCreateAccount() throws Exception {
        EntitiesFactory factory = getInstance();
        String firstName = "Ann";
        String secondName = "Smitt";
        GregorianCalendar birthDay = new GregorianCalendar(1998, 6, 5);
        String gender = "female";
        String login = "annsmitt@gmail.com";
        String password = "123456";
        account = factory.createAccount(firstName,secondName, birthDay, gender, login, password);
        assertNotNull("Аккаунт не проинициализирован", account);
        assertEquals("Различаются имена",account.getFirstName(),firstName);
        assertEquals("Различаются фамилии",account.getSecondName(),secondName);
        assertEquals("Различаются даты рождений",account.getBirthDay(), birthDay);
        assertEquals("Различается пол",account.getGender(),gender);
        assertEquals("Различаются логины",account.getLogin(),login);
        assertEquals("Различаются пароли",account.getPassword(),password);

    }

    /**
    *
    * Method: createAttachment(String name, int size, File file)
    *
    */
    @Test
    public void testCreateAttachment() throws Exception {
        EntitiesFactory factory = getInstance();
        String name = "newfile.txt";
        String s = File.separator;
        File file = new File(System.getProperty("user.dir") + s + "tests" + s + "com" + s + "company" + s + "resources" + s + name);
        file.createNewFile();
        int size = (int)file.length();
        Attachment attach = factory.createAttachment(name,size,file);
        assertNotNull("Вложение не проинициализировано", attach);
        assertEquals("Различаются имена",attach.getName(),name);
        assertEquals("Различаются размеры файлов",attach.getSize(),size);
        assertEquals("Различаются файлы",attach.getFile(), file);
        file.delete();
    }

    /**
    *
    * Method: createContact(String firstName, String secondName, Date birthDay, String gender, InternetAddress internetAddress)
    *
    */
    @Test
    public void testCreateContact() throws Exception {
        EntitiesFactory factory = getInstance();
        String firstName = "Ben";
        String secondName = "Williams";
        GregorianCalendar birthDay = new GregorianCalendar(1984, 12, 26);
        String gender = "male";
        InternetAddress internetAddress = new InternetAddress("benwill@gmail.com");
        Contact contact = factory.createContact(firstName,secondName, birthDay, gender, internetAddress);
        assertNotNull("Контакт не проинициализирован", contact);
        assertEquals("Различаются имена",contact.getFirstName(),firstName);
        assertEquals("Различаются фамилии",contact.getSecondName(),secondName);
        assertEquals("Различаются даты рождений",contact.getBirthDay(), birthDay);
        assertEquals("Различается пол",contact.getGender(),gender);
        assertEquals("Различаются электронные адреса",contact.getInternetAddress(), internetAddress);
    }

    /**
    *
    * Method: createFolder(String name, BigInteger idParent, BigInteger idAccount, String fullName)
    *
    */
    @Test
    public void testCreateFolder() throws Exception {
        EntitiesFactory factory = getInstance();
        String name = "favorite";
        BigInteger idParent = null;
        String firstName = "Ann";
        String secondName = "Smitt";
        GregorianCalendar birthDay = new GregorianCalendar(1998, 6, 5);
        String gender = "female";
        String login = "annsmitt@gmail.com";
        String password = "123456";
        account = factory.createAccount(firstName,secondName, birthDay, gender, login, password);
        BigInteger idAccount =  account.getId();
        String fullName = "inbox//favorite";
        Folder folder = factory.createFolder(name,idParent,idAccount,fullName);
        assertNotNull("Папка не проинициализирована", folder);
        assertEquals("Различаются имена папок",folder.getName(),name);
        assertEquals("Различаются родительские папки",folder.getIdParent(),idParent);
        assertEquals("Различаются аккаунты",folder.getIdAccount(), idAccount);
        assertEquals("Различаются полные имена папок",folder.getFullName(),fullName);
    }

    /**
    *
    * Method: createLetter(Date dateFrom, Address recipient, List<Address> sender, String subject, List<Address> carbonCopy, List<Address> blindCarbonCopy, String message, List<Attachment> listAttachment, BigInteger idFolder, boolean isRead)
    *
    */
    @Test
    public void testCreateLetter() throws Exception {
    //TODO
    }

    /**
    *
    * Method: createPerson(String firstName, String secondName, Date birthDay, String gender)
    *
    */
    @Test
    public void testCreatePerson() throws Exception {
        EntitiesFactory factory = getInstance();
        String firstName = "Ann";
        String secondName = "Smitt";
        GregorianCalendar birthDay = new GregorianCalendar(1998, 6, 5);
        String gender = "female";
        Person person  = factory.createPerson(firstName,secondName, birthDay, gender);
        assertNotNull("Персона не проинициализирована", person);
        assertEquals("Различаются имена",person.getFirstName(),firstName);
        assertEquals("Различаются фамилии",person.getSecondName(),secondName);
        assertEquals("Различаются даты рождений",person.getBirthDay(), birthDay);
        assertEquals("Различается пол",person.getGender(),gender);
    }


} 
