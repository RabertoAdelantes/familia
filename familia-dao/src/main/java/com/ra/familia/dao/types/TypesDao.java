package com.ra.familia.dao.types;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ra.familia.dao.AbstractDao;
import com.ra.familia.dao.Pair;
import com.ra.familia.entities.TypeBean;
import com.ra.familia.exceptions.DaoExeception;

import static com.ra.familia.dao.constants.QueriesConstants.*;

public class TypesDao extends AbstractDao<TypeBean> {

	public TypesDao() {

	}

	public Collection<TypeBean> getAllItems() {
		return getAllItems(SELECT_TYPES);
	}

	@Override
	public TypeBean fillBean(final ResultSet rs) throws SQLException {
		TypeBean type = new TypeBean();
		type.setType(rs.getString(T_TP));
		type.setID(rs.getString(PK));
		return type;
	}

	@Override
	public long addItem(TypeBean bean) {
		return 0l;
	}

	@Override
	public void updateItem(TypeBean bean) {
	}

	public TypeBean getItemType(final TypeBean bean) throws DaoExeception {
		StringBuffer where = new StringBuffer();
		List<Pair<Integer, Object>> pairs = new ArrayList<>();
		TypeHelper.fillTypeById(bean, where, pairs);
		return getItemByField(SELECT_TYPES, WHERE + where.toString(), pairs);
	}
}
