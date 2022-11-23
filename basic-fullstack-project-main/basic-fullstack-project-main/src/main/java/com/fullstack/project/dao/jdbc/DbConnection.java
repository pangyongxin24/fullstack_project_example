package com.fullstack.project.dao.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fullstack.project.exceptions.Exceptions.DbException;

/*
 * Provides a connection to the database.
 */
public class DbConnection {

    public static Connection getConnection() {
	String url = "jdbc:mariadb://localhost:3306/people_db";
	String user = "root";
	String passwd = "abcd1234.";

	try {
	    Connection conn = DriverManager.getConnection(url, user, passwd);
	    return conn;
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }

    public static void printConnectionDetails() {
	try (Connection conn = getConnection()) {
	    DatabaseMetaData metadata = conn.getMetaData();
	    System.out.printf("Product name: %s\n", metadata.getDatabaseProductName());
	    System.out.printf("Database version: %s\n", metadata.getDatabaseMajorVersion());
	    System.out.printf("Driver name: %s\n", metadata.getDriverName());
	    System.out.printf("Driver version: %s\n", metadata.getDriverVersion());
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }
}
