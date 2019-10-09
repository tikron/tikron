-- Change column reviews_album.cover from BLOB to VARCHAR.
ALTER TABLE `freakworm`.`reviews_album` DROP COLUMN `cover`;
ALTER TABLE `freakworm`.`reviews_album` ADD COLUMN `cover` VARCHAR(255) AFTER `artist`;
