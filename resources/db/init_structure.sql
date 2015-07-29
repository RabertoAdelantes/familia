------------------------- Sequence section ------------------------------
CREATE SEQUENCE seq_person MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_personref MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_types MINVALUE 1 START WITH 1INCREMENT BY 1 CACHE 100000;
CREATE SEQUENCE seq_media MINVALUE 1 START WITH 1 INCREMENT BY 1CACHE 100000;
CREATE SEQUENCE seq_group MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 100000;

------------------------- End Sequence section ------------------------------
CREATE TABLE Person (
   first_name varchar2(255)  NOT NULL,
   midle_name varchar2(255)  NOT NULL,
   last_name varchar2(255)  NOT NULL,
   last_name2 varchar2(255)  NULL,
   password varchar2(255)  NOT NULL,
   email varchar2(255)  NOT NULL,
   date_birth date  NOT NULL,
   date_death date  NULL,
   isActive integer  DEFAULT 0 NOT NULL,
   isDeleted integer  DEFAULT 1 NOT NULL,
   groupId integer  DEFAULT 1 NOT NULL,
   pk integer  NOT NULL,
   CONSTRAINT Person_pk PRIMARY KEY (pk)
);

CREATE TABLE UserGroup (
   name varchar2(255)  NOT NULL,
   pk integer  NOT NULL,
   CONSTRAINT Group_pk PRIMARY KEY (pk)
) ;

CREATE TABLE PersonReference (
   pk integer  NOT NULL,
   person_pk integer  NOT NULL,
   person_Rel_Id integer  NOT NULL,
   types_pk integer  NOT NULL,
   CONSTRAINT PersonReference_pk PRIMARY KEY (pk)
) ;
CREATE TABLE Types (
   pk integer  NOT NULL,
   name varchar2(255)  NOT NULL,
   type varchar2(100)  NOT NULL,
   CONSTRAINT Types_pk PRIMARY KEY (pk)
) ;
CREATE TABLE Media (
   source clob  NOT NULL,
   external_sourse varchar2(255) NULL,
   notes varchar2(4000)  NULL,
   person_pk integer  NOT NULL,
   types_pk integer  NOT NULL,
   pk integer  NOT NULL,
   "date" date  NULL,
   isPrimary smallint  DEFAULT 0 NOT NULL,
   CONSTRAINT Media_pk PRIMARY KEY (pk)
) ;
CREATE TABLE Places (
   pk integer  NOT NULL,
   name varchar2(255)  NOT NULL,
   notes varchar2(4000)  NOT NULL,
   types_pk integer  NOT NULL,
   person_pk integer  NOT NULL,
   start_date date  NOT NULL,
   end_date date  NOT NULL,
   CONSTRAINT Places_pk PRIMARY KEY (pk)
) ;
