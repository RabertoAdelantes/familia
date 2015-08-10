package com.ra.familia.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.exceptions.DaoExeception;

public abstract class AbstractDao<T> {
	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDao.class);

	abstract Set<T> fillBeans(ResultSet rs) throws SQLException;

	abstract void addItem(T bean) throws DaoExeception;

	abstract void updateItem(T bean);
	
	protected Connection getConnection() {
		return ConnectionManager.getConnection();
	}

	protected void closeConnection(final Connection conn) {
		ConnectionManager.closeConnection(conn);
	}

	protected void closeStatement(final Statement stmt) {
		ConnectionManager.closeStatement(stmt);
	}

	protected Set<T> getItemByFields(final String sqlQuery, final String where,
			final List<Pair<Integer, Object>> pairs) throws DaoExeception {
		Connection conn = null;
		PreparedStatement stmt = null;
		Set<T> beans = new HashSet<T>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sqlQuery + where);

			fillStatmentParameters(pairs, stmt);
			ResultSet rs = stmt.executeQuery();
			beans = fillBeans(rs);
			rs.close();
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage());
			throw new DaoExeception(ex.getLocalizedMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return beans;
	}

	protected void fillStatmentParameters(
			final List<Pair<Integer, Object>> pairs, PreparedStatement stmt) {
		for (int index = 0; index < pairs.size(); index++) {
			Object obj = pairs.get(index).getValue();
			try {
				if (obj instanceof FileInputStream) {
					FileInputStream fis = (FileInputStream)obj;
					stmt.setBinaryStream(index + 1, fis);
				} else {
					stmt.setString(index + 1, obj.toString());
				}
			} catch (SQLException sqlex) {
				LOG.error(sqlex.getLocalizedMessage());
			}
		}
	}

	protected T getItemByField(final String sqlQuery, final String where,
			final List<Pair<Integer, Object>> pairs) throws DaoExeception {
		Set<T> beans = getItemByFields(sqlQuery, where, pairs);
		T bean = null;
		if (!beans.isEmpty()) {
			bean = beans.iterator().next();
		}
		return bean;
	}

	protected Collection<T> getAllItems(String sqlQuery) {
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
			LOG.error(String.format("Get all items error :{'s'}",e.getLocalizedMessage()));
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return beans;
	}

}
