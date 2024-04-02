CREATE TABLE IF NOT EXISTS tour (
    tour_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40),
    description LONGTEXT,
    start_date DATE,
    end_date DATE,
    timestamp DATETIME,
    image VARCHAR(255),
    price INT,
    destination LONGTEXT,
    days INT,
    PRIMARY KEY (tour_id)
);

CREATE TABLE IF NOT EXISTS User(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(40),
	email VARCHAR(100),
	image LONGTEXT
);