-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Nov 13, 2020 at 07:15 PM
-- Server version: 5.7.30
-- PHP Version: 7.4.9

-- ----------------------------------------------
-- Nom de la base de donnees : jakarta_garros_db      --
-- SGBD : MySql                               --
-- Date de creation : 13 novembre 2020         --
-- Equipe : SBMAW
-- ----------------------------------------------

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

-- Destruction de la base si elle existe
DROP DATABASE IF EXISTS jakarta_garros_db;

-- Creation de la base si elle n'existe pas deja
CREATE DATABASE IF NOT EXISTS jakarta_garros_db;

-- Connexion BD
use jakarta_garros_db;

-- Destruction des tables si elles existent
DROP TABLE IF EXISTS `utilisateur`;
DROP TABLE IF EXISTS `arbitre`;
DROP TABLE IF EXISTS `court`;
DROP TABLE IF EXISTS `horaire_court`;
DROP TABLE IF EXISTS `joueur`;
DROP TABLE IF EXISTS `equipe`;
DROP TABLE IF EXISTS `constitution_equipe`;
DROP TABLE IF EXISTS `sous_tournoi`;
DROP TABLE IF EXISTS `detail_match`;
DROP TABLE IF EXISTS `participation_match`;
DROP TABLE IF EXISTS `reservation`;

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `motDePasse` varchar(64) NOT NULL,
  `role` enum('ADMIN','ORGANISATEUR') NOT NULL,
  CONSTRAINT PK_Utilisateur PRIMARY KEY(`idUtilisateur`),
  CONSTRAINT UC_Utilisateur UNIQUE(`login`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `arbitre`
--

CREATE TABLE `arbitre` (
  `idArbitre` int NOT NULL AUTO_INCREMENT,
  `nomArbitre` varchar(50) NOT NULL,
  `prenomArbitre` varchar(50) NOT NULL,
  `nationaliteArbitre` varchar(50) NOT NULL,
  CONSTRAINT PK_Arbitre PRIMARY KEY(`idArbitre`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `court`
--

CREATE TABLE `court` (
  `idCourt` int NOT NULL AUTO_INCREMENT,
  `nomCourt` varchar(50) NOT NULL,
  `zoneCourt` varchar(100) NOT NULL,
  `typeCourt` enum('DUR_EXTERIEUR','DUR_INTERIEUR','GAZON_EXTERIEUR','MOQUETTE_INTERIEUR','TERRE_BATTUE_EXTERIEUR','TERRE_BATTUE_INTERIEUR') NOT NULL,
  CONSTRAINT PK_Court PRIMARY KEY(`idCourt`),
  CONSTRAINT UC_Court UNIQUE(`nomCourt`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `horaire_court`
--

CREATE TABLE `horaire_court` (
  `idCourt` int NOT NULL,
  `jourOuverture` enum('LUNDI','MARDI','MERCREDI','JEUDI','VENDREDI','SAMEDI', 'DIMANCHE') NOT NULL,
  `horaireOuverture` time NOT NULL,
  `horaireFermeture` time NOT NULL,
  CONSTRAINT PK_Horaire_Court PRIMARY KEY(`idCourt`, `jourOuverture`, `horaireOuverture`, `horaireFermeture`),
  CONSTRAINT FK_Horaire_Court FOREIGN KEY(idCourt) REFERENCES `court`(`idCourt`) ON DELETE CASCADE
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `idJoueur` int NOT NULL AUTO_INCREMENT,
  `nomJoueur` varchar(50) NOT NULL,
  `prenomJoueur` varchar(50) NOT NULL,
  `nationaliteJoueur` varchar(50) NOT NULL,
  `sexe` enum('HOMME','FEMME') NOT NULL,
  `classementJoueur` int NOT NULL,
  CONSTRAINT PK_Joueur PRIMARY KEY(`idJoueur`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `idEquipe` int NOT NULL AUTO_INCREMENT,
  `colonneRemplissage` varchar(50) DEFAULT NULL,
  CONSTRAINT PK_Equipe PRIMARY KEY(`idEquipe`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `constitution_equipe`
--

CREATE TABLE `constitution_equipe` (
  `idEquipe` int NOT NULL AUTO_INCREMENT,
  `idJoueur` int NOT NULL,
  CONSTRAINT PK_Equipe PRIMARY KEY(`idEquipe`, `idJoueur`),
  CONSTRAINT FK_Joueur FOREIGN KEY(idJoueur) REFERENCES `joueur`(`idJoueur`) ON DELETE CASCADE,
  CONSTRAINT FK_Constitution_Equipe FOREIGN KEY(idEquipe) REFERENCES `equipe`(`idEquipe`) ON DELETE CASCADE
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `sous_tournoi`
--

CREATE TABLE `sous_tournoi` (
  `idSousTournoi` int NOT NULL AUTO_INCREMENT,
  `dateDebutSousTournoi` datetime DEFAULT NULL,
  `dateFinSousTournoi` datetime DEFAULT NULL,
  `estSousTournoiValide` tinyint(1) DEFAULT 0,
  `typeSousTournoi` enum('SIMPLE_MESSIEURS','SIMPLE_DAMES','DOUBLE_MESSIEURS','DOUBLE_DAMES','DOUBLE_MIXTE') NOT NULL,
  CONSTRAINT PK_Sous_Tournoi PRIMARY KEY(`idSousTournoi`),
  CONSTRAINT UC_Sous_Tournoi UNIQUE(`typeSousTournoi`)
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `detail_match`
--

CREATE TABLE `detail_match` (
	`idMatch` int NOT NULL AUTO_INCREMENT,
	`dateDebut` datetime NOT NULL,
	`estMatchValide` tinyint(1) DEFAULT 0,
	`idSousTournoi` int NOT NULL,
	`idArbitre` int NOT NULL,
	`duree` time DEFAULT NULL,
	`score1` int DEFAULT 0,
	`score2` int DEFAULT 0,
	`idCourt` int NOT NULL,
	CONSTRAINT PK_Detail_Match PRIMARY KEY(`idMatch`),
	CONSTRAINT FK_Sous_Tournoi FOREIGN KEY(idSousTournoi) REFERENCES `sous_tournoi`(`idSousTournoi`) ON DELETE CASCADE,
	CONSTRAINT FK_Arbitre FOREIGN KEY(idArbitre) REFERENCES `arbitre`(`idArbitre`),
  CONSTRAINT FK_Court_bis FOREIGN KEY(idCourt) REFERENCES `court`(`idCourt`) ON DELETE CASCADE
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8; 

--
-- Structure de la table `participation_match`
--

CREATE TABLE `participation_match` (
	`idMatch` int NOT NULL,
	`idEquipe` int NOT NULL,
	CONSTRAINT PK_Participation_Match PRIMARY KEY(`idMatch`, `idEquipe`),
	CONSTRAINT FK_Participation_Match FOREIGN KEY(idMatch) REFERENCES `detail_match`(`idMatch`) ON DELETE CASCADE,
	CONSTRAINT FK_Participation_Equipe FOREIGN KEY(idEquipe) REFERENCES `equipe`(`idEquipe`) ON DELETE CASCADE
) ENGINE = 'InnoDB' DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int NOT NULL AUTO_INCREMENT,
  `idCourt` int NOT NULL,
  `idMatch` int NOT NULL,
  `dateDebutRes` datetime NOT NULL,
  `dateFinRes` datetime NOT NULL,
  CONSTRAINT PK_Reservation PRIMARY KEY(`idReservation`, `idCourt`, `idMatch`, `dateDebutRes`, `dateFinRes`),
  CONSTRAINT FK_Court FOREIGN KEY(idCourt) REFERENCES `court`(`idCourt`) ON DELETE CASCADE,
  CONSTRAINT FK_Reservation_Match FOREIGN KEY(idMatch) REFERENCES `detail_match`(`idMatch`) ON DELETE CASCADE
) ENGINE= 'InnoDB' DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
