ALTER TABLE `tikron`.`teaser` 
CHANGE COLUMN `start_date` `start_date` DATETIME NOT NULL DEFAULT NULL,
CHANGE COLUMN `end_date` `end_date` DATETIME NOT NULL DEFAULT NULL;
