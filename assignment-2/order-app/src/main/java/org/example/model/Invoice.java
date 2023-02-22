package org.example.model;

import org.example.util.Month;
import java.util.List;

public class Invoice {

    private static Long ID = 0l;
    private Long invoiceID;
    private List<Product> products;
    private Month createdAt;
    private Long ownerID;

    public Invoice(List<Product> products, Month createdAt, Long ownerID) {
        this.products = products;
        this.createdAt = createdAt;
        ID += 1;
        this.invoiceID = ID;
        this.ownerID = ownerID;
    }

    public Long getInvoiceID() {
        return invoiceID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Month getCreatedAt() {
        return createdAt;
    }
    public Long getOwnerID(){
        return ownerID;
    }

    public Double calculateTotalValue(){
        return getProducts().stream().mapToDouble(Product::getValue).sum();
    }

    @Override
    public String toString(){
        String s = "ID: " + this.invoiceID +
                "\t\tOwner ID: " + this.ownerID +
                "\t\tCreatedAt: " + this.createdAt;
        for (Product product:this.products) {
            s += "\n" + product.toString();
        }
        s += "\n" + "Total Value: " + calculateTotalValue();
        return s;
    }
}
