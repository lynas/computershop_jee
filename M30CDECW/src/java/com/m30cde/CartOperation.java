package com.m30cde;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "CartOperation", urlPatterns = {"/CartOperation"})
public class CartOperation extends HttpServlet {
    @EJB
    DBConBean connDB;
    static ArrayList<Product> cart = new ArrayList<Product>();
    static int cartItemNo;


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

        int selectedItem = Integer.parseInt(request.getParameter("itemQuantity"));
        int itemID = Integer.parseInt(request.getParameter("productID"));
        String page = request.getParameter("page").toLowerCase().toString();
        String iName = request.getParameter("iname").toString();
        double itemPrice = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(itemID, page, iName, selectedItem, itemPrice);

        boolean duplicateProductChecker = false;
        if (cart.isEmpty()) {
            cart.add(product);

        } else {

            for (int i = 0; i < cart.size(); i++) {
                int a = cart.get(i).getItemId();
                int b = product.getItemId();
                if (a == b) {
                    cart.get(i).setItemQuantity(cart.get(i).getItemQuantity() + selectedItem);
                    duplicateProductChecker = true;
                    break;
                }
            }
            if (!duplicateProductChecker) {
                cart.add(product);
            }
        }
        cartItemNo = 0;
        for (int i = 0; i < cart.size(); i++) {
            cartItemNo = cartItemNo + cart.get(i).getItemQuantity();
            System.out.println("item " + i + " = " + cart.get(i).getItemId() + "--" + cart.get(i).getItemType() + "--" + cart.get(i).getItemName() + "--" + cart.get(i).getItemQuantity() + "--" + cart.get(i).getItemPrice());
        }
        String cs = "" + cartItemNo;
        request.setAttribute("cartItemNo", cs);
        String queryString = "SELECT * FROM  `item` WHERE TYPE =  '" + page + "'";
        request.setAttribute("productList", connDB.getProduct(queryString));
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
