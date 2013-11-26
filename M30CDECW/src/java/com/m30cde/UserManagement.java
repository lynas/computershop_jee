package com.m30cde;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
@WebServlet(name = "UserManagement", urlPatterns = {"/UserManagement"})
public class UserManagement extends HttpServlet {

    @EJB
    DBConBean connDB;

    HttpSession currentUser;
    private String message = null;
    User user = new User(0, null, null, null);

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
            throws ServletException, IOException, ServletException, IOException {
        //view cart no start
        int cartItemNo = 0;
        for (int i = 0; i < CartOperation.cart.size(); i++) {
            cartItemNo = cartItemNo + CartOperation.cart.get(i).getItemQuantity();

        }
        String cs = "" + cartItemNo;
        request.setAttribute("cartItemNo", cs);
        //view Cart no end

        currentUser = request.getSession(true);

        String action = request.getParameter("action").toString();


        if (action.compareTo("signIn") == 0) {
            getServletContext().getRequestDispatcher("/UserLogIn.jsp").forward(request, response);
        } else if (action.compareTo("signUp") == 0) {
            getServletContext().getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
        } else if (action.compareTo("home") == 0) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (action.compareTo("authenticateUser") == 0) {

            String userName = request.getParameter("userName");
            String userPassword = request.getParameter("userPassword");
            String Query = "SELECT * FROM  `user` WHERE userName =  '" + userName + "' AND userPassword =  '" + userPassword + "'";
            user = connDB.userAuthentication(Query);

            if (user.getUserType() != null) {
                System.out.println("found user");


                if (user.getUserType().compareTo("admin") == 0) {
                    System.out.println("admin");
                    currentUser.setAttribute("activeUser", user);
                    getServletContext().getRequestDispatcher("/AdminPanel.jsp").forward(request, response);
                } else if (user.getUserType().compareTo("customar") == 0) {
                    currentUser.setAttribute("activeUser", user);
                    request.setAttribute("cartSize", CartOperation.cart.size());
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                System.out.println("invalid user");
                message = "[ Invalid User name / password ]";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/UserLogIn.jsp").forward(request, response);
            }

        } else if (action.compareTo("myPage") == 0) {
            getServletContext().getRequestDispatcher("/UserPanel.jsp").forward(request, response);
        } else if (action.compareTo("logOut") == 0) {
            user.setUserID(0);
            user.setUserType(null);
            user.setUserName(null);
            user.setUserPassword(null);
            currentUser.setAttribute("activeUser", user);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        } else if (action.compareTo("chekout") == 0) {


            System.out.println(user.getUserID());
            if (user.getUserID() != 0) {
                Calendar currentDate = Calendar.getInstance(); //Get the current date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
                String dateNow = formatter.format(currentDate.getTime());


                //keeping record of the users order
                for (int i = 0; i < CartOperation.cart.size(); i++) {

                    String insertQuery = "INSERT INTO `m30cde`.`userorders` (`orderId`, `productName`, `productQuantity`, `productSubtotal`, `user_id`, `date`) "
                            + "VALUES (NULL, '" + CartOperation.cart.get(i).getItemName() + "', '" + CartOperation.cart.get(i).getItemQuantity() + "', '" + CartOperation.cart.get(i).getItemPrice() + "', '" + user.getUserID() + "', '" + dateNow + "');";
                    String updateQuery = "UPDATE  `m30cde`.`item` SET  `item_quantity` =  item_quantity - " + CartOperation.cart.get(i).getItemQuantity() + " WHERE  `item`.`item_name` ='" + CartOperation.cart.get(i).getItemName() + "';";
                    connDB.dataIn(insertQuery);
                    connDB.dataIn(updateQuery);
                }


                CartOperation.cart.removeAll(CartOperation.cart);
                getServletContext().getRequestDispatcher("/Checkout.jsp").forward(request, response);
                System.out.println("user available");
            } else {
                System.out.println("user UN available");
                getServletContext().getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
            }
        } else if (action.compareTo("createUser") == 0) {


            System.out.println("got in");
            System.out.println(request.getParameter("field1"));

            boolean fieldEmpty = false;
            for (int i = 1; i < 14; i++) {
                String fieldName = "field" + i;
                String field = request.getParameter(fieldName).toString();

                if (field.compareTo("") == 0) {
                    fieldEmpty = true;
                    request.setAttribute("errorMsg", "[[ All field must be field ]]");
                    getServletContext().getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
                    break;
                }
                System.out.println("f" + i + " : " + field);
            }
            if (!fieldEmpty) {
                System.out.println("All ok");

                String userName = request.getParameter("field1");
                String password = request.getParameter("field2");
                String email = request.getParameter("field3");
                String fullName = request.getParameter("field4");
                String house = request.getParameter("field5");
                String street = request.getParameter("field6");
                String city = request.getParameter("field7");
                String country = request.getParameter("field8");
                String cardType = request.getParameter("field9");
                String cardNo = request.getParameter("field10");
                String cardSecurityNo = request.getParameter("field11");
                String cardHolderName = request.getParameter("field12");
                String expDate = request.getParameter("field13");

                //check existing user name

                String existingUserCheckQuery = "SELECT * FROM `user` WHERE userName='" + userName + "';";
                User newUser = new User(0, null, null, null);


                newUser = connDB.userAuthentication(existingUserCheckQuery);

                if (newUser.getUserID() > 0) {
                    request.setAttribute("errorMsg", "[[ User name taken ]]");
                    getServletContext().getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
                } else {
                    String createUserQuery = "INSERT INTO `m30cde`.`user` (`user_id`, `user_type`, `userName`, `userPassword`, `fulName`, `email`) VALUES (NULL, 'customar', '" + userName + "', '" + password + "', '" + fullName + "', '" + email + "');";
                    connDB.dataIn(createUserQuery);


                    newUser = connDB.userAuthentication(existingUserCheckQuery);

                    //putting info in userAddress table

                    String userAddressQuery = "INSERT INTO `m30cde`.`useraddress` (`a_Id`, `house`, `street`, `city`, `country`, `user_id`) VALUES (NULL, '" + house + "', '" + street + "', '" + city + "', '" + country + "', '" + newUser.getUserID() + "');";
                    // putting info in userPayment table

                    String userPaymentInforQuery = "INSERT INTO `m30cde`.`userpaymentinfo` (`pay_Id`, `cardType`, `cardNo`, `cardSecurityNo`, `cardHolderName`, `expireDate`, `user_id`) VALUES (NULL, '" + cardType + "', '" + cardNo + "', '" + cardSecurityNo + "', '" + cardHolderName + "', '" + expDate + "-1', '" + newUser.getUserID() + "');";

                    connDB.dataIn(userAddressQuery);
                    connDB.dataIn(userPaymentInforQuery);

                    message = "[ Account created successfully. Please Sign In ]";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/UserLogIn.jsp").forward(request, response);

                }


            }


        } else if (action.compareTo("myorder") == 0) {

            String orderListQuery = "SELECT * FROM `userorders` WHERE user_id=" + user.getUserID() + " ORDER BY date DESC";
            ArrayList<MyOrder> orderList = new ArrayList<MyOrder>();
            orderList.removeAll(orderList);
            orderList = connDB.getMyOrder(orderListQuery);
            request.setAttribute("productList", orderList);
            request.setAttribute("action", action);
            request.setAttribute("listSize", orderList.size());
            getServletContext().getRequestDispatcher("/UserPanel.jsp").forward(request, response);
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
