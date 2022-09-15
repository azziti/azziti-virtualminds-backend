-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 15 sep. 2022 à 15:01
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `virtual-minds`
--

-- --------------------------------------------------------

--
-- Structure de la table `caisse`
--

CREATE TABLE `caisse` (
  `caisseid` bigint(20) NOT NULL,
  `recettes` double NOT NULL,
  `depenses` double NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  `operation_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `caisse`
--

INSERT INTO `caisse` (`caisseid`, `recettes`, `depenses`, `created_at`, `libelle`, `operation_date`) VALUES
(1, 103.41, 0, '2022-09-14 15:59:13', 'Recettes ESP', '2022-09-01 02:00:00'),
(2, 91.85, 0, '2022-09-14 16:07:03', 'Recettes ESP', '2022-09-02 02:00:00'),
(3, 135.34, 0, '2022-09-14 16:08:07', 'Recettes ESP', '2022-09-03 02:00:00'),
(4, 0, 42.5, '2022-09-14 16:09:19', 'Règlement Fournisseur X', '2022-09-03 02:00:00'),
(5, 87.96, 0, '2022-09-14 16:17:46', 'Recettes ESP', '2022-09-04 02:00:00'),
(6, 143.77, 0, '2022-09-14 16:18:22', 'Recettes ESP', '2022-09-05 02:00:00'),
(7, 117.27, 0, '2022-09-14 16:23:46', 'Recettes ESP', '2022-09-07 02:00:00'),
(8, 0, 700, '2022-09-14 16:25:10', 'Dépôt d\'espèces', '2022-09-07 02:00:00'),
(9, 78.91, 0, '2022-09-14 16:32:25', 'Recettes ESP', '2022-09-08 02:00:00'),
(10, 0, 50, '2022-09-14 16:33:08', 'Règlement Fournisseur Y', '2022-09-08 02:00:00'),
(11, 104.68, 0, '2022-09-14 16:33:52', 'Recettes ESP', '2022-09-09 02:00:00'),
(12, 71.61, 0, '2022-09-14 16:39:45', 'Recettes ESP', '2022-09-10 02:00:00'),
(13, 85.16, 0, '2022-09-14 16:40:23', 'Recettes ESP', '2022-09-11 02:00:00'),
(14, 0, 30, '2022-09-14 16:40:50', 'Règlement Fournisseur Z', '2022-09-11 02:00:00'),
(15, 98.98, 0, '2022-09-14 16:41:21', 'Recettes ESP', '2022-09-12 02:00:00'),
(16, 115.03, 0, '2022-09-14 16:41:56', 'Recettes ESP', '2022-09-14 02:00:00'),
(17, 0, 500, '2022-09-14 16:42:16', 'Dépôt d\'espèces', '2022-09-14 02:00:00'),
(18, 315.51, 0, '2022-09-14 16:46:25', 'Recettes ESP', '2022-08-14 02:00:00'),
(19, 69.13, 0, '2022-09-14 17:37:09', 'Recettes ESP', '2022-09-16 02:00:00'),
(20, 95.39, 0, '2022-09-14 17:37:51', 'Recettes ESP', '2022-09-17 02:00:00'),
(21, 200, 0, '2022-09-14 17:38:49', 'Recettes ESP', '2022-09-17 02:00:00'),
(22, 200, 0, '2022-09-14 17:39:00', 'Recettes ESP', '2022-09-17 02:00:00'),
(23, 200, 0, '2022-09-14 17:39:15', 'Recettes ESP', '2022-09-17 02:00:00'),
(24, 200, 0, '2022-09-14 17:39:28', 'Recettes ESP', '2022-09-17 02:00:00'),
(26, 150, 50, '2022-09-14 21:24:24', 'Operation de test', '2022-09-20 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `role_name`) VALUES
(3, 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `password`, `username`) VALUES
(12, '$2a$10$R4JwtdKNopjoXXkoCRDRJ.YQxTibv98n12vjjIwmTCtsTiyjsVAai', 'azziti2000@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `roles_id`) VALUES
(12, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `caisse`
--
ALTER TABLE `caisse`
  ADD PRIMARY KEY (`caisseid`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
  ADD KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `caisse`
--
ALTER TABLE `caisse`
  MODIFY `caisseid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=162;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
