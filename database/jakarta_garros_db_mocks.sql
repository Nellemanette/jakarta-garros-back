-- Connexion à la base
use jakarta_garros_db;

--
-- Insertion des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`login`, `motDePasse`, `role`) VALUES
('aurianne', '582fe20d3b8ebfcab67312ce9af09cd67233436bb8d122bf25fcbb9997c8dfaa', 'ADMIN'), -- mdp : auri
('sabrina', '09d1cc8ef8ec5964c6468e54454f72eebb041cbf3c4dc24d0af7dde176cdadc1', 'ORGANISATEUR'), -- mdp : sab
('mohamed', '4539b01521a8bbb57c5b4ed291d7afeda2524b9d5556e03ef639e24d5ce9ebd5', 'ORGANISATEUR'), -- mdp : momo
('brunelle', 'cc44bc69911b5fb43eed5657f272d5bc823bfdeb79e2b45a47859e41d6805347', 'ORGANISATEUR'), -- mdp : brubru
('wiem', '6ba328424b48ec2655c7fcda2b67477eded4b5071e61224ea7825d5d578db63e', 'ORGANISATEUR') -- mdp : wiwi
;

-- --------------------------------------------------------

--
-- Insertion des données de la table `arbitre`
--

INSERT INTO `arbitre` (`nomArbitre`, `prenomArbitre`, `nationaliteArbitre`) VALUES
('NILI', 'Ali', 'Iran'),
('BERNARDES', 'Carlos', 'Bresil'),
('DUMUSOIS', 'Damien', 'France'),
('MURPHY', 'Fergus', 'Irlande'),
('GARNER', 'Jake', 'Etats-Unis'),
('EL JENNATI', 'Mohamed', 'Maroc');

-- --------------------------------------------------------

--
-- Insertion des données de la table `court`
--

INSERT INTO `court` (`nomCourt`, `zoneCourt`, `typeCourt`) VALUES
('Philippe-Chartrier', 'Avenue Gordon-Bennett', 'TERRE_BATTUE_EXTERIEUR'),
('Suzanne-Lenglen', 'Avenue Gordon-Bennett', 'MOQUETTE_INTERIEUR'),
('Simonne-Mathieu', 'Jardin des serres d\'Auteuil', 'DUR_EXTERIEUR');

-- --------------------------------------------------------

--
-- Insertion des données de la table `horaire_court`
--

INSERT INTO `horaire_court` (`idCourt`,`jourOuverture`, `horaireOuverture`, `horaireFermeture`) VALUES
(1, 'LUNDI', '09:00:00', '21:00:00'),
(1, 'MARDI', '09:00:00', '21:00:00'),
(1, 'MERCREDI', '09:00:00', '21:00:00'),
(1, 'JEUDI', '09:00:00', '21:00:00'),
(1, 'VENDREDI', '09:00:00', '21:00:00'),
(1, 'SAMEDI', '09:00:00', '21:00:00'),
(2, 'LUNDI', '07:00:00', '22:30:00'),
(2, 'MARDI', '07:00:00', '22:30:00'),
(2, 'MERCREDI', '07:00:00', '22:30:00'),
(2, 'JEUDI', '07:00:00', '22:30:00'),
(2, 'VENDREDI', '07:00:00', '22:30:00'),
(2, 'SAMEDI', '07:00:00', '22:30:00'),
(2, 'DIMANCHE', '08:00:00', '18:30:00'),
(3, 'LUNDI', '08:00:00', '12:00:00'),
(3, 'LUNDI', '13:30:00', '23:30:00'),
(3, 'MARDI', '07:00:00', '11:00:00'),
(3, 'MARDI', '13:00:00', '23:30:00'),
(3, 'MERCREDI', '09:00:00', '13:30:00'),
(3, 'MERCREDI', '15:00:00', '22:00:00'),
(3, 'JEUDI', '08:00:00', '12:30:00'),
(3, 'JEUDI', '13:30:00', '23:00:00'),
(3, 'SAMEDI', '07:00:00', '12:00:00'),
(3, 'SAMEDI', '14:00:00', '00:00:00'),
(3, 'DIMANCHE', '08:00:00', '12:30:00')
;

-- --------------------------------------------------------

--
-- Insertion des données de la table `joueur`
--

INSERT INTO `joueur` (`nomJoueur`, `prenomJoueur`, `nationaliteJoueur`, `sexe`, `classementJoueur`) VALUES
('DJOKOVIC', 'Novak', 'Serbie', 'HOMME', 1),
('NADAL', 'Rafael', 'Espagne', 'HOMME', 2),
('THIEM', 'Dominic', 'Autriche', 'HOMME', 3),
('MEDVEDEV', 'Daniil', 'Russie', 'HOMME', 4),
('FEDERER', 'Roger', 'Suisse', 'HOMME', 5),
('BARTY', 'Ashleigh', 'Australie', 'FEMME', 1),
('HALEP', 'Simona', 'Roumanie', 'FEMME', 2),
('OSAKA', 'Naomi', 'Japon', 'FEMME', 3),
('KEKIN', 'Sofia', 'Etats-Unis', 'FEMME', 4),
('SVITOLINA', 'Elina', 'Ukraine', 'FEMME', 5),
('CECCHINATO', 'Marco', 'Italie', 'HOMME', 80), 
('JABEUR', 'Ons', 'Tunisie', 'FEMME', 31),
('TSITSIPAS', 'Stefanos', 'Grece', 'HOMME', 6), 
('WAWRINKA', 'Stan', 'Suisse', 'HOMME', 18),
('KEYS', 'Madison', 'Usa', 'FEMME', 16), 
('MERTENS', 'Elise', 'Belgique', 'FEMME', 20)
; 


INSERT INTO `sous_tournoi` (`typeSousTournoi`) VALUES
('SIMPLE_MESSIEURS'),
('SIMPLE_DAMES'),
('DOUBLE_MESSIEURS'),
('DOUBLE_DAMES'),
('DOUBLE_MIXTE')
;

-- --------------------------------------------------------

--
-- Insertion des données de la table `constitution_equipe`
--

INSERT INTO `equipe` (`colonneRemplissage`) VALUES 
(NULL),
(NULL),
(NULL),
(NULL),
(NULL),
(NULL),
(NULL),
(NULL),
(NULL),
(NULL)
;

-- --------------------------------------------------------

--
-- Insertion des données de la table `constitution_equipe`
--

INSERT INTO `constitution_equipe` (`idEquipe`, `idJoueur`) VALUES 
('1', '1'), 
('2', '2'), 
('3', '3'),
('3', '5'),
('4', '2'), 
('4', '11'),
('5', '6'), 
('6', '7'),
('7', '8'), 
('7', '9'),
('8', '10'), 
('8', '12'),
('9', '15'), 
('9', '14'),
('10', '16'), 
('10', '13')
;

-- --------------------------------------------------------

--
-- Insertion des données de la table `detail_match`
--

INSERT INTO `detail_match` (`dateDebut`, `estMatchValide`, `idSousTournoi`, `idArbitre`,`idCourt`) VALUES 
('2020-12-06 08:30:00', '0', '1', '1', 1), 
('2020-12-10 10:15:00', '0', '3', '2', 1),
('2020-12-07 14:00:00', '0', '2', '3', 1), 
('2020-12-12 09:00:00', '0', '4', '4', 1),
('2020-12-15 15:30:00', '0', '5', '5', 1)
;


-- --------------------------------------------------------

--
-- Insertion des données de la table `participation_match`
--

INSERT INTO `participation_match` (`idMatch`, `idEquipe`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10)
;