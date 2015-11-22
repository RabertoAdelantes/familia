package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.persons.PersonDao;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class PersonServiceImpl implements Services<PersonBean> {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonServiceImpl.class);

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();
	private ApplicationCashe imgCashe = ApplicationCashe.getInsatnce();
	private EncryptionService encryptionService = new EncryptionService();

	@Override
	public PersonBean getById(String personId) throws FamiliaException {
		PersonBean person = (PersonBean) imgCashe.getObject(personId);
		try {
			return person == null ? personDao.getItemById(personId) : person;
		} catch (DaoExeception daoex) {
			throw new FamiliaException(daoex);
		}
	}

	@Override
	public long addItem(PersonBean bean) throws FamiliaException {
		String password = encryptionService.encode(bean.getPassword());
		bean.setPassword(password);
		try {
			return personDao.addItem(bean);
		} catch (DaoExeception daoex) {
			throw new FamiliaException(daoex);
		}
	}

	@Override
	public void updateItem(PersonBean bean) throws FamiliaException {
		try {
			personDao.updateItem(bean);
			imgCashe.add(bean);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
	}

	@Override
	public PersonBean getItemByName(PersonBean person) throws FamiliaException {
		PersonBean personRet = (PersonBean) imgCashe.getObject(person.getID());
		try {
			personRet = personRet == null ? personDao.getItemByName(person)
					: personRet;
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
		addPersonToCashe(personRet);
		return personRet;
	}

	@Override
	public Set<PersonBean> getItemsByName(PersonBean person)
			throws FamiliaException {
		Set<PersonBean> persons;
		try {
			persons = personDao.getItemsByName(person);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
		//addPersonsToCashe(persons);
		return persons;
	}

	public PersonBean getItemByEmail(PersonBean person) {
		PersonBean personRet = (PersonBean) imgCashe.getObject(person.getID());
		try {
			personRet = personRet == null ? personDao.getItemByEmail(person)
					: personRet;
		} catch (DaoExeception ex) {
			LOG.error(ex.getMessage());
		}
		addPersonToCashe(personRet);
		return personRet;
	}

	private void addPersonToCashe(PersonBean person) {
		imgCashe.add(person);
	}

//	private void addPersonsToCashe(Collection<PersonBean> persons) {
//		if (!CollectionUtils.isEmpty(persons)) {
//			persons.forEach(person -> imgCashe.add(person));
//		}
//	}

	@Override
	public Collection<PersonBean> getAllItems() {
		return personDao.getAllItems();
	}

}
