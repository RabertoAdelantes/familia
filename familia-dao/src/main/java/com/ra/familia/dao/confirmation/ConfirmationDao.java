package com.ra.familia.dao.confirmation;

import static com.ra.familia.dao.constants.QueriesConstants.*;
import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ra.familia.dao.AbstractDao;
import com.ra.familia.dao.Pair;
import com.ra.familia.dao.persons.PersonDaoHelper;
import com.ra.familia.entities.ConfirmationBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.helpers.DaoHelper;

public class ConfirmationDao extends AbstractDao<ConfirmationBean> {

	@Override
	public long addItem(ConfirmationBean bean) throws DaoExeception {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		long pk = getConfirmSequence();
		try {
			preparedStatement = conn.prepareStatement(
					INSERT_CONFIRMATION);
			preparedStatement.setLong(1, bean.getUserReference());
			preparedStatement.setString(2, bean.getLink());
			preparedStatement.setDate(3, new Date(bean.getExpired().getTime()));
			preparedStatement.setLong(4, pk);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getMessage());
		} finally {
			closeConnections(preparedStatement,conn);
		}
		return pk;
	}

	@Override
	public void updateItem(ConfirmationBean bean) throws DaoExeception {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		try {
			StringBuffer updateSql = new StringBuffer();
			updateSql.append(UPDATE_CONFIRMATION);
			StringBuffer conditions = new StringBuffer();
			Pair<Integer, Object> pair = PersonDaoHelper.setUpdateCondition(
					conditions, C_LINK, bean.getLink());
			addPair(pairs, pair);

			pair = DaoHelper.setUpdateCondition(conditions, C_PERSON_FK,
					bean.getUserReference());
			addPair(pairs, pair);

			pair = PersonDaoHelper.setUpdateBooleanCondition(conditions, C_ISUSED,
					bean.isUsed());
			addPair(pairs, pair);

			updateSql.append(conditions.toString());
			preparedStatement = conn.prepareStatement(updateSql.toString());
			fillStatmentParameters(pairs, preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getMessage());
		} finally {
			closeConnections(preparedStatement,conn);

		}
	}

	@Override
	public ConfirmationBean fillBean(ResultSet rs) throws SQLException {
		String link = rs.getString(C_LINK);
		Date expiredDate = rs.getDate(C_DATE);
		Long userRef = rs.getLong(C_PERSON_FK);
		String pk = rs.getString(PK);
		Boolean isUsed = rs.getBoolean(C_ISUSED);
		ConfirmationBean confirmationBean = new ConfirmationBean(link, userRef, expiredDate);
		confirmationBean.setID(pk);
		confirmationBean.setUsed(isUsed);
		return confirmationBean;
	}

	public ConfirmationBean getByUUID(String uuid) throws DaoExeception {
		ConfirmationBean bean = new ConfirmationBean(uuid, 0, null);
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		ConfirmationHelper.fillTypeByUUID(bean, where, pairs);
		return getItemByField(SELECT_CONFIRMATION, WHERE + where.toString(),
				pairs);
	}

}