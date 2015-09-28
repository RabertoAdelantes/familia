package com.ra.familia.services;

import java.util.Collection;
import java.util.Set;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.media.MediaDao;
import com.ra.familia.dao.media.MediaRefDao;
import com.ra.familia.entities.MediaBean;
import com.ra.familia.entities.MediaRefBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class MediaServiceImpl implements Services<MediaBean> {

	private MediaDao mediaDao = DaoFactory.getInstance().getMediaDao(); 
	private MediaRefDao mediaRefDao = DaoFactory.getInstance().getMediaRefDao(); 
	@Override
	public MediaBean getById(String personId) throws FamiliaException {
		return null;
	}

	@Override
	public long addItem(MediaBean bean) throws FamiliaException  {
		try {
			return mediaDao.addItem(bean);
		} catch (DaoExeception daoEx) {
			throw new FamiliaException(daoEx);
		}
	}

	@Override
	public void updateItem(MediaBean bean) {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public MediaBean getItemByName(MediaBean bean)  {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public Set<MediaBean> getItemsByName(MediaBean bean) throws FamiliaException {
		throw new UnsupportedOperationException("Not implemnted yet");
	}

	@Override
	public Collection<MediaBean> getAllItems() {
		return mediaDao.getAllItems();
	}
	
	public void addMediaRef(long pk, long mediaPk) throws DaoExeception
	{
		MediaRefBean mediaRefBean = new MediaRefBean();
		mediaRefBean.setMedia_fk(String.valueOf(mediaPk));
		mediaRefBean.setPerson_fk(String.valueOf(pk));
		mediaRefDao.addItem(mediaRefBean);
	}

}
