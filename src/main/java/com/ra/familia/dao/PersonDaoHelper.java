package com.ra.familia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.servlets.utils.TablesDictionary;

public class PersonDaoHelper implements TablesDictionary{

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
				conditions.append(P_TABLE + "." + fieldName + " =?");
			} else {
				conditions.append(" " + delimiter + " " + P_TABLE + "."
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
				conditions.append(P_TABLE + "." + fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" " + delimiter + " " + P_TABLE + "."
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
				where, P_FIRST_NAME, bean.getFirstName());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereAndCondition(where, P_PASSWORD,
				bean.getPassword());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}
	
	public static void fillSearchById(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, String>> pairs) {
		Pair<Integer, String> pair = PersonDaoHelper.setWhereAndCondition(
				where, PK, bean.getId());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static void fillSearchAll(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, String>> pairs) {
		Pair<Integer, String> pair = PersonDaoHelper.setWhereOrCondition(
				where, P_FIRST_NAME, bean.getFirstName());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, P_PASSWORD,
				bean.getPassword());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}

		pair = PersonDaoHelper.setWhereOrCondition(
				where, P_LAST_NAME, bean.getSecondName());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, P_MIDLE_NAME,
				bean.getMidleName());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
		pair = PersonDaoHelper.setWhereOrCondition(where, P_EMAIL,
				bean.getEmail());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static PersonBean fillBeanByRs(final ResultSet rs) throws SQLException {
		PersonBean person = new PersonBean();
		person.setId(rs.getString(PK));
		person.setFirstName(rs.getString(P_FIRST_NAME));
		person.setLastName(rs.getString(P_LAST_NAME));
		person.setMidleName(rs.getString(P_MIDLE_NAME));
		person.setPassword(rs.getString(P_PASSWORD));
		person.setEmail(rs.getString(P_EMAIL));
		person.setDateBirth(rs.getString(P_DATE_BIRTH));
		person.setDateBirth(rs.getString(P_DATE_DEATH));
		return person;
	}
}
