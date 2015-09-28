package com.ra.familia.dao;

import com.ra.familia.dao.confirmation.ConfirmationDao;
import com.ra.familia.dao.media.MediaDao;
import com.ra.familia.dao.media.MediaRefDao;
import com.ra.familia.dao.persons.PersonDao;
import com.ra.familia.dao.persons.PersonGroupDao;
import com.ra.familia.dao.types.TypesDao;

public final class DaoFactory {
	
	private static DaoFactory daoFactory;
	private static PersonDao personDao = new PersonDao();
	private static PersonGroupDao personGroupDao = new PersonGroupDao();
	private static MediaDao mediaDao = new MediaDao();
	private static MediaRefDao mediaRefDao = new MediaRefDao();
	private static TypesDao typesDao = new TypesDao();
	private static ConfirmationDao confirmationDao = new ConfirmationDao();
	
	private DaoFactory()
	{
		
	}
	
	public static DaoFactory getInstance()
	{
		if (daoFactory==null)
		{
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	
	public PersonDao getPersonDao()
	{
		return personDao;
	}
	
	public TypesDao getTypesDao()
	{
		return typesDao;
	}
	
	public PersonGroupDao getPersonGroupDao()
	{
		return personGroupDao;
	}

	public MediaRefDao getMediaRefDao() {
		return mediaRefDao;
	}

	public MediaDao getMediaDao() {
		return mediaDao;
	}
	
	public ConfirmationDao getConfirmationDao()
	{
		return confirmationDao;
	}

}
