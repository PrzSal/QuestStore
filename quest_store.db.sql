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
INSERT INTO `UsersTable` VALUES (1,'Jan','Kowalski','j.kowalski@gmail.com','mentor','mentor','mentor');
INSERT INTO `UsersTable` VALUES (2,'Agnieszka','Koszany','a.koszany','akosz','akoszany','mentor');
INSERT INTO `UsersTable` VALUES (3,'Przemyslaw','Salak','p.salak@','student','student','student');
INSERT INTO `UsersTable` VALUES (4,'Pawel','Syktus','p.syktus@','psyktus','a912','student');
INSERT INTO `UsersTable` VALUES (5,'Bob','Studer','b.stude','bob','stude','student');
INSERT INTO `UsersTable` VALUES (6,'Jurek','Magnus','j.magnu','jurek','magnus','admin');
INSERT INTO `UsersTable` VALUES (7,'Asia','Kowalska','a.dobra','asia','kowalsk','mentor');
INSERT INTO `UsersTable` VALUES (8,'Kamil','Kmiec','kkmiec@','kkmiec','km','student');
INSERT INTO `UsersTable` VALUES (9,'Piotr','Szmytke','pszmytke@','pszmytke','szym','student');
INSERT INTO `UsersTable` VALUES (10,'Michalina','Borek','m.borek','admin','admin','admin');
INSERT INTO `UsersTable` VALUES (11,'Karol','Kotarski','op.pl','Uj','Uj','student');
INSERT INTO `UsersTable` VALUES (12,'Michal','Kowalski','@op.pl','mk','mk12','student');
CREATE TABLE "TeamsTable" (
	`team_id`	INTEGER,
	`team_name`	TEXT UNIQUE,
	`artifact_id`	INTEGER DEFAULT null,
	`votes`	TEXT DEFAULT null,
	`state`	INTEGER DEFAULT 0,
	PRIMARY KEY(team_id)
);
INSERT INTO `TeamsTable` VALUES (1,'Dreem Is Green','mentor_ride_on_elephant',NULL,2);
INSERT INTO `TeamsTable` VALUES (2,'gwarki','private_mentoring',NULL,2);
INSERT INTO `TeamsTable` VALUES (3,'young','private_mentoring','3',1);
INSERT INTO `TeamsTable` VALUES (4,'lorem',NULL,NULL,0);
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
CREATE TABLE "StudentsTable"
(
`user_id`
INTEGER
NOT
NULL
UNIQUE,
`experience`
INTEGER
DEFAULT
0,
`team_id`
INTEGER,
`class_name`
TEXT
);
INSERT INTO `StudentsTable` VALUES (3,470,1,'krk2017-1');
INSERT INTO `StudentsTable` VALUES (4,600,1,'krk2017-1');
INSERT INTO `StudentsTable` VALUES (8,140,1,'krk2017-1');
INSERT INTO `StudentsTable` VALUES (9,820,1,'krk2017-1');
INSERT INTO `StudentsTable` VALUES (12,89,2,'krk2016-1');
INSERT INTO `StudentsTable` VALUES (5,77,3,'bud2016-3');
INSERT INTO `StudentsTable` VALUES (11,120,4,'krk2017-2');
CREATE TABLE "SessionTable" (
	`session_id`	TEXT,
	`user_id`	INTEGER,
	`user_name`	TEXT,
	`user_type`	TEXT
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
INSERT INTO `MentorsTable` VALUES (2,'krk17');
INSERT INTO `MentorsTable` VALUES (5,'krk17');
INSERT INTO `MentorsTable` VALUES (7,'krk17');
INSERT INTO `MentorsTable` VALUES (1,'krk17');
INSERT INTO `MentorsTable` VALUES (11,'krk16');
INSERT INTO `MentorsTable` VALUES (12,'Krk-2017-3');
CREATE TABLE "MailBox" (
	`id`	INTEGER,
	`content`	TEXT,
	`read`	INTEGER DEFAULT 1,
	`header`	TEXT,
	`user_id_recipient`	INTEGER,
	`user_id_sender`	INTEGER,
	PRIMARY KEY(id)
);
INSERT INTO `MailBox` VALUES (1,'test mail
',0,'header',10,4);
INSERT INTO `MailBox` VALUES (2,'mail testbhhidviuj novjnovnjvd nkaknvsvas oasvasonn n nnnnnnnnnnnnn nnnnnnnnnnn nnnvvvosnv lvnVv  VA[SSPLVV MPSOVKAOovonvo  jsasnvnsVBSVIN AVBA  BHJHBD BH ABHAB V OVWNQVIV',1,'header1

',10,5);
INSERT INTO `MailBox` VALUES (3,'mail',0,'header2',7,6);
INSERT INTO `MailBox` VALUES (4,'check',1,'read',10,2);
INSERT INTO `MailBox` VALUES (5,'new',0,'new',10,11);
INSERT INTO `MailBox` VALUES (6,'hhhhjj',0,'neeww',10,9);
INSERT INTO `MailBox` VALUES (7,'sprawdzam',0,'newwwdcwckckml',10,7);
INSERT INTO `MailBox` VALUES (8,'kddnwjwf',0,'test header',10,7);
INSERT INTO `MailBox` VALUES (9,'tresc wiadomosci',0,'to nowy naglowek',9,10);
INSERT INTO `MailBox` VALUES (10,'Dziala',0,'Co tam ',10,9);
INSERT INTO `MailBox` VALUES (11,'dsaac',0,'sadsdssa',2,10);
INSERT INTO `MailBox` VALUES (12,'dsaac',0,'sadsdssa',2,10);
INSERT INTO `MailBox` VALUES (13,'testnowy',1,'1131',10,9);
INSERT INTO `MailBox` VALUES (14,'content1132',1,'naglowek1',10,7);
INSERT INTO `MailBox` VALUES (15,'aga',1,'czesc',10,2);
INSERT INTO `MailBox` VALUES (16,'asia',1,'jo',9,10);
INSERT INTO `MailBox` VALUES (17,'1258',1,'1257',2,10);
INSERT INTO `MailBox` VALUES (18,'119',1,'nowaaaaanag',10,1);
INSERT INTO `MailBox` VALUES (19,'content test',1,'header127',3,10);
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
INSERT INTO `ArtifactsTable` VALUES ('private_mentoring',100,'mentoring',0);
INSERT INTO `ArtifactsTable` VALUES ('mentor_ride_on_elephant',200,'fun',0);
CREATE TABLE `ArtifactCategory`
(
`artifact_category_name`
TEXT
UNIQUE
);
INSERT INTO `ArtifactCategory` VALUES ('fun');
INSERT INTO `ArtifactCategory` VALUES ('mentoring');
COMMIT;
