package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/atelier_reparation";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Dbamanager1";

    public static Connection connect() {
        Connection conn = null;
        try {
            // Établir la connexion
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
        return conn;
    }

    /*public static void main(String[] args) {
        // Tester la connexion
        Connection connexion = connect();

        // Fermer la connexion si elle a été établie
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }*/
}
