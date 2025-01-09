package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeOrdinateur {
    private int id;
    private String nom;

    public TypeOrdinateur(int id, String nom) {
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


    public static TypeOrdinateur getById(Connection conn, int id) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM TypeOrdinateur WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TypeOrdinateur(rs.getInt("id"), rs.getString("nom"));
            }
        }
        return null;
    }
    public static List<TypeOrdinateur> getAll(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM TypeOrdinateur";
        List<TypeOrdinateur> types=new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                types.add(new TypeOrdinateur(rs.getInt("id"), rs.getString("nom")));
            }
        }
        return types;
    }

}
