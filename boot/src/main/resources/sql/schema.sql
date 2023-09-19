CREATE TABLE USER (
    ID                   uuid                 NOT NULL,
    BAND_ID              uuid,
    OWN_BAND_ID          uuid,
    FIRST_NAME           text                 NOT NULL,
    LAST_NAME            text                 NOT NULL,
    EMAIL                text                 NOT NULL,
    PASSWORD             text                 NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (ID)
);