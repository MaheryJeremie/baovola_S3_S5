-- Insert data into Marque
INSERT INTO Marque (nom) VALUES
                             ('Apple'),
                             ('Dell'),
                             ('HP'),
                             ('Lenovo'),
                             ('Asus');

-- Insert data into Modele
INSERT INTO Modele (nom, marque_id) VALUES
                                        ('MacBook Pro 16-inch', 1),  -- Apple
                                        ('MacBook Air', 1),          -- Apple
                                        ('XPS 13', 2),               -- Dell
                                        ('XPS 15', 2),               -- Dell
                                        ('HP Pavilion Desktop', 3),  -- HP
                                        ('HP Spectre x360', 3),      -- HP
                                        ('ThinkPad X1 Carbon', 4),   -- Lenovo
                                        ('ThinkPad T14', 4),         -- Lenovo
                                        ('ZenBook 14', 5),           -- Asus
                                        ('ROG Zephyrus G14', 5);     -- Asus

-- Insert data into TypeOrdinateur
INSERT INTO TypeOrdinateur (nom) VALUES
                                     ('Laptop'),
                                     ('Desktop'),
                                     ('Tablet'),
                                     ('Workstation'),
                                     ('Server');

-- Insert data into TypeProbleme
INSERT INTO TypeProbleme (nom) VALUES
                                   ('Battery Issue'),
                                   ('Overheating'),
                                   ('Screen Flickering'),
                                   ('Keyboard Not Working'),
                                   ('Software Error'),
                                   ('Slow Performance');

-- Insert data into Ordinateur
INSERT INTO Ordinateur (marque_id, type_id, modele_id, date_ajout) VALUES
                                                                       (1, 1, 1, '2025-01-01 10:00:00'), -- Apple, Laptop, MacBook Pro 16-inch
                                                                       (1, 1, 2, '2025-01-02 12:00:00'), -- Apple, Laptop, MacBook Air
                                                                       (2, 1, 3, '2025-01-03 09:15:00'), -- Dell, Laptop, XPS 13
                                                                       (2, 1, 4, '2025-01-04 11:30:00'), -- Dell, Laptop, XPS 15
                                                                       (3, 2, 5, '2025-01-05 08:45:00'), -- HP, Desktop, HP Pavilion Desktop
                                                                       (3, 1, 6, '2025-01-06 10:00:00'), -- HP, Laptop, HP Spectre x360
                                                                       (4, 1, 7, '2025-01-07 14:00:00'), -- Lenovo, Laptop, ThinkPad X1 Carbon
                                                                       (4, 1, 8, '2025-01-08 16:30:00'), -- Lenovo, Laptop, ThinkPad T14
                                                                       (5, 1, 9, '2025-01-09 10:15:00'), -- Asus, Laptop, ZenBook 14
                                                                       (5, 1, 10, '2025-01-10 13:00:00'); -- Asus, Laptop, ROG Zephyrus G14

-- Insert data into Etat
INSERT INTO Etat (nom) VALUES
                           ('Pending'),
                           ('In Progress'),
                           ('Completed'),
                           ('Shipped'),
                           ('Canceled');

-- Insert data into Reparation
INSERT INTO Reparation (ordinateur_id, type_probleme_id, date_debut, etat_id) VALUES
                                                                                  (1, 1, '2025-01-05 10:00:00', 2),  -- MacBook Pro 16-inch, Battery Issue, In Progress
                                                                                  (2, 6, '2025-01-06 14:00:00', 3),  -- MacBook Air, Slow Performance, Completed
                                                                                  (3, 4, '2025-01-07 08:30:00', 1),  -- XPS 13, Keyboard Not Working, Pending
                                                                                  (4, 2, '2025-01-08 09:45:00', 2),  -- XPS 15, Overheating, In Progress
                                                                                  (5, 3, '2025-01-09 11:00:00', 4),  -- HP Pavilion Desktop, Screen Flickering, Shipped
                                                                                  (6, 5, '2025-01-10 12:30:00', 2),  -- HP Spectre x360, Software Error, In Progress
                                                                                  (7, 2, '2025-01-11 10:00:00', 1),  -- ThinkPad X1 Carbon, Overheating, Pending
                                                                                  (8, 1, '2025-01-12 14:15:00', 3),  -- ThinkPad T14, Battery Issue, Completed
                                                                                  (9, 6, '2025-01-13 09:00:00', 2),  -- ZenBook 14, Slow Performance, In Progress
                                                                                  (10, 3, '2025-01-14 15:30:00', 4); -- ROG Zephyrus G14, Screen Flickering, Shipped
