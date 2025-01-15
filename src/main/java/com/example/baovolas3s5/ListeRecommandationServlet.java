package com.example.baovolas3s5;

import entity.*;
import util.DatabaseConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listeRecommendations")
public class ListeRecommandationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn = DatabaseConnection.connect();
            List<ComposantRecommande> listeComposants = ComposantRecommande.getAll(conn);

            request.setAttribute("listeComposants", listeComposants);
            request.getRequestDispatcher("pages/composant/liste.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
        finally {
            if(conn != null) {
                try{
                    conn.close();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        String dateInsertion= request.getParameter("dateInsertion");
        try {
            conn = DatabaseConnection.connect();
            List<ComposantRecommande> listeComposants = ComposantRecommande.getAllByDate(conn,dateInsertion);

            request.setAttribute("listeComposants", listeComposants);
            request.getRequestDispatcher("pages/composant/liste.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
        finally {
            if(conn != null) {
                try{
                    conn.close();

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
