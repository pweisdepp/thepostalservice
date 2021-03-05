/*
This script is the first time the MySQL database volume connects. Running
clean.sh in the root directory will both delete all current data, as well as
queue this and any other script in the init/ directory to be run the next time
the db server starts.
*/

USE postal;

CREATE TABLE IF NOT EXISTS addresses (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(2) NOT NULL,
    number VARCHAR(20),
    street VARCHAR(128),
    unit    VARCHAR(10),
    city    VARCHAR(40),
    district VARCHAR(128),
    region VARCHAR(128),
    post_code VARCHAR(30)
);
