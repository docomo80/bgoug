-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.13 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5453
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bgoug_test
CREATE DATABASE IF NOT EXISTS `bgoug_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bgoug_test`;

-- Dumping structure for table bgoug_test.application
CREATE TABLE IF NOT EXISTS `application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.application: ~5 rows (approximately)
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` (`id`, `name`) VALUES
	(1, 'Счетоводство'),
	(2, 'Банкови системи'),
	(3, 'Човешки ресурси'),
	(4, 'Нефтопреработка'),
	(5, 'Фармация');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhr48nopy5aorw0ta1ii704tpu` (`event_id`),
  CONSTRAINT `FKhr48nopy5aorw0ta1ii704tpu` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.comment: ~4 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `description`, `event_id`) VALUES
	(8, 'Много добро преживяване', 3),
	(9, 'Много се напихме', 2),
	(10, 'Много яко', 2),
	(11, 'Пак бих повторил', 1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.company
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_of_business` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_niu8sfil2gxywcru9ah3r4ec5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.company: ~4 rows (approximately)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `address`, `name`, `type_of_business`) VALUES
	(4, 'Woodbine Street 56', 'Oracle', 'разработка на софтуер'),
	(6, 'ул. Пробуда 60', 'ТехноЛогика', 'внедряване на информационни системи, разработка на софтуер, консултантски услуги и специализирано обучение.'),
	(7, 'ул Хр. Ботев 41', 'Без компания', 'безработни'),
	(10, 'ул. Булаир 21', 'ДиТра', 'предлага комплексни решения за проектиране на изделията, инженерни симулации, техническа комуникация и цялостно управление на инженерната дейност');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.event
CREATE TABLE IF NOT EXISTS `event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.event: ~3 rows (approximately)
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`id`, `cost`, `date`, `description`, `location`, `name`) VALUES
	(1, 1236, '2017-11-29 12:20:36', 'среща на всички членове', 'Боровец', 'Пролетен семинар'),
	(2, 4563, '2018-09-04 00:00:00', 'есенна среща ', 'Хисаря', 'Есенен семинар'),
	(3, 4563, '2016-09-04 00:00:00', 'среща на членовете', 'Сандански', 'Среща през април');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.member
CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `discount` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `member_type` varchar(255) DEFAULT NULL,
  `membership_fee` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pc_platform` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKax2gealrg44mnq3ibas3q9de6` (`company_id`),
  CONSTRAINT `FKax2gealrg44mnq3ibas3q9de6` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.member: ~6 rows (approximately)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `account_non_expired`, `account_non_locked`, `address`, `credentials_non_expired`, `discount`, `enabled`, `member_type`, `membership_fee`, `name`, `password`, `pc_platform`, `position`, `telephone_number`, `username`, `company_id`) VALUES
	(1, b'1', b'1', '87 Woodbine Street', b'1', 0, b'1', 'INDIVIDUAL', b'1', 'Веселин Сариев', '$2a$10$DYU7yxl8TeNOV7D9I98l7OxOpPZzoe7YKlC/OcMmFVo1eMB6V68LO', '004', 'developer', '07404085495', 'docomo', 6),
	(2, b'1', b'1', '87 Woodbine Street', b'1', 0, b'1', 'INDIVIDUAL', b'0', 'Петя Ковачева', '$2a$10$owj6shgB5iwvFgrJqQCXDOuRT1sXxwuyj4CFE7ydyQkBAWgw/J/42', '002', 'developer', '07404085495', 'petito', 4),
	(3, b'1', b'1', 'Perushtica 7', b'1', 0, b'1', 'CORPORATE', b'0', 'Мариян Николов', '$2a$10$QCsBmLLkDSH93Kow/.HYI..4LHsjGiBdGfaaoBHvBcwk0dZUIx5cS', '002', 'developer', '0878533500', 'marchelo', 7),
	(4, b'1', b'1', '197 Whitehall Road', b'1', 0, b'1', 'INDIVIDUAL', b'0', 'Пенка Георгиева', '$2a$10$AbijatWo2iLkwUHlq/MF8e.gGSztoS.MNQLGy0wwFM.nheB8wvyp.', '004', 'back-end developer', '0896356457', 'penka82', 7),
	(5, b'1', b'1', 'Perushtica 21', b'1', 0, b'1', 'INDIVIDUAL', b'0', 'Иван Тодоров', '$2a$10$yiXiqQA7UudmdvN9EJKtDeJTFPz6u2AOiCVUpOW321y5sGXUuRAtC', '005', 'front-end developer', '0878659874', 'vankata', 10),
	(6, b'1', b'1', '189 Whitehall Road', b'1', 0, b'1', 'INDIVIDUAL', b'0', 'Вероника Стаменова', '$2a$10$d71GMREEJNKtcvp8JsuxMeXAm8JDopFSuRJ9Xx4ktwYah3qnVE1kG', '001', 'developer', '0878533589', 'ronito', 4);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.members_applications
CREATE TABLE IF NOT EXISTS `members_applications` (
  `member_id` bigint(20) NOT NULL,
  `application_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`,`application_id`),
  KEY `FKt4douwqpmf9k0sfui9qewjeqb` (`application_id`),
  CONSTRAINT `FKnjgmcko7o7k8baonbqm0to9wf` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKt4douwqpmf9k0sfui9qewjeqb` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.members_applications: ~10 rows (approximately)
/*!40000 ALTER TABLE `members_applications` DISABLE KEYS */;
INSERT INTO `members_applications` (`member_id`, `application_id`) VALUES
	(2, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(1, 2),
	(2, 2),
	(3, 2),
	(4, 2),
	(5, 2),
	(6, 2);
/*!40000 ALTER TABLE `members_applications` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.members_event
CREATE TABLE IF NOT EXISTS `members_event` (
  `member_id` bigint(20) NOT NULL,
  `event_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`,`event_id`),
  KEY `FKo0fauaee3wr6nrpvojtoci6aa` (`event_id`),
  CONSTRAINT `FKo0fauaee3wr6nrpvojtoci6aa` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FKu4lhgi6bn9l756mjvek3d3uo` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.members_event: ~6 rows (approximately)
/*!40000 ALTER TABLE `members_event` DISABLE KEYS */;
INSERT INTO `members_event` (`member_id`, `event_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(5, 2),
	(6, 2),
	(4, 3);
/*!40000 ALTER TABLE `members_event` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.members_recommended_members
CREATE TABLE IF NOT EXISTS `members_recommended_members` (
  `member_id` bigint(20) NOT NULL,
  `recommended_member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`,`recommended_member_id`),
  KEY `FKeet3jnvxt3y8bncdhfbih7q4k` (`recommended_member_id`),
  CONSTRAINT `FKeet3jnvxt3y8bncdhfbih7q4k` FOREIGN KEY (`recommended_member_id`) REFERENCES `recommended_members` (`id`),
  CONSTRAINT `FKs8mkn1yf6eko9iv6dxtnvb75d` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.members_recommended_members: ~4 rows (approximately)
/*!40000 ALTER TABLE `members_recommended_members` DISABLE KEYS */;
INSERT INTO `members_recommended_members` (`member_id`, `recommended_member_id`) VALUES
	(2, 1),
	(3, 2),
	(5, 3),
	(6, 4);
/*!40000 ALTER TABLE `members_recommended_members` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.members_roles
CREATE TABLE IF NOT EXISTS `members_roles` (
  `member_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`,`role_id`),
  KEY `FKf9ds956i3re2d8tfbdqbik9d2` (`role_id`),
  CONSTRAINT `FKf9ds956i3re2d8tfbdqbik9d2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKtcx440qhb934tihsb7vddbvy4` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.members_roles: ~6 rows (approximately)
/*!40000 ALTER TABLE `members_roles` DISABLE KEYS */;
INSERT INTO `members_roles` (`member_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6);
/*!40000 ALTER TABLE `members_roles` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.recommended_members
CREATE TABLE IF NOT EXISTS `recommended_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.recommended_members: ~4 rows (approximately)
/*!40000 ALTER TABLE `recommended_members` DISABLE KEYS */;
INSERT INTO `recommended_members` (`id`, `name`) VALUES
	(1, 'Веселин Сариев'),
	(2, 'Петя Ковачева'),
	(3, 'Мариян Николов'),
	(4, 'Пенка Георгиева');
/*!40000 ALTER TABLE `recommended_members` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.roles: ~6 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `authority`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER'),
	(3, 'ROLE_USER'),
	(4, 'ROLE_USER'),
	(5, 'ROLE_USER'),
	(6, 'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
