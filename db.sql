CREATE DATABASE `jdbc_app` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE  `students` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  `fathers_name` varchar(45) DEFAULT NULL,
  `mothers_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=900 DEFAULT CHARSET=utf8;

