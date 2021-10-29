create table t_crypto
(
    cmc_id   INTEGER not null
        primary key,
    quantity REAL    not null,
    remark   TEXT
);

create table t_key
(
    name  VARCHAR not null
        primary key,
    key   VARCHAR not null,
    value VARCHAR not null
);
