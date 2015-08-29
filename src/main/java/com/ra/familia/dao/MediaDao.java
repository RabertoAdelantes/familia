package com.ra.familia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.MediaBean;

public class MediaDao extends AbstractDao<MediaBean> {
	private static final Logger LOG = LoggerFactory.getLogger(MediaDao.class);
	private static String SELECT = "SELECT * FROM MEDIA";

	public MediaDao() {

	}
	
	public Collection<MediaBean> getAllItems() {
		return getAllItems(SELECT);
	}

	private MediaBean fillBean(final ResultSet rs) throws SQLException {
		MediaBean media = new MediaBean();
		media.setNotes(rs.getString("NAME"));
		media.setSource(rs.getString("TYPE"));
		//media.setType(TypeBean.TYPES.AUDIO);
		return media;
	}

	@Override
	public Set<MediaBean> fillBeans(final ResultSet rs) throws SQLException {
		Set<MediaBean> types = new HashSet<MediaBean>();
		while (rs.next()) {
			MediaBean mediaBean = fillBean(rs);
			types.add(mediaBean);
		}
		return types;
	}

	@Override
	void addItem(MediaBean bean) {
	}

	@Override
	void updateItem(MediaBean bean) {		
	}
}
