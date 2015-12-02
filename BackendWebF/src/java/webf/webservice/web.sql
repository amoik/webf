-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 02. Dez 2015 um 15:32
-- Server-Version: 10.0.21-MariaDB
-- PHP-Version: 5.6.15

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
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `course`
--

INSERT INTO `course` (`course_pk`, `title`, `description`) VALUES
(1, 'WEBF', 'Gaengige Webframeworks aus der Java Welt');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `person_pk` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `firstname` varchar(128) DEFAULT NULL,
  `lastname` varchar(128) DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`person_pk`, `username`, `password`, `role`, `firstname`, `lastname`, `birthday`) VALUES
(23, 'ich', '12', 'Admin', 'TestFirst', 'TestLast', '2015-12-02'),
(24, 'anti88', 'am', 'Lektor', 'TestFirst', 'TestLast', '2015-12-02'),
(28, 'pa15l01', '123', 'Student', 'Hansi', 'Bauer', '1970-01-01'),
(29, 'pa15l02', '123', 'Student', 'Peppi', 'Gruber', '1970-01-01'),
(30, 'pa15l03', '123', 'Student', 'Franzi', 'Fuchs', '1970-01-01');

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
(24, 1, 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_pk`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_pk`);

--
-- Indizes für die Tabelle `person_course_membership`
--
ALTER TABLE `person_course_membership`
  ADD PRIMARY KEY (`person_fk`,`course_fk`),
  ADD KEY `person_fk` (`person_fk`),
  ADD KEY `course_fk` (`course_fk`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `course`
--
ALTER TABLE `course`
  MODIFY `course_pk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `person_pk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `person_course_membership`
--
ALTER TABLE `person_course_membership`
  ADD CONSTRAINT `person_course_membership_ibfk_1` FOREIGN KEY (`person_fk`) REFERENCES `person` (`person_pk`),
  ADD CONSTRAINT `person_course_membership_ibfk_2` FOREIGN KEY (`course_fk`) REFERENCES `course` (`course_pk`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
