package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.exceptions.DaoExeception;

public interface Services<T> {
	public T getById(String personId) throws DaoExeception;

	public void addItem(T bean) throws DaoExeception;

	public void updateItem(T bean);
	
	public T getItemByName(T bean) throws DaoExeception;
	
	public Set<T> getItemsByName(T bean) throws DaoExeception;
	
	public Collection<T> getAllItems();

}
