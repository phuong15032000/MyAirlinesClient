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
import model.Airport;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightRoute extends HttpServlet {

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
            out.println("<title>Servlet addFlightRoute</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFlightRoute at " + request.getContextPath() + "</h1>");
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
        ClientFTP.dout.writeUTF("getListFlightRoute");
        ClientFTP f = new ClientFTP();
        List<FlightRoute> flightRouteList = new ArrayList<FlightRoute>();
        ObjectInputStream objectInput = new ObjectInputStream(f.soc.getInputStream());
        
        List<Airport> airportList = new ArrayList<Airport>();
        try {
            Object object;
            object = objectInput.readObject();
            flightRouteList = (List<FlightRoute>) object;
            
            Object object2;
            object2 = objectInput.readObject();
            airportList = (List<Airport>) object2;
            
            request.setAttribute("result", "load dc list rui");
            request.setAttribute("flightRouteList", flightRouteList);
            
            request.setAttribute("airportList", airportList);
            request.getRequestDispatcher("addFlightRoute.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAirport.class.getName()).log(Level.SEVERE, null, ex);
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
