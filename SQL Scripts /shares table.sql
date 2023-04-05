create database BRIGHTINVESTMENT;

use BRIGHTINVESTMENT;

create table shares(
shareId int not null primary key,
shareName varchar(60) not null,
shareUnitPrice int not null);

insert into shares(shareId,shareName,shareUnitPrice)
values (501, 'Kitana',25500),
(502,'Kai', 48900),
(503, 'Nvida', 67200),
(504, 'Livi', 53950),
(506, 'Rimowa',74600),
(507,'Jinx',57800),
(509,'OuterNet',86398);

select * from shares;

