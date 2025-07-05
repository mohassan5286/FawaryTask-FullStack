package ecommerce;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Store {
    List<Product> products = new ArrayList<>() ;

    public Store() {
        loadProducts();
    }

    public List<Product> getProducts() {
        return products;
    }

    private void loadProducts(){

        products.add(new Product("Apple", 20.0, 1, 50.0, LocalDate.of(2025, 8, 20)));
        products.add(new Product("Banana", 10.0, 1, 30.0, LocalDate.of(2025, 8, 5)));
        products.add(new Product("Meat", 150.0, 1, 100.0, LocalDate.of(2023, 9, 15)));
        products.add(new Product("TV", 10_000.0, 15));
        products.add(new Product("Phone", 5_000.0, 20));

    }

    public void display(){

        System.out.println();

        if (products.isEmpty()){
            System.out.println("Empty");
        }

        else {
            int i = 1 ;
            for (Product item: products) {
                System.out.println(i + ". Name:" + item.getName() + " ".repeat(15 - item.getName().length()) +  "price:" +  item.getPrice() + " ".repeat(15 - String.valueOf(item.getPrice()).length()) + "Quantity:" + item.getQuantity() + " ".repeat(15 - String.valueOf(item.getQuantity()).length()) + "Weight:" + item.getWeight().map(w -> w + " KG").orElse("No weight"));
                i += 1 ;
            }
        }

    }

}
