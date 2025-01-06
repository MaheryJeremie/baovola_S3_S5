CREATE VIEW VueStock AS
SELECT c.id, c.nom, c.prix_unitaire, c.stock, c.prix_vente
FROM Composant c;

CREATE VIEW VueReparationsParTechnicien AS
SELECT r.id, r.appareil_id, r.employe_id, r.type_reparation_id, r.date_debut, r.etat_id
FROM Reparation r
WHERE r.employe_id = CURRENT_USER;

CREATE VIEW VueReparationsTerminees AS
SELECT r.id, r.appareil_id, r.employe_id, r.type_reparation_id, r.date_debut, r.etat_id
FROM Reparation r
         JOIN Etat e ON r.etat_id = e.id
WHERE e.nom = 'Terminée';

CREATE VIEW VueFactures AS
SELECT r.id, r.appareil_id, r.date_debut, tr.tarif + SUM(dr.quantite * c.prix_vente) AS montant_total
FROM Reparation r
         JOIN TypeReparation tr ON r.type_reparation_id = tr.id
         JOIN DetailReparation dr ON r.id = dr.reparation_id
         JOIN Composant c ON dr.composant_id = c.id
GROUP BY r.id, r.appareil_id, r.date_debut, tr.tarif;

CREATE VIEW VueEmployes AS
SELECT e.id, e.nom, e.prenom, e.email, e.poste_id, p.nom AS poste, p.salaire
FROM Employe e
         JOIN Poste p ON e.poste_id = p.id;

CREATE VIEW VueClients AS
SELECT c.id, c.nom, c.prenom, c.email, c.telephone
FROM Client c;

CREATE VIEW VueAppareilsClients AS
SELECT a.id, a.client_id, a.marque_id, a.type_id, a.modele, a.date_ajout
FROM Appareil a;

CREATE VIEW VueComposantsUtilises AS
SELECT dr.reparation_id, c.nom, dr.quantite, c.prix_vente, (dr.quantite * c.prix_vente) AS total
FROM DetailReparation dr
         JOIN Composant c ON dr.composant_id = c.id;

CREATE VIEW VueTableauDeBord AS
SELECT
    (SELECT SUM(tr.tarif + SUM(dr.quantite * c.prix_vente)) FROM Reparation r
                                                                     JOIN TypeReparation tr ON r.type_reparation_id = tr.id
                                                                     JOIN DetailReparation dr ON r.id = dr.reparation_id
                                                                     JOIN Composant c ON dr.composant_id = c.id) AS chiffre_affaire,
    (SELECT SUM(e.salaire) FROM Employe e) AS depenses,
    (SELECT SUM(tr.tarif + SUM(dr.quantite * c.prix_vente)) FROM Reparation r
                                                                     JOIN TypeReparation tr ON r.type_reparation_id = tr.id
                                                                     JOIN DetailReparation dr ON r.id = dr.reparation_id
                                                                     JOIN Composant c ON dr.composant_id = c.id) -
    (SELECT SUM(e.salaire) FROM Employe e) AS benefice;
