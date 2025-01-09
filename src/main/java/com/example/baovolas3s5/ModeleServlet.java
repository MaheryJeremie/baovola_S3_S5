package com.example.baovolas3s5;


import entity.Marque;
import entity.Modele;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/modeleServlet")
public class ModeleServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn=DatabaseConnection.connect();
            List<Marque>marques=Marque.getAll(conn);
            request.setAttribute("marques",marques);
            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
            }
            request.getRequestDispatcher("pages/modele/insert.jsp").forward(request,response);

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
        String nom=request.getParameter("nom");
        int marqueId=Integer.parseInt(request.getParameter("idMarque"));
        Modele modele=new Modele(nom);
        try {
            conn= DatabaseConnection.connect();
            modele.create(conn,marqueId);
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getMethod() {
                    return "GET"; // Forcer la méthode à GET
                }
            };

            // Ajouter un message d'insertion réussie
            wrappedRequest.setAttribute("message", "Insertion réussie");
            request.getRequestDispatcher("/modeleServlet").forward(wrappedRequest,response);

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
