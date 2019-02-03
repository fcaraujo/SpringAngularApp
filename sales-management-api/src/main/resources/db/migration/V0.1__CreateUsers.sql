CREATE TABLE users (
  id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  username      VARCHAR(100) NOT NULL,
  first_name    VARCHAR(50)  NOT NULL,
  
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;