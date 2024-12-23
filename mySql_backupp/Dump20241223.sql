-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: emlakdb
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buildingage`
--

DROP TABLE IF EXISTS `buildingage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildingage` (
  `ageID` int NOT NULL AUTO_INCREMENT,
  `ageRange` varchar(8) NOT NULL,
  PRIMARY KEY (`ageID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildingage`
--

LOCK TABLES `buildingage` WRITE;
/*!40000 ALTER TABLE `buildingage` DISABLE KEYS */;
INSERT INTO `buildingage` VALUES (15,'0-5'),(16,'5-10'),(17,'10-20'),(18,'20+');
/*!40000 ALTER TABLE `buildingage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryID` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'Home'),(4,'Office');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `userID` int DEFAULT NULL,
  `listingNo` int DEFAULT NULL,
  KEY `userID` (`userID`),
  KEY `listingNo` (`listingNo`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`listingNo`) REFERENCES `listings` (`listingNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (945762,587420),(945763,587419),(945764,587417),(945765,587415),(945765,587414);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listings`
--

DROP TABLE IF EXISTS `listings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listings` (
  `listingNo` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `listingDate` date DEFAULT NULL,
  `categoryID` int DEFAULT NULL,
  `statusID` int DEFAULT NULL,
  `roomCountID` int DEFAULT NULL,
  `ageID` int DEFAULT NULL,
  `areaSqMeters` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `neighborhood` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`listingNo`),
  KEY `userID` (`userID`),
  KEY `categoryID` (`categoryID`),
  KEY `statusID` (`statusID`),
  KEY `roomCountID` (`roomCountID`),
  KEY `ageID` (`ageID`),
  CONSTRAINT `listings_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `listings_ibfk_2` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `listings_ibfk_3` FOREIGN KEY (`statusID`) REFERENCES `status` (`statusID`),
  CONSTRAINT `listings_ibfk_4` FOREIGN KEY (`roomCountID`) REFERENCES `roomcount` (`roomCountID`),
  CONSTRAINT `listings_ibfk_5` FOREIGN KEY (`ageID`) REFERENCES `buildingage` (`ageID`)
) ENGINE=InnoDB AUTO_INCREMENT=587421 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listings`
--

LOCK TABLES `listings` WRITE;
/*!40000 ALTER TABLE `listings` DISABLE KEYS */;
INSERT INTO `listings` VALUES (587412,945762,'2024-11-01',3,1,11,15,65,4500000.00,'Istanbul','Kadikoy','Moda'),(587413,945762,'2024-11-11',3,2,12,16,55,20000.00,'Istanbul','Uskudar','Altunizade'),(587414,945764,'2024-11-15',3,1,13,17,120,1500000.00,'Izmir','Karsiyaka','Bostanli'),(587415,945765,'2024-11-02',4,2,13,17,50,15000.00,'Antalya','Muratpasa','Lara'),(587416,945766,'2024-11-18',4,1,14,18,85,4200000.00,'Bursa','Nilufer','Gorukle'),(587417,945767,'2024-11-21',3,1,12,15,75,2750000.00,'Adana','Seyhan','Kurtulus'),(587418,945768,'2024-11-23',3,2,12,15,70,10000.00,'Samsun','Atakum','Denizevleri'),(587419,945769,'2024-12-03',3,1,12,16,65,9000.00,'Trabzon','Ortahisar','Kalkinma'),(587420,945770,'2024-11-18',4,2,12,16,125,850000.00,'Gaziantep','Sahinbey','Alleben');
/*!40000 ALTER TABLE `listings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomcount`
--

DROP TABLE IF EXISTS `roomcount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomcount` (
  `roomCountID` int NOT NULL AUTO_INCREMENT,
  `roomCount` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`roomCountID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomcount`
--

LOCK TABLES `roomcount` WRITE;
/*!40000 ALTER TABLE `roomcount` DISABLE KEYS */;
INSERT INTO `roomcount` VALUES (11,'1+1'),(12,'2+1'),(13,'3+1'),(14,'4+1');
/*!40000 ALTER TABLE `roomcount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `statusID` int NOT NULL AUTO_INCREMENT,
  `statusName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'For Sale'),(2,'For Rent');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `passwords` varchar(20) NOT NULL,
  `contactNo` varchar(20) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=945771 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (945762,'Mehmet','Yilmaz','mehmet@mail.com','1234','5551234567'),(945763,'Ayse','Demir','ayse@mail.com','abcde','5547895612'),(945764,'Ahmet','Celik','ahmet@mail.com','pass123','5539874561'),(945765,'Zeynep','Kaya','zeynep@mail.com','1q2w3e','5321234567'),(945766,'Selim','Sahin','selim@mail.com','98765','5069874561'),(945767,'Ebru','Arslan','ebru@mail.com','98765','5321239874'),(945768,'Fatma','Guler','fatma@mail.com','f123456','5547896512'),(945769,'Hasan','Koc','hasanko@mail.com','1234pass','5437896514'),(945770,'Ceyda','Ozcan','ceyda@mail.com','abc123','5047891234');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-23 15:41:20
