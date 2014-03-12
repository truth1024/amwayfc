DROP DATABASE IF EXISTS `amwayfc`;
create database amwayfc default charset utf8;
grant all privileges on amwayfc.* to amwayfc@'%' identified by 'amwayfc';
grant all privileges on amwayfc.* to amwayfc@'localhost' identified by 'amwayfc';
flush privileges;
use amwayfc;

set names utf8;
