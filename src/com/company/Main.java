package com.company;

import com.company.core.EntitiesFactory;
import com.company.core.dao.FileDAO;
import com.company.core.entities.Person;

import java.io.IOException;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) throws IOException {
        EntitiesFactory factory = EntitiesFactory.getInstance();
        Person person = factory.createPerson("Mike", "Grey", new GregorianCalendar(1982, 5, 5), "male");

        FileDAO dao = new FileDAO();
        dao.create(person);
        person = (Person) dao.findByID(BigInteger.valueOf(12));
        System.out.println(person.getFirstName());

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
