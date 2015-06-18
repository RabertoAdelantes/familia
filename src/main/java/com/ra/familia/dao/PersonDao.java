package com.ra.familia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;

import com.ra.familia.entities.PersonBean;

public class PersonDao extends AbstractDao<PersonBean> {
	private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);

	private static final String TABLE = "PERSON".intern();
	private static final String SELECT = "SELECT * FROM " + TABLE;
	private static final String INSERT = "INSERT INTO "
			+ TABLE
			+ " (FIRST_NAME, MIDLE_NAME, LAST_NAME, LAST_NAME2, PASSWORD, EMAIL, DATE_BIRTH, DATE_DEATH, ISACTIVE, ISDELETED, PK) VALUES (?,?,?,?,?,?,?,?,?,?,seq_person.NEXTVAL)";
	private static final String UPDATE = "UPDATE " + TABLE + " SET ";
	private static final Object WHERE = " WHERE ";

	public PersonDao() {

	}

	Set<PersonBean> getAllItems() {
		return getAllItems(SELECT);
	}

	public PersonBean getItemByName(String name) {
		return getItemByField(SELECT, TABLE + ".FIRST_NAME", name);
	}

	public Set<PersonBean> getItemsByName(String name) {
		return getItemsByField(SELECT, TABLE + ".FIRST_NAME", name);
	}

	private PersonBean fillBean(final ResultSet rs) throws SQLException {
		PersonBean person = new PersonBean();
		person.setFirst_name(rs.getString("FIRST_NAME"));
		person.setSecond_name(rs.getString("LAST_NAME"));
		person.setMidle_name(rs.getString("MIDLE_NAME"));
		person.setPassword(rs.getString("PASSWORD"));
		return person;
	}

	@Override
	public Set<PersonBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<PersonBean> persons = new HashSet<PersonBean>();
		while (rs.next()) {
			PersonBean person = fillBean(rs);
			persons.add(person);
		}
		return persons;
	}

	@Override
	void addItem(PersonBean bean) {
		Connection conn = getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
			preparedStatement.setString(1, bean.getFirst_name());
			preparedStatement.setString(2, bean.getMidle_name());
			preparedStatement.setString(3, bean.getSecond_name());
			preparedStatement.setString(4, bean.getSecond_name());
			preparedStatement.setString(5, bean.getPassword());
			preparedStatement.setString(6, bean.getEmail());
			preparedStatement.setTimestamp(7, getCurrentTimeStamp());
			preparedStatement.setTimestamp(8, getCurrentTimeStamp());
			preparedStatement.setBoolean(9, false);
			preparedStatement.setBoolean(10, false);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
		}
	}

	@Override
	void updateItem(PersonBean bean) {
		Connection conn = getConnection();
		try {
			StringBuffer updateSql = new StringBuffer();
			updateSql.append(UPDATE);
			StringBuffer conditions = new StringBuffer();
			addCondition(conditions, "FIRST_NAME", bean.getFirst_name());
			addCondition(conditions, "LAST_NAME", bean.getSecond_name());
			addCondition(conditions, "MIDLE_NAME", bean.getMidle_name());
			addCondition(conditions, "PASSWORD", bean.getPassword());
			addCondition(conditions, "EMAIL", bean.getEmail());
			addBooleanCondition(conditions, "ISACTIVE", bean.isActive());
			addBooleanCondition(conditions, "ISDELETED", bean.isActive());

			updateSql.append(conditions.toString());
			updateSql.append(WHERE);
			updateSql.append(TABLE + ".pk=" + bean.getID());
			PreparedStatement preparedStatement = conn
					.prepareStatement(updateSql.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
		}
	}

	private void addCondition(final StringBuffer conditions,
			final String fieldName, final String fieldValue) {
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(TABLE + "." + fieldName + " ='" + fieldValue
						+ "'");
			} else {
				conditions.append(" , " + TABLE + "." + fieldName + "='"
						+ fieldValue + "'");
			}
		}
	}

	private void addBooleanCondition(final StringBuffer conditions,
			final String fieldName, final Boolean fieldValue) {
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(TABLE + "." + fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" , " + TABLE + "." + fieldName + "='"
						+ convertBooleanToInt(fieldValue) + "'");
			}
		}
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	private int convertBooleanToInt(boolean b) {
		return Boolean.compare(b, false);
	}
}
