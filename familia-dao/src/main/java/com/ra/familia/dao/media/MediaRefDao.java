package com.ra.familia.dao.media;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import com.ra.familia.dao.AbstractDao;
import com.ra.familia.entities.MediaRefBean;
import com.ra.familia.exceptions.DaoExeception;

import static com.ra.familia.dao.constants.QueriesConstants.*;

public class MediaRefDao extends AbstractDao<MediaRefBean> {
	
	public Collection<MediaRefBean> getAllItems() {
		return getAllItems(SELECT_MEDIA);
	}

	@Override
	public MediaRefBean fillBean(final ResultSet rs) throws SQLException {
		MediaRefBean mediaRefBean = new MediaRefBean();
		mediaRefBean.setID(rs.getString(PK));
		mediaRefBean.setMedia_fk(rs.getString(MR_PERSON_FK));
		mediaRefBean.setPerson_fk(rs.getString(MR_MEDIA_FK));
		return mediaRefBean;
	}

	@Override
	public long addItem(MediaRefBean bean) throws DaoExeception {
		PreparedStatement preparedStatement = null;
		long primaryKey = getMediaRefSequence();
		try {
			Connection conn = getConnection();
			preparedStatement = conn.prepareStatement(INSERT_MEDIA_REF);
			preparedStatement.setInt(1, Integer.valueOf(bean.getPerson_fk()));
			preparedStatement.setInt(2, Integer.valueOf(bean.getMedia_fk()));
			preparedStatement.setLong(3, primaryKey);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getLocalizedMessage());
		} finally {
			closeStatment(preparedStatement);
		}
		return primaryKey;
	}

	@Override
	public void updateItem(MediaRefBean bean) {		
	}
}
