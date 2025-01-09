package com.example.baovolas3s5;


import entity.Marque;
import entity.Modele;
import entity.Ordinateur;
import entity.TypeOrdinateur;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/ordinateurServlet")
public class OrdinateurServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn=DatabaseConnection.connect();
            List<Marque>marques=Marque.getAll(conn);
            List<TypeOrdinateur>types=TypeOrdinateur.getAll(conn);
            request.setAttribute("marques",marques);
            request.setAttribute("types",types);
            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
            }
            request.getRequestDispatcher("pages/ordi/insert.jsp").forward(request, response);


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
        int idMarque=Integer.parseInt(request.getParameter("idMarque"));
        int idModele=Integer.parseInt(request.getParameter("idModele"));
        int idType=Integer.parseInt(request.getParameter("idType"));
        String datetimeInput = request.getParameter("date"); // Exemple : "2025-01-08T14:30"

// Conversion du format
        String timestampFormat = datetimeInput.replace("T", " ") + ":00"; // Ajout des secondes

// Création de l'objet Timestamp
        Timestamp date = Timestamp.valueOf(timestampFormat);

        try {
            conn= DatabaseConnection.connect();
            Marque marque=Marque.getById(conn,idMarque);
            Modele modele=Modele.getById(conn,idModele);
            TypeOrdinateur typeOrdinateur=TypeOrdinateur.getById(conn,idType);
            Ordinateur ordinateur=new Ordinateur(marque,typeOrdinateur,modele,date);
            ordinateur.create(conn);
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getMethod() {
                    return "GET"; // Forcer la méthode à GET
                }
            };

            // Ajouter un message d'insertion réussie
            wrappedRequest.setAttribute("message", "Insertion réussie");
            request.getRequestDispatcher("/ordinateurServlet").forward(wrappedRequest,response);

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

