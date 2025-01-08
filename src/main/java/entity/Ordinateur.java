package entity;

import util.DatabaseConnection;

import java.sql.*;

public class Ordinateur {
    private int id;
    private Marque marque;
    private TypeOrdinateur typeOrdinateur;

    private String modele;

    private Timestamp dateAjout;

    public Ordinateur(int id, Marque marque, TypeOrdinateur typeOrdinateur, String modele, Timestamp dateAjout) {
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
                TypeOrdinateur typeOrdi = TypeOrdinateur.getById(conn,rs.getInt("type_id"));
                return new Ordinateur(rs.getInt("id"),marque,typeOrdi, rs.getString("modele"), rs.getTimestamp("date_ajout"));
            }
        }
        return null;
    }

}
