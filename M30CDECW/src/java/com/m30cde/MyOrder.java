package com.m30cde;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
public class MyOrder {
    private int orderId;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private int userId;
    private String orderDate;


    public MyOrder(int oid, String pn, int pq, double pp, int uid, String od) {
        orderId = oid;
        productName = pn;
        productQuantity = pq;
        productPrice = pp;
        userId = uid;
        orderDate = od;
    }

    public int getOrderId() {

        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPdoductQuantity() {
        return productQuantity;
    }

    public double getProductPrice() {

        return productPrice;
    }

    public int getUserId() {

        return userId;
    }

    public String getOrderDate() {
        return orderDate;
    }


}
