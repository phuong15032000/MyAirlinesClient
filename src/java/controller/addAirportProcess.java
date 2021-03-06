/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static controller.ClientTCP.datadin;
import static controller.ClientTCP.dataout;
import static controller.ClientTCP.datasoc;
import static controller.ClientTCP.din;
import static controller.ClientTCP.dout;
import java.io.ObjectOutputStream;
import model.Airport;
/**
 *
 * @author DELL
 */
public class addAirportProcess extends HttpServlet {

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
            out.println("<title>Servlet addAirportProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addAirportProcess at " + request.getContextPath() + "</h1>");
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

        
        ClientTCP.dout.writeUTF("addAirport");
        
        String cityName = request.getParameter("cityName");
        String airportName = request.getParameter("airportName");
        Airport a = new Airport();
        a.setAirportName(airportName);
        a.setCityName(cityName);
        ObjectOutputStream objectOutput;
            objectOutput = new ObjectOutputStream(ClientTCP.soc.getOutputStream());
            objectOutput.writeObject(a);
//        ClientFTP.dout.writeUTF(cityName);
//        ClientFTP.dout.writeUTF(airportName);

        if (ClientTCP.din.readUTF().equals("addAirportSuc")) {
            request.setAttribute("result", "them san bay thanh cong");
            request.getRequestDispatcher("/addAirport").forward(request, response);
        } else if (ClientTCP.datadin.readUTF().equals("addAirportFail")){
            request.getRequestDispatcher("addAirportFail.jsp").forward(request, response);
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
