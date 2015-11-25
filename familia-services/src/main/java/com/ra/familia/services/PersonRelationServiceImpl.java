package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.persons.RelationDao;
import com.ra.familia.entities.RelationBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class PersonRelationServiceImpl implements Services<RelationBean> {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonRelationServiceImpl.class);

	private RelationDao relationsDao = DaoFactory.getInstance().getRelationDao();

	@Override
	public RelationBean getById(String itemId) throws FamiliaException {
		return null;
	}
	@Override
	public long addItem(RelationBean bean) throws FamiliaException {
		try {
			return relationsDao.addItem(bean);
		} catch (DaoExeception daoEx) {
			LOG.error(daoEx.getMessage());
			throw new FamiliaException(daoEx);
		}
	}
	@Override
	public void updateItem(RelationBean bean) throws FamiliaException {
	}
	
	@Override
	public RelationBean getItemByName(RelationBean bean)
			throws FamiliaException {
		return null;
	}
	@Override
	public Set<RelationBean> getItemsByName(RelationBean bean)
			throws FamiliaException {
		return null;
	}
	@Override
	public Collection<RelationBean> getAllItems() {
		return null;
	}
	
}
