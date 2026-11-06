# TP-Gestion-Serie 
📺 GestionSeries

Application web développée dans le cadre du Master SIGLIS (Université de Pau et des Pays de l'Adour - UPPA) pour la gestion hiérarchique de séries, saisons et épisodes.

🚀 Stack Technique & Environnement

Backend : Java EE / Jakarta EE 10 (Servlets, JSP, JSTL)

Serveur d'applications : Apache Tomcat 10.1

Persistance : JPA 2.1 (EclipseLink 2.5.2) avec gestion des cascades (CascadeType.ALL, orphanRemoval = true)

Base de données : PostgreSQL 14

Gestionnaire de dépendances : Maven

IDE : Eclipse IDE

📋 Fonctionnalités Principales

CRUD Séries : Création, affichage et suppression en cascade de séries.

CRUD Saisons : Ajout de saisons dynamiques et suppression en cascade.

Gestion des Épisodes : Création d'épisodes, basculement de l'état de visionnage (vu / non vu) et suppression unitaire.

Architecture MVC & DAO : Séparation stricte entre les vues (JSP), les contrôleurs (Servlets) et l'accès aux données (interfaces DAO et implémentations BDD).

🛠️ Instructions de Déploiement

Cloner le dépôt sur votre machine locale.

Importer le projet dans Eclipse en tant que projet Maven existant.

Configurer une base de données PostgreSQL nommée gestionseries (ou tp_siglis).

Vérifier et adapter les paramètres de connexion dans src/META-INF/persistence.xml.

Lancer l'application sur le serveur Apache Tomcat 10.1 via Run As -> Run on Server.

👨‍💻 Auteur

Projet réalisé par Madou DIAKAYETEBA (Master SIGLIS).