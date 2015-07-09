package com.ra.familia.dao;

import java.sql.*;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {
	private static final Logger LOG = LoggerFactory
			.getLogger(ConnectionManager.class);

	private static Properties properties = PropertiesManager.getJdbcProperties();
	private static Connection con;

	public static Connection getConnection() {
		return getDirectConnection();
	}

	public static Connection getDirectConnection() {
		try {
			Class.forName(properties.getProperty("JDBCDRIVER"));
			try {
				con = DriverManager.getConnection(
						properties.getProperty("DB_URL"),
						properties.getProperty("USER"),
						properties.getProperty("PASSWORD"));
			} catch (SQLException ex) {
				LOG.error(ex.getLocalizedMessage());
			}
		} catch (ClassNotFoundException e) {
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