package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Folder;
import com.company.core.factory.EntityFactoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Sophie
 * @date 15.05.2015.
 */
public class FolderDAO extends DataBaseDAO<Folder> {

    @Override
    protected PreparedStatement getAddEntityQuery(Folder entity) throws SQLException {
        PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO FOLDER VALUES (?,?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getName());
        paramsStatement.setString(3, entity.getIdParentFolder());
        paramsStatement.setBoolean(4, entity.isSystemFolder());
        paramsStatement.setString(5, entity.getDescription());
        paramsStatement.setString(6, entity.getIdAccount());
        paramsStatement.setString(7, entity.getIdPerson());
        return paramsStatement;
    }

    @Override
    public Collection<Folder> getAllEntity(Class<Folder> entityClass) {
        Collection<Folder> collection = new ArrayList<Folder>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Folder folder = factory.createFolder(resultSet.getString("idAccount"), resultSet.getString("name"),
                        resultSet.getString("idParentFolder"), resultSet.getString("idPerson"),resultSet.getBoolean("isSystemFolder"),
                        resultSet.getString("description"));
                folder.setId(resultSet.getString("id"));
                collection.add(folder);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }
}
