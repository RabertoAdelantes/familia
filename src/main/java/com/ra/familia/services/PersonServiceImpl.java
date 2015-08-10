package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;

public class PersonServiceImpl implements Services<PersonBean> {

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();
	private ApplicationCashe imgCashe = ApplicationCashe.getInsatnce();

	@Override
	public PersonBean getById(String personId) throws DaoExeception {
		PersonBean person = (PersonBean) imgCashe.getObject(personId);
		person = person == null ? personDao.getItemById(personId) : person;
		addPersonToCashe(person);
		return person;
	}

	@Override
	public void addItem(PersonBean bean) throws DaoExeception {
		personDao.addItem(bean);
	}

	@Override
	public void updateItem(PersonBean bean) {
		personDao.updateItem(bean);
	}

	@Override
	public PersonBean getItemByName(PersonBean person) throws DaoExeception {
		PersonBean personRet = (PersonBean) imgCashe.getObject(person.getID());
		personRet = personRet == null ? personDao.getItemByName(person) : personRet;
		addPersonToCashe(personRet);
		return personRet;
	}

	@Override
	public Set<PersonBean> getItemsByName(PersonBean person) throws DaoExeception {
		Set<PersonBean> persons =  personDao.getItemsByName(person);
		addPersonsToCashe(persons);
		return persons;
	}

	private void addPersonToCashe(PersonBean person) {
			imgCashe.add(person);
	}
	
	private void addPersonsToCashe(Collection<PersonBean> persons) {
		if (!CollectionUtils.isEmpty(persons)) 
		{
			for (PersonBean person : persons) {
				imgCashe.add(person);
			}
		}
	}

	@Override
	public Collection<PersonBean> getAllItems() {
		return personDao.getAllItems();
	}

}
