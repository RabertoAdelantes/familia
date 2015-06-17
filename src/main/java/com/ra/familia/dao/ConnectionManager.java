package com.ra.familia.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	// TODO: move to property file
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/db";
	public static final String USER = "roberto_adelantes";
	public static final String PASSWORD = "password";
	public static final String JDBCDRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static Connection con;

	public static Connection getConnection() {
		// return getDirectConnection();
		return getJndiConnection();
	}

	public static Connection getDirectConnection() {
		try {
			Class.forName(JDBCDRIVER);
			try {
				con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getJndiConnection() {
		Context initContext = null;
		Connection dbCon = null;
		try {
			initContext = new InitialContext();
			Context webContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) webContext.lookup("jdbc/oradb");
			dbCon = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbCon;
	}

	public static void closeConnection(final Connection conn) {
		try {
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void closeStatement(final Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();

			}
		}
	}
}