package com.ra.familia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.ra.familia.entities.TypesBean;

public class TypesDao extends AbstractDao<TypesBean> {
	private static String SELECT = "SELECT * FROM TYPES";

	public TypesDao() {

	}

	@Override
	Set<TypesBean> getAllItems() {
		Connection conn = null;
		Statement stmt = null;
		Set<TypesBean> types = new HashSet<TypesBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT);
			types = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	TypesBean getItemByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		Set<TypesBean> types = new HashSet<TypesBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT
					+ " where TYPES.NAME = '" + name + "'");
			types = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types.iterator().next();
	}

	@Override
	Set<TypesBean> getItemsByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		Set<TypesBean> types = new HashSet<TypesBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT
					+ " where TYPES.NAME like '%" + name + "%'");
			types = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}
	
	private TypesBean fillBean(final ResultSet rs)
			throws SQLException {
		TypesBean type = new TypesBean();
		type.setName(rs.getString("NAME"));
		type.setType(rs.getString("TYPE"));
		return type;
	}
	
	private Set<TypesBean> fillBeans(final ResultSet rs)
			throws SQLException {
		Set<TypesBean> types = new HashSet<TypesBean>();
		while (rs.next()) {
			TypesBean typesBean = fillBean(rs);
			types.add(typesBean);
		}
		return types;
	}
}
