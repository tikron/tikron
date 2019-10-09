-- add rank for catalogs
ALTER TABLE `tikron`.`gallery_catalog` ADD COLUMN `rank` DOUBLE NULL  AFTER `name` ;


-- add indexes

ALTER TABLE tikron.comment 
	ADD INDEX I_comment_1 (commenttype_id, created_on DESC),
	ADD INDEX I_comment_2 (commenttype_id, album_id, created_on DESC),
	ADD INDEX I_comment_3 (commenttype_id, picture_id, created_on DESC);

ALTER TABLE tikron.gallery_catalog 
	ADD INDEX I_gallery_catalog_1 (name), 
	ADD INDEX I_gallery_catalog_2 (rank);

ALTER TABLE tikron.gallery_category 
	ADD INDEX I_gallery_category_1 (catalog_id, name), 
	ADD INDEX I_gallery_category_2 (catalog_id, rank);

ALTER TABLE tikron.gallery_picture 
	ADD INDEX I_gallery_picture_1 (category_id, name);

ALTER TABLE tikron.reviews_album 
	ADD INDEX I_reviews_album_1 (year, created_on);

ALTER TABLE tikron.reviews_volume 
	ADD INDEX I_reviews_volume_1 (volume_id, number);

ALTER TABLE tikron.reviews_track 
	ADD INDEX I_reviews_track_1 (album_id, number);
