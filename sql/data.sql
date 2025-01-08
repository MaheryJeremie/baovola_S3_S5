-- Insert data into Marque (Brands)
INSERT INTO Marque (nom) VALUES
                             ('Apple'),
                             ('Dell'),
                             ('HP'),
                             ('Lenovo'),
                             ('Asus');

-- Insert data into TypeOrdinateur (Computer Types)
INSERT INTO TypeOrdinateur (nom) VALUES
                                     ('Laptop'),
                                     ('Desktop'),
                                     ('Tablet'),
                                     ('Workstation'),
                                     ('Server');

-- Insert data into TypeProbleme (Problem Types)
INSERT INTO TypeProbleme (nom) VALUES
                                   ('Battery Issue'),
                                   ('Overheating'),
                                   ('Screen Flickering'),
                                   ('Keyboard Not Working'),
                                   ('Software Error'),
                                   ('Slow Performance');

-- Insert data into Ordinateur (Computers)
INSERT INTO Ordinateur (marque_id, type_id, modele, date_ajout) VALUES
                                                                    (1, 1, 'MacBook Pro 16-inch', '2025-01-01 10:00:00'),
                                                                    (2, 1, 'XPS 13', '2025-01-02 14:30:00'),
                                                                    (3, 2, 'HP Pavilion Desktop', '2025-01-03 09:15:00'),
                                                                    (4, 1, 'ThinkPad X1 Carbon', '2025-01-04 11:00:00'),
                                                                    (5, 1, 'ZenBook 14', '2025-01-05 08:45:00');

-- Insert data into Etat (Repair States)
INSERT INTO Etat (nom) VALUES
                           ('Pending'),
                           ('In Progress'),
                           ('Completed'),
                           ('Shipped'),
                           ('Canceled');

-- Insert data into Reparation (Repairs)
INSERT INTO Reparation (ordinateur_id, type_probleme_id, date_debut, etat_id) VALUES
                                                                                  (1, 1, '2025-01-05 10:00:00', 2),  -- MacBook Pro, Battery Issue, In Progress
                                                                                  (2, 4, '2025-01-06 14:00:00', 3),  -- XPS 13, Keyboard Not Working, Completed
                                                                                  (3, 2, '2025-01-04 11:30:00', 1),  -- HP Pavilion, Overheating, Pending
                                                                                  (4, 3, '2025-01-07 09:00:00', 4),  -- ThinkPad X1 Carbon, Screen Flickering, Shipped
                                                                                  (5, 6, '2025-01-06 08:45:00', 2);  -- ZenBook 14, Slow Performance, In Progress
