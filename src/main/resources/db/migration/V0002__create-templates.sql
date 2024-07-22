CREATE TABLE IF NOT EXISTS templates (
    id                  uuid                  primary key             default uuid_generate_v4()        ,
    name                varchar(255)          not null                                                  ,
    created_at          timestamp             with time zone          default current_timestamp not null
);