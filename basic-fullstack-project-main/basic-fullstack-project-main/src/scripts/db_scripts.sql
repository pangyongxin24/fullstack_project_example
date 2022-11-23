# SQL script to generate the database.

DROP DATABASE people_db;
CREATE DATABASE people_db;

USE people_db;

CREATE TABLE country(
id_country INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
PRIMARY KEY (id_country)
);

CREATE TABLE person(
id_person INTEGER NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
last_name VARCHAR(250) NOT NULL,
age INTEGER NOT NULL,
id_country INTEGER NOT NULL,
PRIMARY KEY (id_person),
FOREIGN KEY (id_country) REFERENCES country(id_country)
);

INSERT INTO country(id_country, name) VALUES (1,'China');
INSERT INTO country(id_country, name) VALUES (2,'Brazil');
INSERT INTO country(id_country, name) VALUES (3,'Mexico');
INSERT INTO country(id_country, name) VALUES (4,'United States');

INSERT INTO person(id_person, name, last_name, age, id_country) VALUES (NULL, 'Michael', 'Jordan', 50, 4);
INSERT INTO person(id_person, name, last_name, age, id_country) VALUES (NULL, 'Silver', 'Cactus', 21, 4);
INSERT INTO person(id_person, name, last_name, age, id_country) VALUES (NULL, 'Catty', 'Ta', 20, 1);
INSERT INTO person(id_person, name, last_name, age, id_country) VALUES (NULL, 'Charly', 'Montana', 43, 3);
INSERT INTO person(id_person, name, last_name, age, id_country) VALUES (NULL, 'Roberto', 'Carlos', 60, 2);
