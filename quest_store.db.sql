BEGIN TRANSACTION;
CREATE TABLE "WalletTable"
(
`wallet_id`
INTEGER,
`coolcoins`
INTEGER
DEFAULT
1000,
`user_id`
INTEGER
UNIQUE,
PRIMARY
KEY(`wallet_id`)
);
INSERT INTO `WalletTable` VALUES (1,1000,3);
INSERT INTO `WalletTable` VALUES (2,1000,4);
INSERT INTO `WalletTable` VALUES (3,1000,8);
INSERT INTO `WalletTable` VALUES (4,1000,9);
INSERT INTO `WalletTable` VALUES (5,1000,11);
INSERT INTO `WalletTable` VALUES (6,1000,12);
INSERT INTO `WalletTable` VALUES (7,1000,5);
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
INSERT INTO `UsersTable` VALUES (2,'Agnieszka','Koszany','a.koszany','akosz','akoszany','mentor');
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
INSERT INTO `UsersTable` VALUES (15,'Jan','Kowalski','j.kowalski@gmail.com','mentor','mentor','mentor');
CREATE TABLE "TeamsTable" (
	`team_id`	INTEGER,
	`team_name`	TEXT UNIQUE,
	`artifact_id`	INTEGER DEFAULT null,
	`votes`	INTEGER DEFAULT 0,
	`state`	INTEGER DEFAULT 0,
	PRIMARY KEY(team_id)
);
INSERT INTO `TeamsTable` VALUES (1,'Dreem
Is
Green',NULL,0,0);
INSERT INTO `TeamsTable` VALUES (2,'gwarki','private_mentoring',0,1);
INSERT INTO `TeamsTable` VALUES (3,'young','private_mentoring',1,2);
INSERT INTO `TeamsTable` VALUES (4,'lorem',NULL,0,0);
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
	`team_id`	INTEGER,
	`class_name`	TEXT,
	`voted`	TEXT DEFAULT 'no'
);
INSERT INTO `StudentsTable` VALUES (3,470,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (4,600,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (8,140,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (9,820,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (12,89,2,'krk2016-1','no');
INSERT INTO `StudentsTable` VALUES (5,77,3,'bud2016-3','yes');
INSERT INTO `StudentsTable` VALUES (11,120,4,'krk2017-2','no');
CREATE TABLE "SessionTable"
(
`session_id`
TEXT,
`user_id`
INTEGER,
`user_name`
TEXT,
`user_type`
TEXT
);
INSERT INTO `SessionTable` VALUES ('06e966d2-7dd4-4e48-9a27-808e058985a5',3,'student','student');
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
INSERT INTO `MailBox` VALUES (13,'testnowy',1,'1131',1,9);
INSERT INTO `MailBox` VALUES (14,'content1132',1,'naglowek1',1,7);
INSERT INTO `MailBox` VALUES (15,'aga',1,'czesc',1,2);
INSERT INTO `MailBox` VALUES (16,'asia',1,'jo',9,10);
INSERT INTO `MailBox` VALUES (19,'content
test',1,'header127',3,1);
INSERT INTO `MailBox` VALUES (20,'Hello admin',1,'hello',6,10);
INSERT INTO `MailBox` VALUES (21,'michalina',1,'hello admin',1,6);
INSERT INTO `MailBox` VALUES (22,'hello student',1,'hello student',10,3);
INSERT INTO `MailBox` VALUES (23,'student',1,'test mail to student',3,10);
INSERT INTO `MailBox` VALUES (24,'pawel',1,'pawel',9,10);
INSERT INTO `MailBox` VALUES (25,'jedzie na qna',1,'pawel',3,10);
INSERT INTO `MailBox` VALUES (26,'Dear Codecooler 
, Your team use an artifact: null. You will receive detailed information soon from the Mentor. Regards, Your Mentor ',1,'Use an artifact',3,2);
INSERT INTO `MailBox` VALUES (27,'Dear Codecooler 
, Your team use an artifact: null. You will receive detailed information soon from the Mentor. Regards, Your Mentor ',1,'Use an artifact',4,2);
INSERT INTO `MailBox` VALUES (28,'Dear Codecooler 
, Your team use an artifact: null. You will receive detailed information soon from the Mentor. Regards, Your Mentor ',1,'Use an artifact',8,2);
INSERT INTO `MailBox` VALUES (29,'Dear Codecooler 
, Your team use an artifact: null. You will receive detailed information soon from the Mentor. Regards, Your Mentor ',1,'Use an artifact',9,2);
INSERT INTO `MailBox` VALUES (30,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',2,1);
INSERT INTO `MailBox` VALUES (31,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',13,1);
INSERT INTO `MailBox` VALUES (32,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',15,1);
INSERT INTO `MailBox` VALUES (33,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',2,1);
INSERT INTO `MailBox` VALUES (34,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',13,1);
INSERT INTO `MailBox` VALUES (35,'Dear Mentors, team Dreem
Is
Green buy artifact: private_mentoring. Please contact the team to discuss the purchase. Regards Admin',1,'New group purchase from Dreem
Is
Green .',15,1);
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
INSERT INTO `LevelsTable` VALUES ('test``',3);
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
INTEGER,
`state`
INTEGER
DEFAULT
0
);
INSERT INTO `ArtifactsTable` VALUES ('private mentoring',100,'mentoring',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on elephant',200,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on dog',300,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on cat',400,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on bird',500,'fun',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on cow',600,'fun',0);
CREATE TABLE `ArtifactCategory`
(
`artifact_category_name`
TEXT
UNIQUE
);
INSERT INTO `ArtifactCategory` VALUES ('fun');
INSERT INTO `ArtifactCategory` VALUES ('mentoring');
COMMIT;
