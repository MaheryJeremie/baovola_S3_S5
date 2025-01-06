CREATE OR REPLACE FUNCTION update_stock()
RETURNS TRIGGER AS $$
BEGIN
UPDATE Composant
SET stock = stock + NEW.entree - NEW.sortie
WHERE id = NEW.composant_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_stock
    AFTER INSERT ON MouvementStock
    FOR EACH ROW
    EXECUTE FUNCTION update_stock();
