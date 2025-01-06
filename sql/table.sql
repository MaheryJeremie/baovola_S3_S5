CREATE DATABASE atelier_reparation;


\c atelier_reparation;

CREATE TABLE Marque (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL
);

CREATE TABLE Type (
                      id SERIAL PRIMARY KEY,
                      nom VARCHAR(255) NOT NULL
);

CREATE TABLE Poste (
                       id SERIAL PRIMARY KEY,
                       nom VARCHAR(255) NOT NULL,
                       salaire DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Employe (
                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         prenom VARCHAR(255) NOT NULL,
                         poste_id INT REFERENCES Poste(id),
                         email VARCHAR(255) UNIQUE NOT NULL,
                         mot_de_passe VARCHAR(255) NOT NULL
);

CREATE TABLE Client (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        prenom VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        telephone VARCHAR(20) NOT NULL
);

CREATE TABLE Appareil (
                          id SERIAL PRIMARY KEY,
                          client_id INT REFERENCES Client(id),
                          marque_id INT REFERENCES Marque(id),
                          type_id INT REFERENCES Type(id),
                          modele VARCHAR(255) NOT NULL,
                          date_ajout TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Composant (
                           id SERIAL PRIMARY KEY,
                           nom VARCHAR(255) NOT NULL,
                           type_id INT REFERENCES Type(id),
                           prix_unitaire DECIMAL(10, 2) NOT NULL,
                           stock INT DEFAULT 0,
                           prix_vente DECIMAL(10, 2) NOT NULL
);

CREATE TABLE MouvementStock (
                                id SERIAL PRIMARY KEY,
                                composant_id INT REFERENCES Composant(id),
                                date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                entree INT DEFAULT 0,
                                sortie INT DEFAULT 0,
                                motif VARCHAR(255)
);

CREATE TABLE TypeReparation (
                                id SERIAL PRIMARY KEY,
                                nom VARCHAR(255) NOT NULL,
                                tarif DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Etat (
                      id SERIAL PRIMARY KEY,
                      nom VARCHAR(255) NOT NULL
);

CREATE TABLE Reparation (
                            id SERIAL PRIMARY KEY,
                            appareil_id INT REFERENCES Appareil(id),
                            employe_id INT REFERENCES Employe(id),
                            type_reparation_id INT REFERENCES TypeReparation(id),
                            date_debut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            etat_id INT REFERENCES Etat(id)
);

CREATE TABLE DetailReparation (
                                  id SERIAL PRIMARY KEY,
                                  reparation_id INT REFERENCES Reparation(id),
                                  composant_id INT REFERENCES Composant(id),
                                  quantite INT NOT NULL
);

