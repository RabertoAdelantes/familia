------------------------- Sequence section ------------------------------
CREATE SEQUENCE seq_person MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_personref MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_types MINVALUE 1 START WITH 1INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_media MINVALUE 1 START WITH 1 INCREMENT BY 1CACHE 100000;
CREATE SEQUENCE seq_group MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_place MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_media_ref MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_place_ref MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_confirm_ref MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;

------------------------- End Sequence section ------------------------------
CREATE TABLE Person (
   first_name varchar(255)  NOT NULL,
   midle_name varchar(255)  NOT NULL,
   last_name varchar(255)  NOT NULL,
   last_name2 varchar(255)  NULL,
   password varchar(255)  NOT NULL,
   email varchar(255)  NOT NULL,
   date_birth date  NOT NULL,
   date_death date  NULL,
   isActive integer  DEFAULT 0 NOT NULL,
   isDeleted integer  DEFAULT 1 NOT NULL,
   groupId integer  DEFAULT 1 NOT NULL,
   pk integer  NOT NULL,
   CONSTRAINT Person_pk PRIMARY KEY (pk)
);

CREATE TABLE UserGroup (
   name varchar(255)  NOT NULL,
   pk integer  NOT NULL,
   CONSTRAINT Group_pk PRIMARY KEY (pk)
) ;

CREATE TABLE PersonReference (
   pk integer  NOT NULL,
   person_pk integer  NOT NULL,
   person_relation_Id integer,
   types_fk integer  NOT NULL,
   CONSTRAINT PersonReference_pk PRIMARY KEY (pk)
) ;

CREATE TABLE MediaReference (
   pk integer  NOT NULL,
   person_fk integer  NOT NULL,
   media_fk integer,
   CONSTRAINT MadiaReference_pk PRIMARY KEY (pk)
);

CREATE TABLE PlaceReference (
   pk integer  NOT NULL,
   person_fk integer  NOT NULL,
   place_fk integer,
   CONSTRAINT PlaceReference_pk PRIMARY KEY (pk)
);

CREATE TABLE Types (
   pk integer  NOT NULL,
   type varchar(100)  NOT NULL,
   CONSTRAINT Types_pk PRIMARY KEY (pk)
) ;

CREATE TABLE Media (
   external_source varchar(255) NULL,
   notes varchar(4000)  NULL,
   types_pk integer  NOT NULL,
   pk integer  NOT NULL,
   last_update date  NULL,
   isPrimary smallint  DEFAULT 0 NOT NULL,
   CONSTRAINT Media_pk PRIMARY KEY (pk)
);
CREATE TABLE Places (
   pk integer  NOT NULL,
   name varchar(255)  NOT NULL,
   notes varchar(4000)  NOT NULL,
   types_fk integer  NOT NULL,
   person_fk integer  NOT NULL,
   start_date date  NOT NULL,
   end_date date  NOT NULL,
   CONSTRAINT Places_pk PRIMARY KEY (pk)
);

CREATE TABLE UserConfirmation (
   pk integer  NOT NULL,
   link varchar(255)  NOT NULL,  
   person_fk integer  NOT NULL,
   expire_date date,
   isUsed integer,
   CONSTRAINT UserConfirmation_pk PRIMARY KEY (pk)
);
