package com.ra.familia.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Set;

public abstract class AbstractDao<T> {

	abstract Set<T> getAllItems(); 
	abstract T getItemByName(String name); 
	abstract Set<T> getItemsByName(String name);
	
	public Connection getConnection()
	{
		return ConnectionManager.getConnection();
	}
	
	public void closeConnection(final Connection conn)
	{
		ConnectionManager.closeConnection(conn);
	}
	
	public void closeStatement(final Statement stmt)
	{
		ConnectionManager.closeStatement(stmt);
	}
}
