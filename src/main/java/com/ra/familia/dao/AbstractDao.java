package com.ra.familia.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.exceptions.DaoExeception;

public abstract class AbstractDao<T> {
	private static final String SELECT_NEXTVAL_SEQ_PERSON = "SELECT nextval('seq_person')";
	private static final String SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL = "select seq_person.nextval from dual";
	private static final String MM_DD_YYYY = "mm/dd/yyyy";
	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDao.class);

	private static final String POSTGRESS_TYPE = "org.postgresql.jdbc4.Jdbc4Connection";
	private static final String ORACLE_TYPE = "oracle.jdbc.driver.T4CConnection";
	
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
					FileInputStream fis = (FileInputStream) obj;
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
			LOG.error(String.format("Get all items error :{'s'}",
					e.getLocalizedMessage()));
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return beans;
	}

	protected Timestamp getTimeStamp(String dateValue) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					MM_DD_YYYY);
			Date parsedDate = dateFormat.parse(dateValue);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException ex) {
			LOG.error("Date parse exception : ",ex.getLocalizedMessage());
		}
		return timestamp;
	}

	protected long getNextSequence() {
		long returnValue = 0;
		Connection conn = getConnection();
		String query = getType(conn.getClass().getName());
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet ress = stmt.executeQuery(query);
			if (ress.next())
			{
		      returnValue = ress.getLong("nextval");
			}
		} catch (SQLException sqex) {
			LOG.error("Get sequence value exception : ",sqex.getLocalizedMessage());
		}
		finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return returnValue;
	}
	
	private String getType(String connectionType) {
		String query = SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL;
		switch (connectionType) {
		case POSTGRESS_TYPE:
			query = SELECT_NEXTVAL_SEQ_PERSON;
			break;
		case ORACLE_TYPE:
			query = SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL;
			break;
		}
		return query;
	}
}
