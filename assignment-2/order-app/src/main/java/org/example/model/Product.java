package org.example.model;

public class Product {

    private static Long ID = 0l;
    private Long productID;
    private String productName;
    private Double value;

    public Product(){}

    public Product(String productName, Double value) {
        this.productName = productName;
        this.value = value;
        ID += 1;
        this.productID = ID;
    }

    public Long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString(){
        String s = "Product ID: " + this.productID +
                "\t\tProduct Name: " + this.productName +
                "\t\tValue: " + this.value;
        return s;
    }
}
