package com.company.core.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 10.05.2015.
 */
public class ConnectionwithDBTest {

    @Test
    public void testGetInstance() throws Exception {
        ConnectionwithDB dataBase = new ConnectionwithDB();
        assertNotNull("DataBase is null",dataBase);
    }
}
