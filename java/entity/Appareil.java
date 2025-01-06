package entity;

import util.DatabaseConnection;

import java.sql.*;

public class Appareil {
    private int id;
    private int clientId;
    private int marqueId;
    private int typeId;
    private String modele;
    private Timestamp dateAjout;

    public Appareil() {}
    public Appareil(int clientId, int marqueId, int typeId, String modele){
        this.clientId = clientId;
        this.marqueId = marqueId;
        this.typeId = typeId;
        this.modele = modele;
    }

    public Appareil(int id, int clientId, int marqueId, int typeId, String modele, Timestamp dateAjout) {
        this.id = id;
        this.clientId = clientId;
        this.marqueId = marqueId;
        this.typeId = typeId;
        this.modele = modele;
        this.dateAjout = dateAjout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(int marqueId) {
        this.marqueId = marqueId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Appareil (client_id, marque_id, type_id, modele) VALUES (?, ?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getClientId());
            ps.setInt(2, getMarqueId());
            ps.setInt(3, getTypeId());
            ps.setString(4, getModele());
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

    public static Appareil getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Appareil WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Appareil(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("marque_id"), rs.getInt("type_id"), rs.getString("modele"), rs.getTimestamp("date_ajout"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE Appareil SET client_id = ?, marque_id = ?, type_id = ?, modele = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setInt(1, getClientId());
            ps.setInt(2, getMarqueId());
            ps.setInt(3, getTypeId());
            ps.setString(4, getModele());
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
        String sql = "DELETE FROM Appareil WHERE id = ?";
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
