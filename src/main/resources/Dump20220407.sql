-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: asd
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.21.04.1

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `city` varchar(255) DEFAULT NULL,
                           `number` int NOT NULL,
                           `street` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Vilnius',35,'Konstitucijos pr.');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `address_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKf3ryyh4bd143l5b8stt65hwgr` (`address_id`),
                            CONSTRAINT `FKf3ryyh4bd143l5b8stt65hwgr` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,1);
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desk`
--

DROP TABLE IF EXISTS `desk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desk` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `desk_number` int NOT NULL,
                        `is_available` tinyint NOT NULL DEFAULT '1',
                        `room_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK1bdrqq8yryu4slrjw05x8ao8s` (`room_id`),
                        CONSTRAINT `FK1bdrqq8yryu4slrjw05x8ao8s` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desk`
--

LOCK TABLES `desk` WRITE;
/*!40000 ALTER TABLE `desk` DISABLE KEYS */;
INSERT INTO `desk` VALUES (1,1,1,1),(2,2,1,1),(3,4,1,1),(4,1,1,2),(5,2,1,2),(6,3,1,2),(7,4,1,2),(8,1,1,3),(9,2,1,3),(10,3,1,3),(11,4,1,3),(12,1,1,4),(13,2,1,4),(14,3,1,4),(15,4,1,4),(16,1,1,5),(17,2,1,5),(18,3,1,5),(19,4,1,5),(20,3,1,1);
/*!40000 ALTER TABLE `desk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `floor_number` int NOT NULL,
                         `building_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKfvb11l7lpgqc6qdrg3bm24kr3` (`building_id`),
                         CONSTRAINT `FKfvb11l7lpgqc6qdrg3bm24kr3` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,5,1);
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `date` date DEFAULT NULL,
                                `desk_id` bigint DEFAULT NULL,
                                `user_user_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FK5kvt0r1lvn2dy8ky0g9j0medq` (`desk_id`),
                                KEY `FKp65u1hd8e5ainp9oseyxwydgd` (`user_user_id`),
                                CONSTRAINT `FK5kvt0r1lvn2dy8ky0g9j0medq` FOREIGN KEY (`desk_id`) REFERENCES `desk` (`id`),
                                CONSTRAINT `FKp65u1hd8e5ainp9oseyxwydgd` FOREIGN KEY (`user_user_id`) REFERENCES `users_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2022-04-10',1,12345678),(2,'2022-04-10',2,12345678),(3,'2022-04-10',10,12345678),(4,'2022-04-11',4,12345678),(5,'2022-05-11',5,12345678),(6,'2022-04-30',2,12345678);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `room_name` varchar(255) DEFAULT NULL,
                        `floor_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FKstlo96g0nkwp4urd4e0ki5b3h` (`floor_id`),
                        CONSTRAINT `FKstlo96g0nkwp4urd4e0ki5b3h` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Room Nr1',1),(2,'Room Nr2',1),(3,'Room Nr3',1),(4,'Room Nr4',1),(5,'Room Nr5',1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_table`
--

DROP TABLE IF EXISTS `users_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_table` (
                               `user_id` bigint NOT NULL,
                               `active` tinyint NOT NULL,
                               `email` varchar(255) DEFAULT NULL,
                               `first_name` varchar(255) DEFAULT NULL,
                               `last_name` varchar(255) DEFAULT NULL,
                               `middle_name` varchar(255) DEFAULT NULL,
                               `password` varchar(255) DEFAULT NULL,
                               `role` int DEFAULT NULL,
                               PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_table`
--

LOCK TABLES `users_table` WRITE;
/*!40000 ALTER TABLE `users_table` DISABLE KEYS */;
INSERT INTO `users_table` VALUES (12345678,1,'update@email.com','updatedFirstName','updatedLastName','updatedMiddleName','updatedFirstName',0),(123455879,0,'update@email.com','updatedFirstName','updatedLastName','updatedMiddleName','updatedFirstName',0),(123456879,1,'update@email.com','updatedFirstName','updatedLastName','updatedMiddleName','updatedFirstName',0);
/*!40000 ALTER TABLE `users_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;