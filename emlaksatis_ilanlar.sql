-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: emlaksatis
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
-- Table structure for table `ilanlar`
--

DROP TABLE IF EXISTS `ilanlar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ilanlar` (
  `ilanNo` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `ilanTarihi` date DEFAULT NULL,
  `KategoriID` int DEFAULT NULL,
  `DurumID` int DEFAULT NULL,
  `OdaSayisiID` int DEFAULT NULL,
  `YasID` int DEFAULT NULL,
  `Metrekare` int DEFAULT NULL,
  `Fiyat` decimal(10,2) DEFAULT NULL,
  `Sehir` varchar(50) DEFAULT NULL,
  `ilce` varchar(50) DEFAULT NULL,
  `Mahalle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ilanNo`),
  KEY `userID` (`userID`),
  KEY `KategoriID` (`KategoriID`),
  KEY `DurumID` (`DurumID`),
  KEY `OdaSayisiID` (`OdaSayisiID`),
  KEY `YasID` (`YasID`),
  CONSTRAINT `ilanlar_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `ilanlar_ibfk_2` FOREIGN KEY (`KategoriID`) REFERENCES `kategori` (`KategoriID`),
  CONSTRAINT `ilanlar_ibfk_3` FOREIGN KEY (`DurumID`) REFERENCES `durum` (`DurumID`),
  CONSTRAINT `ilanlar_ibfk_4` FOREIGN KEY (`OdaSayisiID`) REFERENCES `oda_sayisi` (`OdaSayisiID`),
  CONSTRAINT `ilanlar_ibfk_5` FOREIGN KEY (`YasID`) REFERENCES `binayas` (`YasID`)
) ENGINE=InnoDB AUTO_INCREMENT=587421 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ilanlar`
--

LOCK TABLES `ilanlar` WRITE;
/*!40000 ALTER TABLE `ilanlar` DISABLE KEYS */;
INSERT INTO `ilanlar` VALUES (587412,945762,'2024-11-01',3,1,11,15,65,4500000.00,'İstanbul','Kadıköy','Moda'),(587413,945762,'2024-11-11',3,2,12,16,55,20000.00,'İstanbul','Üsküdar','Altunizade'),(587414,945764,'2024-11-15',3,1,13,17,120,1500000.00,'İzmir','Karşıyaka','Bostanlı'),(587415,945765,'2024-11-02',4,2,13,17,50,15000.00,'Antalya','Muratpaşa','Lara'),(587416,945766,'2024-11-18',4,1,14,18,85,4200000.00,'Bursa','Nilüfer','Görükle'),(587417,945767,'2024-11-21',3,1,12,15,75,2750000.00,'Adana','Seyhan','Kurtuluş'),(587418,945768,'2024-11-23',3,2,12,15,70,10000.00,'Samsun','Atakum','Denizevleri'),(587419,945769,'2024-12-03',3,1,12,16,65,9000.00,'Trabzon','Ortahisar','Kalkınma'),(587420,945770,'2024-11-18',4,2,12,16,125,850000.00,'Gaziantep','Şahinbey','Alleben');
/*!40000 ALTER TABLE `ilanlar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-23  1:05:24
