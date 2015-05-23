package com.company.core.dao.EntityDBDAO;

import com.company.core.dao.DataBaseDAO;
import com.company.core.entity.Account;
import com.company.core.entity.Contact;
import com.company.core.factory.EntityFactoryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Sophie
 * @date 14.05.2015.
 */
public class AccountDAO extends DataBaseDAO<Account> {

    @Override
    protected PreparedStatement getAddEntityQuery(Account entity) throws SQLException {
        PreparedStatement paramsStatement = dataBase.connection.prepareStatement("INSERT INTO ACCOUNT VALUES (?,?,?,?,?,?)");
        paramsStatement.setString(1, entity.getId());
        paramsStatement.setString(2, entity.getEmail());
        paramsStatement.setString(3, entity.getPassword());
        paramsStatement.setString(4, entity.getOutgoingMailServer());
        paramsStatement.setString(5, entity.getIncomingMailServer());
        paramsStatement.setString(6, entity.getIdPerson());
        return paramsStatement;
    }


    @Override
    public Collection<Account> getAllEntity(Class<Account> entityClass) {
        Collection<Account> collection = new ArrayList<>();
        try {
            paramsStatement = dataBase.connection.prepareStatement("SELECT *  FROM " + entityClass.getSimpleName());
            ResultSet resultSet = paramsStatement.executeQuery();
            while (resultSet.next()) {
                EntityFactoryImpl factory = (EntityFactoryImpl) EntityFactoryImpl.getInstance();
                Account account = factory.createAccount(resultSet.getString("email"),resultSet.getString("password"),
                        resultSet.getString("outgoingMailServer"),resultSet.getString("incomingMailServer"),resultSet.getString("idPerson"));
                account.setId(resultSet.getString("id"));
                collection.add(account);
            }
        } catch (SQLException e) {
            logger.error("Can't delete record ", e);
            e.printStackTrace();
        }
        return collection;
    }
}
