package entity;

import util.DatabaseConnection;

import java.sql.*;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private int posteId;
    private String email;
    private String motDePasse;

    public Employe() {}
    public Employe(String nom, String prenom, int posteId, String email, String motDePasse){
        this.nom = nom;
        this.prenom = prenom;
        this.posteId = posteId;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Employe(int id, String nom, String prenom, int posteId, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.posteId = posteId;
        this.email = email;
        this.motDePasse = motDePasse;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPosteId() {
        return posteId;
    }

    public void setPosteId(int posteId) {
        this.posteId = posteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Employe (nom, prenom, poste_id, email, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setString(2, getPrenom());
            ps.setInt(3, getPosteId());
            ps.setString(4, getEmail());
            ps.setString(5, getMotDePasse());
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

    public static Employe getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Employe WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("poste_id"), rs.getString("email"), rs.getString("mot_de_passe"));
            }
        }
        return null;
    }

    public void update(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "UPDATE Employe SET nom = ?, prenom = ?, poste_id = ?, email = ?, mot_de_passe = ? WHERE id = ?";
        PreparedStatement ps=null;
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setString(2, getPrenom());
            ps.setInt(3, getPosteId());
            ps.setString(4, getEmail());
            ps.setString(5, getMotDePasse());
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
        String sql = "DELETE FROM Employe WHERE id = ?";
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


