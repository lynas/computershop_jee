package com.m30cde;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
@WebServlet(name = "PageOperation", urlPatterns = {"/PageOperation"})
public class PageOperation extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            String page = request.getParameter("page").toLowerCase().toString();
            String queryString = "SELECT * FROM  `item` WHERE TYPE =  '" + page + "'";
            request.setAttribute("productList", connDB.getProduct(queryString));

            int cartItemNo = 0;
            for (int i = 0; i < CartOperation.cart.size(); i++) {
                cartItemNo = cartItemNo + CartOperation.cart.get(i).getItemQuantity();

            }
            String cs = "" + cartItemNo;
            request.setAttribute("cartItemNo", cs);

        } catch (Exception e) {
            System.out.println(e);
        }
        getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
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
