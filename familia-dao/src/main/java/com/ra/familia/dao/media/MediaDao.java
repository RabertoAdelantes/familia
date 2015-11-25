package com.ra.familia.dao.media;

import static com.ra.familia.dao.constants.TablesConstants.*;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import com.ra.familia.dao.AbstractDao;
import com.ra.familia.entities.MediaBean;
import com.ra.familia.exceptions.DaoExeception;

import static com.ra.familia.dao.constants.QueriesConstants.*;

public class MediaDao extends AbstractDao<MediaBean> {
	
	public Collection<MediaBean> getAllItems() {
		return getAllItems(SELECT_MEDIA);
	}
	
	@Override
	public MediaBean fillBean(final ResultSet rs) throws SQLException {
		MediaBean media = new MediaBean();
		media.setNotes(rs.getString(M_NOTES));
		media.setDate(rs.getDate(M_LAST_UPDATES));
		media.setExternalSrc(rs.getString(M_EXTSOURCE));
		media.setID(rs.getString(PK));
		media.setPrimary(rs.getBoolean(M_ISPRIMARY));		
		byte[] bytes = rs.getBytes(M_SOURCE);
		if (bytes!=null&&bytes.length!=0)
		{
			media.setSource(bytes); 
		}
		return media;
	}

	@Override
	public long addItem(MediaBean bean) throws DaoExeception {
		Connection conn = getConnection();
		PreparedStatement preparedStatement = null;
		long pk = getMediaRefSequence();
		ByteArrayInputStream fis = null;
		try {
			preparedStatement = conn.prepareStatement(INSERT_MEDIA);
			preparedStatement.setString(1, bean.getExternalSrc());
			preparedStatement.setString(2, bean.getNotes());
			preparedStatement
					.setInt(3, Integer.valueOf(bean.getType().getID()));
			preparedStatement.setDate(4, getCurrentDate());
			preparedStatement.setInt(5,
					Boolean.compare(bean.isPrimary(), false));
			fis = new ByteArrayInputStream(bean.getSource());
			preparedStatement.setBinaryStream(6, fis);
			preparedStatement.setLong(7, pk);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlEx) {
			throw new DaoExeception(sqlEx.getLocalizedMessage());
		} finally {
			IOUtils.closeQuietly(fis);
			closeConnections(preparedStatement,conn);
		}
		return pk;
	}

	@Override
	public void updateItem(MediaBean bean) {		
	}
}
