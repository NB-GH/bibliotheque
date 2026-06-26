USE `bibliotheque`;

CREATE TABLE `avis`(
`avis_id` int(11) not null PRIMARY KEY AUTO_INCREMENT,
`avis_texte` longtext,
`avis_date` DATE,
`livre_id` int(11),
`adherent_id` int(11),
CONSTRAINT fk_avis_livres FOREIGN KEY (livre_id) REFERENCES livres(livre_id),
CONSTRAINT fk_avis_adherents FOREIGN KEY (adherent_id) references adherents(adherent_id)
);
-- IL manque encore les fonctionnalités de lecture des avis dans la fenetre.
