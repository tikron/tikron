ALTER TABLE tikron.gallery_category 
ADD COLUMN commentable SMALLINT(6) NULL DEFAULT 0 AFTER categorytype_id,
ADD COLUMN rateable SMALLINT(6) NULL DEFAULT 0 AFTER commentable;
