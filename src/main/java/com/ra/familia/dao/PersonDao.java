package com.ra.familia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.ra.familia.entities.PersonBean;

public class PersonDao extends AbstractDao<PersonBean> {
	private static String SELECT = "SELECT * FROM PERSON";

	public PersonDao() {

	}
	
	@Override
	Set<PersonBean> getAllItems() {
		Connection conn = null;
		Statement stmt = null;
		Set<PersonBean> persons = new HashSet<PersonBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT);
			persons = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public PersonBean getItemByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		PersonBean personBean = null; 
		Set<PersonBean> persons = new HashSet<PersonBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT
					+ " where PERSON.FIRST_NAME = '" + name + "'");
			persons = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!persons.isEmpty())
		{
			personBean = persons.iterator().next();
		}
		return personBean;
	}

	

	@Override
	public Set<PersonBean> getItemsByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		Set<PersonBean> persons = new HashSet<PersonBean>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT
					+ " where PERSON.FIRST_NAME like '%" + name + "%'");
			persons = fillBeans(rs);
			rs.close();
			closeStatement(stmt);
			closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	private PersonBean fillBean(final ResultSet rs)
			throws SQLException {
		PersonBean person = new PersonBean();
		person.setFirst_name(rs.getString("FIRST_NAME"));
		person.setSecond_name(rs.getString("LAST_NAME"));
		person.setMidle_name(rs.getString("MIDLE_NAME"));
		person.setPassword_name(rs.getString("PASSWORD"));
		return person;
	}
	
	private Set<PersonBean> fillBeans(final ResultSet rs)
			throws SQLException {
		Set<PersonBean> persons = new HashSet<PersonBean>();
		while (rs.next()) {
			PersonBean person = fillBean(rs);
			persons.add(person);
		}
		return persons;
	}
}
