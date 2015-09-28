-- types init data
INSERT INTO types(pk,  type) VALUES (1000, 'Audio');
INSERT INTO types(pk,  type) VALUES (1001, 'Photo');
INSERT INTO types(pk,  type) VALUES (1002, 'Video');
INSERT INTO types(pk,  type) VALUES (1003, 'Mama');
INSERT INTO types(pk,  type) VALUES (1004, 'Papa');
INSERT INTO types(pk,  type) VALUES (1005, 'Brother');
INSERT INTO types(pk,  type) VALUES (1006, 'Sister');
INSERT INTO types(pk,  type) VALUES (1007, 'GMama');
INSERT INTO types(pk,  type) VALUES (1008, 'GPapa');

-- user group init data 
INSERT INTO usergroup(name,pk) VALUES ('Admin',1000);
INSERT INTO usergroup(name,pk) VALUES ('User',1001);
-- users init data 
INSERT INTO person(first_name, midle_name, last_name, last_name2, password, email, 
            date_birth, date_death, isactive, isdeleted, groupid, pk)
    VALUES ('Admin', 'Admin', 'Admin', 'Admin', 'AdminPass!23$', 'Admin@Admin.com', 
            (TO_DATE('1988-08-08', 'yyyy-mm-dd')),(TO_DATE('2088-08-08', 'yyyy-mm-dd')), 1, 0, 1000, 100000);