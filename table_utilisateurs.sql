CREATE TABLE utilisateurs (
    utilisateur_id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'bibliothecaire') DEFAULT 'bibliothecaire'
);

INSERT INTO Utilisateurs (login, password_hash, role)
VALUES ('', SHA2('', 256), 'admin');