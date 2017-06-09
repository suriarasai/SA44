CREATE SCHEMA `sa44` ;
CREATE TABLE `sa44`.`student` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `nick_name` VARCHAR(45) NULL,

  `fee` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
  INSERT INTO `sa44`.`student` (`id`, `name`, `nick_name`, `fee`) VALUES ('1', 'Jonathan', 'John', '1200');
INSERT INTO `sa44`.`student` (`id`, `name`, `nick_name`, `fee`) VALUES ('2', 'Kyaw', 'Silent', '1200');
INSERT INTO `sa44`.`student` (`id`, `name`, `nick_name`, `fee`) VALUES ('3', 'Madhuri', 'Maddie', '1200');
INSERT INTO `sa44`.`student` (`id`, `name`, `nick_name`, `fee`) VALUES ('4', 'Pham', 'Nice', '1200');
