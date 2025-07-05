package ecommerce;

import java.time.LocalDate;
import java.util.Optional;

public class Product {

    private String name;
    private double price;
    private int quantity;
    private Double weight;
    private LocalDate expiryDate;

    public Product(String name, double price, int quantity) {
        this.name = name ;
        this.price = price ;
        this.quantity = quantity ;
    }

    public Product(String name, double price, int quantity, double weight) {
        this(name, price, quantity);
        this.weight = weight ;
    }

    public Product(String name, double price, int quantity, LocalDate expiryDate) {
        this(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    public Product(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        this(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public Optional<Double> getWeight() {
        return Optional.ofNullable(weight);
    }
    public Optional<LocalDate> getExpiryDate() {
        return Optional.ofNullable(expiryDate);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

}