package com.ra.familia.dao.persons;

import static com.ra.familia.dao.constants.QueriesConstants.*;
import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.NotImplementedException;

import com.ra.familia.dao.AbstractDao;
import com.ra.familia.entities.RelationBean;
import com.ra.familia.exceptions.DaoExeception;

public class RelationDao extends AbstractDao<RelationBean> {

	@Override
	public RelationBean fillBean(ResultSet rs) throws SQLException {
		RelationBean relationBean = new RelationBean();
		relationBean.setID(rs.getString(PK));
		relationBean.setPersonId(rs.getString(R_PERSON_PK));
		relationBean.setPersonRelationId(rs.getString(R_PERSON_RELATION_FK));
		relationBean.setTypeId(rs.getString(R_TYPES_FK));
		return relationBean;
	}

	@Override
	public long addItem(RelationBean bean) throws DaoExeception {
		PreparedStatement preparedStatement = null;
		long pk = getPersonRelationSequence();
		try {
			preparedStatement = getConnection().prepareStatement(
					INSERT_PERSONS_RELATIONS);
			preparedStatement.setInt(1, Integer.valueOf(bean.getPersonId()));
			preparedStatement.setInt(2, Integer.valueOf(bean.getPersonRelationId()));
			preparedStatement.setInt(3, Integer.valueOf(bean.getTypeId()));
			preparedStatement.setLong(4, pk);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getMessage());
		} finally {
			closePrepeareStatment(preparedStatement);
		}
		return pk;
	}

	@Override
	public void updateItem(RelationBean bean) throws DaoExeception {
		throw new NotImplementedException("RelationDao.update()");
	}

}
