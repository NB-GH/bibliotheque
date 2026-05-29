# Projet BiblioJeunes

Il s'agit d'une application pour une bibliothèque qui permet de gérer complètement ses ressources : adhérents, catalogue de livres et emprunts. L'application offre une interface par onglets pour ajouter, modifier et supprimer des membres, des livres ainsi que les emprunts associés.

# Stack technique

- Expression de besoin : Cahier des charges [(ici)](./Cahier%20des%20Charges.pdf) 
- Langage : Java 21
- Interface graphique : Swing [(ici)](./src/view/)
- Style de l'interface : classe Java [(ici)](./src/resources/Style.java)
- Connexion à la base de données : JDBC [(ici)](./src/model/DatabaseConnection.java)
- Driver JDBC pour MySQL : MySQL Connector/J
- Base de données : MySQL [(ici)](./bibliotheque-3.sql)
- Modélisation de la BDD : MySQLWorkBench [(ici)](./bibiotheque.mwb)
- Documentation : JavaDoc [(ici)](./doc/)

# Modalités d’accès aux productions et à leur documentation

La documentation du projet, JavaDoc, est disponible et téléchargeable [depuis ce dossier](./doc/index.html)
et le cahier des charges fourni par le prestataire [à partir d'ici](Cahier%20des%20Charges.pdf) 

# Descriptif de la réalisation professionnelle

L’application contient une fenêtre, sur laquelle s’affiche un tableau des livres et de l’ensemble des ressources de BiblioJeunes. On peut voir aussi qui sont les adhérents de la bibliothèque et les emprunts qu’ils ont effectué.
Les livres, les adhérents et les emprunts sont stockés dans la base de données de la bibliothèque.
Depuis cette fenêtre, il est aussi possible d’administrer toutes ces ressources, de les modifier, d’ajouter et de supprimer un livre un adhérent, un emprunt. La base de données est modifiée en direct, par ces actions depuis la fenêtre.
L’application suit un pattern MVC.
Model représente les données (Livre, Adherent, Emprunt).
Database permet d’effectuer les opérations sur la base de données. Les erreurs des requêtes SQL sont
capturées dans des throws SQLException.
View contient les interfaces graphiques développées avec la bibliothèque Swing.
Cette architecture permet de séparer la logique métier, l’accès aux données et l’interface utilisateur.

# Lancement du projet (en local)

## Prérequis à installer

- [Java 21](https://www.oracle.com/java/technologies/downloads/) — pour exécuter l'application
- [XAMPP](https://www.apachefriends.org/) (ou équivalent) — pour faire tourner Apache + MySQL en local
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) — le driver JDBC pour connecter Java à MySQL
- [Git](https://git-scm.com/) — pour récupérer le projet

---

### Étape 1 — Démarrer XAMPP

Ouvrez le **Panneau de contrôle XAMPP** et démarrez les deux services suivants :
- **Apache** → permet au PHP de répondre aux appels de l'API
- **MySQL** → démarre la base de données

Les deux indicateurs doivent passer au **vert** avant de continuer.

---

### Étape 2 — Cloner le projet

Ouvrez un terminal et tapez :
```bash
git clone https://github.com/NB-GH/bibliotheque.git
```
> pour télécharger tout le code source du projet depuis GitHub et créer le dossier `bibliotheque/`.

---

### Étape 3 — Importer la base de données

1. Ouvrez votre navigateur et allez sur **http://localhost/phpmyadmin**
2. Créez une nouvelle base de données nommée `bibliotheque`
3. Importez [le fichier SQL de la base des données du projet](./bibliotheque-3.sql) via l'onglet **Importer**

> Ce fichier SQL crée automatiquement toutes les tables et insère les données nécessaires au fonctionnement de l'application. 

4. Importez [le fichier SQL des déclencheurs sur la suppression de données](./triggers_on_delete.sql) via l'onglet **Importer**

>Ce fichier SQL installe la procédure stockée et les triggers pour assurer la journalisation des suppressions.

La modélisation de la base de données avec MySQL Workbench est consultable via [ce fichier](./bibiotheque.mwb)

---

### Étape 4 — Configurer le connecteur MySQL dans Eclipse

Ajoutez le connecteur .jar au classpath de votre projet, dans les librairies externes du projet, selon votre IDE. 

---

### Étape 5 — Lancer l'application

Ouvrez le projet dans votre IDE, puis compilez et exécutez la classe Main.java

L'interface graphique Swing de BiblioJeunes s'ouvre directement. 


