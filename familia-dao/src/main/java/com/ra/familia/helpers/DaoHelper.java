package com.ra.familia.helpers;

import static com.ra.familia.dao.constants.QueriesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.Pair;

public class DaoHelper {

	private static final Logger LOG = LoggerFactory.getLogger(DaoHelper.class);

	private static final String SELECT_NEXTVAL_SEQ_PERSON = "SELECT nextval('%s')";
	private static final String SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL = "select %s.nextval from dual";
	private static final String POSTGRESS_TYPE = "org.postgresql.jdbc4.Jdbc4Connection";
	private static final String ORACLE_TYPE = "oracle.jdbc.driver.T4CConnection";

	public static String getSeqQuery(String connectionType, String seqName) {
		String query = String.format(SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL,
				seqName);
		switch (connectionType) {
		case POSTGRESS_TYPE:
			query = String.format(SELECT_NEXTVAL_SEQ_PERSON, seqName);
			break;
		case ORACLE_TYPE:
			query = String.format(SELECT_SEQ_PERSON_NEXTVAL_FROM_DUAL, seqName);
			break;
		}
		return query;
	}

	public static void addPair(final List<Pair<Integer, Object>> pairs,
			Pair<Integer, Object> pair) {
		if (pair.getKey() != null && pair.getValue() != null && !pair.getValue().toString().isEmpty()) {
			pairs.add(pair);
		}
	}

	public static boolean isBoolean(Object obj) {
		boolean isBoolean = false;
		if (obj != null) {
			isBoolean = Boolean.TRUE.toString()
					.equalsIgnoreCase(obj.toString()) ? true : false;
		}
		return isBoolean;
	}

	public static int getInteger(Object obj) {
		int retValue = -1;
		if (isNumeric(obj)) {
			retValue = Integer.valueOf(obj.toString());
		}
		return retValue;
	}

	public static boolean isNumeric(Object value) {
		boolean isNumeric = false;
		if (value != null) {
			isNumeric = value.toString().matches("-?\\d+(\\.\\d+)?");
		}
		return isNumeric;
	}

	public static boolean isParameterInRowSet(final ResultSet rowSet,
			String columnName) {
		boolean isInRowSet = false;
		try {
			for (int index = 1; index < rowSet.getMetaData().getColumnCount() + 1; index++) {
				String name = rowSet.getMetaData().getColumnName(index);
				if (name.equalsIgnoreCase(columnName)) {
					isInRowSet = true;
				}
			}
		} catch (SQLException sqlEx) {
			LOG.warn(sqlEx.getMessage());
		}
		return isInRowSet;
	}

	public static Pair<Integer, Object> setUpdateCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, COMA);
	}

	public static Pair<Integer, Object> setUpdateNullCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue) {
		return setNullCondition(conditions, fieldName, fieldValue, COMA);
	}

	public static Pair<Integer, Object> setWhereAndCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, AND);
	}

	public static Pair<Integer, Object> setWhereOrCondition(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setCondition(conditions, fieldName, fieldValue, OR);
	}
	
	public static Pair<Integer, Object> setWhereOrConditionLike(
			final StringBuffer conditions, final String fieldName,
			final String fieldValue) {
		return setLikeCondition(conditions, fieldName, fieldValue, OR);
	}

	public static Pair<Integer, Object> setUpdateBooleanCondition(
			final StringBuffer conditions, final String fieldName,
			final Boolean fieldValue) {
		return setUpdateBooleanCondition(conditions, fieldName, fieldValue,
				COMA);
	}

	public static Pair<Integer, Object> setLikeCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();
		if (fieldValue != null&&!fieldValue.toString().isEmpty()) {
			if (conditions.toString().isEmpty()) {
				conditions.append(fieldName + LIKE +"?");
			} else {
				conditions.append(" " + delimiter + LIKE + fieldName + "?");
			}
			int count = StringUtils.countMatches(conditions.toString(),
					delimiter);
			pair.put(Integer.valueOf(count), fieldValue);
		}
		return pair;
	}
	
	public static Pair<Integer, Object> setCondition(
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

	public static Pair<Integer, Object> setNullCondition(
			final StringBuffer conditions, final String fieldName,
			final Object fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();
		if (conditions.toString().isEmpty()) {
			conditions.append(fieldName + " =?");
		} else {
			conditions.append(" " + delimiter + " " + fieldName + "=?");
		}
		int count = StringUtils.countMatches(conditions.toString(), delimiter);
		pair.put(Integer.valueOf(count), fieldValue);
		return pair;
	}

	public static Pair<Integer, Object> setUpdateBooleanCondition(
			final StringBuffer conditions, final String fieldName,
			final Boolean fieldValue, final String delimiter) {
		Pair<Integer, Object> pair = new Pair<Integer, Object>();

		if (fieldValue != null) {
			if (conditions.toString().isEmpty()) {
				conditions.append(fieldName + " ='"
						+ convertBooleanToInt(fieldValue) + "'");
			} else {
				conditions.append(" " + delimiter + " " + fieldName + "='"
						+ convertBooleanToInt(fieldValue) + "'");
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
}