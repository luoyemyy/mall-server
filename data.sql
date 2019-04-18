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
INSERT INTO `order` VALUES (1,'111',1,0,12,0,'111','111','aaa','ssss','2019-04-18 21:42:45','2019-04-18 21:42:45','111','111',1),(2,'cc435c659f174e25ba42223dec33c927',5,0,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:27:41','2019-04-18 22:27:41','d9cac14f986d4276a3d7e39943c5bb68',NULL,1),(3,'3bf5fe653a634874bb4f1ad351907dfd',5,0,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:30:33','2019-04-18 22:30:33','5e9381b8b05e469a82ca6430c3e53b16',NULL,1),(4,'c87726d2a4344b649b0cb0d242dffa85',5,0,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:30:55','2019-04-18 22:30:55','82f056a63719487890a95e1a41ac6b88',NULL,1),(5,'6baad337334f411d8acb5e3cf246864e',5,0,300,0,'叶玉明','18681506437','江西省 九江市 湖口县 均桥镇枫树村一组010号','332500','2019-04-18 22:32:24','2019-04-18 22:32:24','09d2716f8ce04727a86d36f8b589a3dd',NULL,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (1,3,9,1,300),(2,4,9,1,300),(3,5,9,1,300);
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
INSERT INTO `shop_cart` VALUES (155,5,9,1);
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
INSERT INTO `we_chat_user` VALUES (2,2,5,'C/qNPVVRDzZU656ZCYRcfA==');
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

-- Dump completed on 2019-04-18 22:37:00
