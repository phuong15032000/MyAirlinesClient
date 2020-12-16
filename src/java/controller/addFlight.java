/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Airbrand;
import model.Aircraft;
import model.Flight;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlight extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addFlight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFlight at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientTCP.dout.writeUTF("getListFLight");
        ClientTCP f = new ClientTCP();
        
        List<Aircraft> aircraftList = new ArrayList<Aircraft>();
        List<FlightRoute> flightRouteList = new ArrayList<FlightRoute>();
        List<Flight> flightList = new ArrayList<Flight>();
        
        ObjectInputStream objectInput = new ObjectInputStream(f.soc.getInputStream());
        
        try {
            Object object1;
            object1 = objectInput.readObject();
            aircraftList = (List<Aircraft>) object1;
            
            Object object2;
            object2 = objectInput.readObject();
            flightRouteList = (List<FlightRoute>) object2;
            
            Object object3;
            object3 = objectInput.readObject();
            flightList = (List<Flight>) object3;
            
            request.setAttribute("aircraftList", aircraftList);
            request.setAttribute("flightRouteList", flightRouteList);
            request.setAttribute("flightList", flightList);
            
            request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAirplane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
