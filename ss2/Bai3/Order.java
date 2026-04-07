package org.example.ss2.Bai3;

import java.util.Date;

public class Order {
    String orderId;
    String name;
    int price;
    Date date;

    public Order(String orderId, String name, int price, Date date) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}