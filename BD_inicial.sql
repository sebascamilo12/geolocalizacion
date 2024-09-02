CREATE DATABASE  IF NOT EXISTS `geolocalizacion` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `geolocalizacion`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: geolocalizacion
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `distancia`
--

DROP TABLE IF EXISTS `distancia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distancia` (
  `codigo_iso` varchar(4) NOT NULL,
  `distancia_a_buenosaires` double DEFAULT NULL,
  PRIMARY KEY (`codigo_iso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `invocaciones`
--

DROP TABLE IF EXISTS `invocaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invocaciones` (
  `codigo_iso` varchar(4) NOT NULL,
  `fecha_invocacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moneda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo_moneda` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `codigo_iso` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (250,'AFN','AF'),(251,'EUR','AX'),(252,'ALL','AL'),(253,'DZD','DZ'),(254,'USD','AS'),(255,'EUR','AD'),(256,'AOA','AO'),(257,'XCD','AI'),(258,'ARS','AR'),(259,'AMD','AM'),(260,'AWG','AW'),(261,'AUD','AU'),(262,'EUR','AT'),(263,'AZN','AZ'),(264,'BSD','BS'),(265,'BHD','BH'),(266,'BDT','BD'),(267,'BBD','BB'),(268,'BYR','BY'),(269,'EUR','BE'),(270,'BZD','BZ'),(271,'XOF','BJ'),(272,'BMD','BM'),(273,'BTN','BT'),(274,'BOB','BO'),(275,'BAM','BA'),(276,'BWP','BW'),(277,'NOK','BV'),(278,'BRL','BR'),(279,'USD','IO'),(280,'BND','BN'),(281,'BGN','BG'),(282,'XOF','BF'),(283,'BIF','BI'),(284,'KHR','KH'),(285,'XAF','CM'),(286,'CAD','CA'),(287,'CVE','CV'),(288,'KYD','KY'),(289,'XAF','CF'),(290,'XAF','TD'),(291,'CLP','CL'),(292,'CNY','CN'),(293,'AUD','CX'),(294,'AUD','CC'),(295,'COP','CO'),(296,'KMF','KM'),(297,'XAF','CG'),(298,'CDF','CD'),(299,'NZD','CK'),(300,'CRC','CR'),(301,'XOF','CI'),(302,'HRK','HR'),(303,'CUP','CU'),(304,'EUR','CY'),(305,'CZK','CZ'),(306,'DKK','DK'),(307,'DJF','DJ'),(308,'XCD','DM'),(309,'DOP','DO'),(310,'USD','EC'),(311,'EGP','EG'),(312,'USD','SV'),(313,'XAF','GQ'),(314,'ERN','ER'),(315,'EUR','EE'),(316,'ETB','ET'),(317,'FKP','FK'),(318,'DKK','FO'),(319,'FJD','FJ'),(320,'EUR','FI'),(321,'EUR','FR'),(322,'EUR','GF'),(323,'XPF','PF'),(324,'EUR','TF'),(325,'XAF','GA'),(326,'GMD','GM'),(327,'GEL','GE'),(328,'EUR','DE'),(329,'GHS','GH'),(330,'GIP','GI'),(331,'EUR','GR'),(332,'DKK','GL'),(333,'XCD','GD'),(334,'EUR','GP'),(335,'USD','GU'),(336,'GTQ','GT'),(337,'GBP','GG'),(338,'GNF','GN'),(339,'XOF','GW'),(340,'GYD','GY'),(341,'HTG','HT'),(342,'AUD','HM'),(343,'EUR','VA'),(344,'HNL','HN'),(345,'HKD','HK'),(346,'HUF','HU'),(347,'ISK','IS'),(348,'INR','IN'),(349,'IDR','ID'),(350,'IRR','IR'),(351,'IQD','IQ'),(352,'EUR','IE'),(353,'GBP','IM'),(354,'ILS','IL'),(355,'EUR','IT'),(356,'JMD','JM'),(357,'JPY','JP'),(358,'GBP','JE'),(359,'JOD','JO'),(360,'KZT','KZ'),(361,'KES','KE'),(362,'AUD','KI'),(363,'KPW','KP'),(364,'KRW','KR'),(365,'KWD','KW'),(366,'KGS','KG'),(367,'LAK','LA'),(368,'EUR','LV'),(369,'LBP','LB'),(370,'LSL','LS'),(371,'LRD','LR'),(372,'LYD','LY'),(373,'CHF','LI'),(374,'LTL','LT'),(375,'EUR','LU'),(376,'MOP','MO'),(377,'MKD','MK'),(378,'MGA','MG'),(379,'MWK','MW'),(380,'MYR','MY'),(381,'MVR','MV'),(382,'XOF','ML'),(383,'EUR','MT'),(384,'USD','MH'),(385,'EUR','MQ'),(386,'MRO','MR'),(387,'MUR','MU'),(388,'EUR','YT'),(389,'MXN','MX'),(390,'USD','FM'),(391,'MDL','MD'),(392,'EUR','MC'),(393,'MNT','MN'),(394,'XCD','MS'),(395,'MAD','MA'),(396,'MZN','MZ'),(397,'MMK','MM'),(398,'NAD','NA'),(399,'AUD','NR'),(400,'NPR','NP'),(401,'EUR','NL'),(402,'ANG','AN'),(403,'XPF','NC'),(404,'NZD','NZ'),(405,'NIO','NI'),(406,'XOF','NE'),(407,'NGN','NG'),(408,'NZD','NU'),(409,'AUD','NF'),(410,'USD','MP'),(411,'NOK','NO'),(412,'OMR','OM'),(413,'PKR','PK'),(414,'USD','PW'),(415,'ILS','PS'),(416,'PAB','PA'),(417,'PGK','PG'),(418,'PYG','PY'),(419,'PEN','PE'),(420,'PHP','PH'),(421,'NZD','PN'),(422,'PLN','PL'),(423,'EUR','PT'),(424,'USD','PR'),(425,'QAR','QA'),(426,'EUR','RE'),(427,'RON','RO'),(428,'RUB','RU'),(429,'RWF','RW'),(430,'SHP','SH'),(431,'XCD','KN'),(432,'XCD','LC'),(433,'EUR','PM'),(434,'XCD','VC'),(435,'WST','WS'),(436,'EUR','SM'),(437,'STD','ST'),(438,'SAR','SA'),(439,'XOF','SN'),(440,'EUR','ME'),(441,'SCR','SC'),(442,'SLL','SL'),(443,'SGD','SG'),(444,'EUR','SK'),(445,'EUR','SI'),(446,'SBD','SB'),(447,'SOS','SO'),(448,'ZAR','ZA'),(449,'GBP','GS'),(450,'EUR','ES'),(451,'LKR','LK'),(452,'SDG','SD'),(453,'SRD','SR'),(454,'NOK','SJ'),(455,'SZL','SZ'),(456,'SEK','SE'),(457,'CHF','CH'),(458,'SYP','SY'),(459,'TWD','TW'),(460,'TJS','TJ'),(461,'TZS','TZ'),(462,'THB','TH'),(463,'USD','TL'),(464,'XOF','TG'),(465,'NZD','TK'),(466,'TOP','TO'),(467,'TTD','TT'),(468,'TND','TN'),(469,'TRY','TR'),(470,'TMT','TM'),(471,'USD','TC'),(472,'AUD','TV'),(473,'UGX','UG'),(474,'UAH','UA'),(475,'AED','AE'),(476,'GBP','GB'),(477,'USD','US'),(478,'USD','UM'),(479,'UYU','UY'),(480,'UZS','UZ'),(481,'VUV','VU'),(482,'VEF','VE'),(483,'VND','VN'),(484,'USD','VG'),(485,'USD','VI'),(486,'XPF','WF'),(487,'MAD','EH'),(488,'YER','YE'),(489,'ZMK','ZM'),(490,'ZWL','ZW'),(491,'RSD','RS');
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rango_ips_paises`
--
