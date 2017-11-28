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
	`team_name`	TEXT DEFAULT null,
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
TEXT,
`voted`
TEXT
DEFAULT
'no'
);
INSERT INTO `StudentsTable` VALUES (3,470,0,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (4,600,1,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (8,140,0,'krk2017-1','no');
INSERT INTO `StudentsTable` VALUES (9,820,0,'krk2017-1','no');
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
INSERT INTO `SessionTable` VALUES ('ea81c25a-1fc9-4f81-aefe-5ec1e0cd5be4',3,'student','student',1);
INSERT INTO `SessionTable` VALUES ('87607d9a-f254-442e-aac6-5ba67a1190a5',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('4f3bf283-e8a4-48ee-836b-1290b6ebdc85',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('9d30963c-3cec-42f6-9316-39a2e6889ccc',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('086748d5-44b5-4a18-9fc7-5a467c8cff0e',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('3b7a8049-a399-4039-a509-71a2aadbf2e7',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('adbadaa2-5577-43a1-894f-944f935b8ddd',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('8f12b25e-f4aa-4549-b12f-24e29c8d8eb6',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('cbfe8ef0-6f00-4f2e-b2a6-dfc060c4f56b',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('1e8301db-58c2-4aca-bb8c-010e97f6de5b',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('e3a1fac7-59c1-4f88-be30-dd48cdcc54d8',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('5e6a2e6f-af79-4292-9e35-61e96211d4c1',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('f8856400-b13a-4711-897d-2c835047c39f',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('d3b72b76-b65b-417a-86bc-71ce6a8fe897',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('74d01158-d38c-4036-a40c-30c451d8af67',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('dc6ad54b-7fee-4ebe-aa98-6188f67b4323',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('17df3f36-2f08-4b64-81b3-350999384a32',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('eaa3a651-f4f0-4ac7-9ce2-5e245f1a9b33',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('f2dea10e-bba0-4f59-a7eb-3f65d06a8290',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('8faece6e-e2af-4c5a-93c8-f91b20fbfb57',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('6a9bd823-81c8-4109-9699-c3d5143a8d74',15,'mentor','mentor',0);
INSERT INTO `SessionTable` VALUES ('17823206-21bf-4599-82f8-573b2f6ad08c',15,'mentor','mentor',0);
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
INSERT INTO `MailBox` VALUES (20,'Hello
admin',1,'hello',6,10);
INSERT INTO `MailBox` VALUES (21,'michalina',1,'hello
admin',1,6);
INSERT INTO `MailBox` VALUES (22,'hello
student',1,'hello
student',10,3);
INSERT INTO `MailBox` VALUES (23,'student',1,'test
mail
to
student',3,10);
INSERT INTO `MailBox` VALUES (24,'pawel',1,'pawel',9,10);
INSERT INTO `MailBox` VALUES (25,'jedzie
na
qna',1,'pawel',3,10);
INSERT INTO `MailBox` VALUES (26,'Dear
Codecooler
,
Your
team
use
an
artifact:
null.
You
will
receive
detailed
information
soon
from
the
Mentor.
Regards,
Your
Mentor
',1,'Use
an
artifact',3,2);
INSERT INTO `MailBox` VALUES (27,'Dear
Codecooler
,
Your
team
use
an
artifact:
null.
You
will
receive
detailed
information
soon
from
the
Mentor.
Regards,
Your
Mentor
',1,'Use
an
artifact',4,2);
INSERT INTO `MailBox` VALUES (28,'Dear
Codecooler
,
Your
team
use
an
artifact:
null.
You
will
receive
detailed
information
soon
from
the
Mentor.
Regards,
Your
Mentor
',1,'Use
an
artifact',8,2);
INSERT INTO `MailBox` VALUES (29,'Dear
Codecooler
,
Your
team
use
an
artifact:
null.
You
will
receive
detailed
information
soon
from
the
Mentor.
Regards,
Your
Mentor
',1,'Use
an
artifact',9,2);
INSERT INTO `MailBox` VALUES (30,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',2,1);
INSERT INTO `MailBox` VALUES (31,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',13,1);
INSERT INTO `MailBox` VALUES (32,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',15,1);
INSERT INTO `MailBox` VALUES (33,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',2,1);
INSERT INTO `MailBox` VALUES (34,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',13,1);
INSERT INTO `MailBox` VALUES (35,'Dear
Mentors,
team
Dreem
Is
Green
buy
artifact:
private_mentoring.
Please
contact
the
team
to
discuss
the
purchase.
Regards
Admin',1,'New
group
purchase
from
Dreem
Is
Green
.',15,1);
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
