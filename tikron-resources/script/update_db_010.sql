ALTER TABLE `tikron`.`user_role`
DROP FOREIGN KEY `FK_user_role_2`;
ALTER TABLE `tikron`.`user_role` 
ADD CONSTRAINT `FK_user_role_2`
  FOREIGN KEY (`role_id`)
  REFERENCES `tikron`.`role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
UPDATE `tikron`.`role` SET `id`='ROLE_ADMIN' WHERE `id`='ADMINISTRATE';
UPDATE `tikron`.`role` SET `id`='ROLE_MANAGE' WHERE `id`='MANAGE';
UPDATE `tikron`.`role` SET `id`='ROLE_USE' WHERE `id`='USE';
