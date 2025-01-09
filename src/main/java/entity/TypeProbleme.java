package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TypeProbleme {
    private int id;
    private String nom;

    public TypeProbleme(int id,String nom){
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




    public static List<TypeProbleme> getAll(Connection conn) throws SQLException {
        List<TypeProbleme> result = new ArrayList<>();
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        String sql = "SELECT * FROM TypeProbleme";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new TypeProbleme(rs.getInt("id"), rs.getString("nom") ));
            }
        }
        return result;
    }

}
