package com.ra.familia.dao;

import static com.ra.familia.servlets.utils.TablesDictionary.PK;
import static com.ra.familia.servlets.utils.TablesDictionary.P_EMAIL;
import static com.ra.familia.servlets.utils.TablesDictionary.P_FILE_DATA;
import static com.ra.familia.servlets.utils.TablesDictionary.P_FIRST_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_ISACTIVE;
import static com.ra.familia.servlets.utils.TablesDictionary.P_ISDELETED;
import static com.ra.familia.servlets.utils.TablesDictionary.P_LAST_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_MIDLE_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_PHOTO;
import static com.ra.familia.servlets.utils.TablesDictionary.P_TABLE;

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
	private static final String INSERT = "INSERT INTO "
			+ P_TABLE
			+ " (FIRST_NAME, MIDLE_NAME, LAST_NAME, LAST_NAME2, PASSWORD, EMAIL, DATE_BIRTH, DATE_DEATH, ISACTIVE, ISDELETED, PK) VALUES (?,?,?,?,?,?,?,?,?,?,seq_person.NEXTVAL)";
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

	public PersonBean getItemById(final String id) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonBean bean = new PersonBean();
		bean.setID(id);
		PersonDaoHelper.fillSearchById(bean, where, pairs);
		return getItemByField(SELECT, WHERE + where.toString(), pairs);
	}

	public Set<PersonBean> getItemsByName(final PersonBean bean) throws DaoExeception {
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
			preparedStatement.setTimestamp(7,
					PersonDaoHelper.getCurrentTimeStamp());
			preparedStatement.setTimestamp(8,
					PersonDaoHelper.getCurrentTimeStamp());
			preparedStatement.setBoolean(9, false);
			preparedStatement.setBoolean(10, false);
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
			pairs.add(pair);
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_LAST_NAME,
					bean.getSecondName());
			pairs.add(pair);
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_MIDLE_NAME,
					bean.getMidleName());
			pairs.add(pair);

			pair = PersonDaoHelper.setUpdateCondition(conditions, P_EMAIL,
					bean.getEmail());
			pairs.add(pair);

			pair = PersonDaoHelper.setUpdateCondition(conditions, P_PHOTO, bean
					.getFilePath().toString());
			pairs.add(pair);
			
			pair = PersonDaoHelper.setUpdateCondition(conditions, P_FILE_DATA, bean
					.getDbFile());
			pairs.add(pair);

			PersonDaoHelper.setUpdateBooleanCondition(conditions, P_ISACTIVE,
					bean.isActive());
			PersonDaoHelper.setUpdateBooleanCondition(conditions, P_ISDELETED,
					bean.isActive());
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
	
	public Collection<PersonBean> getAllItems() {
		return super.getAllItems(SELECT);
	}

}
