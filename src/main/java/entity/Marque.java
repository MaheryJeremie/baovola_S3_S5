package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Marque {
    private int id;
    private String nom;

    public Marque() {}
    public Marque(String nom){
        this.nom = nom ;
    }

    public Marque(int id, String nom) {
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

        String sql = "INSERT INTO Marque (nom) VALUES (?)";
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
    public static List<Marque> getAll(Connection conn) throws SQLException{
        if (conn==null){
            conn=DatabaseConnection.connect();
        }
        List<Marque> marques=new ArrayList<>();
        String sql="SELECT * FROM Marque";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                marques.add(new Marque(rs.getInt("id"), rs.getString("nom")));
            }
            return marques;
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if (rs!=null){
                ps.close();
            }
            if (ps!=null){
                ps.close();
            }
        }
    }

    public static Marque getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Marque WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Marque(rs.getInt("id"), rs.getString("nom"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE Marque SET nom = ? WHERE id = ?";
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
        String sql = "DELETE FROM Marque WHERE id = ?";
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

