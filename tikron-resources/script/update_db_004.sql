-- Remove unnacessary columns for image dimensions
ALTER TABLE freakworm.gallery_category DROP COLUMN image_height;
ALTER TABLE freakworm.gallery_category DROP COLUMN image_width;

-- Remove unnacessary columns for image and thumbnail dimensions
ALTER TABLE freakworm.gallery_picture DROP COLUMN image_height;
ALTER TABLE freakworm.gallery_picture DROP COLUMN image_width;
ALTER TABLE freakworm.gallery_picture DROP COLUMN thumbnail_height;
ALTER TABLE freakworm.gallery_picture DROP COLUMN thumbnail_width;
