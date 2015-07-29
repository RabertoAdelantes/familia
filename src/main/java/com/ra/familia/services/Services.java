package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

public interface Services<T> {
	public T getById(String personId);

	public void addItem(T bean);

	public void updateItem(T bean);
	
	public T getItemByName(T bean);
	
	public Set<T> getItemsByName(T bean);
	
	public Collection<T> getAllItems();

}
