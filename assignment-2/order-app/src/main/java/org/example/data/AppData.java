package org.example.data;

import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.Product;

import java.util.HashMap;

public class AppData {

    private HashMap<Long, Customer> customers;
    private HashMap<Long, Invoice> invoices;
    private HashMap<Long, Product> products;

    public AppData() {
        this.customers = new HashMap<>();
        this.invoices = new HashMap<>();
        this.products = new HashMap<>();
    }

    public HashMap<Long, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(HashMap<Long, Customer> customers) {
        this.customers = customers;
    }

    public HashMap<Long, Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(HashMap<Long, Invoice> invoices) {
        this.invoices = invoices;
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }
}
