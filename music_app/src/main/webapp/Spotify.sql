create user c##spotify identified by spotify;
grant unlimited tablespace to c##spotify;
grant resource, connect, dba to c##spotify;



create table album(
	id_album varchar2(10) primary key,
	name_albums nvarchar2(255),
	id_singer varchar2(10),
	genre_album nvarchar2(255),
	artWorkPath varchar2(255),
	postedBy_album nvarchar2(255),
	show nvarchar2(255),
	postDay date
)

create table singer(
	id_singer varchar2(10) primary key ,
	name_singer nvarchar2(100),
	picture_singer blob
)
create table genres(
	id_genre varchar2(10) primary key,
	name_genre nvarchar2(255)
)
create table song(
	id_song varchar2(10) primary key,
	id_singer varchar2(10),
	id_album varchar2(10),
    	id_genre varchar2(10),
	name_song varchar2(255),
	path_song varchar2(255),
	listens number (10),
	postBy_song nvarchar2(255),
	postDay_song date
)


create table users(
	id_user varchar2(10) primary key,
	name_user nvarchar2(255),
	name_account nvarchar2(255),
	password varchar2(20),
	email varchar2(255),
	decentralization number(1),
	registration_date date,
	avatar blob
)
ALTER TABLE album 
ADD CONSTRAINT fk_singer_album
FOREIGN KEY (id_singer) REFERENCES singer(id_singer);

ALTER TABLE song 
ADD CONSTRAINT fk_singer_song
FOREIGN KEY (id_singer) REFERENCES singer(id_singer);

ALTER TABLE song 
ADD CONSTRAINT fk_song_genre
FOREIGN KEY (id_genre) REFERENCES genres(id_genre);
------------


create table mng_album(
    table_name varchar2(20),
    transaction_name VARCHAR2(10),

    by_user VARCHAR2(30),

    transaction_date varchar2(30)
    
);
create or replace trigger album_trg
before insert or update or delete on album
for each row
declare
    v_user varchar2(10);
    v_date varchar2(30);
begin
    select user, to_char(sysdate, 'DD/MM/YY HH24:MI:SS') into v_user,v_date from dual;
    if inserting then
        insert into mng_album(table_name, transaction_name, by_user,transaction_date)
        values('ALBUM','INSERTING',v_user,v_date);
    elsif updating then
        insert into mng_album(table_name, transaction_name, by_user,transaction_date)
        values('ALBUM','UPDATING',v_user,v_date);
    elsif deleting then
        insert into mng_album(table_name, transaction_name, by_user,transaction_date)
        values('ALBUM','DELETING',v_user,v_date);
    end if;
end;

insert into album(id_album,name_albums,id_singer) values('08','Tracking NO2','05');
-------------
desc album;
create table album_backup as select * from album;

create or replace trigger album_backup_trg
before insert or update or delete on album
for each row
begin
    if inserting then
        insert into album_backup(id_album, name_albums, id_singer, genre_album, artworkpath,postedby_album,show,postday)
        values(:new.id_album,:new.name_albums,:new.id_singer, :new.genre_album, 
        :new.artworkpath,:new.postedby_album,:new.show,:new.postday);
    elsif deleting then
        delete from album_backup where id_album = :old.id_album and id_singer= :old.id_singer;
    elsif updating then
        update album_backup set id_album = :new.id_album, 
        name_albums= :new.name_albums, 
        id_singer= :new.id_singer, 
        genre_album= :new.genre_album, 
        artworkpath= :new.artworkpath,
        postedby_album= :new.postedby_album,
        show= :new.show,
        postday= :new.postday
        where id_album = :old.id_album and id_singer = :old.id_singer;
    end if;
end;
------------------
desc genres
create table genres_backup as select * from genres;

create or replace trigger genres_backup_trg
before insert or update or delete on genres
for each row
begin
    if inserting then
        insert into genres_backup(id_genre,name_genre)
        values(:new.id_genre, :new.name_genre);
    elsif updating then
        update genres_backup set id_genre =:new.id_genre, name_genre=:new.name_genre
        where id_genre = :old.id_genre;
    elsif deleting then
        delete from genres_backup where id_genre = :old.id_genre;
    end if;
end;

-----------------
desc singer
create table singer_backup as select id_singer,name_singer,picture_singer from singer;

create or replace trigger singer_backup_trg
before insert or update or delete on singer
for each row
begin
    if inserting then
        insert into singer_backup (id_singer,name_singer,picture_singer)
        values(:new.id_singer, :new.name_singer,:new.picture_singer);
    elsif updating then
        update singer_backup set id_singer=:new.id_singer,name_singer=:new.name_singer,picture_singer=:new.picture_singer
        where id_singer = :new.id_singer;
    elsif deleting then
        delete from singer_backup where id_singer=:old.id_singer;
    end if;
    exception
    when others then dbms_output.put_line(sqlcode||sqlerrm);
end;
----------------
desc song
create table song_backup as select * from song;

create or replace trigger song_backup_trg
before insert or update or delete on song
for each row
begin
    if inserting then
        insert into song_backup(id_song,id_singer,id_album,id_genre,path_song,listens,postby_song,postday_song)
        values(:new.id_song,:new.id_singer,:new.id_album,:new.id_genre,:new.path_song,:new.listens,:new.postby_song,:new.postday_song);
    elsif updating then
        update song_backup set id_song=:new.id_song,
        id_singer=:new.id_singer,
        id_album=:new.id_album,
        id_genre=:new.id_genre,
        path_song=:new.path_song,
        listens=:new.listens,
        postby_song=:new.postby_song,
        postday_song=:new.postday_song;
    elsif deleting then
        delete from song_backup where id_song = :old.id_song and id_singer=:old.id_singer;
        
    end if;
    exception
    when others then dbms_output.put_line(sqlcode||sqlerrm);
end;
-----------------
desc users
create table users_backup as select * from users;

create or replace trigger users_backup_trg
before insert or update or delete on users
for each row
begin
    if inserting then
     insert into users_backup(id_user, name_user,name_account,password,email,decentralization, registration_date,avatar)
     values(:new.id_user, :new.name_user,:new.name_account,:new.password,:new.email,:new.decentralization, :new.registration_date,:new.avatar);
    elsif updating then
        update users_backup set id_user=:new.id_user, 
        name_user=:new.name_user,
        name_account=:new.name_account,
        password=:new.password,
        email=:new.email,
        decentralization=:new.decentralization,
        registration_date=:new.registration_date,
        avatar=:new.avatar;
    elsif deleting then
        delete from users_backup where id_user = :old.id_user;
    end if;

end;

-------------------
create table spotify_event_logon_logoff(
    event_type varchar2(20),
    logon_date date,
    logon_time varchar2(15),
    logof_date date,
    logof_time varchar2(15)
);


create or replace trigger spotify_logon
after logon on schema
begin
    insert into spotify_event_logon_logoff values(
    ora_sysevent,
    sysdate,
    to_char(sysdate,'hh24:mi:ss'),
    null,
    null);
    commit;

end;
--select * from spotify_event_logon_logoff;
--disc;
--conn c##spotify/spotify;
--------
--create or replace trigger spotify_logoff
--before logoff on schema
--begin
--    insert into spotify_event_logon_logoff values(
--    ora_sysevent,
--    null,
--    null,
--    sysdate,
--    to_char(sysdate,'hh24:mi:ss'));
--    commit;
--
--end;
--select * from spotify_event_logon_logoff;
--disc;
--conn c##spotify/spotify;


---trigger auto-crement table users
--step 1
create sequence users_seq start with 1 increment by 1;
--step 2
CREATE OR REPLACE TRIGGER  bi_users
  before insert on users             
  for each row  
begin   
  if :NEW.id_user is null then 
    select users_seq.nextval into :NEW.id_user from dual; 
  end if; 
end;
---trigger auto-crement table song
create sequence song_seq start with 10 increment by 1;

CREATE OR REPLACE TRIGGER  bi_song_trg
  before insert on song             
  for each row  
begin   
  if :NEW.id_song is null then 
    select song_seq.nextval into :NEW.id_song from dual; 
  end if; 
end;

---trigger auto-crement table singer
create sequence singer_seq start with 10 increment by 1;

CREATE OR REPLACE TRIGGER  bi_singer_trg
  before insert on singer             
  for each row  
begin   
  if :NEW.id_singer is null then 
    select singer_seq.nextval into :NEW.id_singer from dual; 
  end if; 
end;
---trigger auto-crement table genres

create sequence genres_seq start with 10 increment by 1;

CREATE OR REPLACE TRIGGER  bi_genres_trg
  before insert on genres            
  for each row  
begin   
  if :NEW.id_genre is null then 
    select genres_seq.nextval into :NEW.id_genre from dual; 
  end if; 
end;
---trigger auto-crement table album
create sequence album_seq start with 10 increment by 1;

CREATE OR REPLACE TRIGGER  bi_album_trg
  before insert on album            
  for each row  
begin   
  if :NEW.id_album is null then 
    select album_seq.nextval into :NEW.id_album from dual; 
  end if; 
end;

--add data into table singer
insert into singer(name_singer)
values (N'Đàm Vĩnh Hưng');
insert into singer(name_singer)
values (N'Sơn Tùng M-TP');
insert into singer(name_singer)
values (N'Hà Anh Tuấn');
insert into singer(name_singer)
values (N'Bùi Anh Tuấn');
insert into singer(name_singer)
values (N'Phan Mạnh Quỳnh');
insert into singer(name_singer)
values (N'Lương Bích Hưu');
insert into singer(name_singer)
values (N'Uyên Linh');
insert into singer(name_singer)
values (N'Thu Minh');

--add data into table genres
insert into genres(name_genre) values ('Pop');
insert into genres(name_genre) values ('Rock');
insert into genres(name_genre) values ('Jazz');
insert into genres(name_genre) values ('Opera');
insert into genres(name_genre) values ('Country');
insert into genres(name_genre) values ('RnB');
insert into genres(name_genre) values ('Dance');
insert into genres(name_genre) values ('Blues');
insert into genres(name_genre) values ('Ballad');
insert into genres(name_genre) values ('Accoustic');
insert into genres(name_genre) values ('Bolero');


--album singer DamVinhHung
insert into album ( name_albums, id_singer)
values ('Vol.1 - Tình ơi xin ngủ yên ','10');
insert into album ( name_albums, id_singer)
values ('Vol.2 - Bình minh sẽ mang em đi ','10');
insert into album ( name_albums, id_singer)
values ('Vol.3 - Một trái tim tình si ','10');
insert into album ( name_albums, id_singer)
values ('Vol.5 - Giọt nước mắt cho đời ','10');
insert into album ( name_albums, id_singer)
values ('Vol.6 - Hưng ','10');
insert into album ( name_albums, id_singer)
values ('Vol.7 - Mr. Đàm ','10');
insert into album ( name_albums, id_singer)
values ('Vol.9 - Giải thoát ','10');
insert into album ( name_albums, id_singer)
values ('Vol.10 - Lạc mất em ','10');

--album singer Son Tung MTP
insert into album ( name_albums, id_singer)
values ('M-TP ','11');
insert into album ( name_albums, id_singer)
values ('Cơn mưa ngang qua ','11');
insert into album ( name_albums, id_singer)
values ('Âm thầm bên em ','11');
insert into album ( name_albums, id_singer)
values ('Chắc ai đó sẽ về ','11');
insert into album ( name_albums, id_singer)
values ('Lạc trôi ','11');

--album singer Ha Anh Tuan
insert into album ( name_albums, id_singer)
values ('Cafe sáng ','12');
insert into album ( name_albums, id_singer)
values ('Sài Gòn Radio ','12');
insert into album ( name_albums, id_singer)
values ('Cock-tail ','12');
insert into album ( name_albums, id_singer)
values ('Lava ','12');
insert into album ( name_albums, id_singer)
values ('Streets Rhythm ','12');
insert into album ( name_albums, id_singer)
values ('Truyện ngắn ','12');
insert into album ( name_albums, id_singer)
values ('Cuối ngày người đàn ông một mình ','12');
insert into album ( name_albums, id_singer)
values ('Những vết thương lành ','12');
insert into album ( name_albums, id_singer)
values ('Chân dung ','12');
insert into album ( name_albums, id_singer)
values ('Biết tương tư ','12');

--album singer Bui Anh Tuan
insert into album ( name_albums, id_singer)
values ('Tết trong tâm hồn ','13');
insert into album ( name_albums, id_singer)
values ('Nơi tình yêu trở lại ','13');
insert into album ( name_albums, id_singer)
values ('Xin em ','13');
insert into album ( name_albums, id_singer)
values ('Mùa đông tình yêu ','13');
insert into album ( name_albums, id_singer)
values ('Cuộc xâm lăng của các chàng trai','13');
insert into album ( name_albums, id_singer)
values ('Thu Nhớ','13');
insert into album ( name_albums, id_singer)
values ('Hát gọi mùa xuân ','13');

--album singer Phan Manh Quynh
insert into album ( name_albums, id_singer)
values ('Người yêu cũ ','14');
insert into album ( name_albums, id_singer)
values ('Anh ghét làm bạn em','14');
insert into album ( name_albums, id_singer)
values ('Không quan trọng','14');
insert into album ( name_albums, id_singer)
values ('Mất hy vọng','14');
insert into album ( name_albums, id_singer)
values ('Vợ người ta','14');
insert into album ( name_albums, id_singer)
values ('Khi người mình yêu khóc','14');
insert into album ( name_albums, id_singer)
values ('Tri kỷ','14');

--album singer LuongBichHuu
insert into album ( name_albums, id_singer)
values ('Vol.2 Ây da Ây da','15');
insert into album ( name_albums, id_singer)
values ('It is Not Over','15');
insert into album ( name_albums, id_singer)
values ('Story of Time','15');
insert into album ( name_albums, id_singer)
values ('Đứt từng đoạn ruột','15');
insert into album (name_albums, id_singer)
values ('Mình cưới nhau nhé','15');

--album singer Uyen Linh
insert into album ( name_albums, id_singer)
values ('Giấc mơ tôi','16');
insert into album ( name_albums, id_singer)
values ('Ước sao ta chưa gặp nhau','16');
insert into album ( name_albums, id_singer)
values ('Portrait','16');
insert into album ( name_albums, id_singer)
values ('Chờ người nơi ấy','16');
insert into album ( name_albums, id_singer)
values ('Tình khúc một thời 1','16');


--album singer Thu Minh
insert into album ( name_albums, id_singer)
values ('Ước mơ','17');
insert into album ( name_albums, id_singer)
values ('Lời cuối','17');
insert into album ( name_albums, id_singer)
values ('Nếu như','17');
insert into album ( name_albums, id_singer)
values ('Tình em','17');
insert into album ( name_albums, id_singer)
values ('Thiên đàng','17');
insert into album ( name_albums, id_singer)
values ('I Do','17');
insert into album ( name_albums, id_singer)
values ('Giác quan thứ 6','17');
insert into album ( name_albums, id_singer)
values ('Body Language','17');
insert into album ( name_albums, id_singer)
values ('The Best of Thu Minh','17');
insert into album ( name_albums, id_singer)
values ('Album Hit Remix','17');



-------------------------------------------

insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values('admin','admin','admin','admin@gmail.com',1,'13-sep-22 ');

insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values('Khanh','Khanh','Khanh','Khanh@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Tai','Tai','Tai','Tai@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Hiep','Hiep','Hiep','Hiep@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Nhan','Nhan','Nhan','Nhan@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Duoc','Duoc','Duoc','Duoc@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Hoc','Hoc','Hoc','Hoc@gmail.com',0,'13-sep-22 ');
insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Dai','Dai','Dai','Dai@gmail.com',0,'13-sep-22 ');

insert into users ( name_user, name_account, password, email, decentralization, registration_date )
values(N'Phung','Phung','Phung','Phung@gmail.com',0,'24-sep-22 ');


------------------------------------------


--song singer Dam Vinh Hung
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('10','11','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FDamVinhHung%2FTaiNhacMienPhi.Net%20-%20Bi%E1%BB%83n%20T%C3%ACnh.mp3?alt=media&token=804396dd-b6e4-4a6a-aff5-e5777cab480d','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('10','11','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FDamVinhHung%2FTaiNhacMienPhi.Net%20-%20Chi%E1%BA%BFc%20V%C3%B2ng%20C%E1%BA%A7u%20H%C3%B4n.mp3?alt=media&token=92a56812-d7bd-47d3-ab04-aef12d0a83be','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('10','11','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FDamVinhHung%2FTaiNhacMienPhi.Net%20-%20M%E1%BB%99t%20c%C3%B5i%20%C4%91i%20v%E1%BB%81.mp3?alt=media&token=cda1ad55-cf74-48ac-b51b-adedd0b266dc','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('10','11','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FDamVinhHung%2FTaiNhacMienPhi.Net%20-%20Tan%20V%E1%BB%A1.mp3?alt=media&token=71c59084-ec10-4294-a609-136996118432','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('10','11','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FDamVinhHung%2FTaiNhacMienPhi.Net%20-%20Ti%E1%BA%BFng%20gi%C3%B3%20x%C3%B4n%20xao.mp3?alt=media&token=7d745c95-c251-4abf-92eb-d6d74f9d9ca2','7-Nov-22');

--song singer Son Tung
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('11','18','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FSonTungMTP%2FTaiNhacMienPhi.Net%20-%20Anh%20Sai%20R%E1%BB%93i.mp3?alt=media&token=b1b3c37a-9105-4198-aca9-d7cb9113656c','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('11','18','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FSonTungMTP%2FTaiNhacMienPhi.Net%20-%20Bu%C3%B4ng%20%C4%90%C3%B4i%20Tay%20Nhau%20Ra.mp3?alt=media&token=528b8372-de48-408f-9831-f212d1671e92','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('11','18','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FSonTungMTP%2FTaiNhacMienPhi.Net%20-%20B%C3%ACnh%20Y%C3%AAn%20Nh%E1%BB%AFng%20Ph%C3%BAt%20Gi%C3%A2y.mp3?alt=media&token=d2d45f94-24a6-4b4e-8628-43454ebbc51b','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('11','18','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FSonTungMTP%2FTaiNhacMienPhi.Net%20-%20Ch%C3%BAng%20Ta%20Kh%C3%B4ng%20Thu%E1%BB%99c%20V%E1%BB%81%20Nhau.mp3?alt=media&token=c277279f-fcc0-4b3f-aa1c-8385ec25f4a7','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('11','18','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FSonTungMTP%2FTaiNhacMienPhi.Net%20-%20Ch%E1%BA%A1y%20Ngay%20%C4%90i.mp3?alt=media&token=43901abd-345a-40cf-9779-bfba883edb5c','7-Nov-22');

--song singer Ha ANh Tuan
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('12','23','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FHaAnhTuan%2FTaiNhacMienPhi.Net%20-%20Th%C3%A1ng%20M%E1%BA%A5y%20Em%20Nh%E1%BB%9B%20Anh_.mp3?alt=media&token=45aa8e76-50f6-4333-baf1-dfd35bdc288d','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('12','23','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FHaAnhTuan%2FTaiNhacMienPhi.Net%20-%20B%C3%B3ng%20M%C6%B0a.mp3?alt=media&token=9e7f5dd9-80ad-4fe8-b1c7-4497910b7311','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('12','23','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FHaAnhTuan%2FTaiNhacMienPhi.Net%20-%20C%C3%B3%20Ch%C3%A0ng%20Trai%20Vi%E1%BA%BFt%20L%C3%AAn%20C%C3%A2y.mp3?alt=media&token=557f83ec-8a72-4a1e-9ff0-bd3c6675533f','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('12','23','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FHaAnhTuan%2FTaiNhacMienPhi.Net%20-%20C%E1%BA%A3%20M%E1%BB%99t%20Tr%E1%BB%9Di%20Th%C6%B0%C6%A1ng%20Nh%E1%BB%9B.mp3?alt=media&token=dde3249d-32d5-47e8-9fcd-89f700980495','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('12','23','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FHaAnhTuan%2FTaiNhacMienPhi.Net%20-%20Ng%C3%A0y%20Ch%C6%B0a%20Gi%C3%B4ng%20B%C3%A3o.mp3?alt=media&token=fe99b5d4-3f50-4823-ab76-e0e0d1784e18','7-Nov-22');


--song singer Bui anh Tuan
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('13','34','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FBuiAnhTuan%2FTaiNhacMienPhi.Net%20-%20Bu%C3%B4ng.mp3?alt=media&token=3353de32-c31c-464b-afca-93d2914c4a1a','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('13','34','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FBuiAnhTuan%2FTaiNhacMienPhi.Net%20-%20B%C3%AD%20M%E1%BA%ADt%20Kh%C3%B4ng%20T%C3%AAn.mp3?alt=media&token=8aabe4b7-5302-4234-8098-a6cb40a85624','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('13','34','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FBuiAnhTuan%2FTaiNhacMienPhi.Net%20-%20Chia%20Tay.mp3?alt=media&token=ba8747a4-80bd-4e22-8aaf-4b4a05052f2a','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('13','34','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FBuiAnhTuan%2FTaiNhacMienPhi.Net%20-%20Ch%E1%BB%89%20C%C3%B2n%20L%E1%BA%A1i%20T%C3%ACnh%20Y%C3%AAu.mp3?alt=media&token=fdb8c76e-fba5-4edb-bb62-e6887aef62d7','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('13','34','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FBuiAnhTuan%2FTaiNhacMienPhi.Net%20-%20Hoang%20Mang.mp3?alt=media&token=d92bd95e-bfcc-4545-9700-cd8d7bbd77a3','7-Nov-22');

--song singer Phan Manh Quynh
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('14','40','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FPhanManhQuynh%2FCon%20tin%20v%E1%BB%A1%20tan.mp3?alt=media&token=cd0a4fc8-a8f1-4293-9628-6660abed4684','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('14','40','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FPhanManhQuynh%2FH%C3%A3y%20ra%20kh%E1%BB%8Fi%20ng%C6%B0%E1%BB%9Di%20%C4%91%C3%B3%20%C4%91i.mp3?alt=media&token=8e005f8b-53bf-4bc1-8597-7a96839933da','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('14','40','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FPhanManhQuynh%2FTaiNhacMienPhi.Net%20-%20C%C3%B3%20M%E1%BB%99t%20N%C6%A1i%20Nh%C6%B0%20Th%E1%BA%BF.mp3?alt=media&token=3d2bb4c6-7ff9-421f-a658-0e0c7ff5c471','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('14','40','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FPhanManhQuynh%2FTaiNhacMienPhi.Net%20-%20Huy%E1%BB%81n%20Tho%E1%BA%A1i.mp3?alt=media&token=544d5530-1588-4f3f-951a-ee3188c01ab7','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('14','40','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FPhanManhQuynh%2FTaiNhacMienPhi.Net%20-%20H%C3%A0%20Lan%20(M%E1%BA%AFt%20Bi%E1%BA%BFc%20OST).mp3?alt=media&token=f52796ee-8167-440d-b0e0-ff08de4ae837','7-Nov-22');

--song singer Luong Bich Huu
insert into song ( id_singer, id_album, id_genre,path_song,postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20Em%20D%E1%BB%ABng%20L%E1%BA%A1i.mp3?alt=media&token=aa5cfa33-81e9-4989-ab5a-405b4b4e8ee7','24-sep-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20Em%20V%E1%BA%ABn%20Ho%C3%A0i%20Y%C3%AAu%20Anh.mp3?alt=media&token=934cd1f1-4f77-4a52-bdca-ffa3229ba70f','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20Em%20Y%C3%AAu%20Anh.mp3?alt=media&token=ed081880-3960-4787-91c0-5c055b4054aa','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20M%E1%BB%89m%20C%C6%B0%E1%BB%9Di%20Cho%20Qua.mp3?alt=media&token=60f9acd3-1217-42f3-860a-07ba4b33ed54','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20N%C6%B0%E1%BB%9Bc%20M%E1%BA%AFt%20H%C3%B3a%20%C4%90%C3%A1.mp3?alt=media&token=e3dd3e01-ab22-4839-8501-141cceecd089','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20N%E1%BB%A5%20H%C3%B4n%20Bi%E1%BB%87t%20Ly.mp3?alt=media&token=b2ae60e4-b281-415d-8671-4945e0ed6a89','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20N%E1%BB%A5%20H%E1%BB%93ng%20Mong%20Manh.mp3?alt=media&token=3d88beed-ba9a-413b-a429-da766b3fb070','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('15','49','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FLuongBichHuu%2FTaiNhacMienPhi.Net%20-%20Qu%C3%AAn%20c%C3%A1ch%20y%C3%AAu.mp3?alt=media&token=502f44bc-3983-49dd-b533-e018d956295d','7-Nov-22');

--song singer Uyen Linh
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('16','52','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FUyenLinh%2FTaiNhacMienPhi.Net%20-%20Bi%E1%BA%BFt%20%C4%90%C3%A2u.mp3?alt=media&token=22c8f72b-09c9-42cf-a374-2732d5ee5f13','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('16','52','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FUyenLinh%2FTaiNhacMienPhi.Net%20-%20B%C3%A0i%20H%C3%A1t%20C%E1%BB%A7a%20Em.mp3?alt=media&token=f2f0536e-35d9-4864-ba21-e03d9a830b46','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('16','52','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FUyenLinh%2FTaiNhacMienPhi.Net%20-%20B%C3%B3ng%20M%C3%A2y%20%C4%90%E1%BB%9Di%20T%C3%B4i.mp3?alt=media&token=99b20e41-1606-408e-868f-e5b7993d870c','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('16','52','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FUyenLinh%2FTaiNhacMienPhi.Net%20-%20C%C3%B3%20%C4%90%C3%B4i%20Khi.mp3?alt=media&token=36f215f5-9ce4-4929-911d-b05e521d7726','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('16','52','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FUyenLinh%2FTaiNhacMienPhi.Net%20-%20Gi%E1%BA%A5c%20M%C6%A1%20Tuy%E1%BB%87t%20V%E1%BB%9Di.mp3?alt=media&token=c817f938-a8a6-48b1-a03d-347bb8d3e6e6','7-Nov-22');


--song singer Thu Minh
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('17','60','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FThuMinh%2FTaiNhacMienPhi.Net%20-%2060%20N%C4%83m%20Cu%E1%BB%99c%20%C4%90%E1%BB%9Di.mp3?alt=media&token=50f34dfc-1d06-4abe-81f7-db5aaba1d479','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('17','60','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FThuMinh%2FTaiNhacMienPhi.Net%20-%20Bay%20(Remix%202016).mp3?alt=media&token=d6c6e083-621a-46f3-8208-b34797f31a6d','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('17','60','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FThuMinh%2FTaiNhacMienPhi.Net%20-%20B%E1%BA%AFt%20%C4%90%E1%BA%A7u%20T%E1%BB%AB%20M%E1%BB%99t%20K%E1%BA%BFt%20Th%C3%BAc.mp3?alt=media&token=705072fc-acbe-446e-9302-df8585d4ef25','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('17','60','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FThuMinh%2FTaiNhacMienPhi.Net%20-%20C%C3%B4%20%E1%BA%A4y%20S%E1%BA%BD%20Kh%C3%B4ng%20Y%C3%AAu%20Anh%20Nh%C6%B0%20Em.mp3?alt=media&token=126c8c33-5bdc-478c-92e6-3b90bb42cb5b','7-Nov-22');
insert into song ( id_singer, id_album, id_genres, path_song, postday_song)
values ('17','60','10','https://firebasestorage.googleapis.com/v0/b/fir-dc1e5.appspot.com/o/Mp3%2FThuMinh%2FTaiNhacMienPhi.Net%20-%20Ph%C3%BAt%20y%C3%AAu%20%C4%91%E1%BA%A7u.mp3?alt=media&token=c959ee69-2e56-40ff-bc1c-261497998af9','7-Nov-22');


---------------------------
--trigger
CREATE OR REPLACE TRIGGER bi_album
BEFORE INSERT ON album
for each row
enable
declare 
    v_user varchar2(10);
begin
    select user into v_user from dual;
    dbms_output.put_line('you inserted a line by '||v_user);
end;

insert into album(name_albums,id_singer) values(N'Buowsc qua nhau','10');
