package com.ra.familia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.entities.PersonBean;

public class PersonDaoHelper {
	private static final String TABLE = "PERSON".intern();

	private PersonDaoHelper() {

	}

	public static Pair<Integer, String> setUpdateCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " , ");
	}

	public static Pair<Integer, String> setWhereAndCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " AND ");
	}
	
	public static Pair<Integer, String> setWhereOrCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " OR ");
	}

	public static void setUpdateBooleanCondition(final StringBuffer conditions,
			final String fieldName, final Boolean fieldValue) {
		setUpdateBooleanCondition(conditions, fieldName, fieldValue, " , ");
	}

	private static Pair<Integer, String> setCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue, final String delimiter) {
		Pair<Integer, String> pair = new Pair<Integer, String>();
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(TABLE + "." + fieldName + " =?");
			} else {
				conditions.append(" " + delimiter + " " + TABLE + "."
						+ fieldName + "=?");
			}
			int count = StringUtils.countMatches(conditions.toString(),
					delimiter);
			pair.put(Integer.valueOf(count), fieldValue);
		}
		return pair;
	}

	private static void setUpdateBooleanCondition(
			final StringBuffer conditions, final String fieldName,
			final Boolean fieldValue, final String delimiter) {
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(TABLE + "." + fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" " + delimiter + " " + TABLE + "."
						+ fieldName + "='" + convertBooleanToInt(fieldValue)
						+ "'");
			}
		}
	}

	public static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	public static int convertBooleanToInt(boolean b) {
		return Boolean.compare(b, false);
	}

	public static void fillSearchByName(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, String>> pairs) {
		Pair<Integer, String> pair = PersonDaoHelper.setWhereAndCondition(
				where, "FIRST_NAME", bean.getFirst_name());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereAndCondition(where, "PASSWORD",
				bean.getPassword());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}
	
	public static void fillSearchById(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, String>> pairs) {
		Pair<Integer, String> pair = PersonDaoHelper.setWhereAndCondition(
				where, "pk", bean.getId());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static void fillSearchAll(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, String>> pairs) {
		Pair<Integer, String> pair = PersonDaoHelper.setWhereOrCondition(
				where, "FIRST_NAME", bean.getFirst_name());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, "PASSWORD",
				bean.getPassword());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}

		pair = PersonDaoHelper.setWhereOrCondition(
				where, "LAST_NAME", bean.getSecond_name());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, "MIDLE_NAME",
				bean.getMidle_name());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, "EMAIL",
				bean.getEmail());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static PersonBean fillBeanByRs(final ResultSet rs) throws SQLException {
		PersonBean person = new PersonBean();
		person.setId(rs.getString("PK"));
		person.setFirst_name(rs.getString("FIRST_NAME"));
		person.setSecond_name(rs.getString("LAST_NAME"));
		person.setMidle_name(rs.getString("MIDLE_NAME"));
		person.setPassword(rs.getString("PASSWORD"));
		return person;
	}
}
