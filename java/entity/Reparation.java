package entity;

import util.DatabaseConnection;

import java.sql.*;

public class Reparation {
    private int id;
    private int appareilId;
    private int employeId;
    private int typeReparationId;
    private Timestamp dateDebut;
    private int etatId;

    public Reparation() {}
    public Reparation(int appareilId, int employeId, int typeReparationId, int etatId){
        this.appareilId = appareilId;
        this.employeId = employeId;
        this.typeReparationId = typeReparationId;
        this.etatId = etatId;
    }

    public Reparation(int id, int appareilId, int employeId, int typeReparationId, Timestamp dateDebut, int etatId) {
        this.id = id;
        this.appareilId = appareilId;
        this.employeId = employeId;
        this.typeReparationId = typeReparationId;
        this.dateDebut = dateDebut;
        this.etatId = etatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppareilId() {
        return appareilId;
    }

    public void setAppareilId(int appareilId) {
        this.appareilId = appareilId;
    }

    public int getEmployeId() {
        return employeId;
    }

    public void setEmployeId(int employeId) {
        this.employeId = employeId;
    }

    public int getTypeReparationId() {
        return typeReparationId;
    }

    public void setTypeReparationId(int typeReparationId) {
        this.typeReparationId = typeReparationId;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getEtatId() {
        return etatId;
    }

    public void setEtatId(int etatId) {
        this.etatId = etatId;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Reparation (appareil_id, employe_id, type_reparation_id, etat_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getAppareilId());
            ps.setInt(2, getEmployeId());
            ps.setInt(3, getTypeReparationId());
            ps.setInt(4, getEtatId());
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

    public static Reparation getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn=DatabaseConnection.connect();
        }
            String sql = "SELECT * FROM Reparation WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Reparation(rs.getInt("id"), rs.getInt("appareil_id"), rs.getInt("employe_id"), rs.getInt("type_reparation_id"), rs.getTimestamp("date_debut"), rs.getInt("etat_id"));
                }
            }
            return null;
        }

        public void update(Connection conn) throws SQLException {
            if (conn==null){
                conn= DatabaseConnection.connect();
            }
            String sql = "UPDATE Reparation SET appareil_id = ?, employe_id = ?, type_reparation_id = ?, etat_id = ? WHERE id = ?";
            PreparedStatement ps=null;
            try{
                ps= conn.prepareStatement(sql);
                ps.setInt(1, getAppareilId());
                ps.setInt(2, getEmployeId());
                ps.setInt(3, getTypeReparationId());
                ps.setInt(4, getEtatId());
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
            String sql = "DELETE FROM Reparation WHERE id = ?";
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

