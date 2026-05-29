CREATE TABLE logs_centralises( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_id INT,
    source_table varchar(50),
    nom varchar(255),	
	informations varchar(255),
    date_source date,
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    action VARCHAR(50)
);

DELIMITER //
CREATE PROCEDURE log_suppressions(
    IN p_source_id INT,
    IN p_source_table VARCHAR(50),
    IN p_nom VARCHAR(255),
    IN p_informations VARCHAR(255),
    IN p_date_source DATE
)
BEGIN
    INSERT INTO logs_centralises (source_id,
                                  source_table,
                                  nom,
                                  informations,
                                  date_source,
                                  action_time,
                                  `action`)
                                  VALUES (p_source_id,
                                          p_source_table,
                                          p_nom,
                                          p_informations,
                                          p_date_source,
                                          NOW(),
                                          'DELETE');
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER suppressionLivre
AFTER DELETE ON livres
FOR EACH ROW
BEGIN
    CALL log_suppressions(
        OLD.livre_id, 
        'livres', 
        OLD.titre, 
        CONCAT(OLD.auteur, "  ", OLD.isbn), 
        OLD.date_ajout);
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER suppressionAdherent
AFTER DELETE ON adherents
FOR EACH ROW
BEGIN
CALL log_suppressions(OLD.adherent_id, 'adherents', 
                      CONCAT(OLD.nom, " ", OLD.prenom),
                      CONCAT(OLD.email, " ", OLD.telephone), 
                      OLD.date_inscription);
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER suppressionEmprunt
AFTER DELETE ON emprunts
FOR EACH ROW
BEGIN
CALL log_suppressions(OLD.emprunt_id, 'emprunts',
                      CONCAT(OLD.livre_id, " soit le livre ",
                             (SELECT l.titre
                              FROM livres l
                              WHERE l.livre_id = OLD.livre_id)),
                      CONCAT(OLD.adherent_id, " soit l'adhérent ",
                             (SELECT CONCAT(a.nom, ' ', a.prenom)
                              FROM adherents a
                              WHERE a.adherent_id = OLD.adherent_id)),
                       OLD.date_emprunt);
END//
DELIMITER ; 