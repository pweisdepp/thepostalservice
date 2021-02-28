/*
This script is the first time the MySQL database volume connects. Running
clean.sh in the root directory will both delete all current data, as well as
queue this and any other script in the init/ directory to be run the next time
the db server starts.
*/

USE postal;

CREATE TABLE IF NOT EXISTS address (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(2) NOT NULL
);


INSERT INTO addresses (country) VALUES ('US'), ('DE');
