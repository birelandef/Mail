package com.company.core.dao;

import com.company.core.EntitiesFactory;
import com.company.core.factory.entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

/** 
* MemoryDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>��� 28, 2015</pre> 
* @version 1.0 
*/ 
public class MemoryDAOTest {

    MemoryDAO memory;

@Before
public void beforeClass() throws Exception {
    memory = new MemoryDAO();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Map<String, String> map) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    EntitiesFactory factory = EntitiesFactory.getInstance();
    String firstName = "Ann";
    String secondName = "Smitt";
    GregorianCalendar birthDay = new GregorianCalendar(1998, 6, 5);
    String gender = "female";
    Person person  = factory.createPerson(firstName,secondName, birthDay, gender);
    memory.create(person);
    //TODO
//    assertEquals("Различаются имена",person.getFirstName(),firstName);
//    assertEquals("Различаются фамилии",person.getSecondName(),secondName);
//    assertEquals("Различаются даты рождений",person.getBirthDay(), birthDay);
//    assertEquals("Различается пол",person.getGender(),gender);
} 

/** 
* 
* Method: update(Generator entity) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(BigInteger id) 
* 
*/ 
@Test
public void testRemove() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByID(BigInteger id) 
* 
*/ 
@Test
public void testFindByID() throws Exception { 
//TODO: Test goes here... 
} 


} 
