package entity;

import util.DatabaseConnection;

import java.sql.*;

public class TypeReparation {
    private int id;
    private String nom;
    private double tarif;

    public TypeReparation() {}
    public TypeReparation(String nom, double tarif){
        this.nom = nom;
        this.tarif = tarif;
    }

    public TypeReparation(int id, String nom, double tarif) {
        this.id = id;
        this.nom = nom;
        this.tarif = tarif;
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

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO TypeReparation (nom, tarif) VALUES (?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setDouble(2, getTarif());
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

    public static TypeReparation getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM TypeReparation WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TypeReparation(rs.getInt("id"), rs.getString("nom"), rs.getDouble("tarif"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE TypeReparation SET nom = ?, tarif = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setDouble(2, getTarif());
            ps.setInt(3, getId());
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
        String sql = "DELETE FROM TypeReparation WHERE id = ?";
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
