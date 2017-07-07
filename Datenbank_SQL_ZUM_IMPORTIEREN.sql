-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 07. Jul 2017 um 10:03
-- Server-Version: 10.1.19-MariaDB
-- PHP-Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `beschwerdemanagement`
--
CREATE DATABASE IF NOT EXISTS `beschwerdemanagement` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `beschwerdemanagement`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `antworten`
--

CREATE TABLE `antworten` (
  `id` int(11) NOT NULL,
  `t_id` int(11) NOT NULL,
  `ma_id` int(11) NOT NULL,
  `text` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `antworten`
--

INSERT INTO `antworten` (`id`, `t_id`, `ma_id`, `text`) VALUES
(1, 1, 1, 'Haben Sie den Computer schon einmal neu gestartet?'),
(2, 1, 2, 'Sind alle Stecker am Computer angeschlossen? :)'),
(3, 2, 3, 'Ich habe ihr Internet neu installiert.'),
(4, 3, 2, 'Haben Sie den Computer schon einmal neu gestartet?'),
(5, 3, 1, 'Sind alle Stecker am Computer angeschlossen? :)'),
(6, 4, 1, 'Ich habe ihr Internet neu installiert.');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunden`
--

CREATE TABLE `kunden` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `kunden`
--

INSERT INTO `kunden` (`id`, `name`) VALUES
(1, 'Peter Lustig'),
(2, 'Susanne Summ'),
(3, 'Werner Weißnix'),
(4, 'Dieter Dumm'),
(5, 'Klaus Kaputt');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mitarbeiter`
--

CREATE TABLE `mitarbeiter` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `mitarbeiter`
--

INSERT INTO `mitarbeiter` (`id`, `name`) VALUES
(1, 'Simon Schlau'),
(2, 'Karsten Klug'),
(3, 'Gernot Genial'),
(4, 'Ivonne Intellent');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `k_id` int(11) NOT NULL,
  `ma_id` int(11) NOT NULL,
  `erstellung` datetime NOT NULL,
  `letzte_bearbeitung` datetime NOT NULL,
  `text` text COLLATE utf8_unicode_ci NOT NULL,
  `geschlossen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `tickets`
--

INSERT INTO `tickets` (`id`, `k_id`, `ma_id`, `erstellung`, `letzte_bearbeitung`, `text`, `geschlossen`) VALUES
(1, 1, 2, '2017-06-22 20:25:05', '2017-06-22 20:25:05', 'Mein Computer funktioniert nicht.', 1),
(2, 4, 3, '2017-06-22 20:25:06', '2017-06-22 20:25:06', 'Mein Internet wurde gelöscht', 1),
(3, 5, 1, '2017-06-22 20:25:10', '2017-06-22 20:25:10', 'Mein Computer funktioniert nicht.', 1),
(4, 1, 1, '2017-06-22 20:25:10', '2017-06-22 20:25:10', 'Mein Internet wurde gelöscht', 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `antworten`
--
ALTER TABLE `antworten`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `kunden`
--
ALTER TABLE `kunden`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `antworten`
--
ALTER TABLE `antworten`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT für Tabelle `kunden`
--
ALTER TABLE `kunden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT für Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
