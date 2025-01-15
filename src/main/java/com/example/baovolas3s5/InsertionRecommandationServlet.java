package com.example.baovolas3s5;

import entity.Composant;
import entity.ComposantRecommande;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/insertionRecommendation")
public class InsertionRecommandationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn= DatabaseConnection.connect();
            List<Composant> composantList =Composant.getAll(conn);
            request.setAttribute("composants",composantList);
            request.setAttribute("message",request.getAttribute("message"));
            request.getRequestDispatcher("/pages/composant/insertRecommande.jsp").forward(request,response);


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
        try {
            conn= DatabaseConnection.connect();
            String idComposant = request.getParameter("idComposant");
            String dateInsertion = request.getParameter("dateInsertion");
            ComposantRecommande composant=new ComposantRecommande(Composant.getById(conn,Integer.parseInt(idComposant)), Date.valueOf(dateInsertion));
            composant.create(conn);
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getMethod() {
                    return "GET"; // Forcer la méthode à GET
                }
            };

            // Ajouter un message d'insertion réussie
            wrappedRequest.setAttribute("message", "Insertion réussie");
            request.getRequestDispatcher("/insertionRecommendation").forward(wrappedRequest,response);

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
