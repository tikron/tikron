ALTER TABLE `freakworm`.`gallery_category` ADD COLUMN `display_type` INTEGER NOT NULL DEFAULT 0;

-- Add catalog for private pictures
INSERT
INTO
    freakworm.gallery_catalog
    (
        id,
        created_on,
        name,
        title,
        short_description,
        long_description,
        version,
        visible
    )
    VALUES
    (
        3,
        CURRENT_TIMESTAMP,
        'casket',
        'Schatulle',
        'Schatulle',
        'Hier kommen die privaten Schätze rein.',
        0,
        1
    );

-- Add category for fusion 2011
INSERT
INTO
    freakworm.gallery_category
    (
        id,
        created_on,
        long_description,
        name,
        rank,
        short_description,
        title,
        version,
        visible,
        catalog_id,
        on_teaser,
        display_type,
        image_name,
        image_width,
        image_height
    )
    VALUES
    (
        0,
        current_timestamp,
        'Fusion Festival 2011',
        'fusion_2011',
        0,
        'Fusion Festival 2011',
        'Fusion Festival 2011',
        0,
        1,
        3,
        0,
        1,
        'fusion_2011.png',
        200,
        133
    );
