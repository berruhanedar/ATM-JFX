-- Database Create
CREATE DATABASE user_management;

-- Select database
USE user_management;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     email VARCHAR(100) NOT NULL UNIQUE
);



-- ###################################################################################################################

-- Insert
MERGE INTO users (id, username, password, email) KEY(id) VALUES (1,'Berru','123456','berru@gmail.com' )

-- Select
select * FROM users;

-- Find User
SELECT  *  FROM users WHERE username="Berru" AND "123456";

-- Update
UPDATE users SET username="Berru", password="12345644", email="berru@gmail.com" WHERE id=1;

-- delete
DELETE FROM users  WHERE id=1;