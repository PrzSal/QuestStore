BEGIN TRANSACTION;
CREATE TABLE "WalletTable" (
	`wallet_id`	INTEGER,
	`coolcoins`	INTEGER DEFAULT 1000,
	`user_id`	INTEGER UNIQUE,
	PRIMARY KEY(`wallet_id`)
);
INSERT INTO `WalletTable` VALUES (1,1000,3);
INSERT INTO `WalletTable` VALUES (2,1000,4);
INSERT INTO `WalletTable` VALUES (3,1000,8);
INSERT INTO `WalletTable` VALUES (4,1000,9);
CREATE TABLE "UsersTable" (
	`user_id`	INTEGER NOT NULL UNIQUE,
	`name`	TEXT,
	`surname`	TEXT,
	`email`	TEXT UNIQUE,
	`login`	TEXT UNIQUE,
	`password`	TEXT,
	`user_type`	TEXT,
	PRIMARY KEY(`user_id`)
);
INSERT INTO `UsersTable` VALUES (1,'Jan','Kowalski','j.kowalski@gmail.com','mentor','mentor','mentor');
INSERT INTO `UsersTable` VALUES (2,'Agnieszka','Koszany','a.koszany','akosz','akoszany','mentor');
INSERT INTO `UsersTable` VALUES (3,'Arek','Haffney','a.haffney','student','student','student');
INSERT INTO `UsersTable` VALUES (4,'Krzysztof','Knutse','k.knutse','knutse','a912','student');
INSERT INTO `UsersTable` VALUES (5,'Bob','Studer','b.stude','bob','stude','mentor');
INSERT INTO `UsersTable` VALUES (6,'Jurek','Magnus','j.magnu','jurek','magnus','admin');
INSERT INTO `UsersTable` VALUES (7,'Asia','Kowalska','a.dobra','asia','kowalsk','mentor');
INSERT INTO `UsersTable` VALUES (8,'Diana','Dab','d.dab','diana','dab','student');
INSERT INTO `UsersTable` VALUES (9,'Joasia','Szymanska','j.szymanska','joasia','szym','student');
INSERT INTO `UsersTable` VALUES (10,'Michalina','Borek','m.borek','admin','admin','admin');
CREATE TABLE "TeamsTable" (
	`team_name`	INTEGER UNIQUE
);
INSERT INTO `TeamsTable` VALUES ('new');
INSERT INTO `TeamsTable` VALUES ('old');
INSERT INTO `TeamsTable` VALUES ('young');
CREATE TABLE "StudentsWithQuests" (
	`quest_name`	INTEGER NOT NULL,
	`quest_category`	TEXT,
	`price`	INTEGER,
	`title`	INTEGER,
	`user_id`	INTEGER NOT NULL
);
CREATE TABLE "StudentsWithArtifacts" (
	`artifact_name`	INTEGER NOT NULL,
	`title`	INTEGER,
	`price`	INTEGER,
	`artifact_category`	TEXT,
	`user_id`	INTEGER
);
CREATE TABLE "StudentsTable" (
	`user_id`	INTEGER NOT NULL UNIQUE,
	`experience`	INTEGER DEFAULT 0,
	`level_name`	TEXT,
	`team_id`	INTEGER,
	`class_name`	TEXT
);
INSERT INTO `StudentsTable` VALUES (3,0,'noob','new','krk17');
INSERT INTO `StudentsTable` VALUES (4,0,'noob','old','krk16');
INSERT INTO `StudentsTable` VALUES (8,0,'noob','old','krk17');
INSERT INTO `StudentsTable` VALUES (9,0,'noob','young','krk17');
CREATE TABLE "QuestsTable" (
	`quest_name`	TEXT NOT NULL UNIQUE,
	`price`	INTEGER NOT NULL,
	`quest_category`	TEXT NOT NULL
);
INSERT INTO `QuestsTable` VALUES ('pass si week',100,'fun');
INSERT INTO `QuestsTable` VALUES ('take part in reqruit',400,'mentoring');
CREATE TABLE "QuestCategory" (
	`quest_category_name`	TEXT NOT NULL UNIQUE
);
INSERT INTO `QuestCategory` VALUES ('fun');
INSERT INTO `QuestCategory` VALUES ('mentoring');
CREATE TABLE "MentorsTable" (
	`user_id`	INTEGER NOT NULL UNIQUE,
	`class_name`	TEXT
);
INSERT INTO `MentorsTable` VALUES (2,'krk17');
INSERT INTO `MentorsTable` VALUES (5,'krk17');
INSERT INTO `MentorsTable` VALUES (7,'krk17');
INSERT INTO `MentorsTable` VALUES (1,'krk17');
CREATE TABLE "LevelsTable" (
	`level_name`	TEXT NOT NULL UNIQUE,
	`min`	INTEGER NOT NULL
);
INSERT INTO `LevelsTable` VALUES ('noob',0);
INSERT INTO `LevelsTable` VALUES ('beginner',400);
INSERT INTO `LevelsTable` VALUES ('medium',600);
INSERT INTO `LevelsTable` VALUES ('java guru',1000);
INSERT INTO `LevelsTable` VALUES ('replace mentor','22 500');
CREATE TABLE "ClassTable" (
	`class_name`	TEXT NOT NULL UNIQUE
);
INSERT INTO `ClassTable` VALUES ('krk17');
INSERT INTO `ClassTable` VALUES ('krk16');
CREATE TABLE "ArtifactsTable" (
	`artifact_name`	TEXT NOT NULL UNIQUE,
	`price`	INTEGER,
	`artifact_category`	INTEGER
);
INSERT INTO `ArtifactsTable` VALUES ('private mentoring',100,'mentoring');
INSERT INTO `ArtifactsTable` VALUES ('mentor ride on elephant',200,'fun');
CREATE TABLE `ArtifactCategory` (
	`artifact_category_name`	TEXT UNIQUE
);
INSERT INTO `ArtifactCategory` VALUES ('fun');
INSERT INTO `ArtifactCategory` VALUES ('mentoring');
COMMIT;