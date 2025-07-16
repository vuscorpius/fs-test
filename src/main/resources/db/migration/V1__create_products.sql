CREATE TABLE product
(
    id         UUID PRIMARY KEY         DEFAULT gen_random_uuid(),
    title      VARCHAR(255)   NOT NULL,
    handle      VARCHAR(255)   NOT NULL,
    body_html  TEXT,
    price      NUMERIC(10, 2) NOT NULL,
    vendor  VARCHAR(255),
    variants   JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);