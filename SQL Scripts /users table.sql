use BRIGHTINVESTMENT;
select * from shares;

Use BRIGHTINVESTMENT;

create table users(
userId int not null primary key,
companyName varchar(50) not null,
phoneNumber varchar(30) not null,
eMail varchar(70) not null,
balance double not null);

ALTER TABLE users
ADD userPassword varchar(90) not null;

select * from users;

insert into users (userId, companyName,phoneNumber,eMail,balance, userPassword)
values (101,'Nuli Accounting','02073859831','nuliaccounting@gmail.com',500000.00,'hello91'),
(102, 'Pax Builders', '03000754391','paxbuilders@gmail.com', 1000000.00,'hello91'),
(103, 'Helios', '01157689452','helios@gmail.com', 400000.00,'hello91'),
(104, 'Elysium Funeral Care', '02075643912','efc@gmail.com', 875000.00,'hello91'),
(105, 'Adora Weddings', '01157643246','adw@gmail.com', 2000000,'hello91');


