package com.example.baovolas3s5;


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

@WebServlet("/modeleListServlet")
public class ModeleListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        try {
            conn=DatabaseConnection.connect();
            List<Modele>modeles=Modele.getAll(conn);
            request.setAttribute("modeles",modeles);
            request.getRequestDispatcher("pages/modele/liste.jsp").forward(request,response);

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
