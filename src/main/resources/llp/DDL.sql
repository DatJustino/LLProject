

/*drop database llp;*/
create database llp;
use llp;


create table customer
(
    customer_id int auto_increment
        primary key,
    cemail      varchar(255) null,
    cpassword   varchar(20)  null
);

create table orders
(
    orderid      int auto_increment
        primary key,
    address      varchar(255) null,
    description  varchar(255) null,
    fname        varchar(255) null,
    image        blob         null,
    lname        varchar(255) null,
    phone_number varchar(255) null,
    subject      varchar(255) null
);


create table product
(
    productid           int auto_increment
        primary key,
    height              varchar(255) null,
    length              varchar(255) null,
    product_description varchar(255) null,
    product_image       varchar(255) null,
    product_name        varchar(255) null,
    product_price       int          null,
    product_quantity    int          null,
    width               varchar(255) null
);

create table blogposts
(
    blogpostid int auto_increment
        primary key,
    content    varchar(255) not null,
    createdate datetime(6)  not null,
    fileurl    varchar(255) null,
    imageurl   varchar(255) null,
    title      varchar(255) not null
);

create table comments
(
    commentid  int auto_increment
        primary key,
    content    varchar(255) not null,
    created_at datetime(6)  not null,
    blogpostid int          not null,
    constraint FKi6vemkqlywf9faepuwjka2lj9
        foreign key (blogpostid) references blogposts (blogpostid)
);


create table order_product
(
    orderid   int not null,
    productid int not null,
    constraint FKc87t5fm1h28jfxqssf2t31trf
        foreign key (productid) references product (productid),
    constraint FKfejhc0jk4gpbc3og12p4w2eve
        foreign key (orderid) references orders (orderid)
);

