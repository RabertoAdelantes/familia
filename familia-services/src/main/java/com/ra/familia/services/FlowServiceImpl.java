package com.ra.familia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.ra.familia.entities.ConfirmationBean;
import com.ra.familia.entities.FlowBean;
import com.ra.familia.entities.MediaBean;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.entities.TypeBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.exceptions.FamiliaException;

public class FlowServiceImpl implements Services<FlowBean> {

	private static final String PHOTO = "Photo";

	private PersonServiceImpl personService = new PersonServiceImpl();
	private MediaServiceImpl mediaService = new MediaServiceImpl();
	private TypesServiceImpl typesServiceImpl = new TypesServiceImpl();
	private ConfirmationServiceImpl confirmationServiceImpl = new ConfirmationServiceImpl();

	@Override
	public FlowBean getById(String personId) throws FamiliaException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public long addItem(FlowBean bean) throws FamiliaException {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			PersonBean personBean = bean.getPearsonBean();
			long personPk = personService.addItem(personBean);
			MediaBean media = prepeareMedia(personBean, PHOTO);
			long mediaPk = mediaService.addItem(media);
			mediaService.addMediaRef(personPk, mediaPk);
			ConfirmationBean confirmationBean = new ConfirmationBean(
					personBean.getConfirmationUuid(), personPk, new Date());
			confirmationServiceImpl.addItem(confirmationBean);
			conn.commit();
		} catch (SQLException | DaoExeception ex) {
			try {
				conn.rollback();
			} catch (SQLException sqlEx) {
				throw new FamiliaException(sqlEx);
			}
			throw new FamiliaException(ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
				throw new FamiliaException(sqlEx);
			}
		}
		return 0;
	}

	@Override
	public void updateItem(FlowBean bean) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public FlowBean getItemByName(FlowBean bean) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Set<FlowBean> getItemsByName(FlowBean bean) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Collection<FlowBean> getAllItems() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private MediaBean prepeareMedia(PersonBean bean, String type)
			throws FamiliaException {
		MediaBean media = new MediaBean();
		media.setDate(new Date());
		media.setSource(bean.getDbFile());
		media.setPrimary(true);
		TypeBean mediaType = new TypeBean();
		mediaType.setType(type);
		mediaType = typesServiceImpl.getItemByName(mediaType);
		media.setTypes(mediaType);
		return media;
	}

}
