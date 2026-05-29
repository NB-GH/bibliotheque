# Projet BiblioJeunes

Il s'agit d'une application de gestion de bibliothèque en Java avec interface graphique Swing et base de données MySQL.

## Description

Gestion complète d'une bibliothèque : adhérents, livres et emprunts. L'application permet d'ajouter, modifier et supprimer des membres, des livres et des emprunts.

## Fonctionnalités

- Gestion des adhérents (membres) - Gestion du catalogue de livres - Gestion des emprunts et retours - Interface graphique intuitive avec onglets - Base de données MySQL persistante

## Prérequis

- Java 8 ou supérieur 
- MySQL installé et configuré 
- MySQL Connector peut être téléchargé à partir du site offficiel de 


## Structure du projet

- Main.java — Point d'entrée - model/ — Modèles de données et accès à la BD - view/ — Interface graphique (Swing)

## Base de données

Initialiser la BDD  et ces tables adherents, livres, emprunts avec [ce fichier](bibliotheque-3.sql).
Une procédure stockée et des triggers permettent la journalisation des suppressions des tables, disponible dans [ce fichier](triggers_on_delete.sql)

## JavaDoc
La documentation du projet est disponible [à partir d'ici](./doc/index.html)
Cahier des Charges [(ici)](Cahier%20des%20Charges.pdf) 