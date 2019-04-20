-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: mall
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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (6,'张三','020-81167888','510000','510000','广东省','广州市','海珠区','新港中路397号','广东省 广州市 海珠区 新港中路397号'),(7,'叶玉明','18681506437','332500','360429','江西省','九江市','湖口县','均桥镇枫树村一组010号','江西省 九江市 湖口县 均桥镇枫树村一组010号'),(8,'叶玉明','18681506437','518101','440306','广东省','深圳市','宝安区','岭下花园1巷4号1002','广东省 深圳市 宝安区 岭下花园1巷4号1002'),(9,'张三','020-81167888','510000','510000','广东省','广州市','海珠区','新港中路397号','广东省 广州市 海珠区 新港中路397号');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `admin_user`
--

LOCK TABLES `admin_user` WRITE;
/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
INSERT INTO `admin_user` VALUES (1,1,1,'5943E41213FAEAE0B3A648A143286B40',1),(2,2,2,'2FDBBC29EF741B2C2D31EFB9C7E67CF3',1),(3,3,3,'9F447E5756FB515EFDC009762E38D7B3',1);
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (32,'水果',1,1),(33,'蔬菜',2,1),(34,'饮料',3,1),(35,'肉类',4,1),(36,'海鲜',5,1),(37,'鱼',6,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hot`
--

LOCK TABLES `hot` WRITE;
/*!40000 ALTER TABLE `hot` DISABLE KEYS */;
INSERT INTO `hot` VALUES (1,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_110032_503.jpg','144',1,1),(2,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_112152_932.jpg','allomorph',3,1),(3,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_112316_436.jpg','旅途',2,1),(4,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190401_150952_545.jpg','最新',4,1),(5,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_112415_829.jpg','爱情',5,1);
/*!40000 ALTER TABLE `hot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hot_product`
--

LOCK TABLES `hot_product` WRITE;
/*!40000 ALTER TABLE `hot_product` DISABLE KEYS */;
INSERT INTO `hot_product` VALUES (6,1,12),(7,1,13),(11,2,13),(14,3,12),(15,4,12),(16,5,12);
/*!40000 ALTER TABLE `hot_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `key_value`
--

LOCK TABLES `key_value` WRITE;
/*!40000 ALTER TABLE `key_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `key_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (2,'cc435c659f174e25ba42223dec33c927',5,9,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:27:41','2019-04-20 11:10:52','d9cac14f986d4276a3d7e39943c5bb68',NULL,1),(3,'3bf5fe653a634874bb4f1ad351907dfd',5,9,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:30:33','2019-04-20 11:10:52','5e9381b8b05e469a82ca6430c3e53b16',NULL,1),(4,'c87726d2a4344b649b0cb0d242dffa85',5,9,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:30:55','2019-04-20 11:10:52','82f056a63719487890a95e1a41ac6b88',NULL,1),(5,'6baad337334f411d8acb5e3cf246864e',5,9,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:32:24','2019-04-20 11:10:52','09d2716f8ce04727a86d36f8b589a3dd',NULL,1),(6,'2f152f0c2392489c8f8e7c46df55360c',5,9,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 20:26:03','2019-04-20 11:10:52','68c3b63ad23940338b7101b70225ff84',NULL,1),(7,'6445522182c143e080b4702a347f7388',5,9,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 20:26:15','2019-04-20 11:10:52','692eb5c284fd43f99a48b10d3aa8eaa2',NULL,1),(8,'b5e1bf64a0944c628b7c28de366ba8e3',5,9,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 20:27:24','2019-04-20 11:10:52','dabd6040525e47019641fa24572e46bb',NULL,1),(9,'c27b8da8fcf54e49b191a706d0075417',5,9,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 21:38:23','2019-04-20 11:10:52','c1cdd2787a01403eaa57ab91f774f17b',NULL,1),(10,'e18ae342bb584a3cb9dd8fdf2b5ce596',5,9,99,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 21:39:22','2019-04-20 11:10:52','ac4049bcf29045eea6843c603759f72c',NULL,1),(11,'e1e064c0caa848aeb60e9c61e462b646',5,9,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 22:04:12','2019-04-20 11:10:52','34f7789979024b628670b9cfc9609805',NULL,1),(12,'7a795182121e4ad786f499f5f6eaf772',5,1,99,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 22:05:20','2019-04-19 22:06:12','c9e3acd5881d4dcf9f43510ff67b4d64',NULL,1),(13,'493e2d0b0c8e4851ae14725205a379aa',5,9,8,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 22:26:08','2019-04-20 11:10:52','0e52e909f2e842d2a76138bbe3cb4c1f',NULL,1),(14,'c5706a36b2594d7a8221e78f3c5c8083',5,1,8,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 22:27:47','2019-04-19 22:27:48','374782b05cd44332b41ddec2918d407e',NULL,1),(15,'a9372c89e5e4463e857cb145eef533da',5,1,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-19 22:27:57','2019-04-19 22:28:39','96c761ada09149d29bbc97eccc55d00f',NULL,1),(16,'1c481f1aa62c45f3832ff58a2d9a6160',5,1,312,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 15:32:40','2019-04-20 15:32:41','d949d66ee23c4435a8efb8d41da95d88',NULL,1),(17,'5f30a4c98e2e45b383c084da4032b6af',5,1,312,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 15:33:30','2019-04-20 15:33:31','34cb97c4bad14441ae0ca9204dc1eaf1',NULL,1),(18,'3156afa674b241ef8358f8a8e4d6b06d',5,1,197,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 15:40:18','2019-04-20 16:06:49','31f57006f78a4f42a409884be9784b67',NULL,1),(19,'9d3d1138b5604875bcf31f83d53bf784',5,9,197,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 15:40:39','2019-04-20 22:57:06','5d3ea616f4214018814a8c3e71e75676',NULL,1),(20,'607ca442cfcb404baed6596b22f51154',5,1,133,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 15:41:35','2019-04-20 15:41:42','2fc78cbcb6814942844c6dac3f1658ea',NULL,1),(21,'dffefd9c7007400aa1cb976b1ae75c65',5,1,24,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:56:42','2019-04-20 22:56:55','e10fff13097e4455b654cc99c625c8d7',NULL,1),(22,'d785ccf4dccf471295f63ea5d3cbdf81',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:20','2019-04-20 22:57:20','3428bd4b5adb4e1d850036920fb01817',NULL,1),(23,'5164ab27f7cc49e78f0f8039068e2246',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:22','2019-04-20 22:57:22','295468f981e8412c9301475d18c22cb4',NULL,1),(24,'57d4b33b1ab8475f9c9addd0426c5bf8',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:23','2019-04-20 22:57:23','24600c9cd7564acfab53f0fedcedc0c5',NULL,1),(25,'aec1240cd35c44fb87e1a8384b2fd4e5',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:28','2019-04-20 22:57:28','4dc501dfe1604bf99a7c020b9cd5ab45',NULL,1),(26,'cb86b78455c44379b987f2e0895337b0',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:30','2019-04-20 22:57:30','2babdff376f3437b9ca7d064b61e9c77',NULL,1),(27,'734b39cb14e14f7997d618785df31038',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:57:35','2019-04-20 22:57:35','d438cd7e44274b5588b7b2de4c5cdb1c',NULL,1),(28,'c38a4a6f3e074d01bb8a158944e56854',5,0,15,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 22:58:02','2019-04-20 22:58:02','e062735497c34986a6e65855f91e8b22',NULL,1),(29,'953b89c707214d9fb1c714cdc0222dec',5,0,16,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:06:50','2019-04-20 23:06:50','d584b694559445d2a7904fab8b55b8d9',NULL,1),(30,'11e80cdf304541149c56dc2c6d7880d6',5,0,16,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:07:01','2019-04-20 23:07:01','87d399e27f284d5db5d94b0e57cd763d',NULL,1),(31,'cc640b42f64b4fb9a838721939ce97d7',5,0,16,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:07:11','2019-04-20 23:07:11','5ac5d27a63274d54a27a7cf2b0e4a974',NULL,1),(32,'e36e0f5b473143649ae4991238ff59d2',5,1,16,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:08:23','2019-04-20 23:09:03','2d27f613971a4c56b43606c5decbd2f0',NULL,1),(33,'10481ae52521485fb9da67bb3c29f036',5,1,30,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:09:14','2019-04-20 23:09:15','58dcac2571e64dd4a116b9e151232ac0',NULL,1),(34,'36869d0994de471f932980753914f91a',5,1,16,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:14:42','2019-04-20 23:15:23','3fb6c2729da944bcb59e0c9201f30009',NULL,1),(35,'ed012b3cf08d4e9f8d36d86862a044f1',5,1,30,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-20 23:15:35','2019-04-20 23:15:37','7c27551b63ce4920aa872c47c735bed4',NULL,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (1,3,9,1,300),(2,4,9,1,300),(3,5,9,1,300),(4,6,11,1,15),(5,7,11,1,15),(6,8,11,1,15),(7,9,9,1,300),(8,10,21,1,99),(9,11,11,1,15),(10,12,21,1,99),(11,13,22,1,8),(12,14,22,1,8),(13,15,11,1,15),(14,16,9,1,300),(15,16,10,1,12),(16,17,9,1,300),(17,17,10,1,12),(18,18,24,1,8),(19,18,23,1,8),(20,18,22,1,8),(21,18,21,1,99),(22,18,20,1,4),(23,18,19,1,6),(24,18,18,1,5),(25,18,17,1,4),(26,18,16,1,33),(27,18,15,1,22),(28,19,24,1,8),(29,19,23,1,8),(30,19,22,1,8),(31,19,21,1,99),(32,19,20,1,4),(33,19,19,1,6),(34,19,18,1,5),(35,19,17,1,4),(36,19,16,1,33),(37,19,15,1,22),(38,20,24,1,8),(39,20,23,1,8),(40,20,22,1,8),(41,20,21,1,99),(42,20,20,1,4),(43,20,19,1,6),(44,21,23,1,8),(45,21,24,1,8),(46,21,22,1,8),(47,22,12,1,15),(48,23,12,1,15),(49,24,12,1,15),(50,25,12,1,15),(51,26,12,1,15),(52,27,12,1,15),(53,28,12,1,15),(54,29,24,1,8),(55,29,23,1,8),(56,30,24,1,8),(57,30,23,1,8),(58,31,24,1,8),(59,31,23,1,8),(60,32,22,1,8),(61,32,23,1,8),(62,33,11,1,15),(63,33,12,1,15),(64,34,22,1,8),(65,34,23,1,8),(66,35,12,1,15),(67,35,11,1,15);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `postage`
--

LOCK TABLES `postage` WRITE;
/*!40000 ALTER TABLE `postage` DISABLE KEYS */;
INSERT INTO `postage` VALUES (1,'北京',15,1),(2,'天津',15,1),(3,'河北',15,1),(4,'山西',15,1),(5,'内蒙古',0,0),(6,'辽宁',0,0),(7,'吉林',15,0),(8,'黑龙江',0,0),(9,'上海',10,1),(10,'江苏',15,1),(11,'浙江',10,1),(12,'安徽',0,0),(13,'福建',15,1),(14,'江西',0,1),(15,'山东',0,0),(16,'河南',15,1),(17,'湖北',10,1),(18,'湖南',10,1),(19,'广东',15,1),(20,'广西',0,0),(21,'海南',0,0),(22,'重庆',15,1),(23,'四川',0,0),(24,'贵州',0,0),(25,'云南',15,0),(26,'西藏',0,0),(27,'陕西',0,0),(28,'甘肃',15,0),(29,'青海',0,0),(30,'宁夏',0,0),(31,'新疆',0,0),(32,'台湾',0,0),(33,'香港',0,0),(34,'澳门',0,0);
/*!40000 ALTER TABLE `postage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172037_395.jpg','螃蟹','帝王蟹',350,300,0,1,4,'2019-03-27 04:20:47',1),(10,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172134_324.jpg','猪肉','猪肉',13,12,0,1,1,'2019-03-27 04:21:42',1),(11,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172202_287.jpg','牛肉','牛肉',19,15,0,1,9,'2019-03-27 04:22:10',1),(12,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_121319_181.jpg','香蕉','进口',15,15,0,1,11,'2019-03-28 23:13:29',1),(13,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_123616_690.jpg','怡宝','纯净水',2,1.5,0,1,10,'2019-03-28 23:36:29',1),(14,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140526_109.jpg','苹果','苹果',11,11,0,1,12,'2019-04-03 01:05:40',1),(15,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140604_129.jpg','梨','梨',22,22,0,1,13,'2019-04-03 01:06:21',1),(16,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140646_972.jpg','番茄','番茄',333,33,0,1,14,'2019-04-03 01:06:56',1),(17,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140733_551.jpg','西红柿','西红柿',4,4,0,1,15,'2019-04-03 01:07:41',1),(18,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140802_530.jpg','橙子','橙子',5,5,0,1,16,'2019-04-03 01:08:13',1),(19,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_141601_936.jpg','西瓜','西瓜',6,6,0,1,17,'2019-04-03 01:16:10',1),(20,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_140132_003.jpg','樱桃','樱桃',4,4,0,1,18,'2019-04-08 01:01:43',1),(21,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_140636_198.jpg','葡萄','葡萄',99,99,0,1,19,'2019-04-08 01:06:46',1),(22,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141518_457.jpg','苹果','香甜可口',10,8,0,1,20,'2019-04-15 01:15:46',1),(23,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_142029_847.jpg','苹果','香甜可口',10,8,0,1,21,'2019-04-15 01:20:32',1),(24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_142354_292.jpg','苹果','香甜可口',10,8,0,1,22,'2019-04-15 01:23:59',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (18,9,33,2),(19,10,35,1),(20,11,35,2),(21,12,32,1),(22,13,34,1),(23,12,34,2),(24,11,34,3),(25,11,32,2),(26,11,33,3),(27,11,36,1),(28,11,37,1),(29,12,33,4),(30,12,35,3),(31,12,36,2),(32,12,37,2),(33,13,32,3),(34,13,33,5),(35,13,35,4),(36,13,36,3),(37,13,37,3),(38,9,34,4),(39,9,32,4),(40,9,35,5),(41,9,36,4),(42,10,34,5),(43,10,32,5),(44,10,33,6),(45,14,34,6),(46,15,34,7),(47,16,34,8),(48,17,34,9),(49,18,34,10),(50,19,34,11),(51,20,34,12),(52,21,34,13),(53,22,32,6),(54,23,32,7),(55,24,32,8);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (15,9,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172044_473.jpg',1,1),(16,10,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172140_900.jpg',1,1),(17,11,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190327_172208_744.jpg',1,1),(18,9,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190328_133427_328.jpg',1,3),(19,9,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190328_133442_988.jpg',1,2),(20,9,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190328_133419_429.jpg',1,4),(21,12,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_121327_908.jpg',1,1),(22,13,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190329_123626_827.jpg',1,1),(23,14,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140539_587.jpg',1,1),(24,15,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140620_125.jpg',1,1),(25,16,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140656_352.jpg',1,1),(26,17,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140740_933.jpg',1,1),(27,18,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_140812_911.jpg',1,1),(28,19,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190403_141610_420.jpg',1,1),(32,20,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_140120_553.jpg',1,1),(33,21,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_140645_942.jpg',1,1),(34,21,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_135414_394.jpg',2,1),(35,21,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_174531_357.jpg',2,2),(36,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140211_538.jpg',3,1),(37,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140439_046.jpg',3,2),(38,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140503_551.jpg',3,3),(39,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140537_375.jpg',3,4),(40,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141405_029.jpg',4,1),(41,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141439_635.jpg',4,2),(42,0,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141451_137.jpg',4,3),(43,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140211_538.jpg',1,1),(44,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140439_046.jpg',1,2),(45,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140503_551.jpg',1,3),(46,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_140537_375.jpg',1,4),(47,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141405_029.jpg',2,1),(48,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141439_635.jpg',2,2),(49,24,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190415_141451_137.jpg',2,3);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'http://luoyemyy-mall.oss-cn-shenzhen.aliyuncs.com/20190408_141815_967.jpg','叶玉明','18681506437','e10adc3949ba59abbe56e057f20f883e',1,'2019-03-20 17:00:26','2019-03-20 17:00:26'),(2,NULL,'陈一一','18681506436','E10ADC3949BA59ABBE56E057F20F883E',1,'2019-03-21 00:15:10','2019-03-21 00:15:10'),(3,NULL,'_18681506435','18681506435','E10ADC3949BA59ABBE56E057F20F883E',0,'2019-03-21 00:17:47','2019-03-21 00:17:47'),(5,NULL,'weChat',NULL,NULL,0,'2019-04-10 02:19:56','2019-04-10 02:19:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (6,5,6,0),(7,5,7,1),(8,5,8,0),(9,5,9,0);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `we_chat`
--

LOCK TABLES `we_chat` WRITE;
/*!40000 ALTER TABLE `we_chat` DISABLE KEYS */;
INSERT INTO `we_chat` VALUES (2,NULL,'oLlh35DrPMMDS3NVgj-aHXArC_0U');
/*!40000 ALTER TABLE `we_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `we_chat_user`
--

LOCK TABLES `we_chat_user` WRITE;
/*!40000 ALTER TABLE `we_chat_user` DISABLE KEYS */;
INSERT INTO `we_chat_user` VALUES (2,2,5,'DjtJex4sM8L5dKuhny27Gw==');
/*!40000 ALTER TABLE `we_chat_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-20 23:17:05
