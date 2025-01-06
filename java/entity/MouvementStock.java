package entity;

import util.DatabaseConnection;

import java.sql.*;

public class MouvementStock {
    private int id;
    private int composantId;
    private Timestamp date;
    private int entree;
    private int sortie;
    private String motif;

    public MouvementStock() {}
    public MouvementStock(int composantId, int entree, int sortie, String motif){
        this.composantId = composantId;
        this.entree = entree;
        this.sortie = sortie;
        this.motif = motif;
    }

    public MouvementStock(int id, int composantId, Timestamp date, int entree, int sortie, String motif) {
        this.id = id;
        this.composantId = composantId;
        this.date = date;
        this.entree = entree;
        this.sortie = sortie;
        this.motif = motif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComposantId() {
        return composantId;
    }

    public void setComposantId(int composantId) {
        this.composantId = composantId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getEntree() {
        return entree;
    }

    public void setEntree(int entree) {
        this.entree = entree;
    }

    public int getSortie() {
        return sortie;
    }

    public void setSortie(int sortie) {
        this.sortie = sortie;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO MouvementStock (composant_id, entree, sortie, motif) VALUES (?, ?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getComposantId());
            ps.setInt(2, getEntree());
            ps.setInt(3, getSortie());
            ps.setString(4, getMotif());
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

    public static MouvementStock getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM MouvementStock WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MouvementStock(rs.getInt("id"), rs.getInt("composant_id"), rs.getTimestamp("date"), rs.getInt("entree"), rs.getInt("sortie"), rs.getString("motif"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE MouvementStock SET composant_id = ?, entree = ?, sortie = ?, motif = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setInt(1, getComposantId());
            ps.setInt(2, getEntree());
            ps.setInt(3, getSortie());
            ps.setString(4, getMotif());
            ps.setInt(5, getId());
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
        String sql = "DELETE FROM MouvementStock WHERE id = ?";
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

