drop database if exists eday08;
create database day08;
use day08;

create table board(
	bno int auto_increment ,
    bcontent text,
    bwriter text , 
    bpassword text ,
    primary key(bno)
);
select * from board;

use springweb;
drop table todo2;
create table todo2(
	no int auto_increment ,
    content text ,
    state bool,
    constraint todo_pk primary key(no)
);
select * from todo2;

