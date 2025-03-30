create database if not exists coffee_shop_pos;

use coffee_shop_pos;

create table Customer(
    cust_id varchar(25),
    cust_name varchar(25),
    address varchar(40),
    contact varchar(10)PRIMARY KEY
);

create table Product(
    pro_id varchar(25)PRIMARY KEY,
    pro_name varchar(25),
    price varchar(25),
    category varchar(25),
    quantity varchar(25)
);

create table Orders(
    order_id varchar(50)PRIMARY KEY,
    dateAndTime DateTime,
    contact varchar(10),
    FOREIGN KEY (contact) REFERENCES Customer(contact)
);

create table OrderDetails(
    order_id varchar(50),
    pro_id varchar(25),
    qty varchar(25),
    unitPrice varchar(25),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (pro_id) REFERENCES Product(pro_id)
);

