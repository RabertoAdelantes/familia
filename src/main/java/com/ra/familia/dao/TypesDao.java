package com.ra.familia.dao;

import static com.ra.familia.servlets.constants.TablesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.TypeBean;

public class TypesDao extends AbstractDao<TypeBean> {
	private static final Logger LOG = LoggerFactory.getLogger(TypesDao.class);
	private static String SELECT = "SELECT * FROM " + T_NAME;
	private static final String INSERT = "INSERT " + T_NAME
			+ " ("+P_FIRST_NAME+", "+P_MIDLE_NAME+", "+P_LAST_NAME+", "+P_LAST_NAME2+", "+P_PASSWORD+", "+P_EMAIL+", "+P_DATE_BIRTH+", "+P_DATE_DEATH+", "+P_ISACTIVE+", "+P_ISDELETED+", "+P_PHOTO+","+PK+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public TypesDao() {

	}
	
	public Collection<TypeBean> getAllItems() {
		return getAllItems(SELECT);
	}

	private TypeBean fillBean(final ResultSet rs) throws SQLException {
		TypeBean type = new TypeBean();
		type.setName(rs.getString("NAME"));
		type.setType(rs.getString("TYPE"));
		return type;
	}

	@Override
	public Set<TypeBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<TypeBean> types = new HashSet<TypeBean>();
		while (rs.next()) {
			TypeBean typesBean = fillBean(rs);
			types.add(typesBean);
		}
		return types;
	}

	@Override
	void addItem(TypeBean bean) {
	}

	@Override
	void updateItem(TypeBean bean) {		
	}
	
	public static void main(String[] args) {
		System.out.println(new TypesDao().getAllItems());
	}
}
