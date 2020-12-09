/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class connectServer extends HttpServlet {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Socket soc;
    public static DataInputStream din = null;
    public static DataOutputStream dout = null;
    public static DataInputStream datadin = null;
    public static DataOutputStream dataout = null;
    public static Socket datasoc;
    public static int PortNo, dataPort;
    public static String Host;

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
            out.println("<title>Servlet connectServer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet connectServer at " + request.getContextPath() + "</h1>");
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
        String username = "test";
        String pass = "test";
        String Host = "localhost";
        String port = "5217";
        String port2 = "1503";
        String[] argument = new String[]{Host, port, username, pass};
        String msg = "";
        String msg2="";
        ClientFTP obj= new ClientFTP();
        ClientFTP2 obj2 = new ClientFTP2();
        String[] argument2 = new String[]{Host, port2, username, pass};
        try {
            msg = obj.connect(argument);
            
            msg2 = obj2.connect(argument2);
        } catch (Exception ex) {
            msg = "Failure";
        }
        if (msg.compareTo("Success")!=0){
            request.getRequestDispatcher("server1error.jsp").forward(request, response);
        } else if (msg2.compareTo("Success")!=0){
            request.getRequestDispatcher("server2error.jsp").forward(request, response);
        } else if (msg.compareTo("Success") == 0 && msg2.compareTo("Success")==0) {
            request.getRequestDispatcher("/checkSession").forward(request, response);
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
