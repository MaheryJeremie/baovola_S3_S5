package com.example.baovolas3s5;


import entity.Marque;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/marqueServlet")
public class MarqueServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn=DatabaseConnection.connect();
            List<Marque>marques=Marque.getAll(conn);
            request.setAttribute("marques",marques);
            request.getRequestDispatcher("pages/marque/liste.jsp").forward(request,response);

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
        Marque marque=new Marque(nom);
        try {
            conn= DatabaseConnection.connect();
            marque.create(conn);
            request.setAttribute("message","Insertion reussi");
            request.getRequestDispatcher("pages/marque/insert.jsp").forward(request,response);

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
