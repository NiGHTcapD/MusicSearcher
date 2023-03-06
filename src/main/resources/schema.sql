create sequence bpm_seq start with 1 increment by 50;
create sequence music_key_seq start with 1 increment by 50;
create sequence songs_seq start with 1 increment by 50;
create sequence time_signature_seq start with 1 increment by 50;
create table bpm (idgnorable integer not null, beats_per_minute integer, song_id integer, song_name varchar(100), primary key (idgnorable));
create table music_key (idgnorable integer not null, music_key varchar(10), song_id integer, song_name varchar(100), primary key (idgnorable));
create table songs (id integer not null, artist varchar(100), song_title varchar(100), primary key (id));
create table time_signature (idgnorable integer not null, song_id integer, song_name varchar(100), time_signature varchar(10), primary key (idgnorable));