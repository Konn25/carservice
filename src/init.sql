
    create table admin (
        id bigserial not null,
        email varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table car (
        kilometer integer not null,
        motor float(53),
        price integer not null,
        year integer not null,
        id bigserial not null,
        user_id bigint,
        fuel varchar(255),
        manufacturer varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table picture (
        car_id bigint,
        id bigserial not null,
        name varchar(255),
        type varchar(255),
        image_data oid,
        primary key (id)
    );

    create table refueling (
        fuel_quantity float(53) not null,
        kilometer integer not null,
        price integer not null,
        car_id bigint,
        id bigserial not null,
        date varchar(255),
        primary key (id)
    );

    create table repair (
        price integer not null,
        car_id bigint,
        id bigserial not null,
        repair_id bigint,
        date varchar(255),
        primary key (id)
    );

    create table repair_name (
        id bigserial not null,
        repair_name varchar(255),
        primary key (id)
    );

    create table users (
        id bigserial not null,
        email varchar(255),
        name varchar(255),
        nick_name varchar(255),
        password varchar(255),
        primary key (id)
    );

    alter table if exists car 
       add constraint FKc2osr9qmb46vr8pjyps6weii0 
       foreign key (user_id) 
       references users;

    alter table if exists picture 
       add constraint FK5rvwhn85jx8kr6wfobalp0sxr 
       foreign key (car_id) 
       references car;

    alter table if exists refueling 
       add constraint FK6tw4y7seq38wfub6fnvc8t49r 
       foreign key (car_id) 
       references car;

    alter table if exists repair 
       add constraint FK2d97exad9crdxhd5kgnyc29wj 
       foreign key (car_id) 
       references car;

    alter table if exists repair 
       add constraint FKrmeu0s677tod7mcnggjse8jg9 
       foreign key (repair_id) 
       references repair_name;
