package org.example.model;

import org.example.util.Month;

public class Customer {

    private static Long ID = 0l;
    private Long customerID;
    private String customerName;
    private Month createdAt;

    public Customer(String customerName, Month createdAt) {
        this.customerName = customerName;
        this.createdAt = createdAt;
        ID += 1;
        this.customerID = ID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Month getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        String s =
                "ID: " + getCustomerID() +
                "\tISIM: " + getCustomerName() +
                "\tTARIH: " + getCreatedAt();
        return s;
    }
}
