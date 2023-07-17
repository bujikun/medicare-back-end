-- Adminer 4.8.1 MySQL 8.0.33 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

# DROP DATABASE IF EXISTS `e_healthcare`;
# CREATE DATABASE `e_healthcare` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
USE `e_healthcare`;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`
(
    `id`         BINARY(16)            NOT NULL,
    `name`       varchar(255)   NOT NULL,
    `price`      decimal(10, 2) NOT NULL,
    `img_url`    varchar(1000)  NOT NULL,
    `description`    varchar(1000)  NOT NULL,
    `created_on` datetime(6)    NOT NULL,
    `updated_on` datetime(6) DEFAULT NULL,
    `version`    int         DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`
(
    `id`         BINARY(16)            NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `version`    int      DEFAULT NULL,
    `created_on` datetime(6)                                                  NOT NULL,
    `updated_on` datetime DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_pnvtwliipn6ida3ndjrqt2` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `products_categories`;
CREATE TABLE `products_categories`
(
    `fk_product_id`        BINARY(16)          NOT NULL,
    `fk_category_id`  BINARY(16)          NOT NULL,
    `created_on`       datetime(6) NOT NULL,
    `updated_on`       datetime DEFAULT NULL,
    PRIMARY KEY (`fk_product_id`, `fk_category_id`),
    KEY `FKbvwr8podneeiefx7agb3y6w` (`fk_category_id`),
    CONSTRAINT `FKaeu88selaevs59733qluau` FOREIGN KEY (`fk_product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `FKbvwr8podnb6u53eeiefx7a` FOREIGN KEY (`fk_category_id`) REFERENCES `categories` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`
(
    `product_name`   varchar(255)   NOT NULL,
    `unit_price`  decimal(10, 2) NOT NULL,
    `quantity`    int            NOT NULL,
    `fk_order_id`  BINARY(16)             NOT NULL,
    KEY `order_id` (`fk_order_id`),
    CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`fk_order_id`) REFERENCES `orders` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

TRUNCATE `order_items`;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`         BINARY(16)            NOT NULL,
    `order_number`   varchar(255) NOT NULL,
    `fk_user_id`  BINARY(16)           NOT NULL,
    `customer_name`  varchar(100) NOT NULL,
    `created_on`     datetime(6)  NOT NULL,
    `updated_on`     datetime(6) DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    `version`        int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `order_number` (`order_number`),
    KEY `customer_id` (`fk_user_id`),
    CONSTRAINT `order_ibfk_1` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`         BINARY(16)            NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `version`    int      DEFAULT NULL,
    `created_on` datetime(6)                                                  NOT NULL,
    `updated_on` datetime DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_pnvtwliis6p05dapn6i3ndjrqt2` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`
(
    `id`         BINARY(16)            NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `version`    int      DEFAULT NULL,
    `created_on` datetime(6)                                                  NOT NULL,
    `updated_on` datetime DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_pnvtwliis6p05pn6ida3ndjrqt2` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE `tokens`
(
    `id`         BINARY(16)            NOT NULL,
    `value`       varchar(1000) NOT NULL,
    `username`    varchar(50)   NOT NULL,
    `invalidated` bit(1)        NOT NULL,
    `version`     int DEFAULT NULL,
    `created_on`  datetime(6)   NOT NULL,
    `updated_on`  datetime(6)   DEFAULT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         BINARY(16)            NOT NULL ,
    `username`               varchar(50)   NOT NULL,
    `password`               varchar(255)  NOT NULL,
    `firstname`     varchar(255) NOT NULL,
    `lastname`      varchar(255) NOT NULL,
    `email`          varchar(100) NOT NULL,
    `account_number` varchar(100) NOT NULL,
    `is_account_locked`      bit(1)                                                        NOT NULL,
    `is_account_expired`     bit(1)                                                        NOT NULL,
    `is_credentials_expired` bit(1)                                                        NOT NULL,
    `is_enabled`             bit(1)                                                        NOT NULL,
    `version`                int      DEFAULT NULL,
    `updated_on`             datetime DEFAULT NULL,
    `created_on`             datetime(6)                                                   NOT NULL,
    `created_by`       varchar(50) DEFAULT NULL,
    `last_modified_by`       varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `users_permissions`;
CREATE TABLE `users_permissions`
(
    `fk_user_id`        BINARY(16)          NOT NULL,
    `fk_permission_id`  BINARY(16)          NOT NULL,
    `created_on`       datetime(6) NOT NULL,
    `updated_on`       datetime DEFAULT NULL,
    PRIMARY KEY (`fk_user_id`, `fk_permission_id`),
    KEY `FKbvwr8podnb6u53eeiefx7agb3y6w` (`fk_permission_id`),
    CONSTRAINT `FKaeu8lc9yh8selaevs59733qluau` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKbvwr8podnb6u53eeiefx7agb3y6w` FOREIGN KEY (`fk_permission_id`) REFERENCES `permissions` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions`
(
    `fk_role_id`        BINARY(16)          NOT NULL,
    `fk_permission_id`  BINARY(16)          NOT NULL,
    `created_on`       datetime(6) NOT NULL,
    `updated_on`       datetime DEFAULT NULL,
    PRIMARY KEY (`fk_role_id`, `fk_permission_id`),
    KEY `FKbvwr8podnbwedwdw6u53eeiefx7agb3y6w` (`fk_permission_id`),
    CONSTRAINT `FKaeu8lc9yh8sela8757evs59733qluau` FOREIGN KEY (`fk_role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FKbvwr8podnb6u53e6fsdeiefx7agb3y6w` FOREIGN KEY (`fk_permission_id`) REFERENCES `permissions` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

