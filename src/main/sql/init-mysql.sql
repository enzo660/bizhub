CREATE TABLE IF NOT EXISTS site (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    description TEXT,
    userid BIGINT(20),
    PRIMARY KEY  (id),
    UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    business VARCHAR(64),
    title VARCHAR(64),
    email VARCHAR(64) NOT NULL,
    password VARCHAR(32),
    created DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;