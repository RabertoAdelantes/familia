package com.ra.familia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T> {
	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDao.class);
	
	abstract Set<T> fillBeans(ResultSet rs) throws SQLException;

	abstract void addItem(T bean);

	abstract void updateItem(T bean);

	public Connection getConnection() {
		return ConnectionManager.getConnection();
	}

	public void closeConnection(final Connection conn) {
		ConnectionManager.closeConnection(conn);
	}

	public void closeStatement(final Statement stmt) {
		ConnectionManager.closeStatement(stmt);
	}
	
	public Set<T> getAllItems(String sqlQuery) {
		Connection conn = null;
		Statement stmt = null;
		Set<T> beans = new HashSet<T>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			beans = fillBeans(rs);
			rs.close();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return beans;
	}
	
	public T getItemByField(final String sqlQuery, final String fieldName,final String fieldValue) {
		Connection conn = null;
		PreparedStatement stmt = null;
		T bean = null;
		Set<T> beans = new HashSet<T>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sqlQuery
					+ " where " +fieldName+" =?");
			stmt.setString(1, fieldValue);
			ResultSet rs = stmt.executeQuery();
			beans = fillBeans(rs);
			rs.close();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		if (!beans.isEmpty()) {
			bean = beans.iterator().next();
		}
		return bean;
	}

	public Set<T> getItemsByField(final String sqlQuery, final String fieldName,final String fieldValue) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Set<T> persons = new HashSet<T>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sqlQuery
					+ " where "+fieldName+" like ?");
			stmt.setString(1, "%"+fieldValue+"%");
			ResultSet rs = stmt.executeQuery();
			persons = fillBeans(rs);
			rs.close();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return persons;
	}
	
}
