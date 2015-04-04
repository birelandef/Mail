package com.company;

import com.company.core.EntitiesFactory;
import com.company.core.dao.FileDAO;
import com.company.core.factory.entities.Account;
import com.company.core.factory.entities.Contact;
import com.company.core.factory.entities.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) throws IOException {
        EntitiesFactory factory = EntitiesFactory.getInstance();
        Person person1 = factory.createPerson("Mike", "Grey", new GregorianCalendar(1982, 5, 5), "male", new ArrayList<Account>(),
                new ArrayList<Contact>() );
        Person person2 = factory.createPerson("John", "MacWizard", new GregorianCalendar(1989, 4, 2), "male", new ArrayList<Account>(),
                new ArrayList<Contact>() );
        FileDAO dao = new FileDAO();
        dao.create(person1);
        System.out.println(person1.getId());
        dao.create(person2);
        person1 = (Person) dao.findById(person1.getId());
        person2 = (Person) dao.findById(person2.getId());
        System.out.println(person2.getFirstName());

//        String name = "newfile.txt";
//        String s = File.separator;
//        File file = new File(System.getProperty("user.dir") + s + "tests" + s + "com" + s + "company" + s + "resources" + s + name);
////        file.createNewFile();
//        try {
//            person.serialized(new FileOutputStream(file));
//            person = (Person) person.deserialized(new FileInputStream(file));
//            System.out.println(person.getFirstName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
