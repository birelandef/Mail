package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Attachment;
import com.company.core.entity.Contact;
import com.company.core.factory.EntityFactoryImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Sophie
 * @date 16.05.2015.
 */
public class AttachmentDAO extends DataBaseDAO<Attachment> {
    @Override
    protected PreparedStatement getAddEntityQuery(Attachment entity) throws SQLException {
        PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO ATTACHMENT VALUES (?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getName());
        //TODO привести типы
        paramsStatement.setBytes(3, new byte[2]);
//        paramsStatement.setBlob(3, new ByteArrayInputStream(entity.getFiles()));
        paramsStatement.setString(4, entity.getIdLetter());
        paramsStatement.setString(5, entity.getIdFolder());
        paramsStatement.setString(6, entity.getIdAccount());
        paramsStatement.setString(7, entity.getIdPerson());
        return paramsStatement;
    }

    @Override
    public Collection<Attachment> getAllEntity(Class<Attachment> entityClass) {
        Collection<Attachment> collection = new ArrayList<>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Attachment attachment = factory.createAttachment("picture", new byte[2],resultSet.getString("IDLETTER"), resultSet.getString("IDFOLDER"),
                        resultSet.getString("IDACCOUNT"),resultSet.getString("IDPERSON"));
                attachment.setId(resultSet.getString("id"));
                collection.add(attachment);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }
}
