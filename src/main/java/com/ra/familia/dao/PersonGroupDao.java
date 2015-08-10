package com.ra.familia.dao;

import static com.ra.familia.servlets.utils.TablesDictionary.G_PERSONGROUP;
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

import com.ra.familia.entities.GroupBean;
import com.ra.familia.exceptions.DaoExeception;

public class PersonGroupDao extends AbstractDao<GroupBean> {
	private static final Logger LOG = LoggerFactory.getLogger(PersonGroupDao.class);

	private static final String SELECT = "SELECT * FROM " + G_PERSONGROUP;
	private static final String INSERT = "INSERT INTO "
			+ P_TABLE
			+ " (G_NAME, PK) VALUES (?,?,seq_person.NEXTVAL)";
	private static final String UPDATE = "UPDATE " + G_PERSONGROUP + " SET ";
	private static final String WHERE = " WHERE ";

	public PersonGroupDao() {
	}

	@Override
	public Set<GroupBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<GroupBean> groups = new HashSet<GroupBean>();
		while (rs.next()) {
			GroupBean group = PersonGroupDaoHelper.fillBeanByRs(rs);
			groups.add(group);
		}
		return groups;
	}

	@Override
	public void addItem(GroupBean bean) {
		Connection conn = getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
			preparedStatement.setString(1, bean.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			LOG.error(exception.getLocalizedMessage());
		}
	}

	@Override
	public void updateItem(GroupBean bean) {
		LOG.error("Not yet implemented");
	}
	
	public Collection<GroupBean> getAllItems() {
		return super.getAllItems(SELECT);
	}

	public GroupBean getItemByName(final GroupBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		PersonGroupDaoHelper.fillSearchByName(bean, where, pairs);
		return getItemByField(SELECT, WHERE + where.toString(), pairs);
	}
}
