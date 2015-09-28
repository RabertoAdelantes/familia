package com.ra.familia.dao.confirmation;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.util.List;

import com.ra.familia.dao.Pair;
import com.ra.familia.entities.ConfirmationBean;
import com.ra.familia.helpers.DaoHelper;

public class ConfirmationHelper extends DaoHelper{

	public static void fillTypeByUUID(final ConfirmationBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = DaoHelper.setWhereAndCondition(
				where, C_LINK, bean.getLink());
		addPair(pairs, pair);
	}
	
}