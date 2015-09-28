package com.ra.familia.dao.types;

import static com.ra.familia.dao.constants.TablesConstants.T_TP;

import java.util.List;

import com.ra.familia.dao.Pair;
import com.ra.familia.entities.TypeBean;
import com.ra.familia.helpers.DaoHelper;

public class TypeHelper extends DaoHelper{

	public static void fillTypeById(final TypeBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = DaoHelper.setWhereAndCondition(
				where, T_TP, bean.getType());
		addPair(pairs, pair);
	}
	
}