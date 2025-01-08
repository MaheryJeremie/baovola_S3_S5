package com.example.baovolas3s5;

import entity.Ordinateur;
import entity.Reparation;
import entity.TypeProbleme;
import util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/typeProbleme")
public class TypeProblemeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Connection conn = null;
        try{
            conn = DatabaseConnection.connect();

            List<TypeProbleme> listeProblemes = TypeProbleme.getAll(conn);
            List<Ordinateur> listeOrdinateurs = Reparation.getAllOrdi(conn);
            request.setAttribute("listeOrdinateurs",listeOrdinateurs);
            request.setAttribute("listeProblemes",listeProblemes);
            request.getRequestDispatcher("pages/reparation/liste.jsp").forward(request,response);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
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
