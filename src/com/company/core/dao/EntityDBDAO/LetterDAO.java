package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Attachment;
import com.company.core.entity.Letter;
import com.company.core.factory.EntityFactoryImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import static com.company.core.dao.DBHelper.freeResources;
import static com.company.core.dao.DBHelper.getConnection;

/**Class for special operation with Letters such as getAllEntity and addEntity
 * @author Sophie
 * @date 15.05.2015.
 */

//TODO доделать добавление  Attachments и прочего, проверить Person на idPerson(в хэш-мапах)
public class LetterDAO extends DataBaseDAO<Letter> {

    private static Logger logger = Logger.getLogger(AttachmentDAO.class);


    @Override
    public void addEntity(Letter entity) {
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        try {
            paramsStatement = getAddEntityQuery(entity, connection);
            paramsStatement.executeUpdate();
            insertAllRecipient(connection, entity, "RECIPIENT");
            insertAllRecipient(connection, entity, "COPY");
        } catch (SQLException e) {
            logger.error("Can't add record ", e);
        } finally {
            freeResources(connection, paramsStatement);
        }
    }

    private static void insertAllRecipient(Connection connection, Letter letter, String tableName) throws SQLException {
        List<String> recipients = null;
        PreparedStatement preparedStatement = null;
        if (tableName.equals("RECIPIENT")){
            recipients = letter.getToWhom();
        } else{
            recipients = letter.getCopy();
        }
        try {
        ListIterator<String> iter  = recipients.listIterator();
        while (iter.hasNext()){
                String recipient = iter.next();
                preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?,?)");
                preparedStatement.setString(1, letter.getId());
                preparedStatement.setString(2, letter.getIdFolder());
                preparedStatement.setString(3, letter.getIdAccount());
                preparedStatement.setString(4, letter.getIdPerson());
                preparedStatement.setString(5, recipient);
                preparedStatement.executeUpdate();
            }
        }finally {
            preparedStatement.close();
        }
    }



    @Override
    protected PreparedStatement getAddEntityQuery(Letter entity, Connection connection) throws SQLException {
        PreparedStatement paramsStatement = connection.prepareStatement("INSERT INTO LETTER VALUES (?,?,?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setBoolean(2, entity.isSeen());
        paramsStatement.setString(3, entity.getSubject());
        paramsStatement.setString(4, entity.getMessage());
        paramsStatement.setDate(5, entity.getDate());
        paramsStatement.setString(6, entity.getIdFolder());
        paramsStatement.setString(7, entity.getIdAccount());
        paramsStatement.setString(8, entity.getIdPerson());
        paramsStatement.setString(9, entity.getFromWhom());
        return paramsStatement;
    }

    @Override
    public Collection<Letter> getAllEntity(Class<Letter> entityClass) {
        Collection<Letter> collection = new ArrayList<Letter>();
        Connection connection = getConnection();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM LETTER");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                List<String> toWhom = getToWhom(connection, resultSet.getString("id"),"RECIPIENT");
                List<String> copy = getToWhom(connection, resultSet.getString("id"), "COPY");
                List<Attachment> attachments = getAttachments(connection,factory, resultSet.getString("id"));

                String idPerson = resultSet.getString("idPerson");
                String idFolder = resultSet.getString("idFolder");
                String idAccount = resultSet.getString("idAccount");
                Boolean isSeen = resultSet.getBoolean("isSeen");
                String fromWhom = resultSet.getString("fromWhom");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("message");
                Date date = resultSet.getDate("DATEFROM");

                Letter letter = factory.createLetter(idPerson, idFolder, idAccount, isSeen, fromWhom, toWhom,
                        copy, subject, message, attachments, date);
                letter.setId(resultSet.getString("id"));
                collection.add(letter);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }

    private List<String> getToWhom(Connection connection, String idLetter, String tableName) throws SQLException {
        List<String> listRec = new ArrayList<>();
        PreparedStatement paramsStatement = null;
        try{
            paramsStatement = connection.prepareStatement("SELECT *  FROM " + tableName + " WHERE idLetter = '" + idLetter + "'");
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                listRec.add(resultSet.getString("IDCONTACT"));
            }
        } finally {
            paramsStatement.close();
        }
        return listRec;
    }

    private List<Attachment> getAttachments(Connection connection, EntityFactoryImpl factory, String idLetter) throws SQLException {
        List<Attachment> attachmentList = new ArrayList<>();
        PreparedStatement paramsStatement = null;
        ResultSet resultSet = null;
        try {
            paramsStatement = connection.prepareStatement("SELECT *  FROM ATTACHMENT WHERE idLetter = '" + idLetter + "'");
            resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                //TODO  как привести BLOB к байтам?
                Blob files = resultSet.getBlob("FILES");
                String name = resultSet.getString("NAME");
                String idFolder = resultSet.getString("IDFOLDER");
                String idAccount = resultSet.getString("IDACCOUNT");
                String idPerson = resultSet.getString("IDPERSON");
                Attachment attachment = factory.createAttachment(name, files.getBytes(0, (int) files.length()),idLetter, idFolder, idAccount, idPerson);
                attachmentList.add(attachment);
            }
        } finally {
            freeResources(paramsStatement,resultSet);
        }
        return attachmentList;
    }
}
