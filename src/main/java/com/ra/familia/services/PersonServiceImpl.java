package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

public class PersonServiceImpl implements Services<PersonBean> {

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();
	private AppliactionCashe imgCashe = AppliactionCashe.getInsatnce();

	public PersonBean getById(String personId) {
		PersonBean person = (PersonBean) imgCashe.getObject(personId);
		person = person == null ? personDao.getItemById(personId) : person;
		addPersonToCashe(person);
		return person;
	}

	public void addItem(PersonBean bean) {
		personDao.addItem(bean);
	}

	public void updateItem(PersonBean bean) {
		personDao.updateItem(bean);
	}

	@Override
	public PersonBean getItemByName(PersonBean person) {
		PersonBean personRet = (PersonBean) imgCashe.getObject(person.getID());
		personRet = personRet == null ? personDao.getItemByName(person) : personRet;
		addPersonToCashe(personRet);
		return personRet;
	}

	@Override
	public Set<PersonBean> getItemsByName(PersonBean person) {
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
