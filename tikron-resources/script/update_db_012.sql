ALTER TABLE `tikron`.`clip` ADD COLUMN `playtime2` BIGINT NULL DEFAULT NULL AFTER `playtime`;

UPDATE clip SET playtime2 = SECOND(playtime);

ALTER TABLE `tikron`.`clip` DROP COLUMN `playtime`;

ALTER TABLE `tikron`.`clip` CHANGE COLUMN `playtime2` `playtime` BIGINT(20) NULL DEFAULT NULL;
