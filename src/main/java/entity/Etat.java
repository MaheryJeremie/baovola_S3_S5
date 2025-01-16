package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Etat {
    private int id;
    private String nom;

    public Etat() {}
    public Etat(String nom){
        this.nom = nom ;
    }

    public Etat(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Etat (nom) VALUES (?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, getNom());
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

    public static List<Etat> getAll(Connection conn) throws Exception{
        List<Etat> result = new ArrayList<>();
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Etat";
        PreparedStatement ps = conn.prepareStatement(sql);
        try{
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Etat(rs.getInt("id"), rs.getString("nom")));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (ps!=null){
                ps.close();
            }
        }
        return result;
    }

    public static Etat getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Etat WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etat(rs.getInt("id"), rs.getString("nom"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE Etat SET nom = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setInt(2, getId());
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
        String sql = "DELETE FROM Etat WHERE id = ?";
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

