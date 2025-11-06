# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
DECLARE done INT DEFAULT FALSE;
DECLARE c_fk_name CHAR(255);
DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
AND REFERENCED_TABLE_NAME IS NOT NULL;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

OPEN curs;

read_loop: LOOP
FETCH curs INTO c_fk_name;
IF done THEN
LEAVE read_loop;
END IF;
SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
PREPARE stmt FROM @sql;
EXECUTE stmt;
END LOOP;

CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
PREPARE stmt FROM @sql;
EXECUTE stmt;
END
$$
-- apply changes
create table boat (
  id                            integer auto_increment not null,
  kfz                           varchar(255),
  name                          varchar(255),
  constraint pk_boat primary key (id)
);

create table reservation (
  id                            integer auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  tel_nr                        integer,
  date                          varchar(255),
  time_start                    time,
  time_end                      time,
  boot_id                       integer,
  constraint pk_reservation primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  admin                         varchar(255),
  constraint pk_users primary key (id)
);

-- foreign keys and indices
create index ix_reservation_boot_id on reservation (boot_id);
alter table reservation add constraint fk_reservation_boot_id foreign key (boot_id) references boat (id) on delete restrict on update restrict;


# --- !Downs

-- drop all foreign keys
alter table reservation drop foreign key fk_reservation_boot_id;
drop index ix_reservation_boot_id on reservation;

-- drop all
drop table if exists boat;

drop table if exists reservation;

drop table if exists users;

