package entity;

import util.DatabaseConnection;

import java.sql.*;

public class DetailReparation {
    private int id;
    private int reparationId;
    private int composantId;
    private int quantite;

    public DetailReparation() {}
    public DetailReparation(int reparationId, int composantId, int quantite){
        this.reparationId = reparationId;
        this.composantId = composantId;
        this.quantite = quantite;
    }

    public DetailReparation(int id, int reparationId, int composantId, int quantite) {
        this.id = id;
        this.reparationId = reparationId;
        this.composantId = composantId;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReparationId() {
        return reparationId;
    }

    public void setReparationId(int reparationId) {
        this.reparationId = reparationId;
    }

    public int getComposantId() {
        return composantId;
    }

    public void setComposantId(int composantId) {
        this.composantId = composantId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO DetailReparation (reparation_id, composant_id, quantite) VALUES (?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getReparationId());
            ps.setInt(2, getComposantId());
            ps.setInt(3, getQuantite());
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

    public static DetailReparation getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM DetailReparation WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DetailReparation(rs.getInt("id"), rs.getInt("reparation_id"), rs.getInt("composant_id"), rs.getInt("quantite"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE DetailReparation SET reparation_id = ?, composant_id = ?, quantite = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setInt(1, getReparationId());
            ps.setInt(2, getComposantId());
            ps.setInt(3, getQuantite());
            ps.setInt(4, getId());
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
        String sql = "DELETE FROM DetailReparation WHERE id = ?";
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
