package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reparation {
    private int id;
    private int idOrdinateur;
    private int idTypeProbleme;
    private Timestamp dateDebut;
    private int idEtat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrdinateur() {
        return idOrdinateur;
    }

    public void setIdOrdinateur(int idOrdinateur) {
        this.idOrdinateur = idOrdinateur;
    }

    public int getIdTypeProbleme() {
        return idTypeProbleme;
    }

    public void setIdTypeProbleme(int idTypeProbleme) {
        this.idTypeProbleme = idTypeProbleme;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(int idEtat) {
        this.idEtat = idEtat;
    }

    public Reparation() {
    }

    public Reparation(int id, int idOrdinateur, int idTypeProbleme, Timestamp dateDebut, int idEtat) {
        this.id = id;
        this.idOrdinateur = idOrdinateur;
        this.idTypeProbleme = idTypeProbleme;
        this.dateDebut = dateDebut;
        this.idEtat = idEtat;
    }

    public static List<Ordinateur> getAllOrdi(Connection conn) throws Exception{
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        List<Ordinateur> result = new ArrayList<>();
        String sql = "Select ordinateur_id from Reparation";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result.add(Ordinateur.getById(conn,resultSet.getInt("ordinateur_id")));
            }
        }catch (Exception e){
            throw e;
        }
        return result;
    }
    public static List<Ordinateur> listeOrdiByProbleme(Connection conn,Integer idTypeProbleme) throws Exception{
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        if (idTypeProbleme==null){
            return Reparation.getAllOrdi(conn);
        }
        else{
            List<Ordinateur> result = new ArrayList<>();
            String sql = "Select ordinateur_id from Reparation where 1=1 and type_probleme_id = ?";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try{
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1,idTypeProbleme);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    result.add(Ordinateur.getById(conn,resultSet.getInt("ordinateur_id")));
                }
            }catch (Exception e){
                throw e;
            }
            return result;
        }

    }
}


