package com.ra.familia.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {
	// TODO: move to property file
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);

	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/db";
	public static final String USER = "roberto_adelantes";
    public static final String PASSWORD = "password";

	public static final String JDBCDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static Connection con;

	public static Connection getConnection() {
		return getDirectConnection();
	}

	public static Connection getDirectConnection() {
		try {
			Class.forName(JDBCDRIVER);
			try {
				con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			} catch (SQLException ex) {
				LOG.error(ex.getLocalizedMessage());
			}
		} catch (ClassNotFoundException e) {
			LOG.error(e.getLocalizedMessage());
		}
		return con;
	}

	public static Connection getJndiConnection() {
		Context initContext = null;
		Connection con = null;
		try {
			initContext = new InitialContext();
			Context webContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) webContext.lookup("jdbc/oradb");
			con = ds.getConnection();
		} catch (NamingException e) {
			LOG.error(e.getLocalizedMessage());
		} catch (SQLException e) {
			LOG.error(e.getLocalizedMessage());
		}
		return con;
	}

	public static void closeConnection(final Connection conn) {
		try {
			conn.close();
		} catch (SQLException se) {
			LOG.error(se.getLocalizedMessage());
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				LOG.error(se.getLocalizedMessage());
			}
		}
	}

	public static void closeStatement(final Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException se) {
			LOG.error(se.getLocalizedMessage());
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				LOG.error(se2.getLocalizedMessage());
			}
		}
	}
}