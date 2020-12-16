/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aircraft;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addAirplaneProcess extends HttpServlet {

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
            out.println("<title>Servlet addAirplaneProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addAirplaneProcess at " + request.getContextPath() + "</h1>");
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
        ClientTCP.dout.writeUTF("addAirplane");
        String aircraftName = request.getParameter("aircraftName");
        String airlineBrandId = request.getParameter("airlineBrandId");
        String model = request.getParameter("model");
        int seatNumber = Integer.parseInt(request.getParameter("seatNumber"));

//        ClientFTP.dout.writeUTF(aircraftName);
//        ClientFTP.dout.writeUTF(airlineBrandId);
//        ClientFTP.dout.writeUTF(model);
//        ClientFTP.dout.writeUTF(seatNumber);
        Aircraft a = new Aircraft();
        a.setAircraftName(aircraftName);
        a.setAirbrand(airlineBrandId);
        a.setModel(model);
        a.setSeatNumber(seatNumber);

        ObjectOutputStream objectOutput;
        objectOutput = new ObjectOutputStream(ClientTCP.soc.getOutputStream());
        objectOutput.writeObject(a);

        if (ClientTCP.din.readUTF().equals("addAirplaneSuc")) {
            request.setAttribute("result", "them san bay thanh cong");
            request.getRequestDispatcher("/addAirplane").forward(request, response);
        } else if (ClientTCP.datadin.readUTF().equals("addAirportFail")) {
            request.setAttribute("result", "them san bay khong thanh cong");
            request.getRequestDispatcher("/addAirplane").forward(request, response);
        } else {
            request.setAttribute("result", "them san bay khong thanh cong");
            request.getRequestDispatcher("/addAirplane").forward(request, response);
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
