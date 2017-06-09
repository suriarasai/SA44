CREATE TABLE `sa44`.`movies` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `heroname` VARCHAR(45) NULL,
  `avgrating` DECIMAL(7,1) NULL,
  PRIMARY KEY (`id`));
  
 INSERT INTO `sa44`.`movies` (`id`, `name`, `heroname`, `avgrating`) VALUES ('1', 'Kung Foo Panda', 'Poo', '4');
INSERT INTO `sa44`.`movies` (`id`, `name`, `heroname`, `avgrating`) VALUES ('2', 'Pirates of the Carrabian', 'Jack SParrow', '5');
INSERT INTO `sa44`.`movies` (`id`, `name`, `heroname`, `avgrating`) VALUES ('3', 'Frozen', 'Elisa', '3');
INSERT INTO `sa44`.`movies` (`id`, `name`, `heroname`, `avgrating`) VALUES ('4', 'War Machine', 'Brad Pitt', '4');

  
  