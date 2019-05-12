CREATE DATABASE  IF NOT EXISTS `culturedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `culturedb`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: culturedb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alerts` (
  `id_alerts` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL,
  `variable_name` varchar(45) NOT NULL,
  `lower_limit` decimal(8,2) NOT NULL,
  `upper_limit` decimal(8,2) NOT NULL,
  `measured_value` decimal(8,2) NOT NULL,
  `culture_name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_alerts`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts`
--

LOCK TABLES `alerts` WRITE;
/*!40000 ALTER TABLE `alerts` DISABLE KEYS */;
INSERT INTO `alerts` VALUES (1,'2019-04-21 17:06:50','light',40.00,60.00,41.00,'3','manual',NULL,5,NULL),(2,'2019-04-21 17:07:55','temperature',20.00,30.00,31.00,'3','sensor',2,NULL,NULL);
/*!40000 ALTER TABLE `alerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultures`
--

DROP TABLE IF EXISTS `cultures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cultures` (
  `culture_id` int(11) NOT NULL AUTO_INCREMENT,
  `culture_name` varchar(45) NOT NULL,
  `culture_responsible` varchar(45) NOT NULL,
  `culture_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`culture_id`),
  KEY `culture responsible_idx` (`culture_responsible`),
  CONSTRAINT `culture responsible` FOREIGN KEY (`culture_responsible`) REFERENCES `users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultures`
--

LOCK TABLES `cultures` WRITE;
/*!40000 ALTER TABLE `cultures` DISABLE KEYS */;
INSERT INTO `cultures` VALUES (1,'Tomates','luis@teste.pt','cultura de tomates');
/*!40000 ALTER TABLE `cultures` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_INSERT_cultures` AFTER INSERT ON `cultures` FOR EACH ROW BEGIN
insert into logs_cultures values (null, new.culture_id, new.culture_name, new.culture_description, new.culture_responsible, "Insert", null, (select USER()), (select NOW()), default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_UPDATE_cultures` AFTER UPDATE ON `cultures` FOR EACH ROW BEGIN

declare cf varchar(100);

set @cf := "";

if(new.culture_id <> old.culture_id) then	
if(@cf <> "") then
set @cf := concat(@cf, ",culture_id");
else
set @cf := concat(@cf, "culture_id");
end if;
end if;
if(new.culture_name <> old.culture_name) then
if(@cf <> "") then
set @cf := concat(@cf, ",culture_name");
else
set @cf := concat(@cf, "culture_name");
end if;
end if;
if(new.culture_description <> old.culture_description) then
if(@cf <> "") then
set @cf := concat(@cf, ",culture_description");
else
set @cf := concat(@cf, "culture_description");
end if;
end if;
if(new.culture_responsible <> old.culture_responsible) then
if(@cf <> "") then
set @cf := concat(@cf, ",culture_responsible");
else
set @cf := concat(@cf, "culture_responsible");
end if;
end if;

insert into logs_cultures values (null, new.culture_id, new.culture_name, new.culture_description, new.culture_responsible, "Update", @cf, (select USER()), (select NOW()), default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_DELETE_cultures` AFTER DELETE ON `cultures` FOR EACH ROW BEGIN
insert into logs_cultures values (null, old.culture_id, old.culture_name, old.culture_description, old.culture_responsible, "Delete", null, (select USER()), (select NOW()), default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `light_measures`
--

DROP TABLE IF EXISTS `light_measures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `light_measures` (
  `light_measures_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL,
  `measured_value` decimal(8,2) NOT NULL,
  `measure_id` int(11) NOT NULL,
  PRIMARY KEY (`light_measures_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `light_measures`
--

LOCK TABLES `light_measures` WRITE;
/*!40000 ALTER TABLE `light_measures` DISABLE KEYS */;
/*!40000 ALTER TABLE `light_measures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_cultures`
--

DROP TABLE IF EXISTS `logs_cultures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `logs_cultures` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `culture_id` int(11) NOT NULL,
  `culture_name` varchar(45) NOT NULL,
  `culture_description` varchar(45) NOT NULL,
  `culture_responsible` varchar(45) NOT NULL,
  `action` varchar(45) NOT NULL,
  `changed_fields` varchar(100) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  `date_time` timestamp NOT NULL,
  `migrated` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_cultures`
--

LOCK TABLES `logs_cultures` WRITE;
/*!40000 ALTER TABLE `logs_cultures` DISABLE KEYS */;
INSERT INTO `logs_cultures` VALUES (1,1,'Tomates','cultura de tomates','luis@teste.pt','Insert',NULL,'root@localhost','2019-05-10 18:41:19',_binary '\0');
/*!40000 ALTER TABLE `logs_cultures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_measures`
--

DROP TABLE IF EXISTS `logs_measures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `logs_measures` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `measure_id` int(11) NOT NULL,
  `measure_date_time` timestamp NOT NULL,
  `measure_value` varchar(45) NOT NULL,
  `measure_user` varchar(45) NOT NULL,
  `date_time` timestamp NOT NULL,
  `action` varchar(45) NOT NULL,
  `changed_fields` varchar(100) DEFAULT NULL,
  `migrated` bit(1) NOT NULL DEFAULT b'0',
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_measures`
--

LOCK TABLES `logs_measures` WRITE;
/*!40000 ALTER TABLE `logs_measures` DISABLE KEYS */;
INSERT INTO `logs_measures` VALUES (1,2,'2019-05-10 18:49:10','22.00','luis@teste.pt','2019-05-10 18:49:10','Insert',NULL,_binary '\0','root@localhost'),(2,3,'2019-05-10 18:50:10','22.50','luis@teste.pt','2019-05-10 18:54:18','Insert',NULL,_binary '\0','root@localhost'),(3,4,'2019-05-10 18:50:10','22.50','luis@teste.pt','2019-05-10 18:56:06','Insert',NULL,_binary '\0','root@localhost'),(4,5,'2019-05-10 18:58:10','22.60','luis@teste.pt','2019-05-10 18:56:06','Insert',NULL,_binary '\0','root@localhost'),(5,6,'2019-05-10 19:50:10','22.30','luis@teste.pt','2019-05-10 18:56:06','Insert',NULL,_binary '\0','root@localhost'),(6,7,'2019-05-11 07:10:10','22.20','luis@teste.pt','2019-05-10 18:56:06','Insert',NULL,_binary '\0','root@localhost'),(7,8,'2019-05-11 08:10:10','21.00','luis@teste.pt','2019-05-10 18:56:06','Insert',NULL,_binary '\0','root@localhost');
/*!40000 ALTER TABLE `logs_measures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_professional_categories`
--

DROP TABLE IF EXISTS `logs_professional_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `logs_professional_categories` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `professional_category` varchar(45) NOT NULL,
  `professional_categories` varchar(4) DEFAULT NULL,
  `cultures` varchar(4) DEFAULT NULL,
  `light_measures` varchar(4) DEFAULT NULL,
  `measures` varchar(4) DEFAULT NULL,
  `system` varchar(4) DEFAULT NULL,
  `temperature_measures` varchar(4) DEFAULT NULL,
  `users` varchar(4) DEFAULT NULL,
  `variables` varchar(4) DEFAULT NULL,
  `measured_variables` varchar(4) DEFAULT NULL,
  `alerts` varchar(4) DEFAULT NULL,
  `logs_users` varchar(4) DEFAULT NULL,
  `logs_measures` varchar(4) DEFAULT NULL,
  `logs_cultures` varchar(4) DEFAULT NULL,
  `logs_select` varchar(4) DEFAULT NULL,
  `logs_professional_categories` varchar(4) DEFAULT NULL,
  `addUser` varchar(4) DEFAULT NULL,
  `selectMeasures` varchar(4) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  `date_time` timestamp NOT NULL,
  `changed_fields` varchar(100) DEFAULT NULL,
  `action` varchar(45) NOT NULL,
  `migrated` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`log_id`,`professional_category`),
  UNIQUE KEY `professional_category_UNIQUE` (`professional_category`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_professional_categories`
--

LOCK TABLES `logs_professional_categories` WRITE;
/*!40000 ALTER TABLE `logs_professional_categories` DISABLE KEYS */;
INSERT INTO `logs_professional_categories` VALUES (1,'Administrador','CRUD',NULL,NULL,NULL,NULL,NULL,'CRUD','CRD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'X','X','root@localhost','2019-04-21 15:25:21',NULL,'Insert',_binary '\0'),(2,'Investigador',NULL,'CRU',NULL,'CRUD',NULL,NULL,NULL,NULL,'CRU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'X','root@localhost','2019-04-21 15:28:16',NULL,'Insert',_binary '\0'),(3,'Sistema','CR',NULL,'CR',NULL,NULL,'CR',NULL,NULL,NULL,'CR','CR','CR','CR','CR','CR',NULL,'X','root@localhost','2019-04-21 15:37:07',NULL,'Insert',_binary '\0'),(4,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'root@localhost','2019-05-07 15:33:48',NULL,'Insert',_binary '\0');
/*!40000 ALTER TABLE `logs_professional_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_select`
--

DROP TABLE IF EXISTS `logs_select`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `logs_select` (
  `log_id` int(11) NOT NULL,
  `query` varchar(100) NOT NULL,
  `user` varchar(45) NOT NULL,
  `date_time` timestamp NOT NULL,
  `migrated` bit(1) NOT NULL DEFAULT b'0',
  `table` varchar(45) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_select`
--

LOCK TABLES `logs_select` WRITE;
/*!40000 ALTER TABLE `logs_select` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_select` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_users`
--

DROP TABLE IF EXISTS `logs_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `logs_users` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `professional_category` varchar(45) NOT NULL,
  `date_time` timestamp NOT NULL,
  `action` varchar(45) NOT NULL,
  `changed_fields` varchar(45) DEFAULT NULL,
  `migrated` bit(1) NOT NULL DEFAULT b'0',
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_users`
--

LOCK TABLES `logs_users` WRITE;
/*!40000 ALTER TABLE `logs_users` DISABLE KEYS */;
INSERT INTO `logs_users` VALUES (1,1,'luis@teste.pt','luis','borro','2019-04-23 19:24:04','Insert',NULL,_binary '\0','root@localhost'),(2,1,'luis@teste.pt','luis','borro','2019-04-23 21:20:12','Delete',NULL,_binary '\0','root@localhost'),(3,2,'luis@teste.pt','Luis','Investigador','2019-04-23 21:21:04','Insert',NULL,_binary '\0','root@localhost'),(4,2,'luis@teste.pt','Luis','Investigador','2019-04-23 21:23:30','Delete',NULL,_binary '\0','root@localhost'),(5,3,'luis@teste.pt','Luis','Investigador','2019-04-23 21:23:37','Insert',NULL,_binary '\0','root@localhost'),(6,3,'luis@teste.pt','Luis','Investigador','2019-04-23 21:24:24','Delete',NULL,_binary '\0','root@localhost'),(7,4,'ines@teste.pt','Ines','Investigador','2019-04-23 21:25:00','Insert',NULL,_binary '\0','root@localhost'),(8,4,'ines@teste.pt','Ines','Investigador','2019-04-23 21:26:10','Delete',NULL,_binary '\0','root@localhost'),(9,1,'ines@teste.pt','ines','Investigador','2019-04-24 10:03:47','Insert',NULL,_binary '\0','root@localhost'),(10,2,'luis@teste.pt','luis almeida','Investigador','2019-05-06 21:23:47','Insert',NULL,_binary '\0','root@localhost'),(11,3,'teste@teste.pt','teste','Investigador','2019-05-07 13:20:08','Insert',NULL,_binary '\0','root@localhost'),(12,3,'teste@teste.pt','teste','Investigador','2019-05-07 13:24:20','Delete',NULL,_binary '\0','root@localhost'),(13,4,'teste@teste.pt','teste','Investigador','2019-05-07 13:24:51','Insert',NULL,_binary '\0','root@localhost'),(14,5,'pedro@test.pt','pedro','Investigador','2019-05-07 14:41:02','Insert',NULL,_binary '\0','root@localhost'),(15,6,'coco@teste.pt','coco','Investigador','2019-05-07 19:11:27','Insert',NULL,_binary '\0','root@localhost'),(16,1,'ines@teste.pt','ines','Investigador','2019-05-09 13:37:34','Delete',NULL,_binary '\0','root@localhost'),(17,2,'luis@teste.pt','luis almeida','Investigador','2019-05-09 13:37:34','Delete',NULL,_binary '\0','root@localhost'),(18,4,'teste@teste.pt','teste','Investigador','2019-05-09 13:37:34','Delete',NULL,_binary '\0','root@localhost'),(19,5,'pedro@test.pt','pedro','Investigador','2019-05-09 13:37:34','Delete',NULL,_binary '\0','root@localhost'),(20,6,'coco@teste.pt','coco','Investigador','2019-05-09 13:37:34','Delete',NULL,_binary '\0','root@localhost'),(21,7,'luis@teste.pt','luis almeida','Investigador','2019-05-09 13:43:49','Insert',NULL,_binary '\0','root@localhost'),(22,8,'ines@teste.pt','ines','Investigador','2019-05-09 14:00:54','Insert',NULL,_binary '\0','root@localhost'),(23,9,'testing@teste.pt','testing','Investigador','2019-05-10 10:22:17','Insert',NULL,_binary '\0','root@localhost');
/*!40000 ALTER TABLE `logs_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measured_variables`
--

DROP TABLE IF EXISTS `measured_variables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `measured_variables` (
  `measured_variables_id` int(11) NOT NULL AUTO_INCREMENT,
  `variable_id` int(11) NOT NULL,
  `culture_id` int(11) NOT NULL,
  `lower_limit` decimal(8,2) NOT NULL,
  `upper_limit(8,2)` varchar(45) NOT NULL,
  PRIMARY KEY (`measured_variables_id`),
  KEY `variable id_idx` (`variable_id`),
  KEY `culture id_idx` (`culture_id`),
  CONSTRAINT `culture id` FOREIGN KEY (`culture_id`) REFERENCES `cultures` (`culture_id`),
  CONSTRAINT `variable id` FOREIGN KEY (`variable_id`) REFERENCES `variables` (`variable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measured_variables`
--

LOCK TABLES `measured_variables` WRITE;
/*!40000 ALTER TABLE `measured_variables` DISABLE KEYS */;
INSERT INTO `measured_variables` VALUES (1,1,1,20.00,'26');
/*!40000 ALTER TABLE `measured_variables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measures`
--

DROP TABLE IF EXISTS `measures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `measures` (
  `measure_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL,
  `measured_value` decimal(8,2) NOT NULL,
  `user` varchar(45) NOT NULL,
  `measured_variable_id` int(11) NOT NULL,
  PRIMARY KEY (`measure_id`),
  KEY `mesures id_idx` (`measured_variable_id`),
  KEY `user _idx` (`user`),
  CONSTRAINT `mesures id` FOREIGN KEY (`measured_variable_id`) REFERENCES `measured_variables` (`measured_variables_id`),
  CONSTRAINT `user ` FOREIGN KEY (`user`) REFERENCES `users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measures`
--

LOCK TABLES `measures` WRITE;
/*!40000 ALTER TABLE `measures` DISABLE KEYS */;
INSERT INTO `measures` VALUES (2,'2019-05-10 18:49:10',22.00,'luis@teste.pt',1),(3,'2019-05-10 18:50:10',22.50,'luis@teste.pt',1),(4,'2019-05-10 18:50:10',22.50,'luis@teste.pt',1),(5,'2019-05-10 18:58:10',22.60,'luis@teste.pt',1),(6,'2019-05-10 19:50:10',22.30,'luis@teste.pt',1),(7,'2019-05-11 07:10:10',22.20,'luis@teste.pt',1),(8,'2019-05-11 08:10:10',21.00,'luis@teste.pt',1);
/*!40000 ALTER TABLE `measures` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_INSERT_measures` AFTER INSERT ON `measures` FOR EACH ROW BEGIN
insert into logs_measures value (null, new.measure_id, new.date_time, new.measured_value, new.user, (select now()), "Insert", null, default, (select user()));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_UPDATE_measures` AFTER UPDATE ON `measures` FOR EACH ROW BEGIN

declare cf varchar(100);
declare old_cf varchar(100);
if(new.measure_id <> old.measure_id) then
set cf = contact(old_cf, 'measure_id');
set old_cf = cf;
end if;
if(new.date_time <> old.date_time) then
set cf = concat(old_cf, 'date_time');
set old_cf = cf;
end if;
if(new.measured_value <> old.measured_value) then
set cf = concat(old_cf, 'measured_value');
set old_cf = cf;
end if;
if(new.user <> old.user) then
set cf = concat(old_cf, 'user');
set old_cf = cf;
end if;
if(new.measured_variable_id <> old.measured_variable_id) then
set cf = concat(old_cf, 'measured_variable_id');
set old_cf = cf;
end if;

insert into logs_measure value (null, new.measure_id, new.date_time , new.measured_value, new.user, (select now()), "Update", @cf, default, (select user()));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_DELETE_measures` AFTER DELETE ON `measures` FOR EACH ROW BEGIN
insert into logs_measure value (null, old.measure_id, old.date_time, old.measured_value, old.user, (select now()), "Delete", null, default, (select user()));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `professional_categories`
--

DROP TABLE IF EXISTS `professional_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `professional_categories` (
  `professional_category` varchar(45) NOT NULL,
  `professional_categories` varchar(4) DEFAULT NULL,
  `cultures` varchar(4) DEFAULT NULL,
  `light_measures` varchar(4) DEFAULT NULL,
  `temperature_measures` varchar(4) DEFAULT NULL,
  `measures` varchar(4) DEFAULT NULL,
  `measured_variables` varchar(4) DEFAULT NULL,
  `variables` varchar(4) DEFAULT NULL,
  `users` varchar(4) DEFAULT NULL,
  `system` varchar(4) DEFAULT NULL,
  `alerts` varchar(4) DEFAULT NULL,
  `logs_users` varchar(4) DEFAULT NULL,
  `logs_measures` varchar(4) DEFAULT NULL,
  `logs_cultures` varchar(4) DEFAULT NULL,
  `logs_select` varchar(4) DEFAULT NULL,
  `logs_professional_categories` varchar(4) DEFAULT NULL,
  `addUser` varchar(4) DEFAULT NULL,
  `selectMeasures` varchar(4) DEFAULT NULL,
  `add_user` varchar(255) DEFAULT NULL,
  `select_measures` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`professional_category`),
  UNIQUE KEY `professional_category_UNIQUE` (`professional_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_categories`
--

LOCK TABLES `professional_categories` WRITE;
/*!40000 ALTER TABLE `professional_categories` DISABLE KEYS */;
INSERT INTO `professional_categories` VALUES ('ADMIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Administrador','CRUD',NULL,NULL,NULL,NULL,NULL,'CRD','CRUD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'X','X',NULL,NULL),('Investigador',NULL,'CRU',NULL,NULL,'CRUD','CRU',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'X',NULL,NULL),('Sistema','CR',NULL,'CR','CR',NULL,NULL,NULL,NULL,NULL,'CR','CR','CR','CR','CR','CR',NULL,'X',NULL,NULL);
/*!40000 ALTER TABLE `professional_categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_INSERT_professional_categories` AFTER INSERT ON `professional_categories` FOR EACH ROW BEGIN
insert into logs_professional_categories
value (null, new.professional_category, new.professional_categories, new.cultures, new.light_measures, new.measures, new.system, new.temperature_measures, new.users, new.variables, new.measured_variables, new.alerts, new.logs_users, new.logs_measures, new.logs_cultures, new.logs_select, new.logs_professional_categories, new.addUser, new.selectMeasures, (SELECT USER()), (SELECT NOW()), null, "Insert", default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_UPDATE_professional_categories` AFTER UPDATE ON `professional_categories` FOR EACH ROW BEGIN

declare cf varchar(100);

set @cf := "";

if(new.professional_category <> old.professional_category) then	
if(@cf <> "") then
set @cf := concat(@cf, ",professional_category");
else
set @cf := concat(@cf, "professional_category");
end if;
end if;
if(new.professional_categories <> old.professional_categories) then
if(@cf <> "") then
set @cf := concat(@cf, ",professional_categories");
else
set @cf := concat(@cf, "professional_categories");
end if;
end if;
if(new.cultures <> old.cultures) then
if(@cf <> "") then
set @cf := concat(@cf, ",cultures");
else
set @cf := concat(@cf, "cultures");
end if;
end if;
if(new.light_measures <> old.light_measures) then
if(@cf <> "") then
set @cf := concat(@cf, ",light_measures");
else
set @cf := concat(@cf, "light_measures");
end if;
end if;
if(new.logs_users <> old.logs_users) then
if(@cf <> "") then
set @cf := concat(@cf, ",logs_users");
else
set @cf := concat(@cf, "logs_users");
end if;
end if;
if(new.logs_measures <> old.logs_measures) then
if(@cf <> "") then
set @cf := concat(@cf, ",logs_measures");
else
set @cf := concat(@cf, "logs_measures");
end if;
end if;
if(new.logs_cultures <> old.logs_cultures) then
if(@cf <> "") then
set @cf := concat(@cf, ",logs_cultures");
else
set @cf := concat(@cf, "logs_cultures");
end if;
end if;
if(new.measures <> old.measures) then
if(@cf <> "") then
set @cf := concat(@cf, ",measures");
else
set @cf := concat(@cf, "measures");
end if;
end if;
if(new.system <> old.system) then
if(@cf <> "") then
set @cf := concat(@cf, ",system");
else
set @cf := concat(@cf, "system");
end if;
end if;
if(new.temperature_measures <> old.temperature_measures) then
if(@cf <> "") then
set @cf := concat(@cf, ",temperature_measures");
else
set @cf := concat(@cf, "temperature_measures");
end if;
end if;
if(new.users <> old.users) then
if(@cf <> "") then
set @cf := concat(@cf, ",users");
else
set @cf := concat(@cf, "users");
end if;
end if;
if(new.variables <> old.variables) then
if(@cf <> "") then
set @cf := concat(@cf, ",variables");
else
set @cf := concat(@cf, "variables");
end if;
end if;
if(new.measured_variables <> old.measured_variables) then
if(@cf <> "") then
set @cf := concat(@cf, ",measured_variables");
else
set @cf := concat(@cf, "measured_variables");
end if;
end if;
if(new.alerts <> old.alerts) then
if(@cf <> "") then
set @cf := concat(@cf, ",alerts");
else
set @cf := concat(@cf, "alerts");
end if;
end if;
if(new.logs_select <> old.logs_select) then
if(@cf <> "") then
set @cf := concat(@cf, ",logs_select");
else
set @cf := concat(@cf, "logs_select");
end if;
end if;
if(new.logs_professional_categories <> old.logs_professional_categories) then
if(@cf <> "") then
set @cf := concat(@cf, ",logs_professional_categories");
else
set @cf := concat(@cf, "logs_professional_categories");
end if;
end if;
if(new.addUser <> old.addUser) then
if(@cf <> "") then
set @cf := concat(@cf, ",addUser");
else
set @cf := concat(@cf, "addUser");
end if;
end if;
if(new.selectMeasures <> old.selectMeasures) then
if(@cf <> "") then
set @cf := concat(@cf, ",selectMeasures");
else
set @cf := concat(@cf, "selectMeasures");
end if;
end if;

insert into logs_professional_categories
value (null, new.professional_category, new.professional_categories, new.cultures, new.light_measures, new.measures, new.system, new.temperature_measures, new.users, new.variables, new.measured_variables, new.alerts, new.logs_users, new.logs_measures, new.logs_cultures, new.logs_select, new.logs_professional_categories, new.addUser, new.selectMeasures, (SELECT USER()), (SELECT NOW()), null, "Update", default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_DELETE_professional_categories` AFTER DELETE ON `professional_categories` FOR EACH ROW BEGIN
insert into logs_professional_categories
value (null, old.professional_category, old.professional_categories, old.cultures, old.light_measures, old.measures, old.system, old.temperature_measures, old.users, old.variables, old.measured_variables, old.alerts, old.logs_users, old.logs_measures, old.logs_cultures, old.logs_select, old.logs_professional_categories, old.addUser, old.selectMeasures, (SELECT USER()), (SELECT NOW()), null, "Delete", default);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id_Role` int(11) NOT NULL AUTO_INCREMENT,
  `role` char(50) NOT NULL,
  PRIMARY KEY (`id_Role`,`role`),
  UNIQUE KEY `id_Role_UNIQUE` (`id_Role`),
  UNIQUE KEY `role_name_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Investigador'),(2,'Administrador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system`
--

DROP TABLE IF EXISTS `system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `system` (
  `sensor_id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor_name` varchar(45) NOT NULL,
  `lower_limit` decimal(8,2) NOT NULL,
  `upper_limit` decimal(8,2) NOT NULL,
  PRIMARY KEY (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system`
--

LOCK TABLES `system` WRITE;
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
/*!40000 ALTER TABLE `system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temperature_measure`
--

DROP TABLE IF EXISTS `temperature_measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `temperature_measure` (
  `temperature_measure_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL,
  `measured_value` decimal(8,2) NOT NULL,
  `measure_id` int(11) NOT NULL,
  PRIMARY KEY (`temperature_measure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temperature_measure`
--

LOCK TABLES `temperature_measure` WRITE;
/*!40000 ALTER TABLE `temperature_measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `temperature_measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `user_role` char(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `id_role_UNIQUE` (`id_role`),
  KEY `roles_idx` (`user_role`),
  CONSTRAINT `role_name` FOREIGN KEY (`user_role`) REFERENCES `roles` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (5,'Investigador','luis@teste.pt'),(6,'Investigador','ines@teste.pt'),(7,'Investigador','testing@teste.pt');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `professional_category` varchar(45) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `enabled` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`email`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (7,'luis@teste.pt','luis almeida','Investigador','123456789',NULL),(8,'ines@teste.pt','ines','Investigador','123456789',NULL),(9,'testing@teste.pt','testing','Investigador','123456789',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_INSERT_users` AFTER INSERT ON `users` FOR EACH ROW BEGIN
insert into logs_users
value (null, new.user_id, new.email, new.name, new.professional_category, (SELECT NOW()), "Insert", null, default, (select user()));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_UPDATE_users` AFTER UPDATE ON `users` FOR EACH ROW BEGIN

declare cf varchar(100);

set @cf := "";

if(new.user_id <> old.user_id) then	
if(@cf <> "") then
set @cf := concat(@cf, ",user_id");
else
set @cf := concat(@cf, "user_id");
end if;
end if;
if(new.email <> old.email) then
if(@cf <> "") then
set @cf := concat(@cf, ",email");
else
set @cf := concat(@cf, "email");
end if;
end if;
if(new.name <> old.name) then
if(@cf <> "") then
set @cf := concat(@cf, ",name");
else
set @cf := concat(@cf, "name");
end if;
end if;
if(new.professional_category <> old.professional_category) then
if(@cf <> "") then
set @cf := concat(@cf, ",professional_category");
else
set @cf := concat(@cf, "professional_category");
end if;
end if;

insert into logs_users 
value (null, new.user_id, new.email, new.name, new.professional_category, (SELECT NOW()), "Update", @cf, default, (select user()));

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `register_log_AFTER_DELETE_users` AFTER DELETE ON `users` FOR EACH ROW BEGIN
insert into logs_users
value (null, old.user_id, old.email, old.name, old.professional_category, (SELECT NOW()), "Delete", null, default, (select user()));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `variables`
--

DROP TABLE IF EXISTS `variables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `variables` (
  `variable_id` int(11) NOT NULL AUTO_INCREMENT,
  `variable_name` varchar(45) NOT NULL,
  PRIMARY KEY (`variable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variables`
--

LOCK TABLES `variables` WRITE;
/*!40000 ALTER TABLE `variables` DISABLE KEYS */;
INSERT INTO `variables` VALUES (1,'Temperature'),(2,'Light');
/*!40000 ALTER TABLE `variables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'culturedb'
--

--
-- Dumping routines for database 'culturedb'
--
/*!50003 DROP PROCEDURE IF EXISTS `addUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser`(in name char(20), in email char(30), in professional_category char(30), in password char(32))
begin

	DECLARE counter int;
	DECLARE num_rows int;
	DECLARE col_name TEXT;
	DECLARE col_names CURSOR FOR
	SELECT column_name
	FROM INFORMATION_SCHEMA.COLUMNS
	WHERE table_name = 'professional_categories'
	ORDER BY ordinal_position;
  
	SET counter = 1;
	SET num_rows = (SELECT COUNT(*)
	FROM INFORMATION_SCHEMA.COLUMNS
	WHERE table_name = 'professional_categories');

if exists ( select * from professional_categories where professional_category = professional_category ) then
	insert into user_roles values(null, professional_category, email);
    insert into users values(null, email, name, professional_category,password,null);
	set @query1 := concat("CREATE USER '", email ,"' @localhost IDENTIFIED BY '",password,"' ");
	PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;

	OPEN col_names;
	the_loop: LOOP

	IF counter > num_rows THEN
        CLOSE col_names;
        LEAVE the_loop;
	END IF;

    FETCH col_names 
    INTO col_name;     
		
        set @outvar = '';
        set @col = concat("select professional_categories.", col_name , " from professional_categories where professional_category='",professional_category,"' into @outvar"); 
		PREPARE stmt1 FROM @col;  
        EXECUTE stmt1;
        DEALLOCATE PREPARE stmt1;
        
        set @query2 = '';
		if @outvar = 'CRUD' then
			set @query2 := concat("GRANT SELECT, INSERT, UPDATE, DELETE ON culturedb.", col_name, " TO '" , email , "'@'localhost'");
	    elseif @outvar = 'CRD' then
			set @query2 := concat("GRANT SELECT, INSERT, DELETE ON culturedb.",col_name," TO '" , email , "'@'localhost'");
		elseif @outvar = 'CRU' then
			set @query2 := concat("GRANT SELECT, INSERT, UPDATE ON culturedb.",col_name," TO '" , email , "'@'localhost'");
		elseif @outvar = 'CR' then
			set @query2 := concat("GRANT SELECT, INSERT ON culturedb.",col_name," TO '" , email , "'@'localhost'");
		elseif @outvar = 'X' then
			set @query2 := concat("GRANT EXECUTE ON PROCEDURE culturedb.",col_name," TO '", email ,"'@'localhost'");
		end if;
        if @query2 != '' then
		 PREPARE stmt2 FROM @query2;  
         EXECUTE stmt2;
         DEALLOCATE PREPARE stmt2;
        end if;
        
    SET counter = counter + 1;  
	END LOOP the_loop;

end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `anyQuery` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `anyQuery`(in selectcommand text)
BEGIN
	set @query = selectcommand;
		PREPARE stmt1 FROM @query;  
        EXECUTE stmt1;
        DEALLOCATE PREPARE stmt1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserPassword` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserPassword`(in email char(30))
BEGIN
select authentication_string from mysql.user where user = email;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectMeasures` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectMeasures`(in selectCommand TEXT)
begin	
	set @command = LOWER(selectCommand);
    
    set @aux1 = '';
    SELECT substring_index(@command , " " , 1 ) as first_word into @aux1;
    
    set @aux2 = '';
    SELECT substring_index(@command , "from " , -1 ) as table_name into @aux2;
    
    if @aux1 != ' ' then
		if @aux1 = 'select' then
			if @aux2 = 'measures' then
				PREPARE stmt FROM @command;
				EXECUTE stmt;
				DEALLOCATE PREPARE stmt;
				insert into logs_select values (null, @command, (select User()), (select now()), 0, @aux2);
			end if;
        end if;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-12 17:38:33
