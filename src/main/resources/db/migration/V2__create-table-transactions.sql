create table transactions(

    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(300),
    value double,
    type varchar(30),
    active tinyint,

    primary key(id)
);
