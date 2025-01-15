package entity;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
public class ComposantRecommande {
    int id;
    Composant composant;
    Date dateInsertion;

    public ComposantRecommande(int id, Composant composant, Date dateInsertion) {
        this.id = id;
        this.composant = composant;
        this.dateInsertion = dateInsertion;
    }

    public ComposantRecommande(Composant composant, Date dateInsertion) {
        this.composant = composant;
        this.dateInsertion = dateInsertion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }

    public Date getDateInsertion() {
        return dateInsertion;
    }

    public void setDateInsertion(Date dateInsertion) {
        this.dateInsertion = dateInsertion;
    }

    public void create(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }

        String sql = "INSERT INTO ComposantRecommande (composant_id,dateInsertion) VALUES (?,?)";
        PreparedStatement ps=null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, getId());
            ps.setDate(2,getDateInsertion());
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


    public static List<ComposantRecommande> getAll(Connection conn) throws SQLException {
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        List<ComposantRecommande> composants=new ArrayList<>();
        String sql = "SELECT * FROM ComposantRecommande";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Composant composant=Composant.getById(conn,rs.getInt("composant_id"));
                composants.add(new ComposantRecommande(rs.getInt("id"),composant,rs.getDate("date_insertion")));
            }
        }
        return composants;
    }
    public static List<ComposantRecommande> getAllByDate(Connection conn, String dateInsertion) throws SQLException {
        if (conn == null) {
            conn = DatabaseConnection.connect();
        }

        List<ComposantRecommande> composants = new ArrayList<>();
        String sql = "SELECT * FROM Composant WHERE EXTRACT(MONTH FROM dateInsertion) = EXTRACT(MONTH FROM ?) " +
                "AND EXTRACT(YEAR FROM dateInsertion) = EXTRACT(YEAR FROM ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dateInsertion); // Set the first parameter
            ps.setString(2, dateInsertion); // Set the second parameter

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Composant composant = Composant.getById(conn, rs.getInt("composant_id"));
                    composants.add(new ComposantRecommande(rs.getInt("id"), composant, rs.getDate("dateInsertion")));
                }
            }
        }
        return composants;
    }



}
