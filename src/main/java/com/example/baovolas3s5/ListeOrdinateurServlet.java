package com.example.baovolas3s5;

import entity.*;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listeOrdinateur")
public class ListeOrdinateurServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        String idProblemeParam = request.getParameter("idProbleme");
        try {
            conn = DatabaseConnection.connect();
            List<Marque> listeMarques = Marque.getAll(conn);
            List<Etat> listeEtats = Etat.getAll(conn);
            List<TypeOrdinateur> listeTypeOrdi = TypeOrdinateur.getAll(conn);
            request.setAttribute("listeEtats", listeEtats);
            request.setAttribute("listeMarques", listeMarques);
            request.setAttribute("listeTypeOrdi", listeTypeOrdi);

            if (isNumeric(idProblemeParam)) {
                Integer idProbleme = Integer.parseInt(idProblemeParam);
                List<Reparation> listeReparations = Reparation.listeReparationByProbleme(conn,idProbleme);
                List<TypeProbleme> listeProblemes = TypeProbleme.getAll(conn);

                request.setAttribute("selected",idProblemeParam);
                request.setAttribute("listeProblemes", listeProblemes);
                request.setAttribute("listeReparations", listeReparations);


                request.getRequestDispatcher("pages/reparation/liste.jsp").forward(request, response);
            } else {
                List<Ordinateur> listeOrdinateurs = Reparation.getAllOrdi(conn);
                List<TypeProbleme> listeProblemes = TypeProbleme.getAll(conn);
                List<Reparation> listeReparations = Reparation.getAll(conn);
                request.setAttribute("listeReparations", listeReparations);
                request.setAttribute("listeProblemes", listeProblemes);
                request.setAttribute("listeOrdinateurs", listeOrdinateurs);
                request.getRequestDispatcher("pages/reparation/liste.jsp").forward(request, response);
            }
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

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
