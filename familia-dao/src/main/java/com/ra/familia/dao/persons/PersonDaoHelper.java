package com.ra.familia.dao.persons;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.dao.Pair;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.helpers.DaoHelper;

public class PersonDaoHelper extends DaoHelper {

	public static void fillSearchByName(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = setWhereAndCondition(where, P_TABLE + "."
				+ P_FIRST_NAME, bean.getFirstName());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereAndCondition(where, P_TABLE + "."
				+ P_PASSWORD, bean.getPassword());
		addPair(pairs, pair);
	}

	public static void fillSearchByEmail(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = setWhereAndCondition(where, P_TABLE + "."
				+ P_EMAIL, bean.getEmail());
		addPair(pairs, pair);
	}

	public static void fillSearchById(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = setWhereAndCondition(where, P_TABLE + "."
				+ PK, bean.getID());
		addPair(pairs, pair);
	}

	public static void fillSearchAll(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = setWhereOrConditionLike(where, P_TABLE
				+ "." + P_FIRST_NAME, likeCtiteriaWraper(bean.getFirstName()));
		addPair(pairs, pair);
		pair = setWhereOrConditionLike(where, P_TABLE + "." + PK,
				likeCtiteriaWraper(bean.getID()));
		addPair(pairs, pair);
		pair = setWhereOrConditionLike(where, P_TABLE + "." + P_PASSWORD,
				likeCtiteriaWraper(bean.getPassword()));
		addPair(pairs, pair);

		pair = setWhereOrConditionLike(where, P_TABLE + "." + P_LAST_NAME,
				likeCtiteriaWraper(bean.getSecondName()));
		addPair(pairs, pair);
		pair = setWhereOrConditionLike(where, P_TABLE + "." + P_MIDLE_NAME,
				likeCtiteriaWraper(bean.getMidleName()));
		addPair(pairs, pair);
		pair = setWhereOrConditionLike(where, P_TABLE + "." + P_EMAIL,
				likeCtiteriaWraper(bean.getEmail()));
		addPair(pairs, pair);
	}

	private static String likeCtiteriaWraper(String inValue) {
		return StringUtils.isEmpty(inValue) ? null : "%" + inValue + "%";
	}

	public static PersonBean fillBeanByRs(final ResultSet rs)
			throws SQLException {
		PersonBean person = new PersonBean();
		if (isColumnExist(rs, PK)) {
			person.setID(rs.getString(PK));
		}
		person.setFirstName(rs.getString(P_FIRST_NAME));
		person.setLastName(rs.getString(P_LAST_NAME));
		person.setMidleName(rs.getString(P_MIDLE_NAME));
		if (isColumnExist(rs, P_PASSWORD)) {
			person.setPassword(rs.getString(P_PASSWORD));
		}
		if (isColumnExist(rs, P_EMAIL)) {
			person.setEmail(rs.getString(P_EMAIL));
		}
		if (isColumnExist(rs, P_DATE_BIRTH)) {
			person.setDateBirth(rs.getString(P_DATE_BIRTH));
		}
		if (isColumnExist(rs, P_DATE_DEATH)) {
			person.setDateDeath(rs.getString(P_DATE_DEATH));
		}
		if (isColumnExist(rs, P_GROUP_ID)) {
			person.setGroupId(rs.getString(P_GROUP_ID));
		}
		if (isColumnExist(rs, P_ISDELETED)) {
			person.setDeleted(IS_ADMIN.equals(rs.getString(P_ISDELETED)) ? true
					: false);
		}
		if (isColumnExist(rs, P_ISACTIVE)) {
			person.setActive(IS_ADMIN.equals(rs.getString(P_ISACTIVE)) ? true
					: false);
		}
		if (isColumnExist(rs, M_SOURCE)) {
			byte[] bytes = rs.getBytes(M_SOURCE);
			if (bytes != null && bytes.length != 0) {
				person.setDbFile(bytes);
			}
		}

		if (isColumnExist(rs, T_TP)) {
			person.setConnection(rs.getString(T_TP));

		}
		return person;
	}
}
