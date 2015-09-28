package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.confirmation.ConfirmationDao;
import com.ra.familia.entities.ConfirmationBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class ConfirmationServiceImpl implements Services<ConfirmationBean> {

	private ConfirmationDao confirmationDao = DaoFactory.getInstance()
			.getConfirmationDao();

	@Override
	public ConfirmationBean getById(String uuid) throws FamiliaException {
		try {
			return confirmationDao.getByUUID(uuid);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
	}

	@Override
	public long addItem(ConfirmationBean bean) throws FamiliaException {
		try {
			return confirmationDao.addItem(bean);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}

	}

	@Override
	public void updateItem(ConfirmationBean bean) throws FamiliaException {
		try {
			confirmationDao.updateItem(bean);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
	}

	@Override
	public ConfirmationBean getItemByName(ConfirmationBean bean)
			throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public Set<ConfirmationBean> getItemsByName(ConfirmationBean bean)
			throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public Collection<ConfirmationBean> getAllItems() {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

}
