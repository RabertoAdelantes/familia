package com.ra.familia.dao;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.entities.PersonBean;


public class PersonDaoHelper {

	private PersonDaoHelper() {

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

	public static Pair<Integer, Object> setUpdateBooleanCondition(final StringBuffer conditions,
			final String fieldName, final Boolean fieldValue) {
		return setUpdateBooleanCondition(conditions, fieldName, fieldValue, " , ");
	}

	private static Pair<Integer, Object> setCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();
		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(fieldName + " =?");
			} else {
				conditions.append(" " + delimiter + " " + fieldName + "=?");
			}
			int count = StringUtils.countMatches(conditions.toString(),
					delimiter);
			pair.put(Integer.valueOf(count), fieldValue);
		}
		return pair;
	}

	private static Pair<Integer, Object> setUpdateBooleanCondition(
			final StringBuffer conditions, final String fieldName,
			final Boolean fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();

		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" " + delimiter + " "	+ fieldName + "='" + convertBooleanToInt(fieldValue)
						+ "'");
			}
			int count = StringUtils.countMatches(conditions.toString(),
					delimiter);
			pair.put(Integer.valueOf(count), fieldValue);
		}
		return pair;

	}

	public static int convertBooleanToInt(boolean b) {
		return Boolean.compare(b, false);
	}

	public static void fillSearchByName(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereAndCondition(
				where, P_FIRST_NAME, bean.getFirstName());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereAndCondition(where, P_PASSWORD,
				bean.getPassword());
		addPair(pairs, pair);
	}
	
	public static void fillSearchByEmail(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereAndCondition(
				where, P_EMAIL, bean.getEmail());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereAndCondition(where, P_PASSWORD,
				bean.getPassword());
		addPair(pairs, pair);
	}
	
	public static void fillSearchById(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereAndCondition(
				where, PK, bean.getID());
		addPair(pairs, pair);
	}

	public static void fillSearchAll(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereOrCondition(
				where, P_FIRST_NAME, bean.getFirstName());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereOrCondition(
				where, PK, bean.getID());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereOrCondition(where, P_PASSWORD,
				bean.getPassword());
		addPair(pairs, pair);

		pair = PersonDaoHelper.setWhereOrCondition(
				where, P_LAST_NAME, bean.getSecondName());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereOrCondition(where, P_MIDLE_NAME,
				bean.getMidleName());
		addPair(pairs, pair);
		pair = PersonDaoHelper.setWhereOrCondition(where, P_EMAIL,
				bean.getEmail());
		addPair(pairs, pair);
	}

	private static void addPair(final List<Pair<Integer, Object>> pairs,
			Pair<Integer, Object> pair) {
		if (pair.getKey() != null&&pair.getValue()!=null) {
			pairs.add(pair);
		}
	}

	public static PersonBean fillBeanByRs(final ResultSet rs) throws SQLException {
		PersonBean person = new PersonBean();
		person.setID(rs.getString(PK)); 
		person.setFirstName(rs.getString(P_FIRST_NAME));
		person.setLastName(rs.getString(P_LAST_NAME));
		person.setMidleName(rs.getString(P_MIDLE_NAME));
		person.setPassword(rs.getString(P_PASSWORD));
		person.setEmail(rs.getString(P_EMAIL));
		person.setDateBirth(rs.getString(P_DATE_BIRTH));
		person.setDateDeath(rs.getString(P_DATE_DEATH));
		person.setGroupId(rs.getString(P_GROUP_ID));
		person.setDeleted(IS_ADMIN.equals(rs.getString(P_ISDELETED))?true:false);
		person.setActive(IS_ADMIN.equals(rs.getString(P_ISACTIVE))?true:false);
		
		
		Blob blob = rs.getBlob(P_FILE_DATA);
		byte[] bytes= new byte[] {};
		if (blob!=null&&blob.length()!=0)
		{
			bytes = blob.getBytes(1,(int)blob.length());
			person.setDbFile(bytes); 
		}
		String path = rs.getObject(P_PHOTO)==null?StringUtils.EMPTY:rs.getObject(P_PHOTO).toString();
		person.setFilePath(path); 
		return person;
	}
}
