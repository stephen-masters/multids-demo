DROP SCHEMA IF EXISTS `foo_schema`;
CREATE SCHEMA `foo_schema` DEFAULT CHARACTER SET utf8 ;
USE `foo_schema`;
CREATE TABLE foo (
    ID bigint NOT NULL AUTO_INCREMENT,
    Name varchar(5),
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP SCHEMA IF EXISTS `bar_schema`;
CREATE SCHEMA `bar_schema` DEFAULT CHARACTER SET utf8 ;
USE `bar_schema`;
CREATE TABLE bar (
    ID bigint NOT NULL AUTO_INCREMENT,
    Name varchar(5),
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
