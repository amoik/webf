-- phpMyAdmin SQL Dump
-- version 4.5.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 07. Jan 2016 um 16:20
-- Server-Version: 10.0.21-MariaDB
-- PHP-Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `web`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `course`
--

CREATE TABLE `course` (
  `course_pk` int(11) NOT NULL,
  `title` varchar(30) NOT NULL,
  `description` varchar(500) NOT NULL,
  `lector_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `course`
--

INSERT INTO `course` (`course_pk`, `title`, `description`, `lector_id`) VALUES
(1, 'WEBF', 'Gängige Webframeworks aus der Java Welt', 169),
(8, 'CBAS', 'Grundlegende Eigenschaften der Programmiersprache C', 170),
(11, 'TestTitel', 'Nur ein Test', 170),
(15, 'CROSS', 'Crossplatform heislbesn', 170),
(16, 'test', 'test', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `person_pk` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstname` varchar(128) DEFAULT NULL,
  `lastname` varchar(128) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`person_pk`, `username`, `password`, `firstname`, `lastname`, `birthday`, `role_id`) VALUES
(1, 'anti88', 'am', 'Andreas', 'Moik', '1988-04-13', 1),
(2, 'pa14l014', 'am', 'Andreas', 'Moik', '1988-04-13', 3),
(3, 'pa14l013', 'sf', 'Stefan', 'Forster', '1987-01-31', 3),
(169, 'moik', 'am', 'Andreas', 'Moik', '1988-04-13', 2),
(170, 'TestLektor', 'tl', 'Test', 'Lektor', '2016-01-31', 2),
(171, 'heislbesn', 'ah', 'heisl', 'besn', '2016-01-25', 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_course_membership`
--

CREATE TABLE `person_course_membership` (
  `person_fk` int(11) NOT NULL,
  `course_fk` int(11) NOT NULL,
  `note` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `person_course_membership`
--

INSERT INTO `person_course_membership` (`person_fk`, `course_fk`, `note`) VALUES
(2, 1, 4),
(2, 11, 3),
(3, 1, 0),
(3, 11, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `role`
--

CREATE TABLE `role` (
  `title` varchar(32) DEFAULT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `role`
--

INSERT INTO `role` (`title`, `role_id`) VALUES
('ERROR', 0),
('ADMIN', 1),
('Lektor', 2),
('Student', 3),
('Assistenz', 4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_pk`),
  ADD KEY `course_ibfk_1` (`lector_id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_pk`),
  ADD KEY `role_id` (`role_id`);

--
-- Indizes für die Tabelle `person_course_membership`
--
ALTER TABLE `person_course_membership`
  ADD PRIMARY KEY (`person_fk`,`course_fk`),
  ADD KEY `person_fk` (`person_fk`),
  ADD KEY `course_fk` (`course_fk`);

--
-- Indizes für die Tabelle `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `course`
--
ALTER TABLE `course`
  MODIFY `course_pk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `person_pk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=173;
--
-- AUTO_INCREMENT für Tabelle `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`lector_id`) REFERENCES `person` (`person_pk`);

--
-- Constraints der Tabelle `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `person_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

--
-- Constraints der Tabelle `person_course_membership`
--
ALTER TABLE `person_course_membership`
  ADD CONSTRAINT `person_course_membership_ibfk_1` FOREIGN KEY (`person_fk`) REFERENCES `person` (`person_pk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_course_membership_ibfk_2` FOREIGN KEY (`course_fk`) REFERENCES `course` (`course_pk`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
