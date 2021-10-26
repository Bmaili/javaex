/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.50-log : Database - courseSelectionSystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`courseSelectionSystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `courseSelectionSystem`;

/*Table structure for table `tb_opCourse` */

DROP TABLE IF EXISTS `tb_opCourse`;

CREATE TABLE `tb_opCourse` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `type` int(1) NOT NULL,
  `stuNum` int(4) NOT NULL DEFAULT '0',
  `teacherId` int(15) NOT NULL,
  `teacher` varchar(6) NOT NULL,
  `maxStuNum` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id_fk` (`teacherId`),
  CONSTRAINT `teacher_id_fk` FOREIGN KEY (`teacherId`) REFERENCES `tb_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8;

/*Data for the table `tb_opCourse` */

insert  into `tb_opCourse`(`id`,`name`,`type`,`stuNum`,`teacherId`,`teacher`,`maxStuNum`) values (10000,'唐代诗词研究',1,2,10000,'李红',60),(10001,'web开发',1,1,10003,'诸葛亮',70),(10002,'经济学通识',1,1,10003,'诸葛亮',100);

/*Table structure for table `tb_reCourse` */

DROP TABLE IF EXISTS `tb_reCourse`;

CREATE TABLE `tb_reCourse` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `type` int(1) NOT NULL,
  `stuNum` int(4) NOT NULL DEFAULT '0',
  `teacherId` int(15) NOT NULL,
  `teacher` varchar(6) NOT NULL,
  `credit` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `re_teacher_id_fk` (`teacherId`),
  CONSTRAINT `re_teacher_id_fk` FOREIGN KEY (`teacherId`) REFERENCES `tb_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;

/*Data for the table `tb_reCourse` */

insert  into `tb_reCourse`(`id`,`name`,`type`,`stuNum`,`teacherId`,`teacher`,`credit`) values (10000,'高等数学',0,4,10000,'李红',4),(10001,'线性代数',0,4,10001,'王明',3),(10002,'编译原理',0,4,10002,'张飞',3),(10003,'数据库系统',0,4,10003,'诸葛亮',3);

/*Table structure for table `tb_selectCourse` */

DROP TABLE IF EXISTS `tb_selectCourse`;

CREATE TABLE `tb_selectCourse` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `courseId` int(15) NOT NULL,
  `studentId` int(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `select_course_id_fk` (`courseId`),
  KEY `select_student_id_fk` (`studentId`),
  CONSTRAINT `select_course_id_fk` FOREIGN KEY (`courseId`) REFERENCES `tb_opCourse` (`id`),
  CONSTRAINT `select_student_id_fk` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10015 DEFAULT CHARSET=utf8;

/*Data for the table `tb_selectCourse` */

insert  into `tb_selectCourse`(`id`,`courseId`,`studentId`) values (10003,10001,20190001),(10004,10000,20190001),(10005,10000,20190000),(10007,10001,20190000),(10012,10002,20190002),(10014,10000,20190002);

/*Table structure for table `tb_student` */

DROP TABLE IF EXISTS `tb_student`;

CREATE TABLE `tb_student` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL,
  `password` varchar(20) NOT NULL,
  `stuClass` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20190006 DEFAULT CHARSET=utf8;

/*Data for the table `tb_student` */

insert  into `tb_student`(`id`,`name`,`password`,`stuClass`) values (20190000,'周杰伦','123456','物联网1901'),(20190001,'刘亦菲','123456','计算机1901'),(20190002,'胡歌','666666','计算机1901'),(20190003,'薛之谦','666666','计算机1902');

/*Table structure for table `tb_teacher` */

DROP TABLE IF EXISTS `tb_teacher`;

CREATE TABLE `tb_teacher` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(6) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8;

/*Data for the table `tb_teacher` */

insert  into `tb_teacher`(`id`,`name`,`password`,`level`) values (10000,'李红','123456','讲师'),(10001,'王明','123456','讲师'),(10002,'张飞','123456','教授'),(10003,'诸葛亮','666666','教授');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
