package com.ra.familia.services;

import java.sql.Connection;
import java.util.Collection;
import java.util.Set;

import com.ra.familia.dao.ConnectionManager;
import com.ra.familia.exceptions.FamiliaException;

public interface Services<T> {
	T getById(String itemId) throws FamiliaException;

	long addItem(T bean) throws FamiliaException;

	void updateItem(T bean) throws FamiliaException;
	
	T getItemByName(T bean) throws FamiliaException;
	
	Set<T> getItemsByName(T bean) throws FamiliaException;
	
	Collection<T> getAllItems();
	
	default Connection getConnection()
	{
		return ConnectionManager.getConnection();
	}

}
