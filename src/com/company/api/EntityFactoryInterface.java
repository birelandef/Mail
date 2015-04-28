package com.company.api;

import com.company.core.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public interface EntityFactoryInterface {

    /**
     *
     * @param login
     * @param password
     * @param name
     * @param surname
     * @param gender
     * @param birthday
     * @param country
     * @param city
     * @param info
     * @param mailboxes
     * @param contacts
     * @return
     */
    Person createPerson(String login, String password, String name, String surname, Gender gender, Date birthday,
                        String country, String city, String info, Map<String, Account> mailboxes,
                        Map<String, Contact> contacts);

    /**
     *
     * @param email
     * @param password
     * @param outgoingMailServer
     * @param incomingMailServer
     * @return
     */
    Account createAccount(String email, String password, String outgoingMailServer, String incomingMailServer);

    Contact createContact(String email, String name, String surname);

    /**
     *
     * @param idAccount
     * @param name
     * @param idParentFolder
     * @param idPerson
     * @param isSystemFolder
     * @param description
     * @return
     */
    Folder createFolder(String idAccount, String name, String idParentFolder, String idPerson, boolean isSystemFolder,
                        String description);

    /**
     *
     * @param person
     * @param idFolder
     * @param seen
     * @param fromWhom
     * @param toWhom
     * @param copy
     * @param subject
     * @param message
     * @param attachments
     * @param date
     * @return
     */
    Letter createLetter(String person, String idFolder, boolean seen, String fromWhom, List<String> toWhom,
                        ArrayList<String> copy, String subject, String message, List<Attachment> attachments, Date date);

    /**
     *
     * @param name
     * @param file
     * @return
     */
    Attachment createAttachment(String name, byte[] file);
}