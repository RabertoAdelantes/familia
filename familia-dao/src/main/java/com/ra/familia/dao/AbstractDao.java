package com.ra.familia.dao;

import static com.ra.familia.dao.constants.TablesConstants.*;

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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.helpers.DaoHelper;

public abstract class AbstractDao<T> {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDao.class);

	public Set<T> fillBeans(ResultSet rs) throws SQLException {
		Set<T> types = new HashSet<T>();
		while (rs.next()) {
			T mediaBean = fillBean(rs);
			types.add(mediaBean);
		}
		return types;
	}

	public abstract T fillBean(ResultSet rs) throws SQLException;

	public abstract long addItem(T bean) throws DaoExeception;

	public abstract void updateItem(T bean) throws DaoExeception;

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
			LOG.error("getItemByFields:" + ex.getMessage());
			throw new DaoExeception(ex.getMessage());
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
				} else if (DaoHelper.isNumeric(obj)) {
					stmt.setInt(index + 1, DaoHelper.getInteger(obj.toString()));
				} else if (obj instanceof String) {
					if (!StringUtils.isEmpty(obj.toString())) {

						stmt.setString(index + 1,
								obj == null ? null : obj.toString());
					}
				} else if (obj instanceof Timestamp && obj != null) {
					stmt.setTimestamp(index + 1, (Timestamp) obj);
				} else 
				{
					stmt.setString(index + 1,
							obj == null ? null : obj.toString());
				}
			} catch (SQLException sqlex) {
				LOG.error(sqlex.getMessage());
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
		} catch (Exception ex) {
			LOG.error(String.format("Get all items error :%s", ex.getMessage()));
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return beans;
	}

	protected Timestamp getTimeStamp(String dateValue, String dtFormat) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
			Date parsedDate = dateFormat.parse(dateValue);
			timestamp = new Timestamp(parsedDate.getTime());
		} catch (ParseException ex) {
			LOG.error(String.format(
					"Date parse exception : %s. Date value %s. Date format %s",
					ex.getLocalizedMessage(), dateValue, dtFormat));
		}
		return timestamp;
	}

	protected void closePrepeareStatment(PreparedStatement preparedStatement)
			throws DaoExeception {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getMessage());
		}
	}

	protected void addPair(List<Pair<Integer, Object>> pairs,
			Pair<Integer, Object> pair) {
		if (pair.getKey() != null && pair.getValue() != null) {
			pairs.add(pair);
		}
	}

	protected void addPairWithNull(List<Pair<Integer, Object>> pairs,
			Pair<Integer, Object> pair) {
		pairs.add(pair);
	}

	protected static java.sql.Date getCurrentDate() {
		Date today = new Date();
		return new java.sql.Date(today.getTime());
	}

	protected long getPersonRelationSequence() {
		return getNextSequence(SEQ_PERSON_REF);
	}
	
	protected long getPersonSequence() {
		return getNextSequence(SEQ_PERSON);
	}

	protected long getTypesSequence() {
		return getNextSequence(SEQ_TYPES);
	}

	protected long getMediaSequence() {
		return getNextSequence(SEQ_MEDIA);
	}

	protected long getGroupSequence() {
		return getNextSequence(SEQ_GROUP);
	}

	protected long getPlaceSequence() {
		return getNextSequence(SEQ_PLACE);
	}

	protected long getMediaRefSequence() {
		return getNextSequence(SEQ_MEDIA_REF);
	}

	protected long getPlaceRefSequence() {
		return getNextSequence(SEQ_PLACE_REF);
	}

	protected long getConfirmSequence() {
		return getNextSequence(SEQ_CONFIRM_REF);
	}

	private long getNextSequence(String name) {
		long returnValue = 0;
		Connection conn = getConnection();
		String query = DaoHelper.getSeqQuery(conn.getClass().getName(), name);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet ress = stmt.executeQuery(query);
			if (ress.next()) {
				returnValue = ress.getLong("nextval");
			}
		} catch (SQLException sqex) {
			LOG.error("Get sequence value exception : ", sqex.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return returnValue;
	}
}
