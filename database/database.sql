-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;

/*!50503 SET NAMES utf8 */
;

/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
;

/*!40103 SET TIME_ZONE='+00:00' */
;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */
;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
;

/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */
;

--
-- Table structure for table `book`
--
DROP TABLE IF EXISTS `book`;

/*!40101 SET @saved_cs_client     = @@character_set_client */
;

/*!50503 SET character_set_client = utf8mb4 */
;

CREATE TABLE `book` (
  `bookId` int(11) DEFAULT NULL,
  `title` text DEFAULT NULL,
  `ISBN_Number` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `relaese_Date` text DEFAULT NULL,
  `coverImg` text DEFAULT NULL,
  `availableDate` text DEFAULT NULL,
  `lendDate` text DEFAULT NULL,
  `author` text DEFAULT NULL,
  `lendBy` int(11) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `book`
--
LOCK TABLES `book` WRITE;

/*!40000 ALTER TABLE `book` DISABLE KEYS */
;

INSERT INTO
  `book`
VALUES
  (
    1,
    'Konosuba',
    2,
    'Kazuma Satou lives a laughable and pathetic life, being a shut-in NEET with no distinguishable qualities other than an addiction to video games. On his way home, Kazuma dies trying to save a girl from an oncoming truck—or so he believes. In reality, the \"truck\" was a slow-moving tractor, and he merely died from shock.',
    0,
    '2013-10-01',
    'konosuba.jpg',
    '2024-07-21',
    '2024-06-21',
    'Natsume Akatsuki',
    1
  ),
(
    2,
    'Dragon Ball',
    3600604,
    'Bulma, a headstrong 16-year-old girl, is on a quest to find the mythical Dragon Balls—seven scattered magic orbs that grant the finder a single wish. She has but one desire in mind: a perfect boyfriend. On her journey, Bulma stumbles upon Gokuu Son, a powerful orphan who has only ever known one human besides her. Gokuu possesses one of the Dragon Balls, it being a memento from his late grandfather. In exchange for it, Bulma invites Gokuu to be a companion in her travels.',
    0,
    '1984-11-20',
    'dragonBall.jpg',
    '2024-07-21',
    '2024-06-21',
    'Akira Toriyama',
    1
  ),
(
    3,
    'Naruto',
    1978063,
    'Whenever Naruto Uzumaki proclaims that he will someday become the Hokage—a title bestowed upon the best ninja in the Village Hidden in the Leaves—no one takes him seriously. Since birth, Naruto has been shunned and ridiculed by his fellow villagers. But their contempt isn\'t because Naruto is loud-mouthed, mischievous, or because of his ineptitude in the ninja arts, but because there is a demon inside him. Prior to Naruto\'s birth, the powerful and deadly Nine-Tailed Fox attacked the village. In order to stop the rampage, the Fourth Hokage sacrificed his life to seal the demon inside the body of the newborn Naruto.',
    1,
    '1999-09-21',
    'naruto.jpg',
    '',
    '',
    'Masashi Kishimoto',
    0
  ),
(
    4,
    'One Piece',
    8345354,
    'Gol D. Roger, a man referred to as the \"King of the Pirates,\" is set to be executed by the World Government. But just before his demise, he confirms the existence of a great treasure, One Piece, located somewhere within the vast ocean known as the Grand Line. Announcing that One Piece can be claimed by anyone worthy enough to reach it, the King of the Pirates is executed and the Great Age of Pirates begins.',
    1,
    '1997-06-22',
    'onePiece.jpg',
    '',
    '',
    'Eichiro Oda',
    0
  ),
(
    5,
    'Full Metal Alchemist',
    2625068,
    'Alchemists are knowledgeable and naturally talented individuals who can manipulate and modify matter due to their art. Yet despite the wide range of possibilities, alchemy is not as all-powerful as most would believe. Human transmutation is strictly forbidden, and whoever attempts it risks severe consequences. Even so, siblings Edward and Alphonse Elric decide to ignore this great taboo and bring their mother back to life. Unfortunately, not only do they fail in resurrecting her, they also pay an extremely high price for their arrogance: Edward loses his left leg and Alphonse his entire body. Furthermore, Edward also gives up his right arm in order to seal his brother\'s soul into a suit of armor.',
    1,
    '2001-06-12',
    'fma.jpg',
    '2024-07-21',
    '2024-06-21',
    'Hiromu Arakawa',
    0
  ),
(
    6,
    'Death Note',
    3241920,
    'Ryuk, a god of death, drops his Death Note into the human world for personal pleasure. In Japan, prodigious high school student Light Yagami stumbles upon it. Inside the notebook, he finds a chilling message: those whose names are written in it shall die. Its nonsensical nature amuses Light; but when he tests its power by writing the name of a criminal in it, they suddenly meet their demise.',
    1,
    '2003-12-01',
    'deathNote.jpg',
    '',
    '',
    'Tsugumi Oba',
    0
  ),
(
    7,
    'Bleach',
    6843350,
    'For as long as he can remember, high school student Ichigo Kurosaki has been able to see the spirits of the dead, but that has not stopped him from leading an ordinary life. One day, Ichigo returns home to find an intruder in his room who introduces herself as Rukia Kuchiki, a Soul Reaper tasked with helping souls pass over. Suddenly, the two are jolted from their conversation when a Hollow—an evil spirit known for consuming souls—attacks. As Ichigo makes a brash attempt to stop the Hollow, Rukia steps in and shields him from a counterattack. Injured and unable to keep fighting, Rukia suggests a risky plan—transfer half of her Soul Reaper powers to Ichigo. He accepts and, to Rukia\'s surprise, ends up absorbing her powers entirely, allowing him to easily dispatch the Hollow.',
    1,
    '2001-08-07',
    'bleach.jpg',
    '0000-00-00',
    '0000-00-00',
    'Tite Kubo',
    0
  ),
(
    8,
    'Tokyo Ghoul',
    7498344,
    'Lurking within the shadows of Tokyo are frightening beings known as \"ghouls,\" who satisfy their hunger by feeding on humans once night falls. An organization known as the Commission of Counter Ghoul (CCG) has been established in response to the constant attacks on citizens and as a means of purging these creatures. However, the problem lies in identifying ghouls as they disguise themselves as humans, living amongst the masses so that hunting prey will be easier. Ken Kaneki, an unsuspecting university freshman, finds himself caught in a world between humans and ghouls when his date turns out to be a ghoul after his flesh.',
    1,
    '2011-09-08',
    'tokyoGhoul.jpg',
    '0000-00-00',
    '0000-00-00',
    'Sui Ishida',
    0
  ),
(
    9,
    'Demon Slayer',
    4211877,
    'Tanjirou Kamado lives with his impoverished family on a remote mountain. As the oldest sibling, he took upon the responsibility of ensuring his family\'s livelihood after the death of his father. On a cold winter day, he goes down to the local village in order to sell some charcoal. As dusk falls, he is forced to spend the night in the house of a curious man who cautions him of strange creatures that roam the night: malevolent demons who crave human flesh.',
    1,
    '2016-02-15',
    'demonSlayer.jpg',
    '0000-00-00',
    '0000-00-00',
    'Koyoharu Gotouge',
    0
  ),
(
    10,
    'Jujutsu Kaisen',
    5510018,
    'Hidden in plain sight, an age-old conflict rages on. Supernatural monsters known as \"Curses\" terrorize humanity from the shadows, and powerful humans known as \"Jujutsu\" sorcerers use mystical arts to exterminate them. When high school student Yuuji Itadori finds a dried-up finger of the legendary Curse Sukuna Ryoumen, he suddenly finds himself joining this bloody conflict.',
    1,
    '2018-03-05',
    'jujutsuKaisen.jpg',
    '0000-00-00',
    '0000-00-00',
    'Gege Akutami',
    0
  ),
(
    11,
    'Berserk',
    9817312,
    'Guts, a former mercenary now known as the \"Black Swordsman,\" is out for revenge. After a tumultuous childhood, he finally finds someone he respects and believes he can trust, only to have everything fall apart when this person takes away everything important to Guts for the purpose of fulfilling his own desires. Now marked for death, Guts becomes condemned to a fate in which he is relentlessly pursued by demonic beings.',
    1,
    '1989-08-25',
    'berserk.jpg',
    '0000-00-00',
    '0000-00-00',
    'Kentarou Miura',
    0
  ),
(
    12,
    'Mushoku Tensei',
    9223726,
    'Killed while saving a stranger from a traffic collision, a 34-year-old NEET is reincarnated into a world of magic as Rudeus Greyrat, a newborn baby. With knowledge, experience, and regrets from his previous life retained, Rudeus vows to lead a fulfilling life and not repeat his past mistakes.',
    1,
    '2014-01-23',
    'mushokuTensei.jpg',
    '0000-00-00',
    '0000-00-00',
    'Rifujin no Magonote',
    0
  ),
(
    13,
    'Attack on Titan',
    1787849,
    'Hundreds of years ago, horrifying creatures which resembled humans appeared. These mindless, towering giants, called Titans, proved to be an existential threat, as they preyed on whatever humans they could find in order to satisfy a seemingly unending appetite. Unable to effectively combat the Titans, mankind was forced to barricade themselves within large walls surrounding what may very well be humanity\'s last safe haven in the world.',
    1,
    '2009-09-09',
    'attackOnTitan.jpg',
    '0000-00-00',
    '0000-00-00',
    'Hajime Isayama',
    0
  ),
(
    14,
    'Vinland Saga',
    2666425,
    'Thorfinn, son of one of the Vikings\' greatest warriors, is among the finest fighters in the merry band of mercenaries run by the cunning Askeladd, an impressive feat for a person his age. However, Thorfinn is not part of the group for the plunder it entails—instead, for having caused his family great tragedy, the boy has vowed to kill Askeladd in a fair duel. Not yet skilled enough to defeat him, but unable to abandon his vengeance, Thorfinn spends his boyhood with the mercenary crew, honing his skills on the battlefield among the war-loving Danes, where killing is just another pleasure of life.',
    1,
    '2005-04-13',
    'vinlandSaga.jpg',
    '',
    '',
    'Makoto Yukimura',
    0
  ),
(
    15,
    'My Hero Academia',
    1916255,
    'One day, a four-year-old boy came to a sudden realization: the world is not fair. Eighty percent of the world\'s population wield special abilities, known as \"quirks,\" which have given many the power to make their childhood dreams of becoming a superhero a reality. Unfortunately, Izuku Midoriya was one of the few born without a quirk, suffering from discrimination because of it. Yet, he refuses to give up on his dream of becoming a hero; determined to do the impossible, Izuku sets his sights on the elite hero training academy, UA High.',
    1,
    '2014-06-07',
    'myHero.jpg',
    '0000-00-00',
    '0000-00-00',
    'Kouhei Horikoshi',
    0
  ),
(
    16,
    'One Punch Man',
    6676766,
    'After rigorously training for three years, the ordinary Saitama has gained immense strength which allows him to take out anyone and anything with just one punch. He decides to put his new skill to good use by becoming a hero. However, he quickly becomes bored with easily defeating monsters, and wants someone to give him a challenge to bring back the spark of being a hero.',
    1,
    '2012-06-14',
    'onePunchMan.jpg',
    '0000-00-00',
    '0000-00-00',
    'Yusuke Murata',
    0
  ),
(
    17,
    'Kaguya-Sama Love is War',
    8909777,
    'Considered a genius due to having the highest grades in the country, Miyuki Shirogane leads the prestigious Shuchiin Academy\'s student council as its president, working alongside the beautiful and wealthy vice president Kaguya Shinomiya. The two are often regarded as the perfect couple by students despite them not being in any sort of romantic relationship.',
    1,
    '2015-05-19',
    'kaguyaSama.jpg',
    '0000-00-00',
    '0000-00-00',
    'Aka Akasaka',
    0
  ),
(
    18,
    'Sousou no Frieren',
    5026384,
    'The Demon King has been defeated, and the victorious hero party returns home before disbanding. The four—mage Frieren, hero Himmel, priest Heiter, and warrior Eisen—reminisce about their decade-long journey as the moment to bid each other farewell arrives. But the passing of time is different for elves, thus Frieren witnesses her companions slowly pass away one by one.',
    1,
    '2020-04-28',
    'frieren.jpg',
    '0000-00-00',
    '0000-00-00',
    'Kanehito Yamada',
    0
  ),
(
    19,
    'Chainsaw Man',
    1017208,
    'Denji has a simple dream—to live a happy and peaceful life, spending time with a girl he likes. This is a far cry from reality, however, as Denji is forced by the yakuza into killing devils in order to pay off his crushing debts. Using his pet devil Pochita as a weapon, he is ready to do anything for a bit of cash.',
    1,
    '2018-12-03',
    'chainsawMan.jpg',
    '',
    '',
    'Tatsuki Fujimoto',
    0
  ),
(
    20,
    'Dragon Ball Super',
    6857067,
    'Having defeated Boo, Goku is starting to get bored with his life on Earth. His wife, Chi-chi, wants him to get a job, but all he wants to do is train and fight stronger enemies. Elsewhere in the universe, the God of Destruction, Beerus, and his attendant Whis are traveling from planet to planet in search of food and entertainment. After blowing up some hapless victims, Beerus is reminded of a man from his dreams with the moniker \"Super Saiyan God,\" or something like that... The God of Destruction sets his sights on Earth to track down this mysterious man! Maybe this will give Goku something to do?',
    1,
    '2015-06-20',
    'dragonBallSuper.jpg',
    '',
    '',
    'Akira Toriyama',
    0
  ),
(
    21,
    'Boruto: Two Blue Vortex',
    7279075,
    'With everyone\'s memories having been altered, Boruto finds himself being hunted by his own village. After escaping with Sasuke, what future awaits Boruto...?',
    1,
    '2023-08-21',
    'boruto.jpg',
    '0000-00-00',
    '0000-00-00',
    'Masashi Kishimoto',
    0
  ),
(
    22,
    'Boruto: Naruto Next Generations	',
    2738047,
    ' Naruto Uzumaki has finally achieved his dream of becoming Hokage, the leader of the Hidden Leaf Village that he spent his teenage years fighting to protect. Naruto and his peers now live in a world of peace, working hard to uphold an international truce built on good will and diplomacy. However, this stasis comes at a personal cost for the aging hero. Naruto and the shinobi he grew up alongside find that working to upkeep the neutral world takes them away from their families, and even the legendary warriors of Naruto\'s generation must contend with being mediocre parents to their bitter children, including his own son Boruto. Boruto Uzumaki faces a world completely unlike that of his father, finding unique trouble in the distance between the two. Contending with a society that heaps an unbearable load of pressure on his shoulders over his status as the Hokage\'s son, Boruto carves his own path through the world, fighting to make a name for himself as evil forces theaten to shatter the peace his father helped create.',
    1,
    '2016-05-09',
    'borutoOrigin.jpg',
    NULL,
    NULL,
    'Masashi Kishimoto',
    NULL
  );

/*!40000 ALTER TABLE `book` ENABLE KEYS */
;

UNLOCK TABLES;

--
-- Table structure for table `lendhistory`
--
DROP TABLE IF EXISTS `lendhistory`;

/*!40101 SET @saved_cs_client     = @@character_set_client */
;

/*!50503 SET character_set_client = utf8mb4 */
;

CREATE TABLE `lendhistory` (
  `simpleId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `lendId` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `bookTitle` int(11) DEFAULT NULL,
  PRIMARY KEY (`simpleId`)
) ENGINE = InnoDB AUTO_INCREMENT = 35 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `lendhistory`
--
LOCK TABLES `lendhistory` WRITE;

/*!40000 ALTER TABLE `lendhistory` DISABLE KEYS */
;

INSERT INTO
  `lendhistory`
VALUES
  (18, 1, 5399856, '2024-06-19', '2024-06-19', 19),
(19, 1, 3301289, '2024-06-19', '2024-06-20', 20),
(20, 1, 3059408, '2024-06-20', '2024-06-21', 2),
(21, 1, NULL, '2024-06-21', '2024-06-21', 1),
(25, 1, NULL, '2024-06-21', '2024-06-21', 4),
(29, 1, NULL, '2024-06-21', '2024-06-21', 1),
(30, 1, NULL, '2024-06-21', '2024-06-21', 1),
(31, 1, NULL, '2024-06-21', '2024-06-21', 1),
(32, 1, NULL, '2024-06-21', '2024-06-21', 6),
(33, 1, NULL, '2024-06-21', '2024-06-21', 1),
(34, 1, NULL, '2024-06-21', NULL, 2);

/*!40000 ALTER TABLE `lendhistory` ENABLE KEYS */
;

UNLOCK TABLES;

--
-- Table structure for table `lendtracker`
--
DROP TABLE IF EXISTS `lendtracker`;

/*!40101 SET @saved_cs_client     = @@character_set_client */
;

/*!50503 SET character_set_client = utf8mb4 */
;

CREATE TABLE `lendtracker` (
  `simpleId` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `finish` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`simpleId`)
) ENGINE = InnoDB AUTO_INCREMENT = 26 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `lendtracker`
--
LOCK TABLES `lendtracker` WRITE;

/*!40000 ALTER TABLE `lendtracker` DISABLE KEYS */
;

INSERT INTO
  `lendtracker`
VALUES
  (10, 19, 1, '2024-06-19', '2024-07-19', 1),
(11, 20, 1, '2024-06-19', '2024-07-20', 1),
(12, 2, 1, '2024-06-20', '2024-06-21', 1),
(16, 4, 1, '2024-06-21', '2024-06-21', 1),
(23, 6, 1, '2024-06-21', '2024-06-21', 1),
(25, 2, 1, '2024-06-21', NULL, NULL);

/*!40000 ALTER TABLE `lendtracker` ENABLE KEYS */
;

UNLOCK TABLES;

--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;

/*!40101 SET @saved_cs_client     = @@character_set_client */
;

/*!50503 SET character_set_client = utf8mb4 */
;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `numberOfLendedBook` int(11) DEFAULT NULL,
  `admin` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`userId`)
) ENGINE = InnoDB AUTO_INCREMENT = 31 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;

/*!40000 ALTER TABLE `user` DISABLE KEYS */
;

INSERT INTO
  `user`
VALUES
  (
    1,
    'Hbence10',
    'asd',
    'sulisdolgok8@gmail.com',
    NULL,
    1
  ),
(
    30,
    'asd',
    'Asdasd1.',
    'sulisdolgok8@gmail.com',
    NULL,
    0
  );

/*!40000 ALTER TABLE `user` ENABLE KEYS */
;

UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */
;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */
;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */
;

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */
;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */
;

-- Dump completed on 2024-06-22 22:17:21