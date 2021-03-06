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
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightRouteProcess extends HttpServlet {

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
            out.println("<title>Servlet addFlightRouteProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFlightRouteProcess at " + request.getContextPath() + "</h1>");
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
        ClientTCP.dout.writeUTF("addFlightRoute");

        String departurePlace = request.getParameter("departurePlace");
        String arrivalPlace = request.getParameter("arrivalPlace");
        int standardPrice = Integer.parseInt(request.getParameter("standardPrice"));

        FlightRoute fr = new FlightRoute(departurePlace, arrivalPlace, standardPrice);

//       ClientFTP.dout.writeUTF(fr.getDeparturePlace());
//       ClientFTP.dout.writeUTF(fr.getArrivalPlace());
//       ClientFTP.dout.writeInt(fr.getStandardPrice());
        ObjectOutputStream objectOutput;
        objectOutput = new ObjectOutputStream(ClientTCP.soc.getOutputStream());
        objectOutput.writeObject(fr);
        
        if (ClientTCP.din.readUTF().equals("addFlightRouteSuc")) {
            request.setAttribute("result", "them duong bay thanh cong");
            request.getRequestDispatcher("/addFlightRoute").forward(request, response);
        } else if (ClientTCP.datadin.readUTF().equals("addFlightRouteFail")) {
            request.setAttribute("result", "them duong bay khong thanh cong");
            request.getRequestDispatcher("/addFlightRoute").forward(request, response);
        } else {
            request.setAttribute("result", "them duong bay khong thanh cong");
            request.getRequestDispatcher("/addFlightRoute").forward(request, response);
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
