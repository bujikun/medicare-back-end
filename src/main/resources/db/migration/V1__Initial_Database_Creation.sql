-- Adminer 4.8.1 MySQL 8.0.33 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

USE `e_healthcare`;

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
                              `id` binary(16) NOT NULL,
                              `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                              `version` int DEFAULT NULL,
                              `deleted` bit(1) DEFAULT b'0',
                              `created_on` datetime(6) NOT NULL,
                              `updated_on` datetime DEFAULT NULL,
                              `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                              `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UK_pnvtwliipn6ida3ndjrqt2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `categories` (`id`, `name`, `version`, `deleted`, `created_on`, `updated_on`, `created_by`, `last_modified_by`) VALUES
                                                                                                                                (UNHEX('7E98551090CF4DD8B4620549B1A11A0D'),	'Gloves',	NULL,	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                                (UNHEX('8DF057C3D91949BD92ABC06F4268EE5F'),	'Toothbrushes',	NULL,	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                                (UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'),	'Medicines',	NULL,	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                                (UNHEX('E950EEB86746488CA8F3CF519D08FB6D'),	'Syringes',	NULL,	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
                                         `installed_rank` int NOT NULL,
                                         `version` varchar(50) DEFAULT NULL,
                                         `description` varchar(200) NOT NULL,
                                         `type` varchar(20) NOT NULL,
                                         `script` varchar(1000) NOT NULL,
                                         `checksum` int DEFAULT NULL,
                                         `installed_by` varchar(100) NOT NULL,
                                         `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `execution_time` int NOT NULL,
                                         `success` tinyint(1) NOT NULL,
                                         PRIMARY KEY (`installed_rank`),
                                         KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
                               `product_name` varchar(255) NOT NULL,
                               `unit_price` decimal(10,2) NOT NULL,
                               `quantity` int NOT NULL,
                               `fk_order_id` binary(16) NOT NULL,
                               `deleted` bit(1) DEFAULT b'0',
                               KEY `order_id` (`fk_order_id`),
                               CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`fk_order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
                          `id` binary(16) NOT NULL,
                          `fk_user_id` binary(16) NOT NULL,
                          `customer_name` varchar(100) NOT NULL,
                          `total_price` decimal(10,2) NOT NULL,
                          `deleted` bit(1) DEFAULT b'0',
                          `created_on` datetime(6) NOT NULL,
                          `updated_on` datetime(6) DEFAULT NULL,
                          `created_by` varchar(50) DEFAULT NULL,
                          `last_modified_by` varchar(50) DEFAULT NULL,
                          `version` int DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `customer_id` (`fk_user_id`),
                          CONSTRAINT `order_ibfk_1` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
                               `id` binary(16) NOT NULL,
                               `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                               `deleted` bit(1) DEFAULT b'0',
                               `version` int DEFAULT NULL,
                               `created_on` datetime(6) NOT NULL,
                               `updated_on` datetime DEFAULT NULL,
                               `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK_pnvtwliis6p05pn6ida3ndjrqt2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `permissions` (`id`, `name`, `deleted`, `version`, `created_on`, `updated_on`, `created_by`, `last_modified_by`) VALUES
                                                                                                                                 (UNHEX('0A1105A198FB4428894278D33AB2F634'),	'USER',	CONV('0', 2, 10) + 0,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                                 (UNHEX('5967FBC7766D43C0A3FAD1C9FA544381'),	'ADMIN',	CONV('0', 2, 10) + 0,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
                            `id` binary(16) NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `price` decimal(10,2) NOT NULL,
                            `img_url` varchar(1000) DEFAULT NULL,
                            `description` varchar(1000) NOT NULL,
                            `deleted` bit(1) DEFAULT b'0',
                            `created_on` datetime(6) NOT NULL,
                            `updated_on` datetime(6) DEFAULT NULL,
                            `version` int DEFAULT NULL,
                            `created_by` varchar(50) DEFAULT NULL,
                            `last_modified_by` varchar(50) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `products` (`id`, `name`, `price`, `img_url`, `description`, `deleted`, `created_on`, `updated_on`, `version`, `created_by`, `last_modified_by`) VALUES
                                                                                                                                                                 (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'),	'Syringe Intraligamental 1.8ml Euro Thread',	20.55,	'https://medicare-bucket-001.s3.us-east-1.amazonaws.com/1691062813857--5644298a-8dcd-47d6-bd60-3fd35fdbd9a0.jpeg',	'Syringes for Intraligamental Anaesthesia\n(4962 - 4963 - 4964)\nThe intraligament anaesthesia technique is used for the treatment of individual teeth by means of an injection between the alveolar crest and the tooth root. With this technique the needle is inserted through the gingival sulcus into the periodontal space along the mesial or distal surface of the tooth: the anaesthetic solution must be injected slowly keeping a constant and moderate pressure to facilitate the distribution of the liquid. Contraindicated in presence of deep periodontal pockets and gingival acute infection. ',	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	'2023-08-03 14:40:16.000000',	NULL,	NULL,	NULL),
                                                                                                                                                                 (UNHEX('1829FF024BE34870A4155349E36399F1'),	'Protec Latex Powder Free Small Gloves',	10.00,	'https://medicare-bucket-001.s3.us-east-1.amazonaws.com/1691061845635--7988a6c0-824c-4636-9f4b-34e2aa01ef28.jpeg',	'Powder Free Latex Gloves - Latex Exam Gloves.\nPrice is for a Carton of 10 boxes of 100 designed for use in medical areas where protection from blood borne infection is essential.\n    Ambidextrous fitting.\n    Up to five sizes available.\n    High production standards to TGA and FDA requirements from an ISO audited supplier.\n    No Powdered Gloves for people with allergies to conventional glove powders.\n    Each of the three types are coloured coded for easy identification.\n    Perspex Gloves box dispensers available\n',	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	'2023-08-03 14:34:27.000000',	NULL,	NULL,	NULL),
                                                                                                                                                                 (UNHEX('6183F94A300841E68553E006883CC471'),	'Dermovate (Clobetasol Propionate) 0.05% Cream',	7.50,	'https://medicare-bucket-001.s3.us-east-1.amazonaws.com/1691062680118--57545ca9-34be-475d-809a-6bd53558fd7e.jpeg',	'If you suffer from chronic skin flare ups and other corticosteroid creams have not worked, Dermovate could help. \n\nWith OxfordOnlinePharmacy it is easier than ever to get your Dermovate prescription online: no waiting times, no hassle.\n\nDermovate is a strong topical steroid which is effective in the treatment of severe skin conditions such as eczema and psoriasis.\n\nDermovate is a cream that contains the active ingredient Clobetasol Propionate, which is a topical corticosteroid (topical means that it is applied direct to affected area, and a corticosteroid reduces inflammation). Dermovate is stronger than other corticosteroids which is only available on prescription. Dermovate is only to be used when other steroid creams have not been effective.',	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	'2023-08-03 14:38:02.000000',	NULL,	NULL,	NULL),
                                                                                                                                                                 (UNHEX('845CEA8132FE4909917EF31CFF421199'),	'Cura-Heat Back and Shoulder Pain 3 Heat Packs',	2.77,	'https://medicare-bucket-001.s3.us-east-1.amazonaws.com/1691062744683--47dfc10c-f71f-495d-9662-7ec99d931df4.jpeg',	'Cura-Heat Back & Shoulder Pain 3 Heat Packs is a heat patch which gives 12 hour relief from muscles and joint aches and pains. It works by increasing the blood circulation in the area which in turn reduces the stiffness and relaxes the sore muscles. Cura-Heat Back & Shoulder Pain can be bought from OxfordPharmacyOnline.',	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	'2023-08-03 14:39:06.000000',	NULL,	NULL,	NULL),
                                                                                                                                                                 (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'),	'Tepe Nova Soft Toothbrush 80 Pack',	15.99,	'https://medicare-bucket-001.s3.us-east-1.amazonaws.com/1691062519501--dfff0d35-dc96-47f4-bc39-fd5567687a11.jpeg',	'Nova has a tapered brush head with an active tip for increased access. It is specially efficient around the posterior teeth and other difficult to reach areas. The end-rounded filaments ensure gentle cleaning. Non-slip handle with thumb pad. Neck can be angled without heating. Blue tip: medium, yellow tip: soft, pink tip: x-soft. ',	CONV('0', 2, 10) + 0,	'2023-08-01 00:16:08.000000',	'2023-08-03 14:35:22.000000',	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `products_categories`;
CREATE TABLE `products_categories` (
                                       `fk_product_id` binary(16) NOT NULL,
                                       `fk_category_id` binary(16) NOT NULL,
                                       `deleted` bit(1) DEFAULT b'0',
                                       `created_on` datetime(6) NOT NULL,
                                       `updated_on` datetime DEFAULT NULL,
                                       PRIMARY KEY (`fk_product_id`,`fk_category_id`),
                                       KEY `FKbvwr8podneeiefx7agb3y6w` (`fk_category_id`),
                                       CONSTRAINT `FKaeu88selaevs59733qluau` FOREIGN KEY (`fk_product_id`) REFERENCES `products` (`id`),
                                       CONSTRAINT `FKbvwr8podnb6u53eeiefx7a` FOREIGN KEY (`fk_category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `products_categories` (`fk_product_id`, `fk_category_id`, `deleted`, `created_on`, `updated_on`) VALUES
                                                                                                                 (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'),	UNHEX('E950EEB86746488CA8F3CF519D08FB6D'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                                 (UNHEX('1829FF024BE34870A4155349E36399F1'),	UNHEX('7E98551090CF4DD8B4620549B1A11A0D'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                                 (UNHEX('6183F94A300841E68553E006883CC471'),	UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                                 (UNHEX('845CEA8132FE4909917EF31CFF421199'),	UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                                 (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'),	UNHEX('8DF057C3D91949BD92ABC06F4268EE5F'),	NULL,	'2023-08-01 00:16:08.000000',	NULL);

DROP TABLE IF EXISTS `products_sellers`;
CREATE TABLE `products_sellers` (
                                    `fk_product_id` binary(16) NOT NULL,
                                    `fk_seller_id` binary(16) NOT NULL,
                                    `deleted` bit(1) DEFAULT b'0',
                                    `created_on` datetime(6) NOT NULL,
                                    `updated_on` datetime DEFAULT NULL,
                                    PRIMARY KEY (`fk_product_id`,`fk_seller_id`),
                                    KEY `FKbvwr8podndaadadeeiefx7agb3y6w` (`fk_seller_id`),
                                    CONSTRAINT `FKaeu88dadaselaevs59733qluau` FOREIGN KEY (`fk_product_id`) REFERENCES `products` (`id`),
                                    CONSTRAINT `FKbvwr8paasdsodnb6u53eeiefx7a` FOREIGN KEY (`fk_seller_id`) REFERENCES `sellers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `products_sellers` (`fk_product_id`, `fk_seller_id`, `deleted`, `created_on`, `updated_on`) VALUES
                                                                                                            (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'),	UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                            (UNHEX('1829FF024BE34870A4155349E36399F1'),	UNHEX('629269FF521843AABB7ADB8858CE6CFE'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                            (UNHEX('6183F94A300841E68553E006883CC471'),	UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                            (UNHEX('845CEA8132FE4909917EF31CFF421199'),	UNHEX('629269FF521843AABB7ADB8858CE6CFE'),	NULL,	'2023-08-01 00:16:08.000000',	NULL),
                                                                                                            (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'),	UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'),	NULL,	'2023-08-01 00:16:08.000000',	NULL);

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
                         `id` binary(16) NOT NULL,
                         `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `deleted` bit(1) DEFAULT b'0',
                         `version` int DEFAULT NULL,
                         `created_on` datetime(6) NOT NULL,
                         `updated_on` datetime DEFAULT NULL,
                         `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_pnvtwliis6p05dapn6i3ndjrqt2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
                                     `fk_role_id` binary(16) NOT NULL,
                                     `fk_permission_id` binary(16) NOT NULL,
                                     `created_on` datetime(6) NOT NULL,
                                     `updated_on` datetime DEFAULT NULL,
                                     `deleted` bit(1) DEFAULT b'0',
                                     PRIMARY KEY (`fk_role_id`,`fk_permission_id`),
                                     KEY `FKbvwr8podnbwedwdw6u53eeiefx7agb3y6w` (`fk_permission_id`),
                                     CONSTRAINT `FKaeu8lc9yh8sela8757evs59733qluau` FOREIGN KEY (`fk_role_id`) REFERENCES `roles` (`id`),
                                     CONSTRAINT `FKbvwr8podnb6u53e6fsdeiefx7agb3y6w` FOREIGN KEY (`fk_permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `sellers`;
CREATE TABLE `sellers` (
                           `id` binary(16) NOT NULL,
                           `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           `version` int DEFAULT NULL,
                           `deleted` bit(1) DEFAULT b'0',
                           `created_on` datetime(6) NOT NULL,
                           `updated_on` datetime DEFAULT NULL,
                           `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_pnvtdsdsdwliipn6ida3ndjrqt2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `sellers` (`id`, `name`, `version`, `deleted`, `created_on`, `updated_on`, `created_by`, `last_modified_by`) VALUES
                                                                                                                             (UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'),	'100mg',	NULL,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                             (UNHEX('629269FF521843AABB7ADB8858CE6CFE'),	'Johnson& Johnson',	NULL,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL,	NULL),
                                                                                                                             (UNHEX('708B6C170E4942ABA0046425470829A3'),	'Medicare',	NULL,	CONV('0', 2, 10) + 0,	'2023-08-03 13:43:08.000000',	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE `tokens` (
                          `id` binary(16) NOT NULL,
                          `value` varchar(1000) NOT NULL,
                          `username` varchar(50) NOT NULL,
                          `invalidated` bit(1) NOT NULL,
                          `version` int DEFAULT NULL,
                          `deleted` bit(1) DEFAULT b'0',
                          `created_on` datetime(6) NOT NULL,
                          `updated_on` datetime(6) DEFAULT NULL,
                          `created_by` varchar(50) DEFAULT NULL,
                          `last_modified_by` varchar(50) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` binary(16) NOT NULL,
                         `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `account_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                         `is_account_locked` bit(1) NOT NULL,
                         `is_account_expired` bit(1) NOT NULL,
                         `is_credentials_expired` bit(1) NOT NULL,
                         `is_enabled` bit(1) NOT NULL,
                         `deleted` bit(1) DEFAULT b'0',
                         `balance` decimal(10,2) NOT NULL,
                         `version` int DEFAULT NULL,
                         `updated_on` datetime DEFAULT NULL,
                         `created_on` datetime(6) NOT NULL,
                         `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `lastname`, `account_number`, `is_account_locked`, `is_account_expired`, `is_credentials_expired`, `is_enabled`, `deleted`, `balance`, `version`, `updated_on`, `created_on`, `created_by`, `last_modified_by`) VALUES
                                                                                                                                                                                                                                                                                    (UNHEX('F84136E1A60F4890BC8D0C82469CF37A'),	'user',	'{bcrypt}$2a$10$8ND7peb4Dpg.1s0AsmXqne/HMkXbV9zkpAu76Bb9v0MqUmBWC/FNm',	'user',	'user',	'615305E4-9882-4AD7-BACD-6384D71820B6',	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('1', 2, 10) + 0,	CONV('0', 2, 10) + 0,	1000.00,	NULL,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL),
                                                                                                                                                                                                                                                                                    (UNHEX('F99FDFE49F6E4A0AABE1F5178B38D4C7'),	'newton',	'{bcrypt}$2a$10$0JsK1OZ9XbulsNy4fwbQWefMNXfr2Ly6BWnkQQvoxVAkHqh8h4n6C',	'Newton',	'Bujiku',	'70F75E8CBE5F-4F8E-AE1B-3546ED5D345E',	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('1', 2, 10) + 0,	CONV('0', 2, 10) + 0,	900.86,	NULL,	NULL,	'2023-08-03 15:10:24.000000',	NULL,	NULL),
                                                                                                                                                                                                                                                                                    (UNHEX('FC19FD13D8A94DF992915221C40E9898'),	'admin',	'{bcrypt}$2a$10$NHuwmKM1AxCDo4Tp9At./OqO7nbmKdpmgsdabtMIQu4B.hkaXqb7.',	'admin',	'admin',	'9B4F4326-74B9-499C-9B6D-D94794476872',	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('0', 2, 10) + 0,	CONV('1', 2, 10) + 0,	CONV('0', 2, 10) + 0,	426.14,	NULL,	NULL,	'2023-08-01 00:16:08.000000',	NULL,	NULL);

DROP TABLE IF EXISTS `users_permissions`;
CREATE TABLE `users_permissions` (
                                     `fk_user_id` binary(16) NOT NULL,
                                     `fk_permission_id` binary(16) NOT NULL,
                                     `created_on` datetime(6) NOT NULL,
                                     `updated_on` datetime DEFAULT NULL,
                                     `deleted` bit(1) DEFAULT b'0',
                                     PRIMARY KEY (`fk_user_id`,`fk_permission_id`),
                                     KEY `FKbvwr8podnb6u53eeiefx7agb3y6w` (`fk_permission_id`),
                                     CONSTRAINT `FKaeu8lc9yh8selaevs59733qluau` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`id`),
                                     CONSTRAINT `FKbvwr8podnb6u53eeiefx7agb3y6w` FOREIGN KEY (`fk_permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users_permissions` (`fk_user_id`, `fk_permission_id`, `created_on`, `updated_on`, `deleted`) VALUES
                                                                                                              (UNHEX('F84136E1A60F4890BC8D0C82469CF37A'),	UNHEX('0A1105A198FB4428894278D33AB2F634'),	'2023-08-01 00:16:08.000000',	NULL,	NULL),
                                                                                                              (UNHEX('F99FDFE49F6E4A0AABE1F5178B38D4C7'),	UNHEX('0A1105A198FB4428894278D33AB2F634'),	'2023-08-03 15:10:24.000000',	NULL,	NULL),
                                                                                                              (UNHEX('FC19FD13D8A94DF992915221C40E9898'),	UNHEX('0A1105A198FB4428894278D33AB2F634'),	'2023-08-01 00:16:08.000000',	NULL,	NULL),
                                                                                                              (UNHEX('FC19FD13D8A94DF992915221C40E9898'),	UNHEX('5967FBC7766D43C0A3FAD1C9FA544381'),	'2023-08-01 00:16:08.000000',	NULL,	NULL);

-- 2023-08-04 15:31:58
