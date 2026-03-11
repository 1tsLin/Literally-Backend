/*----------------------------------------------------------------------------------------------------------------------
                                                     -- ENUMS --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TYPE language_enum AS ENUM('FRENCH', 'ENGLISH');
CREATE TYPE currency_enum AS ENUM('EUR', 'GBP', 'USD', 'CHF');
CREATE TYPE contributor_category_enum AS ENUM('EDITOR', 'AUTHOR', 'ILLUSTRATOR');
CREATE TYPE order_status_enum AS ENUM('PAID', 'SHIPPED', 'DELIVERED', 'CANCELLED');
CREATE TYPE entity_type_enum AS ENUM('REVIEW', 'PRODUCT', 'SERIES');

CREATE TYPE book_format_enum AS ENUM ('NOVEL','SHORT_STORY', 'POETRY', 'PLAY', 'COMIC', 'MANGA', 'MANHWA',
    'MANHUA', 'GRAPHIC_NOVEL');
CREATE TYPE book_audience_enum AS ENUM('CHILDREN', 'TEEN', 'ADULT', 'SHONEN', 'SEINEN', 'SHOJO', 'JOSEI');
CREATE TYPE book_genre_enum AS ENUM('FANTASY', 'SCI_FI', 'ROMANCE', 'THRILLER', 'MYSTERY', 'HORROR', 'HISTORICAL',
    'DRAMA', 'ADVENTURE', 'SLICE_OF_LIFE');

/*----------------------------------------------------------------------------------------------------------------------
                                                -- Contributors tables --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE contributors (
    id          UUID                        PRIMARY KEY DEFAULT gen_random_uuid(),
    category    contributor_category_enum   NOT NULL,
    name        TEXT
);

/*----------------------------------------------------------------------------------------------------------------------
                                                   -- Series tables --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE series (
    id              UUID                PRIMARY KEY DEFAULT gen_random_uuid(),
    alias           TEXT[],
    format          book_format_enum    NOT NULL,
    audience        book_audience_enum  NOT NULL,
    genres          book_genre_enum[]   NOT NULL DEFAULT ARRAY[]::book_genre_enum[],
    author_id       UUID,
    editor_id       UUID,
    illustrator_id  UUID,

    CONSTRAINT fk_series_author FOREIGN KEY (author_id) REFERENCES contributors(id),
    CONSTRAINT fk_series_editor FOREIGN KEY (editor_id) REFERENCES contributors(id),
    CONSTRAINT fk_series_illustrator FOREIGN KEY (illustrator_id) REFERENCES contributors(id)
);

CREATE TABLE series_localizations (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    series_id   UUID            NOT NULL,
    language    language_enum   NOT NULL,
    name        TEXT            NOT NULL,
    description TEXT,

    CONSTRAINT fk_localization_series FOREIGN KEY (series_id) REFERENCES series(id)
);


/*----------------------------------------------------------------------------------------------------------------------
                                                  -- Products tables --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE products (
    id              UUID                PRIMARY KEY DEFAULT gen_random_uuid(),
    series_id       UUID,
    alias           TEXT[],
    format          book_format_enum    NOT NULL,
    audience        book_audience_enum  NOT NULL,
    genres          book_genre_enum[]   NOT NULL DEFAULT ARRAY[]::book_genre_enum[],
    price           DECIMAL(5,2)        NOT NULL,
    quantity        INTEGER             NOT NULL DEFAULT 0,
    sales           INTEGER             NOT NULL DEFAULT 0,
    author_id       UUID,
    editor_id       UUID,
    illustrator_id  UUID,
    ean             INTEGER,
    pages           INTEGER,
    publishing_date DATE,
    height          DECIMAL(5,3),
    width           DECIMAL(5,3),
    thickness       DECIMAL(5,3),
    weight          DECIMAL(5,3),
    is_active       BOOLEAN             NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_product_series FOREIGN KEY (series_id) REFERENCES series(id),
    CONSTRAINT fk_product_author FOREIGN KEY (author_id) REFERENCES contributors(id),
    CONSTRAINT fk_product_editor FOREIGN KEY (editor_id) REFERENCES contributors(id),
    CONSTRAINT fk_product_illustrator FOREIGN KEY (illustrator_id) REFERENCES contributors(id)
);

CREATE TABLE product_localizations (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id  UUID            NOT NULL,
    language    language_enum   NOT NULL,
    name        TEXT            NOT NULL,
    description TEXT,

    CONSTRAINT fk_product_localization FOREIGN KEY (product_id) REFERENCES products(id)
);

/*----------------------------------------------------------------------------------------------------------------------
                                                   -- User table --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE users (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name  TEXT            NOT NULL,
    last_name   TEXT            NOT NULL,
    address     TEXT,
    language    language_enum   NOT NULL DEFAULT 'FRENCH',
    currency    currency_enum   NOT NULL DEFAULT 'EUR',
    email       TEXT            NOT NULL UNIQUE,
    password    TEXT            NOT NULL,
    id_admin    BOOLEAN         NOT NULL DEFAULT FALSE
);

/*----------------------------------------------------------------------------------------------------------------------
                                           -- Users' products tables --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE favorites (
    user_id     UUID NOT NULL,
    product_id  UUID NOT NULL,

    CONSTRAINT fk_user_favorite FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_favorite_product FOREIGN KEY (product_id) REFERENCES products(id),

    PRIMARY KEY (user_id, product_id)
);

CREATE TABLE orders (
    id              UUID                PRIMARY KEY DEFAULT gen_random_uuid(),
    status          order_status_enum   NOT NULL DEFAULT 'PAID',
    user_id         UUID                NOT NULL,
    creation_date   TIMESTAMPTZ         NOT NULL DEFAULT NOW(),
    update_date     TIMESTAMPTZ         NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_user_order FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    order_id    UUID            NOT NULL,
    product_id  UUID            NOT NULL,
    quantity    INTEGER         NOT NULL,
    price       DECIMAL(5,2)    NOT NULL,

    CONSTRAINT fk_order_items FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES products(id),

    PRIMARY KEY (order_id, product_id)
);

CREATE TABLE cart_items (
    user_id     UUID    NOT NULL,
    product_id  UUID    NOT NULL,
    quantity    INTEGER NOT NULL,

    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES products(id),

    PRIMARY KEY (user_id, product_id)
);

/*----------------------------------------------------------------------------------------------------------------------
                                                 -- Review table --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE reviews (
    id              UUID            NOT NULL DEFAULT gen_random_uuid(),
    user_id         UUID            NOT NULL,
    product_id      UUID            NOT NULL,
    grade           DECIMAL(2, 1)   DEFAULT 0.0,
    comment         TEXT,
    creation_date   TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    update_date     TIMESTAMPTZ,

    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES products(id)
);

/*----------------------------------------------------------------------------------------------------------------------
                                                 -- Media table --
----------------------------------------------------------------------------------------------------------------------*/

CREATE TABLE medias (
    id          UUID                NOT NULL DEFAULT gen_random_uuid(),
    entity_id   UUID                NOT NULL,
    entity_type entity_type_enum    NOT NULL,
    data        BYTEA               NOT NULL
);