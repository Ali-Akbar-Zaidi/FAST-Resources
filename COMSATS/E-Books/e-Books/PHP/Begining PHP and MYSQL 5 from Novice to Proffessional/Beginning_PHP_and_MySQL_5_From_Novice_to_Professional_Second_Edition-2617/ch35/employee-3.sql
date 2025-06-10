CREATE TABLE employee (
   rowID SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
   lastname VARCHAR(35) NOT NULL,
   firstname VARCHAR(35) NOT NULL,
   email VARCHAR(55) NOT NULL UNIQUE,
   INDEX name (lastname, firstname),
   primary key(rowID));