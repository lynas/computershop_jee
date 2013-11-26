package com.m30cde;

/**
 * @author : Md Sazzad Islam
 * @ID : 4628965
 */
public class Product {
    private int itemId;
    private String itemType;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;

    public Product(int id, String type, String name, int quantity, double price) {
        itemId = id;
        itemType = type;
        itemName = name;
        itemQuantity = quantity;
        itemPrice = price;
    }

    public void setItemId(int id) {
        itemId = id;
    }

    public int getItemId() {
        return itemId;
    }


    public void setItemType(String type) {
        itemType = type;
    }

    public String getItemType() {
        return itemType;
    }


    public void setItemName(String name) {
        itemName = name;
    }

    public String getItemName() {
        return itemName;
    }


    public void setItemQuantity(int quantity) {
        itemQuantity = quantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }


    public void setItemPrice(double price) {
        itemPrice = price;
    }

    public double getItemPrice() {
        return itemPrice;
    }


}
