package entity;

import util.DatabaseConnection;

import java.sql.*;

public class Composant {
    private int id;
    private String nom;
    private int typeId;
    private double prixUnitaire;
    private int stock;
    private double prixVente;

    public Composant() {}
    public Composant(String nom, int typeId, double prixUnitaire, int stock, double prixVente){
        this.nom = nom;
        this.typeId = typeId;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.prixVente = prixVente;
    }

    public Composant(int id, String nom, int typeId, double prixUnitaire, int stock, double prixVente) {
        this.id = id;
        this.nom = nom;
        this.typeId = typeId;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.prixVente = prixVente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Composant (nom, type_id, prix_unitaire, stock, prix_vente) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setInt(2, getTypeId());
            ps.setDouble(3, getPrixUnitaire());
            ps.setInt(4, getStock());
            ps.setDouble(5, getPrixVente());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if (ps!=null){
                ps.close();
            }
        }
    }

    public static Composant getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Composant WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Composant(rs.getInt("id"), rs.getString("nom"), rs.getInt("type_id"), rs.getDouble("prix_unitaire"), rs.getInt("stock"), rs.getDouble("prix_vente"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE Composant SET nom = ?, type_id = ?, prix_unitaire = ?, stock = ?, prix_vente = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setInt(2, getTypeId());
            ps.setDouble(3, getPrixUnitaire());
            ps.setInt(4, getStock());
            ps.setDouble(5, getPrixVente());
            ps.setInt(6, getId());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if (ps!=null){
                ps.close();
            }
        }
    }

    public void delete(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "DELETE FROM Composant WHERE id = ?";
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getId());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if (ps!=null){
                ps.close();
            }
        }
    }
}

