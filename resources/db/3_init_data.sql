INSERT INTO types(pk, name, type) VALUES (1000, 'Audio', 'Audio');
INSERT INTO types(pk, name, type) VALUES (1001, 'Photo', 'Photo');
INSERT INTO types(pk, name, type) VALUES (1002, 'Video', 'Video');

INSERT INTO usergroup(name, pk) VALUES ('Admin',1);
INSERT INTO usergroup(name, pk) VALUES ('User',2);

INSERT INTO person(first_name, midle_name, last_name, last_name2, password, email, 
            date_birth, date_death, isactive, isdeleted, groupid, pk, file_data, 
            photo)
    VALUES ('Admin', 'Admin', 'Admin', 'Admin', 'AdminPass!23$', 'Admin@Admin.com', 
            (TO_DATE('1988-08-08', 'yyyy-mm-dd')),(TO_DATE('2088-08-08', 'yyyy-mm-dd')), 1, 0, 0, 100000, null,null);