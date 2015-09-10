package com.ra.familia.dao;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;

public class PersonDao extends AbstractDao<PersonBean> {

	private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);

	private static final String SELECT = "SELECT * FROM " + P_TABLE;
	private static final String INSERT = "INSERT INTO " + P_TABLE + " ("
			+ P_FIRST_NAME + ", " + P_MIDLE_NAME + ", " + P_LAST_NAME + ", "
			+ P_LAST_NAME2 + ", " + P_PASSWORD + ", " + P_EMAIL + ", "
			+ P_DATE_BIRTH + ", " + P_DATE_DEATH + ", " + P_ISACTIVE + ", "
			+ P_ISDELETED + ", " + P_PHOTO + "," + PK
			+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE " + P_TABLE + " SET ";
	private static final String WHERE = " WHERE ";

	public PersonDao() {
	}

	public PersonBean getItemByName(final PersonBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchByName(bean, where, pairs);
		return getItemByField(SELECT, WHERE + where.toString(), pairs);
	}
	
	public PersonBean getItemByEmail(final PersonBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchByEmail(bean, where, pairs);
		return getItemByField(SELECT, WHERE + where.toString(), pairs);
	}

	public PersonBean getItemById(final String id) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonBean bean = new PersonBean();
		bean.setID(id);
		PersonDaoHelper.fillSearchById(bean, where, pairs);
		return getItemByField(SELECT, WHERE + where.toString(), pairs);
	}

	public Set<PersonBean> getItemsByName(final PersonBean bean)
			throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchAll(bean, where, pairs);
		return getItemByFields(SELECT, WHERE + where.toString(), pairs);
	}

	@Override
	public Set<PersonBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<PersonBean> persons = new HashSet<PersonBean>();
		while (rs.next()) {
			PersonBean person = PersonDaoHelper.fillBeanByRs(rs);
			persons.add(person);
		}
		return persons;
	}

	@Override
	public void addItem(PersonBean bean) throws DaoExeception {
		Connection conn = getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
			preparedStatement.setString(1, bean.getFirstName());
			preparedStatement.setString(2, bean.getMidleName());
			preparedStatement.setString(3, bean.getSecondName());
			preparedStatement.setString(4, bean.getSecondName());
			preparedStatement.setString(5, bean.getPassword());
			preparedStatement.setString(6, bean.getEmail());
			preparedStatement
					.setTimestamp(7, getTimeStamp(bean.getDateDeath()));
			preparedStatement
					.setTimestamp(8, getTimeStamp(bean.getDateDeath()));
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 0);
			preparedStatement.setString(11, bean.getFilePath().toString());
			preparedStatement.setLong(12, getNextSequence());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
			throw new DaoExeception(exception.getLocalizedMessage());
		}
	}

	@Override
	public void updateItem(PersonBean bean) {
		Connection conn = getConnection();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		try {
			StringBuffer updateSql = new StringBuffer();
			updateSql.append(UPDATE);
			StringBuffer conditions = new StringBuffer();
			Pair<Integer, Object> pair = PersonDaoHelper.setUpdateCondition(
					conditions, P_FIRST_NAME, bean.getFirstName());
			addPair(pairs, pair);
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_LAST_NAME,
					bean.getSecondName());
			addPair(pairs, pair);
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_MIDLE_NAME,
					bean.getMidleName());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateCondition(conditions, P_EMAIL,
					bean.getEmail());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateCondition(conditions, P_PHOTO, bean
					.getFilePath().toString());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateCondition(conditions, P_FILE_DATA,
					bean.getDbFile());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateBooleanCondition(conditions,
					P_ISACTIVE, bean.isActive());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateBooleanCondition(conditions,
					P_ISDELETED, bean.isDeleted());
			addPair(pairs, pair);

			updateSql.append(conditions.toString());
			updateSql.append(WHERE);
			updateSql.append(P_TABLE + "." + PK + "=" + bean.getID());
			PreparedStatement preparedStatement = conn
					.prepareStatement(updateSql.toString());
			fillStatmentParameters(pairs, preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
		}
	}

	private void addPair(List<Pair<Integer, Object>> pairs,
			Pair<Integer, Object> pair) {
		if (pair.getKey() != null && pair.getValue() != null) {
			pairs.add(pair);
		}
	}

	public Collection<PersonBean> getAllItems() {
		return super.getAllItems(SELECT);
	}

}
