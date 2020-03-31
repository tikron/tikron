-- create new tables

CREATE TABLE user (
	id bigint NOT NULL auto_increment,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	role varchar(255) NOT NULL,
	version integer default 0,
	created_on timestamp,
	PRIMARY KEY	(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comment (
	id bigint NOT NULL auto_increment,
	commenttype_id char(64) NOT NULL,
	created_on timestamp,
	email varchar(255) default NULL,
	author varchar(255) NOT NULL,
	text text NOT NULL,
	url varchar(255) default NULL,
	visible smallint default 0,
	album_id bigint default NULL,
	picture_id bigint default NULL,
	version integer default 0,
	PRIMARY KEY	(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comment_type (
	id char(64) NOT NULL,
	description varchar(255),
	created_on timestamp,
	version integer default 0,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- create indexes and constrains

ALTER TABLE comment
	ADD KEY FK_comment_1 (commenttype_id),
	ADD CONSTRAINT FK_comment_1 FOREIGN KEY (commenttype_id) REFERENCES comment_type (id) ON DELETE CASCADE,
	ADD KEY FK_comment_2 (album_id),
	ADD CONSTRAINT FK_comment_2 FOREIGN KEY (album_id) REFERENCES reviews_album (id) ON DELETE CASCADE,
	ADD KEY FK_comment_3 (picture_id),
	ADD CONSTRAINT FK_comment_3 FOREIGN KEY (picture_id) REFERENCES gallery_picture (id) ON DELETE CASCADE
;

-- insert new data

INSERT INTO comment_type VALUE('guestbook', 'Guestbook', current_timestamp); 
INSERT INTO comment_type VALUE('album', 'Reviews Album', current_timestamp); 
INSERT INTO comment_type VALUE('picture', 'Gallery Picture', current_timestamp); 

-- copy data

insert into user
select * from common_user;

insert into comment
select id, 'album', created_on, email, author, text, url, 1, album_id, picture_id, version
from common_comment
where not album_id is null;

insert into comment
select id, 'picture', created_on, email, author, text, url, 1, album_id, picture_id, version
from common_comment
where not picture_id is null;

insert into comment (commenttype_id, created_on, email, author, text, url, visible, version)
select 'guestbook', created_on, email, author, text, url, visible, version
from guestbook_entry;

-- drop columns
alter table gallery_picture drop column thumbnail_name;

-- drop tables
drop table common_user;
drop table common_comment;
drop table guestbook_entry;
