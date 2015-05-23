//package com.company.core.dao;
//
//import com.company.core.entity.Contact;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * @author Sophie
// * @date 10.05.2015.
// */
//public class DataBaseDAOTest {
//    private static DataBaseDAO<Contact> dataBaseDAO;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        dataBaseDAO = new DataBaseDAO<>();
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testAddEntity() throws Exception {
//        Contact contact = new Contact("email@yandex.ru", "Ksusha", "Sedova");
//
//        dataBaseDAO.addEntity(contact);
//    }
//
//    @Test
//    public void testGetAllEntity() throws Exception {
//
//    }
//
//    @Test
//    public void testFindEntityById() throws Exception {
//
//    }
//
//    @Test
//    public void testUpdateEntity() throws Exception {
//
//    }
//
//    @Test
//    public void testRemoveEntity() throws Exception {
//
//    }
//}