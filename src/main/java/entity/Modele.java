package entity;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modele {
    private int id;
    private String nom;
    private Marque marque;

    public Modele() {
    }

    public Modele(String nom) {
        this.nom = nom;
    }

    public Modele(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Modele(int id, String nom, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
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

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void create(Connection conn,int marqueId) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Modele (nom,marque_id) VALUES (?,?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setInt(2,marqueId);
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
    public static List<Modele> getAll(Connection conn) throws SQLException{
        if (conn==null){
            conn=DatabaseConnection.connect();
        }
        List<Modele> modeles=new ArrayList<>();
        String sql="SELECT * FROM Modele";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Marque m=Marque.getById(conn,rs.getInt("marque_id"));
                modeles.add(new Modele(rs.getInt("id"), rs.getString("nom"),m));
            }
            return modeles;
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
    public static List<Modele> getAllModeleByMarque(Connection conn,int marqueId) throws SQLException{
        if (conn==null){
            conn=DatabaseConnection.connect();
        }
        List<Modele> modeles=new ArrayList<>();
        String sql="SELECT * FROM Modele where marque_id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement(sql);
            ps.setInt(1, marqueId);
            rs=ps.executeQuery();
            while(rs.next()){
                Marque m=Marque.getById(conn,rs.getInt("marque_id"));
                modeles.add(new Modele(rs.getInt("id"), rs.getString("nom"),m));
            }
            return modeles;
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

    public static Modele getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Modele WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Marque marque=Marque.getById(conn,rs.getInt("marque_id"));
                return new Modele(rs.getInt("id"), rs.getString("nom"),marque);
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
