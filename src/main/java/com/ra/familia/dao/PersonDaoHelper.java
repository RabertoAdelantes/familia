package com.ra.familia.dao;

import static com.ra.familia.servlets.utils.TablesDictionary.PK;
import static com.ra.familia.servlets.utils.TablesDictionary.P_DATE_BIRTH;
import static com.ra.familia.servlets.utils.TablesDictionary.P_DATE_DEATH;
import static com.ra.familia.servlets.utils.TablesDictionary.P_EMAIL;
import static com.ra.familia.servlets.utils.TablesDictionary.P_FILE_DATA;
import static com.ra.familia.servlets.utils.TablesDictionary.P_FIRST_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_GROUP_ID;
import static com.ra.familia.servlets.utils.TablesDictionary.P_ISACTIVE;
import static com.ra.familia.servlets.utils.TablesDictionary.P_ISDELETED;
import static com.ra.familia.servlets.utils.TablesDictionary.P_LAST_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_MIDLE_NAME;
import static com.ra.familia.servlets.utils.TablesDictionary.P_PASSWORD;
import static com.ra.familia.servlets.utils.TablesDictionary.P_PHOTO;
import static com.ra.familia.servlets.utils.TablesDictionary.P_TABLE;

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

	public static int convertBooleanToInt(boolean b) {
		return Boolean.compare(b, false);
	}

	public static void fillSearchByName(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereAndCondition(
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
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereAndCondition(
				where, PK, bean.getID());
		if (pair.getKey() != null) {
			pairs.add(pair);
		}
	}

	public static void fillSearchAll(final PersonBean bean,
			final StringBuffer where, final List<Pair<Integer, Object>> pairs) {
		Pair<Integer, Object> pair = PersonDaoHelper.setWhereOrCondition(
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
		person.setID(rs.getString(PK)); 
		person.setFirstName(rs.getString(P_FIRST_NAME));
		person.setLastName(rs.getString(P_LAST_NAME));
		person.setMidleName(rs.getString(P_MIDLE_NAME));
		person.setPassword(rs.getString(P_PASSWORD));
		person.setEmail(rs.getString(P_EMAIL));
		person.setDateBirth(rs.getString(P_DATE_BIRTH));
		person.setDateBirth(rs.getString(P_DATE_DEATH));
		person.setGroupId(rs.getString(P_GROUP_ID));
		person.setDeleted(rs.getString(P_ISDELETED).equals("1")?true:false);
		person.setActive(rs.getString(P_ISACTIVE).equals("1")?true:false);
		
		
		Blob blob = rs.getBlob(P_FILE_DATA);
		byte[] bytes= new byte[] {};
		if (blob.length()!=0)
		{
			bytes = blob.getBytes(1,(int)blob.length());
		}
		person.setDbFile(bytes); 
		String path = rs.getObject(P_PHOTO)==null?StringUtils.EMPTY:rs.getObject(P_PHOTO).toString();
		person.setFilePath(path); 
		return person;
	}
}
