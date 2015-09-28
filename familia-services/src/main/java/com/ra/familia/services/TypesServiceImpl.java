package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.types.TypesDao;
import com.ra.familia.entities.TypeBean;
import com.ra.familia.exceptions.FamiliaException;

public class TypesServiceImpl implements Services<TypeBean> {

	private TypesDao typesDao = DaoFactory.getInstance().getTypesDao();

	@Override
	public TypeBean getById(String itemId) throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public long addItem(TypeBean bean) throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public void updateItem(TypeBean bean) {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public TypeBean getItemByName(TypeBean bean) throws FamiliaException {
		return typesDao.getAllItems().stream()
				.filter(mBean -> mBean.getType().equals(bean.getType()))
				.findFirst().get();
	}

	@Override
	public Set<TypeBean> getItemsByName(TypeBean bean) throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public Collection<TypeBean> getAllItems() {
		throw new UnsupportedOperationException("Not implemnted yet");

	}

}
