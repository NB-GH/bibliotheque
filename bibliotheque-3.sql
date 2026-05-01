SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque`
--
CREATE DATABASE IF NOT EXISTS bibliotheque;
USE bibliotheque;
-- --------------------------------------------------------

--
-- Structure de la table `Adherents`
--

CREATE TABLE `Adherents` (
  `adherent_id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `adresse` varchar(200) DEFAULT NULL,
  `date_inscription` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Adherents`
--

INSERT INTO `Adherents` (`adherent_id`, `nom`, `prenom`, `email`, `telephone`, `adresse`, `date_inscription`) VALUES
(1, 'Martin', 'Pierre', 'pierre.martin@example.com', '0612345678', '12 rue de Paris, 75000 Paris', '2023-01-15'),
(2, 'Durand', 'Marie', 'marie.durand@example.com', '0623456789', '45 avenue des Champs, 75001 Paris', '2023-02-20'),
(3, 'Dupont', 'Jean', 'jean.dupont@example.com', '0634567890', '78 boulevard Haussmann, 75008 Paris', '2023-03-10'),
(4, 'Lefèvre', 'Sophie', 'sophie.lefevre@example.com', '0645678901', '3 rue de la République, 69001 Lyon', '2023-04-05');

-- --------------------------------------------------------

--
-- Structure de la table `Emprunts`
--

CREATE TABLE `Emprunts` (
  `emprunt_id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `adherent_id` int(11) NOT NULL,
  `date_emprunt` date NOT NULL,
  `date_retour_prevue` date NOT NULL,
  `date_retour_reelle` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Emprunts`
--

INSERT INTO `Emprunts` (`emprunt_id`, `livre_id`, `adherent_id`, `date_emprunt`, `date_retour_prevue`, `date_retour_reelle`) VALUES
(1, 2, 2, '2024-01-10', '2024-02-10', NULL),
(2, 7, 1, '2024-02-01', '2024-03-01', '2024-02-28'),
(3, 8, 3, '2024-02-15', '2024-03-15', NULL),
(4, 4, 4, '2024-03-01', '2024-04-01', '2024-03-25'),
(5, 6, 1, '2026-03-19', '2026-04-02', NULL),
(6, 14, 4, '2026-03-19', '2026-04-02', NULL),
(7, 8, 2, '2026-03-26', '2026-04-09', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Livres`
--

CREATE TABLE `Livres` (
  `livre_id` int(11) NOT NULL,
  `titre` varchar(100) NOT NULL,
  `auteur` varchar(100) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT '1',
  `date_ajout` date DEFAULT NULL,
  `categorie` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Livres`
--

INSERT INTO `Livres` (`livre_id`, `titre`, `auteur`, `isbn`, `disponible`, `date_ajout`, `categorie`) VALUES
(1, 'Le Petit Prince', 'Antoine de Saint-Exupéry', '9782070612758', 1, NULL, 'Littérature jeunesse'),
(2, '1985', 'George Orwell', '9782070368228', 0, NULL, 'Science-fiction'),
(3, 'Harry Potter à l\'école des sorciers', 'J.K. Rowling', '9782070584628', 1, NULL, 'Fantastique'),
(4, 'Le Seigneur des Anneaux', 'J.R.R. Tolkien', '9782266282216', 1, NULL, 'Fantastique'),
(5, 'Bel-Ami', 'Guy de Maupassant', '9782070368235', 1, '2026-03-26', 'Classique'),
(6, 'Les Misérables', 'Victor Hugo', '9782070357024', 0, NULL, 'Classique'),
(7, 'Dune', 'Frank Herbert', '9782266279780', 0, NULL, 'Science-fiction'),
(8, 'Le Horlas', 'Guy de Maupassant', '9782070368242', 1, '2026-03-26', 'Nouvelle'),
(9, 'Voyage au centre de la Terre', 'Jules Verne', '9782070584635', 1, '2026-03-26', 'Aventure'),
(10, 'Orgueil et Préjugés', 'Jane Austen', '9782070368259', 1, NULL, 'Roman'),
(11, 'La Pest', 'Albert Camus', '9782070368266', 1, NULL, 'Philosophie'),
(14, 'Blabla', 'blabla', '11111', 1, NULL, 'Policier');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Adherents`
--
ALTER TABLE `Adherents`
  ADD PRIMARY KEY (`adherent_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `Emprunts`
--
ALTER TABLE `Emprunts`
  ADD PRIMARY KEY (`emprunt_id`),
  ADD KEY `livre_id` (`livre_id`),
  ADD KEY `adherent_id` (`adherent_id`);

--
-- Index pour la table `Livres`
--
ALTER TABLE `Livres`
  ADD PRIMARY KEY (`livre_id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Adherents`
--
ALTER TABLE `Adherents`
  MODIFY `adherent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `Emprunts`
--
ALTER TABLE `Emprunts`
  MODIFY `emprunt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `Livres`
--
ALTER TABLE `Livres`
  MODIFY `livre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Emprunts`
--
ALTER TABLE `Emprunts`
  ADD CONSTRAINT `emprunts_ibfk_1` FOREIGN KEY (`livre_id`) REFERENCES `Livres` (`livre_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `emprunts_ibfk_2` FOREIGN KEY (`adherent_id`) REFERENCES `Adherents` (`adherent_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
