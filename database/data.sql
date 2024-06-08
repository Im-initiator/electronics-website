CREATE DATABASE  IF NOT EXISTS `electronics_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `electronics_project`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: electronics_project
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `status` tinyint NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `full_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f6xpj7h12wr185bqhfi1hqlbr` (`user_name`),
  UNIQUE KEY `UK_q0uja26qgu1atulenwup9rxyr` (`email`),
  UNIQUE KEY `UK_di2gpau46j4053lfccsrrq1sb` (`employee_id`),
  CONSTRAINT `FK1kec5bwba2rl0j8garlarwe3d` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'2024-06-04 14:59:58.000000',NULL,1,NULL,'admin','admin@gmail.com',NULL,'Mai Anh',NULL,'$2a$10$PKUexTjMK70kYixB/sGpcOTPwkEWcAibVq3EQ901e9pw/A.f8Je9G',NULL),(1,1,'2024-06-04 19:32:27.017868',NULL,2,'2024-06-04 19:32:27.017868','manager','manager@gmail.com',NULL,'Nguyễn Thị Ngọc Lan',NULL,'$2a$10$JjprFIUSKMNYj5u/D7W2veSf.HwoSBns/Qfgfrjui/I/VYBWUpqiS',NULL),(1,1,'2024-06-05 15:26:22.827791',NULL,14,'2024-06-07 21:30:05.033870','user','userupdate@gmail.com',NULL,'Nguyễn User',NULL,'$2a$10$27nQOr2Ab2ot8BuPLJKcyuE0LiKXNOPao.oGZwjbiIblXa4CYBIQi','/files_upload/images/20240607212948-personal.jpg'),(1,1,'2024-06-05 18:59:50.287467',NULL,16,'2024-06-05 18:59:50.287467','user1','user1@gmail.com',NULL,NULL,NULL,'$2a$10$1sfzC5hQX5tmqY9LkA/9S.1llWE9XZKtjCH4Wuu/T2pFUgJ47D67u','\\files_upload\\images\\userDefault.png'),(1,1,'2024-06-08 02:39:52.212647',NULL,17,'2024-06-08 18:15:11.543418','testttt','test@gmail.com',NULL,'Nguyễn Thị Ngọc Lan',NULL,'$2a$10$ur74tSOYNnliaLKQRSvqo.hyx0J.6icTRSPTbtT8g6bQu4nimyNLC','\\files_upload\\images\\userDefault.png');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKrs2s3m3039h0xt8d5yhwbuyam` (`role_id`),
  CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKrs2s3m3039h0xt8d5yhwbuyam` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (2,1),(14,1),(16,1),(17,1),(1,2),(14,2),(2,3),(17,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertise`
--

DROP TABLE IF EXISTS `advertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertise` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `time_end` date DEFAULT NULL,
  `time_start` date DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `content` text NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgy3jywy3a1iefiykxx2g7i75r` (`shop_id`),
  CONSTRAINT `FKgy3jywy3a1iefiykxx2g7i75r` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise`
--

LOCK TABLES `advertise` WRITE;
/*!40000 ALTER TABLE `advertise` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `google_map` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `province` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqbg9dgelp7va48943wehjds65` (`shop_id`),
  CONSTRAINT `FKqbg9dgelp7va48943wehjds65` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand_products`
--

DROP TABLE IF EXISTS `brand_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand_products` (
  `brand_entity_id` bigint NOT NULL,
  `products_id` bigint NOT NULL,
  PRIMARY KEY (`brand_entity_id`,`products_id`),
  UNIQUE KEY `UK_7eaqjxxyd4t68rexhknrw562s` (`products_id`),
  CONSTRAINT `FKc71ff1i8jkjwitpl76isoep5g` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKhil3omtxuwa4b0vj7ia0hhnfv` FOREIGN KEY (`brand_entity_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_products`
--

LOCK TABLES `brand_products` WRITE;
/*!40000 ALTER TABLE `brand_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `brand_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `quantity` int NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `account_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4pljlvncf45mr98etwpubxvbt` (`account_id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK4pljlvncf45mr98etwpubxvbt` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo2dg7r6afmp8j8fyksgbykxep` (`sender_id`),
  KEY `FKnnm9i8etxrpl2dk69npbkdvcs` (`receiver_id`),
  CONSTRAINT `FKnnm9i8etxrpl2dk69npbkdvcs` FOREIGN KEY (`receiver_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `FKo2dg7r6afmp8j8fyksgbykxep` FOREIGN KEY (`sender_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `price` double DEFAULT NULL,
  `quality` int NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsgsuxxoc1h5pskbjpch4id2ec` (`product_id`),
  CONSTRAINT `FKsgsuxxoc1h5pskbjpch4id2ec` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `date_birth` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `account_id` bigint DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lsnx7na4u8ohrhoeag7un4wh3` (`account_id`),
  KEY `FKcvhlsx8tao1rxt7mpxrot61jt` (`branch_id`),
  CONSTRAINT `FKcfg6ajo8oske94exynxpf7tf9` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKcvhlsx8tao1rxt7mpxrot61jt` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `account_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsskuoi6u8b6tykfj5jy1wng9r` (`account_id`),
  KEY `FKbg4txsew6x3gl6r9swcq190hg` (`product_id`),
  CONSTRAINT `FKbg4txsew6x3gl6r9swcq190hg` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKsskuoi6u8b6tykfj5jy1wng9r` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`),
  CONSTRAINT `FKgpextbyee3uk9u6o2381m7ft1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `content` text NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `short_description` varchar(400) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9cvcq1ukeyr5q9ymacphm43vw` (`shop_id`),
  CONSTRAINT `FK9cvcq1ukeyr5q9ymacphm43vw` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

LOCK TABLES `new` WRITE;
/*!40000 ALTER TABLE `new` DISABLE KEYS */;
/*!40000 ALTER TABLE `new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `content` text NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` enum('LOCAL','GLOBAL','EMAIL') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKraoufg5s1h6vuur2rp0wttvbe` (`shop_id`),
  CONSTRAINT `FKraoufg5s1h6vuur2rp0wttvbe` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `price` double NOT NULL,
  `quality` int NOT NULL,
  `rate` float DEFAULT NULL,
  `sell_number` int DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `branch_id` bigint NOT NULL,
  `brand_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `group_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_sale_id` bigint DEFAULT NULL,
  `product_type_id` bigint DEFAULT NULL,
  `salient_feature_id` bigint DEFAULT NULL,
  `content` text NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bnhanoih6t2i0pcek1olhr5j4` (`product_sale_id`),
  UNIQUE KEY `UK_cebv5wajm5irk9nhohdi7k4dd` (`salient_feature_id`),
  KEY `FKikaed1un46tr8ey7jis9v4868` (`branch_id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FKo5xr9dkohdmfkycjxrabl966k` (`group_id`),
  KEY `FKlabq3c2e90ybbxk58rc48byqo` (`product_type_id`),
  CONSTRAINT `FK9ttusisvymp3t031gxdmm2eb6` FOREIGN KEY (`salient_feature_id`) REFERENCES `salient_feature` (`id`),
  CONSTRAINT `FKikaed1un46tr8ey7jis9v4868` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `FKlabq3c2e90ybbxk58rc48byqo` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`),
  CONSTRAINT `FKo5xr9dkohdmfkycjxrabl966k` FOREIGN KEY (`group_id`) REFERENCES `product_group` (`id`),
  CONSTRAINT `FKrcsc406i3sicq41i5hti0oqgi` FOREIGN KEY (`product_sale_id`) REFERENCES `product_sale` (`id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_group`
--

DROP TABLE IF EXISTS `product_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_group` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_group`
--

LOCK TABLES `product_group` WRITE;
/*!40000 ALTER TABLE `product_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_order`
--

DROP TABLE IF EXISTS `product_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_order` (
  `discount` float DEFAULT NULL,
  `payment_status` int NOT NULL,
  `price` double NOT NULL,
  `product_price` double NOT NULL,
  `ship_cost` float DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `account_id` bigint DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `discount_description` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `note` text,
  `payment_method` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `receive_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `receive_phone` varchar(20) NOT NULL,
  `ship_place` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKig3btav002hjei1bj1n7vm9hn` (`account_id`),
  KEY `FKh73acsd9s5wp6l0e55td6jr1m` (`product_id`),
  CONSTRAINT `FKh73acsd9s5wp6l0e55td6jr1m` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKig3btav002hjei1bj1n7vm9hn` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_order`
--

LOCK TABLES `product_order` WRITE;
/*!40000 ALTER TABLE `product_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_sale`
--

DROP TABLE IF EXISTS `product_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_sale` (
  `discount_amount` double DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `sale_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ken27eby0mk1osyiavry6o19b` (`product_id`),
  KEY `FK4pvqe8s1fkn1acdx7ogqw9ofm` (`sale_id`),
  CONSTRAINT `FK4pvqe8s1fkn1acdx7ogqw9ofm` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  CONSTRAINT `FKn4mby2cj0njhd6s7rbqbf5ujh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_sale`
--

LOCK TABLES `product_sale` WRITE;
/*!40000 ALTER TABLE `product_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_specification`
--

DROP TABLE IF EXISTS `product_specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_specification` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `technical_specification_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjk3nq9o6i8anej70mx7bkiyxc` (`product_id`),
  KEY `FK1r35r63ebr9swk317bqxb6yh1` (`technical_specification_id`),
  CONSTRAINT `FK1r35r63ebr9swk317bqxb6yh1` FOREIGN KEY (`technical_specification_id`) REFERENCES `technical_specification` (`id`),
  CONSTRAINT `FKjk3nq9o6i8anej70mx7bkiyxc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_specification`
--

LOCK TABLES `product_specification` WRITE;
/*!40000 ALTER TABLE `product_specification` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `category_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq3dcgs3t1cilv7ujqx6s5iiag` (`category_id`),
  CONSTRAINT `FKq3dcgs3t1cilv7ujqx6s5iiag` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `content` text,
  `created_by` varchar(255) DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5xt8s5kyjoo43jrr1bp2e05uu` (`shop_id`),
  CONSTRAINT `FK5xt8s5kyjoo43jrr1bp2e05uu` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'2024-06-04 14:57:53.000000',1,'2024-06-04 14:58:25.000000','admin',NULL,'USER'),(0,'2024-06-04 14:58:48.000000',2,'2024-06-04 14:58:54.000000','admin',NULL,'ADMIN'),(0,'2024-06-04 14:58:50.000000',3,'2024-06-04 14:58:56.000000','admin',NULL,'MANAGER'),(0,'2024-06-04 14:58:51.000000',4,'2024-06-04 14:58:57.000000','admin',NULL,'EMPLOYEE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `priority` int DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `time_end` datetime(6) DEFAULT NULL,
  `time_start` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salient_feature`
--

DROP TABLE IF EXISTS `salient_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salient_feature` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `feature_four` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `feature_one` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `feature_three` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `feature_two` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salient_feature`
--

LOCK TABLES `salient_feature` WRITE;
/*!40000 ALTER TABLE `salient_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `salient_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `content` text NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4lnq8ckvahh60850efldsm87` (`shop_id`),
  CONSTRAINT `FKh4lnq8ckvahh60850efldsm87` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(150) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `map` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'012345678','Hoàn Kiếm Hà Nội','/files_upload/images/20240609013523-cv.jpg','AnhMobile','http://googlemap....');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slide`
--

DROP TABLE IF EXISTS `slide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slide` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `link` text,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `short_description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4g7o50m6cyo1wqdq2ok2h9po` (`shop_id`),
  CONSTRAINT `FKh4g7o50m6cyo1wqdq2ok2h9po` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slide`
--

LOCK TABLES `slide` WRITE;
/*!40000 ALTER TABLE `slide` DISABLE KEYS */;
/*!40000 ALTER TABLE `slide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social`
--

DROP TABLE IF EXISTS `social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `shop_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `link` text NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtb8oqfufnoud7mgs1lpv301qj` (`shop_id`),
  CONSTRAINT `FKtb8oqfufnoud7mgs1lpv301qj` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social`
--

LOCK TABLES `social` WRITE;
/*!40000 ALTER TABLE `social` DISABLE KEYS */;
/*!40000 ALTER TABLE `social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_sale`
--

DROP TABLE IF EXISTS `special_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `special_sale` (
  `discount_amount` double NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK556nwherkqsobi1ostem7psl8` (`product_id`),
  CONSTRAINT `FK556nwherkqsobi1ostem7psl8` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_sale`
--

LOCK TABLES `special_sale` WRITE;
/*!40000 ALTER TABLE `special_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `special_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technical_specification`
--

DROP TABLE IF EXISTS `technical_specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technical_specification` (
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `category_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqtro1r7efwc2k7wygsvds4rs` (`category_id`),
  CONSTRAINT `FKbqtro1r7efwc2k7wygsvds4rs` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technical_specification`
--

LOCK TABLES `technical_specification` WRITE;
/*!40000 ALTER TABLE `technical_specification` DISABLE KEYS */;
/*!40000 ALTER TABLE `technical_specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `revoked` tinyint(1) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `account_id` bigint NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modify_date` datetime(6) DEFAULT NULL,
  `access_token` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_modify_by` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKftkstvcfb74ogw02bo5261kno` (`account_id`),
  CONSTRAINT `FKftkstvcfb74ogw02bo5261kno` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (0,0,0,2,'2024-06-05 00:09:44.205337',9,'2024-06-05 00:09:44.205337','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFnZXJAZ21haWwuY29tIiwic3ViIjoibWFuYWdlciIsImlhdCI6MTcxNzUyMDk4NCwiZXhwIjoxNzE3NjA3Mzg0fQ.2IT6p6lFjzN-90gUU1K7WSOBV9TFcuB_1-UgxzSt7sk',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMiIsInN1YiI6Im1hbmFnZXIiLCJpYXQiOjE3MTc1MjA5ODQsImV4cCI6MTcxODEyNTc4NH0.LjZQjV_Tsi8YcYwLbqYqdbTM7EZOQxyzQPUKH52Mx5I',NULL),(0,0,0,2,'2024-06-05 00:09:48.135890',10,'2024-06-05 00:09:48.135890','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFnZXJAZ21haWwuY29tIiwic3ViIjoibWFuYWdlciIsImlhdCI6MTcxNzUyMDk4OCwiZXhwIjoxNzE3NjA3Mzg4fQ.WpK8fGsQRSLfdIvxgt95JjjIXZFhujKOeEdDAnu59sc',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMiIsInN1YiI6Im1hbmFnZXIiLCJpYXQiOjE3MTc1MjA5ODgsImV4cCI6MTcxODEyNTc4OH0.mV-TY9HKqsRmR-wBqeMW49URrmltATohGw63hTlPYMk',NULL),(0,0,0,2,'2024-06-05 01:58:00.331646',11,'2024-06-05 01:58:00.331646','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1hbmFnZXJAZ21haWwuY29tIiwic3ViIjoibWFuYWdlciIsImlhdCI6MTcxNzUyNzQ3OSwiZXhwIjoxNzE3NjEzODc5fQ.jWm9-vDTtcpZ90gnGVwJrnNQ2mfwiauKJ16otc5ji8U',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMiIsInN1YiI6Im1hbmFnZXIiLCJpYXQiOjE3MTc1Mjc0NzksImV4cCI6MTcxODEzMjI3OX0.OwhJx67vUioY3i7-KJKD1yfJoJw7zEhSqKkCiKIu4EQ',NULL),(0,0,1,16,'2024-06-05 18:59:50.382474',13,'2024-06-05 18:59:50.382474','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InVzZXIxQGdtYWlsLmNvbSIsInN1YiI6InVzZXIxIiwiaWF0IjoxNzE3NTg4NzkwLCJleHAiOjE3MTc2NzUxOTB9.exwg68dsP0abgTk5j7bCMDj_m2cQKYddj7P2eXXah4c',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMTYiLCJzdWIiOiJ1c2VyMSIsImlhdCI6MTcxNzU4ODc5MCwiZXhwIjoxNzE4MTkzNTkwfQ.GKCMvH9nCFUTw7xptVQiRwghIOmtpnR6OSudX5adBRo',NULL),(0,0,1,14,'2024-06-07 02:32:21.516221',15,'2024-06-07 02:32:21.516221','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InVzZXJ1cGRhdGVAZ21haWwuY29tIiwic3ViIjoidXNlcm5hbWUiLCJpYXQiOjE3MTc3MDIzNDEsImV4cCI6MTcxNzc4ODc0MX0.cZ2MP-ESrgswPp64L4oPKuPWvlxpuCyjMusvNPU5504',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMTQiLCJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTcxNzcwMjM0MSwiZXhwIjoxNzE4MzA3MTQxfQ.7JAdkrPJ47AOovQV_BpTESJBJ_ldB9iMSmANgmt-aCE',NULL),(0,0,1,14,'2024-06-07 10:20:31.846883',16,'2024-06-07 10:20:31.846883','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InVzZXJ1cGRhdGVAZ21haWwuY29tIiwic3ViIjoidXNlcm5hbWUiLCJpYXQiOjE3MTc3MzA0MzEsImV4cCI6MTcxNzgxNjgzMX0.Nr1ht7_YxOtb5pPYpiPDRJ0Ol8WIE7jLec2di2xG3G8',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMTQiLCJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTcxNzczMDQzMSwiZXhwIjoxNzE4MzM1MjMxfQ.HgGjgPaWz7t_9v3Vj1E-jziloL-mEiRLEXn_7RC0gBc',NULL),(0,0,1,14,'2024-06-07 10:25:52.046169',17,'2024-06-07 10:25:52.046169','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InVzZXJ1cGRhdGVAZ21haWwuY29tIiwic3ViIjoidXNlciIsImlhdCI6MTcxNzczMDc1MSwiZXhwIjoxNzE3ODE3MTUxfQ.35nCzT-BM8t_T0Gdfz-6qAQFWHtYZ6mFLoKh1idW9mE',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMTQiLCJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE3NzMwNzUxLCJleHAiOjE3MTgzMzU1NTF9.ShDNsVv0TqlyEoCQ1KOL8rf4s8IGOjlrpBVRL6WjJQA',NULL),(0,0,1,1,'2024-06-07 23:34:47.206028',18,'2024-06-07 23:34:47.206028','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3Nzc4MDg3LCJleHAiOjE3MTc4NjQ0ODd9.w46sVL47bo0yqszj84rvSEFtJkwQ9Of6w08fucXnG6E',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3Nzc4MDg3LCJleHAiOjE3MTgzODI4ODd9.OUnbrXk11th_HOWmWkzwf20AgrDJ1iv8Ai2JH7hDL7o',NULL),(0,0,1,1,'2024-06-08 02:39:33.707761',19,'2024-06-08 02:39:33.707761','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3Nzg5MTczLCJleHAiOjE3MTc4NzU1NzN9.4-dZReVwH0WhAnwC6we3p4jDnC7wzdqS7GUA84EIp40',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3Nzg5MTczLCJleHAiOjE3MTgzOTM5NzN9.sQ2YQMWIVs6eE1DVg6mC87f2u0h61IcGIM6W0PYzO-Y',NULL),(1,1,0,1,'2024-06-08 19:42:11.130729',20,'2024-06-08 19:42:11.130729','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTc1ODg5OTB9.rUkeGaZ_LogeLxfM5hDVGfuRvK74AVworgWFfugzKEk',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTgxMDczOTB9.6Hn7XvyLT-HE1Bq6NN8rT-TuTeMfuLEP7TG9xMlnbQY',NULL),(1,1,0,1,'2024-06-08 19:42:56.403884',21,'2024-06-08 19:42:56.403884','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTc1ODg5OTB9.rUkeGaZ_LogeLxfM5hDVGfuRvK74AVworgWFfugzKEk',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTgxMDczOTB9.6Hn7XvyLT-HE1Bq6NN8rT-TuTeMfuLEP7TG9xMlnbQY',NULL),(1,1,0,1,'2024-06-08 19:44:06.438962',22,'2024-06-08 19:44:06.438962','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTc1ODg5OTB9.rUkeGaZ_LogeLxfM5hDVGfuRvK74AVworgWFfugzKEk',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3NTAyNTkwLCJleHAiOjE3MTgxMDczOTB9.6Hn7XvyLT-HE1Bq6NN8rT-TuTeMfuLEP7TG9xMlnbQY',NULL),(0,0,1,1,'2024-06-09 01:20:56.170344',23,'2024-06-09 01:20:56.170344','eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3ODcwODU2LCJleHAiOjE3MTc5NTcyNTZ9.GIECb6XNXshEWMW_Fod7yfpQA6tYKW8PbbXw6q6JvPs',NULL,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzE3ODcwODU2LCJleHAiOjE3MTg0NzU2NTZ9.iT_7__kk4o_W_zxEcWh68XaIUPpUNDllUvyq9CLVMX8',NULL);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'electronics_project'
--

--
-- Dumping routines for database 'electronics_project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09  1:37:30
