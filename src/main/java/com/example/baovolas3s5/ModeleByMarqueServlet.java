package com.example.baovolas3s5;

import com.google.gson.Gson;
import entity.Modele;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/modeleByMarque")
public class ModeleByMarqueServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.connect();
            int idMarque = Integer.parseInt(request.getParameter("idMarque"));

            // Récupérer les modèles pour la marque donnée
            List<Modele> modeles = Modele.getAllModeleByMarque(conn, idMarque);

            // Convertir la liste des modèles en JSON
            Gson gson = new Gson();
            String json = gson.toJson(modeles);

            // Configurer la réponse
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
