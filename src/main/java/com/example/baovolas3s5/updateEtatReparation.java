package com.example.baovolas3s5;

import entity.Reparation;
import util.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/updateEtatReparation")
public class updateEtatReparation extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn=null;
        int idReparation = Integer.parseInt(request.getParameter("idReparation"));
        int idEtat = Integer.parseInt(request.getParameter("idEtat"));
        try{
            conn = DatabaseConnection.connect();
            Reparation.updateEtat(conn,idEtat,idReparation);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
