package com.company.core.dao.EntityDBDAO;

import com.company.api.EntityFactoryInterface;
import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Attachment;
import com.company.core.entity.Letter;
import com.company.core.factory.EntityFactoryImpl;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class LetterDAO extends DataBaseDAO<Letter> {
    @Override
    protected PreparedStatement getAddEntityQuery(Letter entity) throws SQLException {
        PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO LETTER VALUES (?,?,?,?,?,?,?,?,?)");
        PreparedStatement paramsStatement2 = dataBase.connection.prepareStatement("SELECT id FROM ACCOUNT WHERE idPerson = '" + entity.getidPerson() +  "'");
        ResultSet resultSet = paramsStatement2.executeQuery();
        resultSet.next();
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setBoolean(2, entity.isSeen());
        paramsStatement.setString(3, entity.getSubject());
        paramsStatement.setString(4, entity.getMessage());
        paramsStatement.setDate(5, entity.getDate());
        paramsStatement.setString(6, entity.getIdFolder());
        paramsStatement.setString(7, resultSet.getString("id"));
        paramsStatement.setString(8, entity.getidPerson());
        paramsStatement.setString(9, entity.getFromWhom());
        return paramsStatement;
    }

    @Override
    public Collection<Letter> getAllEntity(Class<Letter> entityClass) {
        Collection<Letter> collection = new ArrayList<Letter>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                List<String> toWhom = getToWhom(resultSet.getString("id"),"RECIPIENT");
                List<String> copy = getToWhom(resultSet.getString("id"),"COPY");
                List<Attachment> attachments =getAttachments(resultSet.getString("id"));
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Letter letter = factory.createLetter(resultSet.getString("idPerson"), resultSet.getString("idFolder"),
                        resultSet.getBoolean("isSeen"),
                        resultSet.getString("fromWhom"),
                        toWhom, copy,resultSet.getString("subject"),
                        resultSet.getString("message"),attachments,resultSet.getDate("DATEFROM"));
                letter.setId(resultSet.getString("id"));
                collection.add(letter);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }

    private List<String> getToWhom(String idLetter, String tableName) throws SQLException {
        List<String> listRec = new ArrayList<>();
        paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + tableName + " WHERE idLetter = '" + idLetter + "'" );
        ResultSet resultSet = paramsStatement.executeQuery();
        while (resultSet.next()) {
            listRec.add(resultSet.getString("IDCONTACT"));
        }
        return listRec;
    }

    private List<Attachment> getAttachments(String idLetter) throws SQLException {
        List<Attachment> attachmentList = new ArrayList<>();
//        paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM ATTACHMENT WHERE idLetter = '" + idLetter + "'" );
//        ResultSet resultSet = paramsStatement.executeQuery();
//        while (resultSet.next()) {
//            EntityFactoryInterface factory = EntityFactoryImpl.getInstance();
//            //TODO  как привести BLOB к байтам?
//            Blob files = resultSet.getBlob("FILES");
//            Attachment attachment = factory.createAttachment(resultSet.getString("NAME"), files.getBytes(0, (int) files.length()),
//                    resultSet.getString("IDLETTER"), resultSet.getString("IDFOLDER"),resultSet.getString("IDACCOUNT"),
//                    resultSet.getString("IDPERSON"));
//            attachmentList.add(attachment);
//        }
        return attachmentList;
    }
}
