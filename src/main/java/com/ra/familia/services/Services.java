package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.entities.PersonBean;

public interface Services<T> {
	public T getById(String personId);

	public void addItem(PersonBean bean);

	public void updateItem(PersonBean bean);
	
	public T getItemByName(T bean);
	
	public Set<T> getItemsByName(T bean);
	
	public Collection<T> getAllItems();

}
