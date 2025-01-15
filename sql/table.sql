CREATE TABLE Marque (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL
);
CREATE TABLE Modele(
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        marque_id INT REFERENCES Marque(id)
);

CREATE TABLE TypeOrdinateur (
                      id SERIAL PRIMARY KEY,
                      nom VARCHAR(255) NOT NULL
);

CREATE TABLE TypeProbleme (
                                id SERIAL PRIMARY KEY,
                                nom VARCHAR(255) NOT NULL
);

CREATE TABLE Ordinateur (
                          id SERIAL PRIMARY KEY,
                          marque_id INT REFERENCES Marque(id),
                          type_id INT REFERENCES TypeOrdinateur(id),
                          modele_id INT REFERENCES Modele(id),
                          date_ajout TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Etat (
                      id SERIAL PRIMARY KEY,
                      nom VARCHAR(255) NOT NULL
);

CREATE TABLE Reparation (
                            id SERIAL PRIMARY KEY,
                            ordinateur_id INT REFERENCES Ordinateur(id),
                            type_probleme_id INT REFERENCES TypeProbleme(id),
                            date_debut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            etat_id INT REFERENCES Etat(id)
);
create table Composant(
                          id serial primary key ,
                          nom varchar(255)
);

create table Composant_Recommande(
                                     id serial  primary key ,
                                     composant_id int references Composant(id),
                                     dateInsertion date
);