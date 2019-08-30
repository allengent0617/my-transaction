

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  id int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `money` decimal(20,4)  ,
  `created_at` datetime NOT NULL ,
  PRIMARY KEY (id)
) ;

