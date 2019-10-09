-- remove foreign parent keys from unique identifier (name) columns
alter table gallery_category drop index K_gallery_category_1;
alter table gallery_category add constraint K_gallery_category_1 unique key (name);
alter table gallery_picture drop index K_gallery_picture_1;
alter table gallery_picture add constraint K_gallery_picture_1 unique key (name);

alter table user add constraint K_user_1 unique key (name);
