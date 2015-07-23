package com.ra.familia.services;

import java.util.Set;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

public class PersonServiceImpl implements Services<PersonBean>{

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();

	public PersonBean getById(String personId) {
		return personDao.getItemById(personId);
	}
	
	public void addItem(PersonBean bean) {
		personDao.addItem(bean);
	}
	
	public void updateItem(PersonBean bean) {
		personDao.updateItem(bean);
	}

	@Override
	public PersonBean getItemByName(PersonBean person) {
		return personDao.getItemByName(person);
	}

	@Override
	public Set<PersonBean> getItemsByName(PersonBean person) {
		return personDao.getItemsByName(person);
	}
	
}
