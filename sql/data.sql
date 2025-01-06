-- Insertion des marques
INSERT INTO Marque (nom) VALUES
                             ('Samsung'),
                             ('Apple'),
                             ('Dell'),
                             ('HP');

-- Insertion des types d'appareil
INSERT INTO Type (nom) VALUES
                           ('Smartphone'),
                           ('Ordinateur portable'),
                           ('Ordinateur de bureau'),
                           ('Tablette');

-- Insertion des postes
INSERT INTO Poste (nom, salaire) VALUES
                                     ('Technicien', 1200),
                                     ('Réceptionniste', 1000),
                                     ('Admin', 1500);

-- Insertion des employés
INSERT INTO Employe (nom, prenom, poste_id, email, mot_de_passe) VALUES
                                                                     ('Dupont', 'Pierre', 1, 'pierre.dupont@exemple.com', 'password123'),
                                                                     ('Martin', 'Claire', 2, 'claire.martin@exemple.com', 'password123'),
                                                                     ('Lemoine', 'Julien', 3, 'julien.lemoine@exemple.com', 'password123');

-- Insertion des clients
INSERT INTO Client (nom, prenom, email, telephone) VALUES
                                                       ('Bernard', 'Paul', 'paul.bernard@exemple.com', '0123456789'),
                                                       ('Lemoine', 'Alice', 'alice.lemoine@exemple.com', '0987654321');

-- Insertion des appareils
INSERT INTO Appareil (client_id, marque_id, type_id, modele) VALUES
                                                                 (1, 1, 1, 'Samsung Galaxy S21'),
                                                                 (1, 2, 2, 'Dell XPS 13'),
                                                                 (2, 3, 2, 'HP Pavilion 15');

-- Insertion des composants
INSERT INTO Composant (nom, type_id, prix_unitaire, stock, prix_vente) VALUES
                                                                           ('Ecran', 2, 200, 50, 300),
                                                                           ('Batterie', 1, 50, 100, 80),
                                                                           ('Carte mère', 2, 120, 30, 180),
                                                                           ('Disque dur SSD', 2, 100, 20, 150);

-- Insertion des types de réparation
INSERT INTO TypeReparation (nom, tarif) VALUES
                                            ('Réparation écran', 100),
                                            ('Changement batterie', 50),
                                            ('Réparation carte mère', 150),
                                            ('Remplacement SSD', 80);

-- Insertion des états
INSERT INTO Etat (nom) VALUES
                           ('En attente'),
                           ('En cours'),
                           ('Terminée');

-- Insertion des réparations
INSERT INTO Reparation (appareil_id, employe_id, type_reparation_id, etat_id) VALUES
                                                                                  (1, 1, 1, 2),
                                                                                  (2, 2, 3, 1),
                                                                                  (3, 3, 4, 1);

-- Insertion des détails de réparation
INSERT INTO DetailReparation (reparation_id, composant_id, quantite) VALUES
                                                                         (1, 1, 1),
                                                                         (1, 2, 1),
                                                                         (2, 3, 1),
                                                                         (3, 4, 1);

-- Insertion des mouvements de stock
INSERT INTO MouvementStock (composant_id, entree, sortie, motif) VALUES
                                                                     (1, 50, 0, 'Réapprovisionnement'),
                                                                     (2, 100, 0, 'Réapprovisionnement'),
                                                                     (3, 30, 0, 'Réapprovisionnement'),
                                                                     (4, 20, 0, 'Réapprovisionnement');
