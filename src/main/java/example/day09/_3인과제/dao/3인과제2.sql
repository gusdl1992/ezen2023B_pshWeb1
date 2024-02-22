drop database if exists springweb;
create database springweb;
use springweb;

drop table if exists empoploye;
create table empoploye(
	eno int auto_increment ,
    ename varchar(10) ,
    ephone char(13) ,
    primary key(eno)
);

select * from empoploye;

drop table if exists point;
create table point(
	pno int auto_increment ,
    preason text ,
    ppoint int ,
    pdate datetime default now() ,
    eno int ,
    primary key(pno) ,
    foreign key(eno) references empoploye (eno)
);

select * from point;