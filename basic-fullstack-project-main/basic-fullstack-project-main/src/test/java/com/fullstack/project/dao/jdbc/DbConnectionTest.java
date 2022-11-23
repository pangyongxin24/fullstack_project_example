package com.fullstack.project.dao.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * Test cases for the {@code DbConnection} class.
 */
public class DbConnectionTest {

    @Test
    public void getConnection() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    assertNotNull(conn);
	    conn.close();
	} catch (Exception ex) {
	    fail();
	}
	System.out.print("Test cases for country Dao");
    }
}
