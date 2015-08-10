package com.ra.familia.dao;

import static com.ra.familia.servlets.utils.TablesDictionary.G_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.G_PERSONGROUP;
import static com.ra.familia.servlets.utils.TablesDictionary.PK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.entities.GroupBean;
import com.ra.familia.entities.PersonBean;

public class PersonGroupDaoHelper {

	private PersonGroupDaoHelper() {

	}

	public static Pair<Integer, Object> setUpdateCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " , ");
	}

	public static Pair<Integer, Object> setWhereAndCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " AND ");
	}
	
	public static Pair<Integer, Object> setWhereOrCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, " OR ");
	}

	public static void setUpdateBooleanCondition(final StringBuffer conditions,
			final String fieldName, final Boolean fieldValue) {
		setUpdateBooleanCondition(conditions, fieldName, fieldValue, " , ");
	}

	private static Pair<Integer, Object> setCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(G_PERSONGROUP + "." + fieldName + " =?");
			} else {
				conditions.append(" " + delimiter + " " + G_PERSONGROUP + "."
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
				conditions.append(G_PERSONGROUP + "." + fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" " + delimiter + " " + G_PERSONGROUP + "."
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

	
	public static void fillSearchById(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonGroupDaoHelper.setWhereAndCondition(
				where, PK, bean.getID());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static GroupBean fillBeanByRs(final ResultSet rs) throws SQLException {
		GroupBean group = new GroupBean();
		group.setID(rs.getString(PK)); 
		group.setName(rs.getString(G_NAME));
		return group;
	}
	
	
	public static void fillSearchByName(final GroupBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonGroupDaoHelper.setWhereAndCondition(
				where, G_NAME, bean.getName());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}
}
