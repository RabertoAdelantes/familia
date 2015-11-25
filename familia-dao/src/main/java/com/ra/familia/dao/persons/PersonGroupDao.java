package com.ra.familia.dao.persons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.AbstractDao;
import com.ra.familia.dao.Pair;
import com.ra.familia.entities.GroupBean;
import com.ra.familia.exceptions.DaoExeception;

import static com.ra.familia.dao.constants.QueriesConstants.*;

public class PersonGroupDao extends AbstractDao<GroupBean> {
	private static final Logger LOG = LoggerFactory.getLogger(PersonGroupDao.class);

	public PersonGroupDao() {
	}
	
	@Override
	public GroupBean fillBean(ResultSet rs) throws SQLException {
		return PersonGroupDaoHelper.fillBeanByRs(rs);
	}

	@Override
	public long addItem(GroupBean bean) {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		long pk = getGroupSequence();
		try {
			preparedStatement = conn.prepareStatement(INSERT_GR);
			preparedStatement.setString(1, bean.getName());
			preparedStatement.setInt(2, Long.valueOf(pk).intValue());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
		}
		finally
		{
			closeConnections(preparedStatement,conn);
		}
		return pk;
	}

	@Override
	public void updateItem(GroupBean bean) {
		LOG.error("Not yet implemented");
	}
	
	public Collection<GroupBean> getAllItems() {
		return super.getAllItems(SELECT_GR);
	}

	public GroupBean getItemByName(final GroupBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonGroupDaoHelper.fillSearchByName(bean, where, pairs);
		return getItemByField(SELECT_GR, WHERE + where.toString(), pairs);
	}
	
}
