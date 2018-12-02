-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.16-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5327
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
  KEY `FK_comment_event` (`event_id`),
  CONSTRAINT `FK_comment_event` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.comment: ~3 rows (approximately)
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
  PRIMARY KEY (`id`)
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
	(3, 4563, '2018-09-04 00:00:00', 'среща на членовете', 'Сандански', 'Среща през април');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.member
CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `member_type` varchar(255) DEFAULT NULL,
  `membership_fee` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pc_platform` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9esvgikrmti1v7ci6v453imdc` (`name`),
  KEY `FKax2gealrg44mnq3ibas3q9de6` (`company_id`),
  CONSTRAINT `FKax2gealrg44mnq3ibas3q9de6` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.member: ~1 rows (approximately)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `address`, `discount`, `member_type`, `membership_fee`, `name`, `pc_platform`, `position`, `telephone_number`, `company_id`) VALUES
	(1, '87 Woodbine Street', 4, 'INDIVIDUAL', b'1', 'Веселин Сариев', '001', 'developer', '07404085495', 6),
	(2, '197 Whitehall Road', 3, 'CORPORATE', b'0', 'Петя Кочева', '001', 'QA', '0878533500', 7),
	(3, 'Бургас, ул. Хр. Ботев 23', 1, 'INDIVIDUAL', b'0', 'Георги Панайотов', '004', 'developer', '0895647845', 10),
	(4, 'Бургас, ул. Славянска 56', 0, 'INDIVIDUAL', b'1', 'Стоян Физиев', '005', 'QA', '0879546535', 4),
	(5, 'Бургас, ул. Славянска 5', 0, 'CORPORATE', b'0', 'Петър Гочев', '004', 'front-end developer', '0889654898', 4),
	(6, 'Пловдив, ул. Сливница 54', 0, 'CORPORATE', b'1', 'Атанаска Пешева', '001', 'back-end developer', '0896547845', 4),
	(7, 'Плевен, ул. Дунав 56', 0, 'INDIVIDUAL', b'1', 'Ралица Косева', '003', 'QA', '0874569636', 4);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.members_applications
CREATE TABLE IF NOT EXISTS `members_applications` (
  `application_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`application_id`,`member_id`),
  KEY `FKnjgmcko7o7k8baonbqm0to9wf` (`member_id`),
  CONSTRAINT `FKnjgmcko7o7k8baonbqm0to9wf` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKt4douwqpmf9k0sfui9qewjeqb` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.members_applications: ~1 rows (approximately)
/*!40000 ALTER TABLE `members_applications` DISABLE KEYS */;
INSERT INTO `members_applications` (`application_id`, `member_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 3),
	(3, 3),
	(1, 4),
	(2, 5),
	(1, 6),
	(1, 7),
	(2, 7);
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

-- Dumping data for table bgoug_test.members_event: ~0 rows (approximately)
/*!40000 ALTER TABLE `members_event` DISABLE KEYS */;
INSERT INTO `members_event` (`member_id`, `event_id`) VALUES
	(2, 1),
	(6, 1),
	(7, 1),
	(1, 2),
	(5, 2),
	(4, 3);
/*!40000 ALTER TABLE `members_event` ENABLE KEYS */;

-- Dumping structure for table bgoug_test.member_recommended_members
CREATE TABLE IF NOT EXISTS `member_recommended_members` (
  `member_id` bigint(20) NOT NULL,
  `recommended_members` varchar(255) DEFAULT NULL,
  KEY `FKgihcd1p5wrepfw0uj41c89vr` (`member_id`),
  CONSTRAINT `FKgihcd1p5wrepfw0uj41c89vr` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bgoug_test.member_recommended_members: ~0 rows (approximately)
/*!40000 ALTER TABLE `member_recommended_members` DISABLE KEYS */;
INSERT INTO `member_recommended_members` (`member_id`, `recommended_members`) VALUES
	(2, 'Веселин Сариев'),
	(3, 'Петя Кочева'),
	(3, 'Веселин Сариев'),
	(5, 'Петя Кочева'),
	(5, 'Веселин Сариев'),
	(6, 'Георги Панайотов'),
	(6, 'Петя Кочева'),
	(6, 'Веселин Сариев');
/*!40000 ALTER TABLE `member_recommended_members` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
