CREATE DATABASE ChatMessage;
USE ChatMessage;
CREATE TABLE User (
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL,
	Password VARCHAR(100) NOT NULL,
	PRIMARY KEY(Name)  
);

INSERT INTO User VALUES("PuZhiwei","948805382@qq.com","123456"); 
SELECT * FROM User


CREATE TABLE ChatRecord (
	
);
