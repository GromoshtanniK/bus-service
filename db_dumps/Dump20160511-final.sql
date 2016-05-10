-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bus_service
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (40,18);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `route_stop`
--

LOCK TABLES `route_stop` WRITE;
/*!40000 ALTER TABLE `route_stop` DISABLE KEYS */;
INSERT INTO `route_stop` VALUES (193,59.994207556014665,30.330150639141575,'Остановка 1',0,40),(194,59.98440319214586,30.33358386668061,'Остановка 2',1,40);
/*!40000 ALTER TABLE `route_stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `route_user_sub`
--

LOCK TABLES `route_user_sub` WRITE;
/*!40000 ALTER TABLE `route_user_sub` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_user_sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stop_time`
--

LOCK TABLES `stop_time` WRITE;
/*!40000 ALTER TABLE `stop_time` DISABLE KEYS */;
INSERT INTO `stop_time` VALUES (28,9,50,193),(29,13,10,193),(30,16,35,194),(31,19,30,194),(32,21,40,194);
/*!40000 ALTER TABLE `stop_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','pass',2,''),(2,'vlad','pass',1,'gromoshtannik@gmail.com'),(3,'ira','pass',1,'grechko.irinka@gmail.com'),(4,'andrey','pass',1,'gentoo1993@gmail.com');
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

-- Dump completed on 2016-05-11  0:17:38