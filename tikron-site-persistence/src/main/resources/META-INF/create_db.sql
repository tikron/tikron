-- create database schema
CREATE DATABASE tikron;
USE tikron;

-- create new tables

CREATE TABLE user (
	id bigint NOT NULL auto_increment,
	name varchar(255) NOT NULL,
	password varchar(255),
	email varchar(255),
	url varchar(255),
	version integer default 0,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY	(id),
	UNIQUE KEY K_user_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role (
	id char(64) NOT NULL,
	description varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_role (
	user_id BIGINT NOT NULL,
	role_id char(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comment (
	id bigint NOT NULL auto_increment,
	commenttype_id char(64) NOT NULL,
	user_id bigint NOT NULL,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	text text NOT NULL,
	visible smallint default 0,
	clip_id bigint,
	category_id bigint,
	picture_id bigint,
	version integer default 0,
	PRIMARY KEY	(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comment_type (
	id char(64) NOT NULL,
	description varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rating (
	id bigint NOT NULL auto_increment,
	ratingtype_id char(64) NOT NULL,
	clip_id bigint,
	picture_id bigint,
	value double,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY	(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rating_type (
	id char(64) NOT NULL,
	description varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE gallery_catalog (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	long_description text,
	name varchar(255) NOT NULL,
	sequence double,
	short_description text,
	title varchar(255),
	version int(11) DEFAULT '0',
	visible smallint(6) DEFAULT '0',
	PRIMARY KEY (id),
	UNIQUE KEY K_gallery_catalog_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE gallery_category (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	image_name varchar(255),
	long_description text,
	name varchar(255) NOT NULL,
	sequence double,
	short_description text,
	title varchar(255),
	short_title varchar(255),
	version int(11) DEFAULT '0',
	visible smallint(6) DEFAULT '0',
	catalog_id bigint(20) NOT NULL,
	on_teaser smallint(6),
	display_type int(11),
	categorytype_id char(64) NOT NULL,
	commentable smallint(6) DEFAULT '0',
	rateable smallint(6) DEFAULT '0',
	PRIMARY KEY (id),
	UNIQUE KEY K_gallery_category_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE gallery_picture (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	description text,
	image_name varchar(255),
	name varchar(255) NOT NULL,
	title varchar(255),
	version int(11) DEFAULT '0',
	category_id bigint(20) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY K_gallery_picture_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE category_type (
	id char(64) NOT NULL,
	description varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE reviews_album (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	artist varchar(255),
	cover varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dealer_url varchar(255),
	description text,
	label varchar(255),
	name varchar(255) NOT NULL,
	style varchar(255),
	year int(11),
	visible smallint(6) DEFAULT '0',
	version int(11) DEFAULT '0',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE reviews_volume (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	number int(11) NOT NULL DEFAULT '0',
	album_id bigint(20) NOT NULL,
	version int(11) DEFAULT '0',
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE reviews_track (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	artist varchar(255),
	length int(11),
	number int(11) NOT NULL DEFAULT '0',
	title varchar(255) NOT NULL,
	volume_id bigint(20) NOT NULL,
	version int(11) DEFAULT '0',
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE clip (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	title varchar(255),
	short_description text,
	long_description text,
	image_name varchar(255),
	video_name varchar(255),
	video_width smallint,
	video_height smallint,
	date_recorded date,
	playtime integer,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version int(11) DEFAULT '0',
	PRIMARY KEY (id),
	UNIQUE KEY K_clip_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE teaser (
	id bigint NOT NULL auto_increment,
	name varchar(255) NOT NULL,
	sequence double,
	start_date timestamp NULL,
	end_date timestamp NULL,
	visible smallint(6) DEFAULT '0',
	title varchar(255),
	image_name varchar(255),
	image_alt varchar(255),
	picture_id bigint,
	target_url varchar(255),
	caption varchar(255),
	subtitle varchar(255),
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version integer default 0,
	PRIMARY KEY	(id),
	UNIQUE KEY K_teaser_1 (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE web_recommendation (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	title varchar(255),
	description text,
	image_name varchar(255),
	url varchar(255),
	sequence double,
	created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	version int(11) DEFAULT '0',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- add constrains

ALTER TABLE user_role
	ADD KEY FK_user_role_1 (user_id),
	ADD CONSTRAINT FK_user_role_1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
	ADD KEY FK_user_role_2 (role_id),
	ADD CONSTRAINT FK_user_role_2 FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE;

ALTER TABLE comment
	ADD KEY FK_comment_1 (commenttype_id),
	ADD CONSTRAINT FK_comment_1 FOREIGN KEY (commenttype_id) REFERENCES comment_type (id) ON DELETE CASCADE,
	ADD KEY FK_comment_2 (user_id),
	ADD CONSTRAINT FK_comment_2 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
	ADD KEY FK_comment_3 (clip_id),
	ADD CONSTRAINT FK_comment_3 FOREIGN KEY (clip_id) REFERENCES clip (id) ON DELETE CASCADE,
	ADD KEY FK_comment_4 (category_id),
	ADD CONSTRAINT FK_comment_4 FOREIGN KEY (category_id) REFERENCES gallery_category (id) ON DELETE CASCADE,
	ADD KEY FK_comment_5 (picture_id),
	ADD CONSTRAINT FK_comment_5 FOREIGN KEY (picture_id) REFERENCES gallery_picture (id) ON DELETE CASCADE
;

ALTER TABLE rating
	ADD KEY FK_rating_1 (ratingtype_id),
	ADD CONSTRAINT FK_rating_1 FOREIGN KEY (ratingtype_id) REFERENCES rating_type (id) ON DELETE CASCADE,
	ADD KEY FK_rating_2 (clip_id),
	ADD CONSTRAINT FK_rating_2 FOREIGN KEY (clip_id) REFERENCES clip (id) ON DELETE CASCADE,
	ADD KEY FK_rating_3 (picture_id),
	ADD CONSTRAINT FK_rating_3 FOREIGN KEY (picture_id) REFERENCES gallery_picture (id) ON DELETE CASCADE
;

ALTER TABLE gallery_category
	ADD KEY FK_gallery_category_1 (catalog_id),
	ADD CONSTRAINT FK_gallery_category_1 FOREIGN KEY (catalog_id) REFERENCES gallery_catalog (id) ON DELETE CASCADE,
	ADD KEY FK_gallery_category_2 (categorytype_id),
	ADD CONSTRAINT FK_gallery_category_2 FOREIGN KEY (categorytype_id) REFERENCES category_type (id) ON DELETE CASCADE
;

ALTER TABLE gallery_picture
	ADD KEY FK_gallery_picture_1 (category_id),
	ADD CONSTRAINT FK_gallery_picture_1 FOREIGN KEY (category_id) REFERENCES gallery_category (id) ON DELETE CASCADE
;

ALTER TABLE reviews_volume
	ADD KEY FK_reviews_volume_1 (album_id),
	ADD CONSTRAINT FK_reviews_volume_1 FOREIGN KEY (album_id) REFERENCES reviews_album (id) ON DELETE CASCADE
;

ALTER TABLE reviews_track
	ADD KEY FK_reviews_track_1 (volume_id),
	ADD CONSTRAINT FK_reviews_track_1 FOREIGN KEY (volume_id) REFERENCES reviews_volume (id) ON DELETE CASCADE
;

ALTER TABLE teaser
	ADD KEY FK_teaser_1 (picture_id),
	ADD CONSTRAINT FK_teaser_1 FOREIGN KEY (picture_id) REFERENCES gallery_picture (id) ON DELETE SET NULL
;

-- add indexes

ALTER TABLE comment 
	ADD INDEX I_comment_1 (commenttype_id, created_on DESC),
	ADD INDEX I_comment_2 (commenttype_id, clip_id, created_on DESC),
	ADD INDEX I_comment_3 (commenttype_id, picture_id, created_on DESC);

ALTER TABLE rating 
	ADD INDEX I_rating_1 (ratingtype_id, clip_id),
	ADD INDEX I_rating_2 (ratingtype_id, picture_id);

ALTER TABLE gallery_catalog 
	ADD INDEX I_gallery_catalog_1 (name), 
	ADD INDEX I_gallery_catalog_2 (sequence);

ALTER TABLE gallery_category 
	ADD INDEX I_gallery_category_1 (catalog_id, name), 
	ADD INDEX I_gallery_category_2 (catalog_id, sequence);

ALTER TABLE gallery_picture 
	ADD INDEX I_gallery_picture_1 (category_id, name);

ALTER TABLE reviews_album 
	ADD INDEX I_reviews_album_1 (year, created_on);

ALTER TABLE reviews_volume 
	ADD INDEX I_reviews_volume_1 (volume_id, number);

ALTER TABLE reviews_track 
	ADD INDEX I_reviews_track_1 (album_id, number);

ALTER TABLE clip 
	ADD INDEX I_clip_1 (name),
	ADD INDEX I_clip_2 (date_recorded DESC);

ALTER TABLE teaser
	ADD INDEX I_teaser_1 (sequence, start_date);

ALTER TABLE web_recommendation 
	ADD INDEX I_web_recommendation_1 (sequence);

-- initialize data

INSERT INTO role (id, description, created_on)
	VALUE('ADMINISTRATE', 'Administrate Application', current_timestamp);
INSERT INTO role (id, description, created_on)
	VALUE('MANAGE', 'Manage Data', current_timestamp);
INSERT INTO role (id, description, created_on)
	VALUE('USE', 'Using Application', current_timestamp);

INSERT INTO user (name, password, created_on)
	VALUE('manager', '1a8565a9dc72048ba03b4156be3e569f22771f23', current_timestamp);

INSERT INTO user_role (user_id, role_id)
	SELECT user.id, 'MANAGE' FROM user WHERE user.name = 'manager';
INSERT INTO user_role (user_id, role_id)
	SELECT user.id, 'USE' FROM user WHERE user.name = 'manager';

INSERT INTO comment_type (id, description, created_on)
	VALUE('GUESTBOOK', 'Guestbook', current_timestamp); 
INSERT INTO comment_type (id, description, created_on)
	VALUE('CLIP', 'Video Clip', current_timestamp); 
INSERT INTO comment_type (id, description, created_on)
	VALUE('CATEGORY', 'Category', current_timestamp); 
INSERT INTO comment_type (id, description, created_on)
	VALUE('PICTURE', 'Gallery Picture', current_timestamp); 

INSERT INTO rating_type (id, description, created_on)
	VALUE('CLIP', 'Video Clip', current_timestamp); 
INSERT INTO rating_type (id, description, created_on)
	VALUE('PICTURE', 'Gallery Picture', current_timestamp); 

INSERT INTO category_type (id, description, created_on)
	VALUE('GALLERY', 'Picture Gallery', current_timestamp); 
INSERT INTO category_type (id, description, created_on)
	VALUE('REPORT', 'Report', current_timestamp); 
