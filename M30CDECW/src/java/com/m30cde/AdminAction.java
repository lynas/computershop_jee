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
@WebServlet(name = "AdminAction", urlPatterns = {"/AdminAction"})
public class AdminAction extends HttpServlet {

    @EJB
    DBConBean connDB;
    ArrayList<User> userList = new ArrayList<User>();

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

        //view cart no start
        int cartItemNo = 0;
        for (int i = 0; i < CartOperation.cart.size(); i++) {
            cartItemNo = cartItemNo + CartOperation.cart.get(i).getItemQuantity();

        }
        String cs = "" + cartItemNo;
        request.setAttribute("cartItemNo", cs);
        //view Cart no end


        String action = request.getParameter("action");

        //For Opening Add section
        if (action.compareTo("add") == 0) {
            request.setAttribute("action", action);
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
        }

        //For going to index Page
        else if (action.compareTo("goToIndexPage") == 0) {

            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

        //For going to admin panel
        else if (action.compareTo("goToAdminPanel") == 0) {

            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
        }

        //Open update operation box
        else if (action.compareTo("update") == 0) {
            String query = "SELECT * FROM `item` WHERE 1 group by type";
            ArrayList<Product> productList = new ArrayList<Product>();

            productList = connDB.getProduct(query);
            request.setAttribute("productList", productList);
            request.setAttribute("action", action);
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
        }

        //open delete operation box
        else if (action.compareTo("delete") == 0) {
            String query = "SELECT * FROM `item` WHERE 1 group by type";
            ArrayList<Product> productList = new ArrayList<Product>();

            productList = connDB.getProduct(query);
            request.setAttribute("productList", productList);
            request.setAttribute("action", action);
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
        }

        //perform add action
        else if (action.compareTo("addNewProduct") == 0) {
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));


            String insertProduct = "INSERT INTO `m30cde`.`item` (`item_id`, `type`, `item_name`, `item_quantity`, `item_price`) "
                    + "VALUES (NULL, '" + productType + "', '" + productName + "', '" + productQuantity + "', '" + productPrice + "');";

            connDB.dataIn(insertProduct);

            request.setAttribute("action", "addSuccess");
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);

        }


        //perforn update action
        else if (action.compareTo("updateProduct") == 0) {
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            String updateQuery = "UPDATE  item SET  item_quantity = '" + productQuantity + "', item_price ='" + productPrice + "' WHERE  item_name='" + productName + "' and type='" + productType + "';";
            connDB.dataIn(updateQuery);

            request.setAttribute("action", "updateSuccess");
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);

        }

        //perform delete action
        else if (action.compareTo("deleteProduct") == 0) {
            String productType = request.getParameter("productType");
            String productName = request.getParameter("productName");
            String deleteQuery = "delete from item where type='" + productType + "' and item_name='" + productName + "';";
            connDB.dataIn(deleteQuery);

            request.setAttribute("action", "deleteSuccess");
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);

        }

        //open user order box
        else if (action.compareTo("userOrder") == 0) {
            String query = "SELECT * FROM `user` WHERE 1";
            userList = connDB.getUserList(query);
            request.setAttribute("userList", userList);

            request.setAttribute("action", action);
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
        }


        //show user order action
        else if (action.compareTo("showUserOrder") == 0) {
            int selectedUserId = 0;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserName().compareTo(request.getParameter("selectedUserName").toString()) == 0) {
                    selectedUserId = userList.get(i).getUserID();
                    break;
                }
            }
            String orderListQuery = "SELECT * FROM `userorders` WHERE user_id=" + selectedUserId + " ORDER BY date DESC";
            ArrayList<MyOrder> orderList = new ArrayList<MyOrder>();
            orderList = connDB.getMyOrder(orderListQuery);

            request.setAttribute("productList", orderList);
            request.setAttribute("listSize", orderList.size());

            request.setAttribute("action", action);
            getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);

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
