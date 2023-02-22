package org.example.service;

import org.example.data.AppData;
import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.Product;
import org.example.util.Month;
import java.util.*;
import java.util.stream.Collectors;

public class AppService {

    AppData appData = new AppData();
    Scanner scanner = new Scanner(System.in);

    public void run(){
        System.out.println("##### SIPARIS UYGULAMASI #####");
        mainMenu();
    }

    private void mainMenu(){
        System.out.println("VERI OLUSTURULUYOR...");
        fastBoot();
        while(true){
            System.out.print("\n1. MUSTERI OLUSTUR" +
                    "\n2. TUM MUSTERILERI LISTELE" +
                    "\n3. TUM FATURALARI LISTELE" +
                    "\n4. ADINDA C HARFI GECEN MUSTERILERI LISTELE" +
                    "\n5. HAZIRAN AYINDA KAYIT OLAN MUSTERILERIN FATURALARININ TOPLAM TUTARI" +
                    "\n6. 1500TL UZERI FATURALARI LISTELE" +
                    "\n7. 1500TL USTU FATURALARIN ORTALAMASINI HESAPLA" +
                    "\n8. 500TL ALTINDA FATURALARA SAHIP OLAN MUSTERILERI LISTELE" +
                    "\n9. CIKIS" +
                    "\nIslem seciniz: ");
            int islem = scanner.nextInt();
            if(islem == 1){
                createCustomer();
            } else if (islem == 2) {
                listCustomers();
            } else if (islem == 3) {
                listAllInvoices();
            } else if (islem == 4) {
                listCustomersByName('C');
            } else if (islem == 5) {
                sumAllInvoiceValuesByCustomerCreatedAt(Month.HAZIRAN);
            } else if (islem == 6) {
                filterInvoicesByMinValue(1500);
            } else if (islem == 7) {
                averageOfInvoicesByMinValue(1500);
            } else if (islem == 8) {
                listCustomersByMaxInvoiceValues(500);
            } else if (islem == 9) {
                System.out.println("Cikis yapiliyor...");
                break;
            } else {
                System.out.println("Hatali tuslama yaptiniz..!");
            }
        }
    }

    private boolean createInvoices(Customer customer){
        try{
            Invoice invoice;
            List<Product> products;
            Month createdAt;
            Long ownerID;
            Random randomVal1 = new Random();
            ownerID = customer.getCustomerID();
            for(int i = 0; i < randomVal1.nextInt(3,20);i++){
                products = fastCreateProductList();
                createdAt = Month.randomMonthSelector();
                invoice = new Invoice(products, createdAt, ownerID);
                appData.getInvoices().put(invoice.getInvoiceID(), invoice);
            }
            System.out.println(customer.getCustomerName() + " icin faturalar olusturuldu.");
            return true;
        }catch (Exception e){
            System.out.println("FATURALAR OLUSTURULAMADI..!\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //yeni müsteri olustur
    private boolean createCustomer(){
        System.out.println("### MUSTERI OLUSTURMA ###");
        try{
            System.out.print("Musteri adi: ");
            scanner.nextLine();
            String customerName = scanner.nextLine();
            Month createdAt = Month.randomMonthSelector();
            Customer customer = new Customer(customerName, createdAt);
            appData.getCustomers().put(customer.getCustomerID(), customer);
            System.out.println("Musteri olusturuldu.\t\t" + customer.toString());
            createInvoices(customer);
            return true;
        }catch (Exception e){
            System.out.println("MUSTERI OLUSTURULAMADI..!\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // tüm müsterileri listele
    private void listCustomers(){
        System.out.println("### MUSTERILERI LISTELEME ###");
        appData.getCustomers().values().stream().forEach(c -> System.out.println(c.toString()));
    }

    // c harfi gecen musteri listele
    private boolean listCustomersByName(Character c){
        System.out.println("### HARF ICERIGINE GORE MUSTERI LISTELEME ###");
        try{
            appData.getCustomers().values().stream()
                    .filter(customer -> customer.getCustomerName().contains(c.toString().toUpperCase()) || customer.getCustomerName().contains(c.toString().toLowerCase()))
                    .collect(Collectors.toList()).stream()
                    .forEach(customer -> System.out.println(customer.toString()));
            return true;
        }catch (Exception e){
            System.out.println("Listeleme isleminde hata olustu..\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // haziran ayında kayıt olan müsterilerin faturalarının toplam tutarı
    private boolean sumAllInvoiceValuesByCustomerCreatedAt(Month month){
        System.out.println("### MUSTERI KAYIT AYINA GORE FATURA TOPLAMI ###");
        try{
            appData.getCustomers().values().stream()
                    .filter(customer -> customer.getCreatedAt().equals(month))
                    .collect(Collectors.toList()).stream()
                    .forEach(c ->
                            System.out.println(
                                    c.getCustomerName() +
                                            "\tTOPLAM: " +
                                            appData.getInvoices().values().stream()
                                                    .filter(invoice -> invoice.getOwnerID().equals(c.getCustomerID()))
                                                    .mapToDouble(Invoice::calculateTotalValue)
                                                    .sum())
                            );
            return true;
        }catch (Exception e){
            System.out.println("HATA OLUSTU..!\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // tüm faturaları listele
    private boolean listAllInvoices(){
        System.out.println("### TUM FATURALARI LISTELEME ###");
        try{
            appData.getInvoices().values().stream()
                    .forEach(invoice -> System.out.println(invoice.toString()));
            return true;
        }catch (Exception e){
            System.out.println("Faturalar listelerken hata olustu...\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 1500tl üstündeki faturaları listele
    private boolean filterInvoicesByMinValue(double minValue){
        System.out.println("### MIN TUTARA GORE FATURA LISTELEME ###");
        try{
            appData.getInvoices().values().stream()
                    .filter(invoice -> invoice.calculateTotalValue() >= minValue)
                    .collect(Collectors.toList()).stream()
                    .forEach(invoice -> System.out.println(invoice.toString()));
            return true;
        }catch (Exception e){
            System.out.println("Faturalar listelenirken hata olustu...\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 1500 ustu faturaların ortalamasını hesapla
    private boolean averageOfInvoicesByMinValue(double minValue){
        System.out.println("### MIN TUTARA GORE FATURA ORTALAMASINI HESAPLAMA ###");
        try{
            System.out.println(
                    minValue + " TL uzeri faturalarin ortalamasi:\t" +
                    appData.getInvoices().values().stream()
                            .filter(invoice -> invoice.calculateTotalValue() >= minValue)
                            .collect(Collectors.toList()).stream()
                            .mapToDouble(Invoice::calculateTotalValue).average()
                    + " TL"
            );
            return true;
        }catch (Exception e){
            System.out.println("Faturalar listelenirken hata olustu...\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 500 tl altındaki faturalara sahip olan musterileri listele
    private boolean listCustomersByMaxInvoiceValues(double maxValue){
        System.out.println("### MAX TUTARA GORE MUSTERI LISTELEME ###");
        try{
            appData.getInvoices().values().stream()
                    .filter(invoice -> invoice.calculateTotalValue() < maxValue)
                    .forEach(invoice -> System.out.println(appData.getCustomers().get(invoice.getOwnerID()).toString()));
            return true;
        }catch (Exception e){
            System.out.println("Faturalara gore musteriler listelenirken hata olustu...\t" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void fastBoot(){
        fastCreateProducts();
        fastCreateCustomers();
        fastCreateInvoices();
    }

    private void fastCreateInvoices(){
        try{
            Invoice invoice;
            List<Product> products;
            Month createdAt;
            Long ownerID;
            Random randomVal1 = new Random();
            for(Customer customer: appData.getCustomers().values()){
                ownerID = customer.getCustomerID();
                for(int i = 0; i < randomVal1.nextInt(3,20);i++){
                    products = fastCreateProductList();
                    createdAt = Month.randomMonthSelector();
                    invoice = new Invoice(products, createdAt, ownerID);
                    appData.getInvoices().put(invoice.getInvoiceID(), invoice);
                }
            }
        }catch (Exception e){
            System.out.println("HIZLI FATURA OLUSTURMADA HATA OLUSTU: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Product> fastCreateProductList() throws Exception{
        try{
            List<Product> productList = new ArrayList<>();
            List<Product> allProducts = appData.getProducts().values().stream().toList();
            Random randomVal1 = new Random();
            Random randomVal2 = new Random();
            for(int i = 0; i < 3 + randomVal1.nextInt(20); i++){
                productList.add(allProducts.get(randomVal2.nextInt(allProducts.size())));
            }
            return productList;
        }catch (Exception e){
            System.out.println("URUN LISTESI OLUSTURULURKEN HATA OLUSTU: "+ e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void fastCreateCustomers(){
        try{
            Customer customer;
            String customerName;
            Month createdAt;
            for(int i = 0; i < 20; i++){
                customerName = i+1 + ". musteri";
                createdAt = Month.randomMonthSelector();
                customer = new Customer(customerName, createdAt);
                appData.getCustomers().put(customer.getCustomerID(), customer);
            }
        }catch (Exception e){
            System.out.println("MUSTERILER OLUSTURULURKEN HATA OLUSTU: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private void fastCreateProducts(){
        try{
            Product product;
            String productName;
            Double productValue;
            for(int i = 0; i < 10; i++){
                productName = i+1 + ". urun";
                productValue = (Math.random() * 200);
                productValue = productValue*100;
                productValue = (double)(productValue.intValue());
                productValue = productValue / 100;
                product = new Product(productName, productValue);
                appData.getProducts().put(product.getProductID(), product);
            }
        }catch (Exception e){
            System.out.println("URUNLER OLUSTURULURKEN HATA OLUSTU: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
