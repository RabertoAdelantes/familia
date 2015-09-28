package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.persons.PersonGroupDao;
import com.ra.familia.entities.GroupBean;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class PersonGroupServiceImpl implements Services<GroupBean> {

	private static final String ADMIN = "Admin";
	private PersonGroupDao personGroupDao = DaoFactory.getInstance().getPersonGroupDao();

	@Override
	public Collection<GroupBean> getAllItems() {
		return personGroupDao.getAllItems();
	}

	@Override
	public GroupBean getById(String personId) {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public GroupBean getItemByName(GroupBean bean) throws FamiliaException  {
		try {
			return personGroupDao.getItemByName(bean);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
	}

	@Override
	public Set<GroupBean> getItemsByName(GroupBean bean) {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public long addItem(GroupBean bean) {
		return personGroupDao.addItem(bean);
	}

	@Override
	public void updateItem(GroupBean bean) {
		personGroupDao.updateItem(bean);
	}
	
	public boolean isUserAdmin(PersonBean user)
	{
		boolean isAdmin = false;
		Collection<GroupBean> groups = personGroupDao.getAllItems();
		for (GroupBean groupBean : groups) {
			if (groupBean.getID().equals(user.getGroupId())&&ADMIN.equals(groupBean.getName()))
			{
				isAdmin = true;
			}
		}
		return isAdmin;
	}

}
