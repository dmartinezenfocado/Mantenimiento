create database mantenimiento;


CREATE TABLE `equipos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `ano` varchar(100) DEFAULT NULL,
  `serie` varchar(100) NOT NULL,
  `ficha` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


CREATE TABLE `ordenes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_creacion` varchar(100) DEFAULT NULL,
  `fecha_cierre` varchar(100) DEFAULT NULL,
  `tipo_orden` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `equipo_fk` int(11) DEFAULT NULL,
  `estatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orden_fk_idx` (`equipo_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;



CREATE TABLE `materiales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material` varchar(45) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `orden_fk` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `condicion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;


CREATE  VIEW `ordenesv` AS select `e`.`ficha` AS `ficha`,`o`.`id` AS `id`,`o`.`fecha_creacion` AS `fecha_creacion`,`o`.`fecha_cierre` AS `fecha_cierre`,`o`.`tipo_orden` AS `tipo_orden`,`o`.`descripcion` AS `descripcion`,`o`.`equipo_fk` AS `equipo_fk`,`o`.`estatus` AS `estatus` from (`ordenes` `o` join `equipos` `e` on((`e`.`id` = `o`.`equipo_fk`)));



/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
   
/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
  
/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email)
VALUES ('delvin','$2a$10$J/p/wUOTnYD9xujf20ETv.JxLh8e6nJ0bXn9/k3qpjzhiQZyZfyaS', 'Delvin','Martinez','dmartinez@icq24.com');
  
  
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='delvin' and profile.type='ADMIN';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
