package entity;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reparation {
    private int id;
    private Ordinateur ordinateur;
    private int idTypeProbleme;
    private Timestamp dateDebut;
    private int idEtat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ordinateur getOrdinateur() {
        return ordinateur;
    }

    public void setOrdinateur(Ordinateur ordinateur) {
        this.ordinateur = ordinateur;
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

    public Reparation(int id, Ordinateur idOrdinateur, int idTypeProbleme, Timestamp dateDebut, int idEtat) {
        this.id = id;
        this.ordinateur = idOrdinateur;
        this.idTypeProbleme = idTypeProbleme;
        this.dateDebut = dateDebut;
        this.idEtat = idEtat;
    }

    public static void updateEtat(Connection conn,int etat, int id) throws Exception {
        if(conn == null){
            conn = DatabaseConnection.connect();
        }
        PreparedStatement pst = null;
        String sql = "update Reparation set etat_id = ? where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1,etat);
            pst.setInt(2,id);
            pst.execute();
        }catch (Exception e){
            throw e;
        }finally {
            if(pst!=null){
                pst.close();
            }
        }
    }

    public static List<Reparation> getAll(Connection conn) throws Exception{
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        List<Reparation> result = new ArrayList<>();
        String sql = "Select * from Reparation";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                result.add(new Reparation(rs.getInt("id"),Ordinateur.getById(conn,rs.getInt("ordinateur_id")),rs.getInt("type_probleme_id"),rs.getTimestamp("date_debut"),rs.getInt("etat_id")));
            }
        }catch (Exception e){
            throw e;
        }
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
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
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static List<Reparation> listeReparationByProbleme(Connection conn,Integer idTypeProbleme) throws Exception{
        if (conn==null){
            conn= DatabaseConnection.connect();
        }
        if (idTypeProbleme==null){
            return Reparation.getAll(conn);
        }
        else{
            List<Reparation> result = new ArrayList<>();
            String sql = "Select * from Reparation where type_probleme_id = ?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            try{
                pst = conn.prepareStatement(sql);
                pst.setInt(1,idTypeProbleme);
                rs = pst.executeQuery();
                while(rs.next()){
                    result.add(new Reparation(rs.getInt("id"),Ordinateur.getById(conn,rs.getInt("ordinateur_id")),rs.getInt("type_probleme_id"),rs.getTimestamp("date_debut"),rs.getInt("etat_id")));
                }
            }catch (Exception e){
                throw e;
            }
            return result;
        }

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
    public static List<Ordinateur> listeRetourByTypeOrdiAndProbleme(Connection conn, String idTypeOrdi, String idProbleme) throws Exception{
        if (conn == null) {
            conn = DatabaseConnection.connect();
        }

        List<Ordinateur> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT o.id " +
                        "FROM Ordinateur o " +
                        "JOIN TypeOrdinateur to ON o.type_id = to.id " +
                        "JOIN Reparation r ON o.id = r.ordinateur_id " +
                        "JOIN TypeProbleme tp ON r.type_probleme_id = tp.id " +
                        "JOIN Etat e ON r.etat_id = e.id " +
                        "WHERE e.id = 4"
        );
        List<String> params = new ArrayList<>();
        if (idTypeOrdi != null) {
            sql.append(" AND to.id = ?");
            params.add(idTypeOrdi);
        }
        if (idProbleme != null) {
            sql.append(" AND tp.id = ?");
            params.add(idProbleme);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setInt(i + 1, Integer.parseInt(params.get(i)));
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(Ordinateur.getById(conn, resultSet.getInt("id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des ordinateurs.", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}


