package com.ra.familia.dao.constants;

import static com.ra.familia.dao.constants.TablesConstants.*;

public final class QueriesConstants {

	private QueriesConstants() {

	}

	// ****************************** GENERAL
	private static final String SELECT_FROM = "SELECT * FROM ";
	public static final String WHERE = " WHERE ";
	public static final String COMA = " , ";
	public static final String LIKE = " LIKE ";
	public static final String OR = " OR ";
	public static final String AND = " AND ";
	// ****************************** PERSONS
	public static final String SELECT_PERSONS = SELECT_FROM + P_TABLE;
	public static final String SELECT_PERSONS_FULL = "SELECT " + P_TABLE + "."
			+ P_FIRST_NAME + ", " + P_TABLE + "." + P_MIDLE_NAME + ", "
			+ P_TABLE + "." + P_LAST_NAME + ", " + P_TABLE + "." + P_LAST_NAME2
			+ ", " + P_TABLE + "." + P_PASSWORD + ", " + P_TABLE + "."
			+ P_EMAIL + ", " + P_TABLE + "." + P_DATE_BIRTH + ", " + P_TABLE
			+ "." + P_DATE_DEATH + ", " + P_TABLE + "." + P_ISACTIVE + ", "
			+ P_TABLE + "." + P_ISDELETED + ", " + P_TABLE + "." + P_GROUP_ID
			+ "," + P_TABLE + "." + PK + "," + M_TABLE + "." + M_SOURCE
			+ " FROM " + P_TABLE + " LEFT JOIN " + MR_TABLE + " ON " + P_TABLE
			+ "." + PK + "=" + MR_TABLE + "." + MR_PERSON_FK + " LEFT JOIN "
			+ M_TABLE + " ON " + M_TABLE + "." + PK + "=" + MR_TABLE + "."
			+ MR_MEDIA_FK;

	public static final String INSERT_PERSONS = "INSERT INTO " + P_TABLE + " ("
			+ P_FIRST_NAME + ", " + P_MIDLE_NAME + ", " + P_LAST_NAME + ", "
			+ P_LAST_NAME2 + ", " + P_PASSWORD + ", " + P_EMAIL + ", "
			+ P_DATE_BIRTH + ", " + P_DATE_DEATH + ", " + P_ISACTIVE + ", "
			+ P_ISDELETED + ", " + PK + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_PERSONS = "UPDATE " + P_TABLE + " SET ";
	// ******************************** PERSON GROUP
	public static final String SELECT_GR = SELECT_FROM + G_PERSONGROUP;
	public static final String INSERT_GR = "INSERT INTO " + G_PERSONGROUP
			+ " (G_NAME, PK) VALUES (?,?,?)";
	// ****************************** MEDIA TYPES
	public static final String SELECT_TYPES = SELECT_FROM + T_TABLE;
	// ********************************* MEDIA
	public static final String SELECT_MEDIA = SELECT_FROM + M_TABLE;
	public static final String INSERT_MEDIA = "INSERT INTO " + M_TABLE + " ("
			+ M_EXTSOURCE + ", " + M_NOTES + ", " + M_TYPES + ", "
			+ M_LAST_UPDATES + ", " + M_ISPRIMARY + ", " + M_SOURCE + ", " + PK
			+ ") VALUES (?,?,?,?,?,?,?)";
	// ****************************** MEDIA REFERENCES
	public static final String SELECT_MEDIA_REF = SELECT_FROM + MR_TABLE;
	public static final String INSERT_MEDIA_REF = "INSERT INTO " + MR_TABLE
			+ " (" + MR_PERSON_FK + ", " + MR_MEDIA_FK + "," + PK
			+ ") VALUES (?,?,?)";

	// ****************************** MEDIA TYPES
	public static final String SELECT_REFERENCES = SELECT_FROM + R_TABLE;
	public static final String INSERT_REFERENCES = "INSERT INTO " + R_TABLE
			+ "(" + R_PERSON_FK + "," + R_PERSON_RELATION_FK + "," + R_TYPES_FK
			+ "," + PK + " values(?,?,?,?)";

	// ****************************** PLACES TYPES
	public static final String SELECT_PLACES = SELECT_FROM + PL_TABLE;
	public static final String INSERT_PALCES = "INSERT INTO " + PL_TABLE + "("
			+ R_PERSON_FK + "," + PK + " values(?,?,?)";
	// *************************** CONFIRMATION TYPES
	public static final String INSERT_CONFIRMATION = "INSERT INTO " + C_TABLE
			+ " (" + C_PERSON_FK + ", " + C_LINK + ", " + C_DATE + ", " + PK
			+ ") VALUES (?,?,?,?)";
	public static final String UPDATE_CONFIRMATION = "UPDATE " + C_TABLE
			+ " SET ";
	public static final String SELECT_CONFIRMATION = SELECT_FROM + C_TABLE;

	// ****************************** PERSONS RELATIONS
	public static final String INSERT_PERSONS_RELATIONS = "INSERT INTO "
			+ R_TABLE + " (" + R_PERSON_PK + ", " + R_PERSON_RELATION_FK + ", "
			+ R_TYPES_FK + ", " + PK + ") VALUES (?,?,?,?)";
	
	public static final String SELECT_RELATIVES_FOR_PERSON = 
			"select p1."+P_FIRST_NAME+",p1."+P_MIDLE_NAME+",p1."+P_LAST_NAME+",p1."+P_DATE_BIRTH+",innerTable.type from " + P_TABLE + 
			" p1 join (select pr1." + R_PERSON_RELATION_FK + ",pr1.types_fk,tp.type from " + P_TABLE + " p2 join " + R_TABLE + " pr1 on p2.pk=pr1."
			+ R_PERSON_PK + " and p2.pk =? "
			+ "join "+T_TABLE+" tp on tp.pk=pr1.types_fk) "
			+ "AS innerTable on innerTable." + R_PERSON_RELATION_FK + "= p1.pk"
			+ " order by "+P_LAST_NAME+","+P_FIRST_NAME;
}
