package com.ra.familia.dao;

public final class DaoFactory {
	
	private static DaoFactory daoFactory;
	private static PersonDao personDao = new PersonDao();
	private static PersonGroupDao personGroupDao = new PersonGroupDao();
	private static TypesDao typesDao = new TypesDao();
	
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
}
