package entity;

import util.DatabaseConnection;

import javax.print.attribute.standard.OrientationRequested;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ordinateur {
    private int id;
    private Marque marque;
    private TypeOrdinateur typeOrdinateur;

    private Modele modele;

    private Timestamp dateAjout;

    public Ordinateur() {
    }

    public Ordinateur(Marque marque, TypeOrdinateur typeOrdinateur, Modele modele, Timestamp dateAjout) {
        this.marque = marque;
        this.typeOrdinateur = typeOrdinateur;
        this.modele = modele;
        this.dateAjout = dateAjout;
    }

    public Ordinateur(int id, Marque marque, TypeOrdinateur typeOrdinateur, Modele modele, Timestamp dateAjout) {
        this.id = id;
        this.marque = marque;
        this.typeOrdinateur = typeOrdinateur;
        this.modele = modele;
        this.dateAjout = dateAjout;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public TypeOrdinateur getTypeOrdinateur() {
        return typeOrdinateur;
    }
    public void setTypeOrdinateur(TypeOrdinateur typeOrdinateur) {
        this.typeOrdinateur = typeOrdinateur;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }


    public static Ordinateur getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM Ordinateur WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Marque marque = Marque.getById(conn,rs.getInt("marque_id"));
                Modele modele=Modele.getById(conn,rs.getInt("modele_id"));
                TypeOrdinateur typeOrdi = TypeOrdinateur.getById(conn,rs.getInt("type_id"));
                return new Ordinateur(rs.getInt("id"),modele.getMarque(),typeOrdi, modele, rs.getTimestamp("date_ajout"));
            }
        }
        return null;
    }
    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO Ordinateur (marque_id,type_id,modele_id,date_ajout) VALUES (?,?,?,?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getMarque().getId());
            ps.setInt(2,getTypeOrdinateur().getId());
            ps.setInt(3,getModele().getId());
            ps.setTimestamp(4,getDateAjout());
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
    public static List<Ordinateur> getAll(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        List<Ordinateur> ordinateurs=new ArrayList<>();
        String sql = "SELECT * FROM Ordinateur";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Marque marque = Marque.getById(conn,rs.getInt("marque_id"));
                Modele modele=Modele.getById(conn,rs.getInt("modele_id"));
                TypeOrdinateur typeOrdi = TypeOrdinateur.getById(conn,rs.getInt("type_id"));
                ordinateurs.add(new Ordinateur(rs.getInt("id"),modele.getMarque(),typeOrdi, modele, rs.getTimestamp("date_ajout")));
            }
        }
        return ordinateurs;
    }

}
