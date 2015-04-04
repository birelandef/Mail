package com.company.core.dao;

import com.company.core.api.Constants;
import com.company.core.factory.entities.*;
import org.apache.log4j.Logger; // Счастье

import java.io.*;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Sophie on 25.03.2015.
 */
public class FileDAO<T extends Entity> extends MemoryDAO<T> {

    private static final Logger log = Logger.getLogger(FileDAO.class);

    private static File filePerson = new File(Constants.PATHTORESOURCES + "Person" + ".mail");
    private static File fileAccount = new File(Constants.PATHTORESOURCES + "Account" + ".mail");
    private static File fileContact = new File(Constants.PATHTORESOURCES + "Contact" + ".mail");
    private static File fileFolder = new File(Constants.PATHTORESOURCES + "Folder" + ".mail");
    private static File fileLetter = new File(Constants.PATHTORESOURCES + "Letter" + ".mail");
    private static File fileAttachment = new File(Constants.PATHTORESOURCES + "Attachment" + ".mail");

    private static File[] files = {filePerson, fileAccount, fileContact, fileFolder, fileLetter, fileAttachment};

    public FileDAO() throws IOException {
        new File(Constants.PATHTORESOURCES).mkdirs();
        for (File f: files){
            f.createNewFile();
        }
    }

    @Override
    public BigInteger create(T entity) {
        BigInteger id = super.create(entity);
        File outFile = null;
        try {
            switch (entity.getClass().getSimpleName()){
                case "Person": outFile = filePerson;
                                break;
                case "Account": outFile = fileAccount;
                                break;
                case "Contact": outFile = fileContact;
                                break;
                case "Folder": outFile = fileFolder;
                                break;
                case "Letter": outFile = fileLetter;
                                break;
                case "Attachment": outFile = fileAttachment;
                                break;
                default: throw new InvalidObjectException("Type mismatch");
            }
            serialized(entity, new FileOutputStream(outFile));
        } catch (IOException e) {
            log.error("Error has occurred ", e);
        }
        return id;
    }

    @Override
    public void update(BigInteger id, Map<String, Object> parameters) throws IllegalAccessException {
        super.update(id, parameters);

    }

    @Override
    public boolean delete(BigInteger id) {
        T delObj = findById(id);

        return false;
    }

    @Override
    public T findById(BigInteger id) {
        read();
        return super.findById(id);
    }

    /**
     * Read info about all instances of entities from files
     */
    private void read(){
        Person p = null;
        Account ac = null;
        Attachment at = null;
        Folder fol  = null;
        Contact c = null;
        Letter l = null;
        String nameFile = null;
        for (File f: files){
            if (f.length() != 0){
                nameFile = f.getName().substring(0, f.getName().lastIndexOf("."));
                try {
                    switch (nameFile){
                        case "Person":
                            p  =  deserialized(new FileInputStream(filePerson));
                            persons.put(p.getId(), p);
                            break;
                        case "Account":
                            ac = deserialized(new FileInputStream(fileAccount));
                            accounts.put(ac.getId(), ac);
                            break;
                        case "Attachment":
                            at  =  deserialized(new FileInputStream(fileAttachment));
                            attachments.put(at.getId(), at);
                            break;
                        case "Folder":
                            fol  =  deserialized(new FileInputStream(fileFolder));
                            folders.put(fol.getId(), fol);
                            break;
                        case "Contact":
                            c  =  deserialized(new FileInputStream(fileContact));
                            contacts.put(c.getId(), c);
                            break;
                        case "Letter":
                            l  =  deserialized(new FileInputStream(fileLetter));
                            letters.put(l.getId(), l);
                            break;

                    }
                    //Эти ошибки вряд ли выпадут
                } catch (IOException e) {
                    log.error(e);
                } catch (ClassNotFoundException e) {
                    log.error(e);
                }
            }
        }
    }

    public void serialized(Object obj, FileOutputStream out) throws IOException {
        try (ObjectOutputStream oout = new ObjectOutputStream(out)){
            oout.writeObject(obj);
        }
    }

    public  <T extends Entity> T deserialized(FileInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = null;
        try{
            oin = new ObjectInputStream(in);
            T obj  = (T) oin.readObject();
            return  obj;
        } finally {
            oin.close();
            in.close();

        }
    }


//    public FileDAO() {
//        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
//        System.out.println("name " + serialFile.getName());
//        tClass = entity.getClass();
//        try {
//            serialFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    @Override
//    public BigInteger create(T entity) throws IOException {
//        serialFile = new File(System.getProperty("user.dir") + separator +  "resources" + separator + entity.getClass().getSimpleName() + ".mail");
//        System.out.println("name " + serialFile.getName());
//        tClass = entity.getClass();
//        serialFile.createNewFile();
//
//        FileOutputStream outputStream = new FileOutputStream(serialFile, true);//true для дописывания файла
//        entity.serialized(outputStream);
//        outputStream.close();
//        return entity.getId();
//    }
//    /*
//    * для того, чтобы обновить данные о любом экземпляре сущности, перезапишем файл полностью
//     */
//    @Override
//    public void update(T entity) throws IOException {
//        if (read(entity.getId()) != null){
//            delete(entity.getId());
//        }
//        create(entity);

//    }
//
//    @Override
//    public boolean delete(BigInteger id) {
//        //TODO заглушка
//        return false;
//    }
//
//    @Override
//    public T read(BigInteger id)  {
//        T obj = null;
//        try {
//            FileInputStream fis = new FileInputStream(serialFile);
//            obj = (T) tClass.newInstance();
//           while (obj.getId() != id){
//                obj = obj.deserialized(fis);
//            }
//            fis.close();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }

}
