package com.ra.familia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.TypesBean;

public class TypesDao extends AbstractDao<TypesBean> {
	private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);
	private static String SELECT = "SELECT * FROM TYPES";

	public TypesDao() {

	}
	
	Set<TypesBean> getAllItems() {
		return getAllItems(SELECT);
	}
	
	TypesBean getItemByName(String name) {
		return getItemByField(SELECT, "TYPES.NAME", name);
	}

	Set<TypesBean> getItemsByName(String name) {
		return getItemsByField(SELECT, "TYPES.NAME", name);
	}

	private TypesBean fillBean(final ResultSet rs) throws SQLException {
		TypesBean type = new TypesBean();
		type.setName(rs.getString("NAME"));
		type.setType(rs.getString("TYPE"));
		return type;
	}

	public Set<TypesBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<TypesBean> types = new HashSet<TypesBean>();
		while (rs.next()) {
			TypesBean typesBean = fillBean(rs);
			types.add(typesBean);
		}
		return types;
	}

	@Override
	void addItem(TypesBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateItem(TypesBean bean) {
		// TODO Auto-generated method stub
		
	}
}
