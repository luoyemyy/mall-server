-- MySQL Script generated by MySQL Workbench
-- Fri Apr 26 14:17:51 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mall
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mall` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `mall` ;

-- -----------------------------------------------------
-- Table `mall`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '收件人',
  `phone` VARCHAR(45) NULL COMMENT '手机',
  `post_code` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL COMMENT '国家',
  `province` VARCHAR(45) NULL COMMENT '省',
  `city` VARCHAR(60) NULL COMMENT '市',
  `county` VARCHAR(60) NULL COMMENT '县',
  `street` VARCHAR(100) NULL COMMENT '街道',
  `summary` VARCHAR(255) NULL COMMENT '合称',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '地址';


-- -----------------------------------------------------
-- Table `mall`.`we_chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`we_chat` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `union_id` VARCHAR(45) NULL,
  `open_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '微信账号';


-- -----------------------------------------------------
-- Table `mall`.`we_chat_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`we_chat_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `we_chat_id` BIGINT(20) NULL,
  `user_id` BIGINT(20) NULL,
  `token` VARCHAR(60) NULL COMMENT '微信用户登录token',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '微信用户';


-- -----------------------------------------------------
-- Table `mall`.`user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`user_address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL,
  `address_id` BIGINT(20) NULL,
  `type` INT NULL COMMENT '1 默认地址 0 其他地址',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '用户地址';


-- -----------------------------------------------------
-- Table `mall`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cover_image` VARCHAR(255) NULL COMMENT '封面 图片地址',
  `name` VARCHAR(45) NULL COMMENT '名称',
  `description` VARCHAR(255) NULL COMMENT '描述',
  `market_price` FLOAT NULL COMMENT '市场价格',
  `actual_price` FLOAT NULL COMMENT '实际价格',
  `stock` INT NULL COMMENT '库存',
  `online` TINYINT(1) NULL COMMENT '是否上架',
  `sort` INT NULL COMMENT '在全部分类中产品的排序号',
  `create_time` DATETIME NULL,
  `status` INT NULL COMMENT '0 已删除 1 有效',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '产品';


-- -----------------------------------------------------
-- Table `mall`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '名称',
  `sort` INT NULL COMMENT '排序号',
  `state` INT NULL COMMENT '0 无效 1 有效',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分类';


-- -----------------------------------------------------
-- Table `mall`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) NULL,
  `category_id` BIGINT(20) NULL,
  `sort` INT NULL COMMENT '在分类中的产品排序号',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '产品分类';


-- -----------------------------------------------------
-- Table `mall`.`shop_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`shop_cart` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL,
  `product_id` BIGINT(20) NULL,
  `count` INT NULL COMMENT '数量',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '购物车';


-- -----------------------------------------------------
-- Table `mall`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`order` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(45) NULL COMMENT '订单唯一id uuid 32位',
  `user_id` BIGINT(20) NULL,
  `state` INT NULL COMMENT '0 未支付 1 已支付，待确认 2 支付成功，待发货 3 发货中 4 运输中 5 已签收，交易完成 \n6 取消订单，待审核  7 退货，待审核 8 退货中 9 退款中 10 已取消',
  `money` FLOAT NULL COMMENT '总金额 包含邮费',
  `postage` FLOAT NULL COMMENT '邮费',
  `product_count` INT NULL,
  `username` VARCHAR(45) NULL COMMENT '收件人姓名',
  `phone` VARCHAR(45) NULL COMMENT '收件人电话',
  `address` VARCHAR(255) NULL COMMENT '地址',
  `postcode` VARCHAR(45) NULL,
  `create_time` DATETIME NULL,
  `pay_time` DATETIME NULL COMMENT '支付时间',
  `deliver_time` DATETIME NULL COMMENT '配送时间',
  `sign_time` DATETIME NULL COMMENT '签收时间',
  `wx_pay_id` VARCHAR(64) NULL COMMENT '微信支付统一下单号',
  `wx_order_id` VARCHAR(45) NULL COMMENT '微信支付订单号',
  `express_company` VARCHAR(45) NULL COMMENT '快递公司',
  `express_no` VARCHAR(60) NULL COMMENT '快递单编号',
  `cancel_reason` VARCHAR(100) NULL COMMENT '取消/退货原因',
  `refund_money` FLOAT NULL,
  `refuse_order_no` VARCHAR(45) NULL COMMENT '退款单号',
  `refuse_wx_no` VARCHAR(45) NULL COMMENT '微信退款单号',
  `status` INT NULL COMMENT '0 已删除 1 有效',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '订单';


-- -----------------------------------------------------
-- Table `mall`.`order_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`order_product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT(20) NULL,
  `product_id` BIGINT(20) NULL,
  `count` INT NULL COMMENT '数量',
  `price` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '订单产品';


-- -----------------------------------------------------
-- Table `mall`.`admin_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`admin_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL,
  `role` INT NULL COMMENT '角色id 1 系统管理员 2 管理员 3 客服',
  `token` VARCHAR(60) NULL COMMENT '管理用户登录token',
  `state` INT NULL COMMENT '0 无效 1 有效',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '管理人员';


-- -----------------------------------------------------
-- Table `mall`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`product_image` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) NULL,
  `image` VARCHAR(255) NULL COMMENT '图片地址',
  `type` INT NULL COMMENT '1 滑动展示图 2 描述图 3 轮播图模板 4 描述图模板',
  `sort` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '产品图片';


-- -----------------------------------------------------
-- Table `mall`.`hot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`hot` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(255) NULL COMMENT '图片地址',
  `description` VARCHAR(100) NULL COMMENT '描述',
  `sort` INT NULL COMMENT '排序号',
  `state` INT NULL COMMENT '状态',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mall`.`hot_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`hot_product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `hot_id` BIGINT(20) NULL,
  `product_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mall`.`key_value`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`key_value` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(45) NULL,
  `value` VARCHAR(512) NULL,
  `expire` BIGINT(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mall`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `head_image` VARCHAR(255) NULL COMMENT '头像',
  `name` VARCHAR(45) NULL COMMENT '名称',
  `phone` VARCHAR(20) NULL COMMENT '手机',
  `password` VARCHAR(64) NULL COMMENT '密码',
  `gender` INT NULL COMMENT '性别 0 未知 1男 2女',
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '用户';


-- -----------------------------------------------------
-- Table `mall`.`postage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mall`.`postage` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `area` VARCHAR(60) NULL COMMENT '地区（省）',
  `price` FLOAT NULL COMMENT '价格',
  `post` INT NULL COMMENT '0 不邮寄 1 邮寄',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '邮费';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
