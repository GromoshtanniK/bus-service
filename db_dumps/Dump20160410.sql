CREATE DATABASE  IF NOT EXISTS `bus_service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bus_service`;
-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: bus_service
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `route_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `route_number_UNIQUE` (`route_number`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (38,56),(35,103),(36,123),(37,345);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_stop`
--

DROP TABLE IF EXISTS `route_stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route_stop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `altitude` double NOT NULL,
  `latitude` double NOT NULL,
  `stop_name` varchar(45) DEFAULT NULL,
  `is_back_way` tinyint(4) DEFAULT NULL,
  `route_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `route_id_idx` (`route_id`),
  CONSTRAINT `route_id` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_stop`
--

LOCK TABLES `route_stop` WRITE;
/*!40000 ALTER TABLE `route_stop` DISABLE KEYS */;
INSERT INTO `route_stop` VALUES (164,59.9997104808241,30.38988879831951,'?????',0,35),(165,59.968400341125516,30.311954533184384,'ОБРАТНО',1,35),(166,59.98853169949209,30.348690067851873,'',1,35),(167,60.001945783904596,30.308864628399142,'',0,35),(168,59.97545631710727,30.341136967265463,'',0,35),(169,59.97149827300344,30.33804706248114,'',1,35),(170,59.99919462118232,30.358303104961845,'',0,35),(171,60.0003982836669,30.257366215313088,'',1,35),(172,60.00160190147767,30.31744769724685,'333',0,35),(173,60.00057023224121,30.340879475201138,'',1,35),(174,59.99076776033138,30.298564945782203,'',0,36),(175,59.96753975346727,30.30783466013767,'',0,36),(176,59.946620786853785,30.330282036587466,'',0,36),(177,59.92870412180518,30.323415581509327,'',0,36),(178,59.8962916499397,30.316549126431212,'',0,36),(179,60.01475308286598,30.28702336959527,'',0,36),(180,60.00718977419968,30.296636406704657,'',1,36),(181,59.984489431231246,30.30418950729058,'12334',1,36),(182,59.95488674148655,30.323415581509327,'',1,36),(183,59.932839598890716,30.335088555142143,'',1,36),(184,59.90836383411222,30.321355644985893,'',1,36),(185,60.000226335006055,30.349720036114164,'ggh',1,37),(186,60.000398284162124,30.323970829571273,'123',0,38),(187,59.98870371070145,30.331867252911117,'',1,38);
/*!40000 ALTER TABLE `route_stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_user_sub`
--

DROP TABLE IF EXISTS `route_user_sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route_user_sub` (
  `route_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`route_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_user_sub`
--

LOCK TABLES `route_user_sub` WRITE;
/*!40000 ALTER TABLE `route_user_sub` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_user_sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stop_time`
--

DROP TABLE IF EXISTS `stop_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stop_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hours` int(11) NOT NULL,
  `minutes` int(11) NOT NULL,
  `route_stop_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `route_stop_id_idx` (`route_stop_id`),
  CONSTRAINT `route_stop_id` FOREIGN KEY (`route_stop_id`) REFERENCES `route_stop` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop_time`
--

LOCK TABLES `stop_time` WRITE;
/*!40000 ALTER TABLE `stop_time` DISABLE KEYS */;
INSERT INTO `stop_time` VALUES (7,54,55,181),(8,54,55,181),(9,54,55,181),(10,673,44534,181),(11,673,44534,181),(12,673,44534,181),(13,75634,234,181),(14,75634,234,181),(15,75634,234,181),(16,12,12,172),(17,3,2,185),(18,10,15,186),(19,20,10,186);
/*!40000 ALTER TABLE `stop_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` int(1) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','password',2,'gromoshtannik@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-10 23:03:37
