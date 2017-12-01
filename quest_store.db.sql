BEGIN TRANSACTION;
CREATE TABLE "WalletTable"
(
`wallet_id`
INTEGER,
`coolcoins`
INTEGER
DEFAULT
1000,
`user_exp`
INTEGER
DEFAULT
0,
`user_id`
INTEGER
UNIQUE,
PRIMARY
KEY(`wallet_id`)
);
INSERT INTO `WalletTable` VALUES (1,1000,0,3);
INSERT INTO `WalletTable` VALUES (2,1000,0,4);
INSERT INTO `WalletTable` VALUES (3,1000,0,8);
INSERT INTO `WalletTable` VALUES (4,1000,0,9);
INSERT INTO `WalletTable` VALUES (5,1000,0,11);
INSERT INTO `WalletTable` VALUES (6,1000,0,12);
INSERT INTO `WalletTable` VALUES (7,1000,0,5);
CREATE TABLE "UsersTable"
(
`user_id`
INTEGER
NOT
NULL
UNIQUE,
`name`
TEXT,
`surname`
TEXT,
`email`
TEXT
UNIQUE,
`login`
TEXT
UNIQUE,
`password`
TEXT,
`user_type`
TEXT,
PRIMARY
KEY(`user_id`)
);
INSERT INTO `UsersTable` VALUES (1,'Michalina','Borek','m.borek','admin','admin','admin');
INSERT INTO `UsersTable` VALUES (2,'Agnieszka','Koszany','a.koszany','mentor','mentor','mentor');
INSERT INTO `UsersTable` VALUES (3,'Przemyslaw','Salak','p.salak@','student','student','student');
INSERT INTO `UsersTable` VALUES (4,'Pawel','Syktus','p.syktus@','psyktus','a912','student');
INSERT INTO `UsersTable` VALUES (5,'Bob','Studer','b.stude','bob','stude','student');
INSERT INTO `UsersTable` VALUES (6,'Jurek','Magnus','j.magnu','jurek','magnus','admin');
INSERT INTO `UsersTable` VALUES (7,'Asia','Kowalska','a.dobra','asia','kowalsk','mentor');
INSERT INTO `UsersTable` VALUES (8,'Kamil','Kmiec','kkmiec@','kkmiec','km','student');
INSERT INTO `UsersTable` VALUES (9,'Piotr','Szmytke','pszmytke@','pszmytke','szym','student');
INSERT INTO `UsersTable` VALUES (11,'Karol','Kotarski','op.pl','Uj','Uj','student');
INSERT INTO `UsersTable` VALUES (12,'Michal','Kowalski','@op.pl','mk','mk12','student');
INSERT INTO `UsersTable` VALUES (13,'Marcin','Izworski','mi@','mi','mi12','mentor');
INSERT INTO `UsersTable` VALUES (14,'Konrad','Gadzina','kg','kg','kg','mentor');
CREATE TABLE "TeamsTable" (
	`team_id`	INTEGER,
	`team_name`	TEXT DEFAULT null,
	`artifact_id`	INTEGER DEFAULT null,
	`votes`	INTEGER DEFAULT 0,
	`state`	INTEGER DEFAULT 0,
	PRIMARY KEY(team_id)
);
INSERT INTO `TeamsTable` VALUES (1,'DreemIsGreen',NULL,0,0);
INSERT INTO `TeamsTable` VALUES (2,'gwarki','private_mentoring',0,1);
INSERT INTO `TeamsTable` VALUES (3,'young','private_mentoring',1,2);
INSERT INTO `TeamsTable` VALUES (4,'lorem',NULL,0,0);
INSERT INTO `TeamsTable` VALUES (5,'codderzy','mentor_ride_on_cat',1,1);
CREATE TABLE "StudentsWithQuests"
(
`quest_name`
INTEGER
NOT
NULL,
`quest_category`
TEXT,
`price`
INTEGER,
`title`
INTEGER,
`user_id`
INTEGER
NOT
NULL
);
CREATE TABLE "StudentsWithArtifacts"
(
`artifact_name`
TEXT
NOT
NULL,
`price`
INTEGER,
`artifact_category`
TEXT,
`user_id`
INTEGER,
`state`
INTEGER
DEFAULT
0
);
CREATE TABLE "StudentsTable" (
	`user_id`	INTEGER NOT NULL UNIQUE,
	`experience`	INTEGER DEFAULT 0,
	`team_id`	INTEGER DEFAULT 0,
	`class_name`	TEXT,
	`voted`	TEXT DEFAULT 'no'
);
INSERT INTO `StudentsTable` VALUES (3,470,5,'krk2017-1','yes');
INSERT INTO `StudentsTable` VALUES (4,600,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (8,140,5,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (9,820,5,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (12,89,2,'krk2016-1','no');
INSERT INTO `StudentsTable` VALUES (5,77,3,'bud2016-3','yes');
INSERT INTO `StudentsTable` VALUES (11,120,4,'krk2017-2','no');
CREATE TABLE "SessionTable" (
	`session_id`	TEXT,
	`user_id`	INTEGER,
	`user_name`	TEXT,
	`user_type`	TEXT,
	`team_id`	INTEGER DEFAULT null
);
CREATE TABLE "QuestsTable"
(
`quest_name`
TEXT
NOT
NULL
UNIQUE,
`price`
INTEGER
NOT
NULL,
`quest_category`
TEXT
NOT
NULL
);
INSERT INTO `QuestsTable` VALUES ('pass_si_week',100,'fun');
INSERT INTO `QuestsTable` VALUES ('take_part_in_reqruit',400,'mentoring');
CREATE TABLE "QuestCategory"
(
`quest_category_name`
TEXT
NOT
NULL
UNIQUE
);
INSERT INTO `QuestCategory` VALUES ('fun');
INSERT INTO `QuestCategory` VALUES ('mentoring');
CREATE TABLE "MentorsTable"
(
`user_id`
INTEGER
NOT
NULL
UNIQUE,
`class_name`
TEXT
);
INSERT INTO `MentorsTable` VALUES (2,'krk2017-1');
INSERT INTO `MentorsTable` VALUES (13,'krk2017-1');
INSERT INTO `MentorsTable` VALUES (7,'krk2017-2');
INSERT INTO `MentorsTable` VALUES (15,'krk2017-1');
INSERT INTO `MentorsTable` VALUES (14,'bud2016-3');
CREATE TABLE "MailBox"
(
`id`
INTEGER,
`content`
TEXT,
`read`
INTEGER
DEFAULT
1,
`header`
TEXT,
`user_id_recipient`
INTEGER,
`user_id_sender`
INTEGER,
PRIMARY
KEY(id)
);
INSERT INTO `MailBox` VALUES (9,'tresc
wiadomosci',0,'to
nowy
naglowek',9,10);
INSERT INTO `MailBox` VALUES (10,'Dziala',0,'Co
tam
',1,9);
INSERT INTO `MailBox` VALUES (25,'jedzie
na
qna',1,'pawel',3,10);
CREATE TABLE "LevelsTable"
(
`level_name`
TEXT
NOT
NULL
UNIQUE,
`exp_required`
INTEGER
NOT
NULL
);
INSERT INTO `LevelsTable` VALUES ('noob',0);
INSERT INTO `LevelsTable` VALUES ('beginner',400);
INSERT INTO `LevelsTable` VALUES ('medium',600);
INSERT INTO `LevelsTable` VALUES ('java_guru',1000);
INSERT INTO `LevelsTable` VALUES ('replace_mentor','22
500');
INSERT INTO `LevelsTable` VALUES ('Fullstack',400000);
CREATE TABLE "ClassTable"
(
`class_name`
TEXT
NOT
NULL
UNIQUE
);
INSERT INTO `ClassTable` VALUES ('krk2017-1');
INSERT INTO `ClassTable` VALUES ('krk2016-1');
INSERT INTO `ClassTable` VALUES ('krk-2017-2');
INSERT INTO `ClassTable` VALUES ('bud2016-3');
CREATE TABLE "ArtifactsTable"
(
`artifact_name`
TEXT
NOT
NULL
UNIQUE,
`price`
INTEGER,
`artifact_category`
TEXT,
`state`
INTEGER
DEFAULT
0
);
INSERT INTO `ArtifactsTable` VALUES ('private_mentoring',100,'mentoring',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_elephant',200,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_dog',300,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_cat',400,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_bird',500,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_cow',600,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('cosss',2300,'funer',0);
CREATE TABLE `ArtifactCategory`
(
`artifact_category_name`
TEXT
UNIQUE
);
INSERT INTO `ArtifactCategory` VALUES ('fun');
INSERT INTO `ArtifactCategory` VALUES ('mentoring');
COMMIT;
