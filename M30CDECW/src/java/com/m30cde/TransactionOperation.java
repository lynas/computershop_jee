
package com.m30cde;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
@WebServlet(name = "TransactionOperation", urlPatterns = {"/TransactionOperation"})
public class TransactionOperation extends HttpServlet {

    private String crtOP = "";

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


        if (request.getParameter("action").toString().compareTo("clearCart") == 0) {
            try {
                this.crearCart();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (request.getParameter("action").toString().compareTo("summary") == 0) {
            try {
                int productID = Integer.parseInt(request.getParameter("pid"));
                int removeAction = Integer.parseInt(request.getParameter("remove"));
                System.out.println("REMOVE ACTION: " + removeAction);
                if (removeAction == 1) {
                    this.removeOneProduct(productID);
                }
            } catch (Exception e) {
                System.out.println(e);

            }
        }
        getServletContext().getRequestDispatcher("/ShoppingCartController").forward(request, response);

    }

    public void crearCart() {

        CartOperation.cart.removeAll(CartOperation.cart);
    }

    public void removeOneProduct(int selectedProduct) {
        for (int i = 0; i < CartOperation.cart.size(); i++) {
            if (selectedProduct == CartOperation.cart.get(i).getItemId()) {
                CartOperation.cart.remove(CartOperation.cart.get(i));
                break;
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
