/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m30cde;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LynAs
 */
@WebServlet(name = "ProductList", urlPatterns = {"/ProductList"})
public class ProductList extends HttpServlet {

    @EJB
    DBConBean connDB;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.compareTo("ViewList") == 0) {
            String query = "SELECT * FROM `item` WHERE type='" + request.getParameter("selectedItemType") + "'";
            ArrayList<Product> productList = new ArrayList<Product>();

            productList = connDB.getProduct(query);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                for (int i = 0; i < productList.size(); i++) {
                    out.println("<option >" + productList.get(i).getItemName() + "</option>");
                }


            } finally {
                out.close();
            }
        } else if (action.compareTo("ViewDetails") == 0) {


            String query = "SELECT * FROM `item` WHERE type='" + request.getParameter("selectedItemType") + "' and item_name='" + request.getParameter("selectedItemName") + "'";
            ArrayList<Product> productList = new ArrayList<Product>();

            productList = connDB.getProduct(query);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {

                out.println("<tr><td>Product Quantity</td><td><input type=\"text\" name=\"productQuantity\" id=\"productQ\" value='" + productList.get(0).getItemQuantity() + "' /></td></tr>");
                out.println("tr><td>Unite Price:</td><td><input type=\"text\" name=\"productPrice\" id=\"productP\" value='" + productList.get(0).getItemPrice() + "' /></td></tr>");
                out.println("<tr><td></td><td><input type=\"submit\" value=\"Update\" /></td></tr>");


            } finally {
                out.close();
            }


        }


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
