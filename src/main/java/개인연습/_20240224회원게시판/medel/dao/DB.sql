#  2024-02-24 집에서 회원 가입 , 로그인 ,  게시판  연습

# 회원
drop table if exists membership;
create table membership(
	mno int auto_increment ,		# 회원번호 
    mid varchar(10) not null unique,  # 아이디
    mpw varchar(15) not null, 			# 비밀번호
    mphone char(13) ,				# 전화번호
    mdate datetime default now() ,
    primary key(mno)
);

select * from membership;
insert into membership(mid , mpw , mphone) values('qwe' , 'qwe' ,'010-1111-7777');
insert into membership(mid , mpw) values('asd' , 'asd');
# 특정 필드 값이 조건에 충족하는 레코드만 검색 : select 필드명1 , 필드명2 from 테이블명 where 조건식
select * from membership where mid = '1234' and mpw = '1234';

# 게시판
drop table if exists board;
create table board(
	bno int auto_increment ,
    btitle varchar(30) ,
    bcontent text , 
    bdate datetime default now() , 
    mno int ,
    foreign key( mno ) references membership(mno) ,
    primary key(bno)
);

select * from board;