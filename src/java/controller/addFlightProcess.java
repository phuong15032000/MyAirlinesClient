/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import function.addFlightFunc;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aircraft;
import model.Flight;

/**
 *
 * @author DELL
 */
public class addFlightProcess extends HttpServlet {

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
            out.println("<title>Servlet addFlightProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFlightProcess at " + request.getContextPath() + "</h1>");
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
        ClientTCP.dout.writeUTF("addFlight");
        
        addFlightFunc func = new addFlightFunc();
        Aircraft ac = func.getAircraftById(request.getParameter("aircraft"));
        int airplane = Integer.parseInt(request.getParameter("aircraft"));
        int flightRoute = Integer.parseInt(request.getParameter("flightRoute"));
        String departureTimeString = request.getParameter("departureTime");
        String arrivalTimeString = request.getParameter("arrivalTime");

        String date1 = departureTimeString.substring(0, 10);
        String time1 = departureTimeString.substring(11, 16);
        String date2 = arrivalTimeString.substring(0, 10);
        String time2 = arrivalTimeString.substring(11, 16);

        departureTimeString = date1 + " " + time1;
        arrivalTimeString = date2 + " " + time2;

        Flight flight = new Flight();
        flight.setFlightRouteId(flightRoute);
        flight.setAircraftId(airplane);
        flight.setDepartureTime(departureTimeString);
        flight.setArrivalTime(arrivalTimeString);
        flight.setTotalSeats(ac.getSeatNumber());
        flight.setAvailableSeats(ac.getSeatNumber());
        flight.setOrderedSeats(0);

        ObjectOutputStream objectOutput;
        objectOutput = new ObjectOutputStream(ClientTCP.soc.getOutputStream());
        objectOutput.writeObject(flight);

        if (ClientTCP.din.readUTF().equals("addFlightSuc")) {
            request.setAttribute("result", "them chuyen san bay thanh cong");
            request.getRequestDispatcher("/addFlight").forward(request, response);
        } else if (ClientTCP.datadin.readUTF().equals("addFlightFail")) {
            request.setAttribute("result", "them chuyen bay khong thanh cong");
            request.getRequestDispatcher("/addFlight").forward(request, response);
        } else {
            request.setAttribute("result", "them chuyen bay khong thanh cong");
            request.getRequestDispatcher("/addFlight").forward(request, response);
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
