Use BRIGHTINVESTMENT;

create table investments(
investmentId int not null primary key,
userId int not null,
shareId int not null,
shareName varchar(60) not null,
shareQuantity int not null,
FOREIGN KEY (userId) REFERENCES users(userId),
FOREIGN KEY (shareId) REFERENCES shares(shareId)
);

insert into investments(investmentId,userId,shareId,shareName,shareQuantity)
values (601,101,501,'Kitana',3),
(602,101,504,'Livi',7),
(603, 102,506,'Rimowa',4),
(604,102,509,'OuterNet',5),
(605,103,507,'Jinx',9),
(606,103,502,'Kai',5),
(607,103,504,'Livi',2),
(608,103,501,'Kitana',1),
(609,104,502,'Kai',8),
(610,104,503,'Nvida',10),
(611,104,507,'Jinx',4),
(612,104,509,'OuterNet',3),
(613,105,506,'Rimowa',2),
(614,105,504,'Livi',6),
(615,105,501,'Kitana',3);

select * from investments;

select * from investments WHERE userId = 103;

SELECT * FROM investments WHERE userId=103 AND shareId=502;

select * from investments where userId=103 and shareId=509;