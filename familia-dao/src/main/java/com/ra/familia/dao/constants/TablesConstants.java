package com.ra.familia.dao.constants;

public final class TablesConstants {

	private TablesConstants()
	{
		
	}
	//****************************** SEQUENCES *********************************************
	public static final String SEQ_PERSON = "seq_person";
	public static final String SEQ_TYPES = "seq_types";
	public static final String SEQ_MEDIA = "seq_media";
	public static final String SEQ_GROUP = "seq_group";
	public static final String SEQ_PLACE = "seq_place";
	public static final String SEQ_MEDIA_REF = "seq_media_ref";
	public static final String SEQ_PLACE_REF = "seq_place_ref";
	public static final String SEQ_CONFIRM_REF = "seq_confirm_ref";
	public static final String SEQ_PERSON_REF = "seq_person_ref";
	
	//****************************** PERSONS *********************************************
	public static final String P_TABLE = "PERSON";
	public static final String PK = "PK";
	public static final String P_EMAIL = "EMAIL";
	public static final String P_PASSWORD = "PASSWORD";
	public static final String P_FIRST_NAME = "FIRST_NAME";
	public static final String P_MIDLE_NAME = "MIDLE_NAME";
	public static final String P_LAST_NAME = "LAST_NAME";
	public static final String P_LAST_NAME2 = "LAST_NAME2";
	public static final String P_DATE_BIRTH = "DATE_BIRTH";
	public static final String P_DATE_DEATH = "DATE_DEATH";	
	public static final String P_ISACTIVE = "ISACTIVE";
	public static final String P_ISDELETED = "ISDELETED";
	public static final String P_GROUP_ID = "GROUPID";
	public static final String IS_ADMIN = "1";
	public static final String IMG_SUFFIX ="IMG";
	//******************************** PERSON GROUP *******************************************
	public static final String G_PERSONGROUP = "USERGROUP";
	public static final String G_NAME = "NAME";
	//****************************** MEDIA TYPES *******************************************
	public static final String T_TABLE = "TYPES";
	public static final String T_NM = "NAME";
	public static final String T_TP = "TYPE";
	//********************************* MEDIA **********************************************
	public static final String M_TABLE = "MEDIA";
	public static final String M_TYPES = "TYPES_PK";
	public static final String M_LAST_UPDATES = "LAST_UPDATE";
	public static final String M_ISPRIMARY = "ISPRIMARY";
	public static final String M_SOURCE = "SOURCE";
	public static final String M_EXTSOURCE = "EXTERNAL_SOURCE";
	public static final String M_NOTES = "NOTES";
	//********************************* MEDIA REF **********************************************
	public static final String MR_TABLE = "MEDIAREFERENCE";
	public static final String MR_PERSON_FK = "PERSON_FK";
	public static final String MR_MEDIA_FK = "MEDIA_FK";
	//********************************* PLACES **********************************************
	public static final String PL_TABLE = "PLACES";
	//********************************* PERSON REFERENCES **********************************************
	public static final String R_TABLE = "PERSONREFERENCE";
	public static final String R_PERSON_PK = "PERSON_PK";
	public static final String R_PERSON_FK = "PERSON_FK";
	public static final String R_PERSON_RELATION_FK = "PERSON_RELATION_ID";
	public static final String R_TYPES_FK = "TYPES_FK";
	//********************************* CONFIRMATION REFERENCES **********************************************
	public static final String C_TABLE = "USERCONFIRMATION";
	public static final String C_PERSON_FK = "PERSON_FK";
	public static final String C_LINK = "LINK";
	public static final String C_DATE = "EXPIRE_DATE";
	public static final String C_ISUSED = "ISUSED";
	
}
