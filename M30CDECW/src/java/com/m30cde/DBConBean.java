/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m30cde;

import javax.ejb.Singleton;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
@Singleton
public class DBConBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    ArrayList<Product> productList = new ArrayList<Product>();
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DatabaseURL = "jdbc:mysql://localhost/m30cde";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultset = null;
    String dbUserName = "root";
    String dbPassword = "";

    public void dataIn(String query) {
        try {
            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(DatabaseURL, dbUserName, dbPassword);
            statement = (Statement) connection.createStatement();

            statement.execute(query);

        } catch (Exception e) {
            System.out.println(e.toString());

        } finally {
            try {

                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }

    public ArrayList getProduct(String query) {
        productList.removeAll(productList);
        int id;
        String type;
        String name;
        int quantity;
        double price;

        try {
            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(DatabaseURL, dbUserName, dbPassword);
            statement = (Statement) connection.createStatement();
            resultset = (ResultSet) statement.executeQuery(query);

            while (resultset.next()) {

                id = Integer.parseInt(resultset.getObject(1).toString());
                type = (String) resultset.getObject(2);
                name = (String) resultset.getObject(3);
                quantity = Integer.parseInt(resultset.getObject(4).toString());
                price = Double.parseDouble(resultset.getObject(5).toString());
                Product p = new Product(id, type, name, quantity, price);
                productList.add(p);
            }


            statement.execute(query);

        } catch (Exception e) {
            System.out.println(e.toString());

        } finally {
            try {

                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return productList;

    }

    User user = new User(0, null, null, null);

    public User userAuthentication(String query) {
        try {
            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(DatabaseURL, dbUserName, dbPassword);
            statement = (Statement) connection.createStatement();
            resultset = (ResultSet) statement.executeQuery(query);

            while (resultset.next()) {
                user.setUserID(Integer.parseInt(resultset.getObject(1).toString()));
                user.setUserType(resultset.getObject(2).toString());
                user.setUserName(resultset.getObject(3).toString());
                user.setUserPassword(resultset.getObject(4).toString());
            }


        } catch (Exception e) {
            System.out.println(e.toString());

        } finally {
            try {
                //resultset.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return user;

    }

    ArrayList<User> userList = new ArrayList<User>();

    public ArrayList getUserList(String query) {
        int userID;
        String userType;
        String userName;
        String userPassword;
        userList.removeAll(userList);
        try {
            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(DatabaseURL, dbUserName, dbPassword);
            statement = (Statement) connection.createStatement();
            resultset = (ResultSet) statement.executeQuery(query);


            while (resultset.next()) {
                userID = Integer.parseInt(resultset.getObject(1).toString());
                userType = resultset.getObject(2).toString();
                userName = resultset.getObject(3).toString();
                userPassword = resultset.getObject(4).toString();
                User u = new User(userID, userType, userName, userPassword);
                userList.add(u);
            }


        } catch (Exception e) {
            System.out.println(e.toString());

        } finally {
            try {

                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return userList;

    }

    ArrayList<MyOrder> orderList = new ArrayList<MyOrder>();

    public ArrayList getMyOrder(String query) {
        int orderId;
        String productName;
        int productQuantity;
        double productPrice;
        int userId;
        String orderDate;

        try {
            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(DatabaseURL, dbUserName, dbPassword);
            statement = (Statement) connection.createStatement();
            resultset = (ResultSet) statement.executeQuery(query);
            orderList.removeAll(orderList);
            while (resultset.next()) {

                orderId = Integer.parseInt(resultset.getObject(1).toString());
                productName = resultset.getObject(2).toString();
                productQuantity = Integer.parseInt(resultset.getObject(3).toString());
                productPrice = Double.parseDouble(resultset.getObject(4).toString());
                userId = Integer.parseInt(resultset.getObject(5).toString());
                orderDate = resultset.getObject(6).toString();
                MyOrder order = new MyOrder(orderId, productName, productQuantity, productPrice, userId, orderDate);
                orderList.add(order);

            }


        } catch (Exception e) {
            System.out.println(e.toString());

        } finally {
            try {

                statement.close();
                connection.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return orderList;
    }
}
