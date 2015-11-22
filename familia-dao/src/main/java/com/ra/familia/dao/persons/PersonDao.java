package com.ra.familia.dao.persons;

import static com.ra.familia.dao.constants.TablesConstants.*;
import static com.ra.familia.dao.constants.QueriesConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.AbstractDao;
import com.ra.familia.dao.Pair;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.helpers.DaoHelper;

public class PersonDao extends AbstractDao<PersonBean> {

	private static final String YYYY_MM_DD = "yyyy-mm-dd";
	private static final String MM_DD_YYYY = "mm/dd/yyyy";
	private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);


	public PersonBean getItemByName(final PersonBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchByName(bean, where, pairs);
		return getItemByField(SELECT_PERSONS_FULL, WHERE + where.toString(), pairs);
	}

	public PersonBean getItemByEmail(final PersonBean bean)
			throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchByEmail(bean, where, pairs);
		return getItemByField(SELECT_PERSONS, WHERE + where.toString(), pairs);
	}

	public PersonBean getItemById(final String id) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonBean bean = new PersonBean();
		bean.setID(id);
		PersonDaoHelper.fillSearchById(bean, where, pairs);
		return getItemByField(SELECT_PERSONS_FULL, WHERE + where.toString(), pairs);
	}

	public Set<PersonBean> getItemsByName(final PersonBean bean)
			throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonDaoHelper.fillSearchAll(bean, where, pairs);
		return getItemByFields(SELECT_PERSONS_FULL, WHERE + where.toString(), pairs);
	}
	
	@Override
	public PersonBean fillBean(ResultSet rs) throws SQLException {
		return PersonDaoHelper.fillBeanByRs(rs);
	}

	@Override
	public long addItem(PersonBean bean) throws DaoExeception {
		PreparedStatement preparedStatement = null;
		long pk = getPersonSequence();
		try {
			preparedStatement = getConnection()
					.prepareStatement(INSERT_PERSONS);
			preparedStatement.setString(1, bean.getFirstName());
			preparedStatement.setString(2, bean.getMidleName());
			preparedStatement.setString(3, bean.getSecondName());
			preparedStatement.setString(4, bean.getLastName2());
			preparedStatement.setString(5, bean.getPassword());
			preparedStatement.setString(6, bean.getEmail());
			preparedStatement
					.setTimestamp(7, getTimeStamp(bean.getDateBirth(),MM_DD_YYYY));
			preparedStatement
					.setTimestamp(8, getTimeStamp(bean.getDateDeath(),MM_DD_YYYY));
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 0);
			preparedStatement.setLong(11, pk);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getMessage());
		} finally {
			closePrepeareStatment(preparedStatement);
		}
		return pk;
	}

	@Override
	public void updateItem(PersonBean bean) throws DaoExeception {
		Connection conn = getConnection();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		try {
			StringBuffer updateSql = new StringBuffer();
			updateSql.append(UPDATE_PERSONS);
			StringBuffer conditions = new StringBuffer();
			Pair<Integer, Object> pair = DaoHelper.setUpdateCondition(
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
			
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_DATE_BIRTH,
					getTimeStamp(bean.getDateBirth(),YYYY_MM_DD));
			addPair(pairs, pair);
			
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_DATE_DEATH,
					getTimeStamp(bean.getDateDeath(),MM_DD_YYYY));
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
			throw new DaoExeception(exception.getLocalizedMessage());
		}
	}

	public Collection<PersonBean> getAllItems() {
		return super.getAllItems(SELECT_PERSONS_FULL);
	}

}
