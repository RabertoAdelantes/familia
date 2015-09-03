package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.exceptions.DaoExeception;

public interface Services<T> {
	T getById(String personId) throws DaoExeception;

	void addItem(T bean) throws DaoExeception;

	void updateItem(T bean);
	
	T getItemByName(T bean) throws DaoExeception;
	
	Set<T> getItemsByName(T bean) throws DaoExeception;
	
	Collection<T> getAllItems();

}
