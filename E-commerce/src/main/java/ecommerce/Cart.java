package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Product> items = new ArrayList<>() ;

    public List<Product> getItems() {
        return items;
    }

    public void add(Product product, int requiredQuantity) {

            boolean found = false;

            if (requiredQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("The available quantity is lower than you want");
            }

            for (Product item : items) {

                if (item.getName().equals(product.getName())) {
                    found = true;
                    item.setQuantity(item.getQuantity() + requiredQuantity);
                    break ;
                }

            }

            if (!found) {
                Product item = new Product(product.getName(), product.getPrice(), requiredQuantity, product.getExpiryDate().orElse(null));
                items.add(item);
            }

            product.setQuantity(product.getQuantity() - requiredQuantity);

    }

    public void add(Product product, int requiredQuantity, double requiredWeight) {

        boolean found = false;

        if (requiredWeight * requiredQuantity > product.getWeight().get()) {
            throw new IllegalArgumentException("The available weight is lower than you want");
        }

        for (Product item : items) {

            if (item.getName().equals(product.getName()) && item.getWeight().get() == requiredWeight) {
                found = true;
                item.setQuantity(item.getQuantity() + requiredQuantity);
                break;
            }

        }

        if (!found) {
            Product item = new Product(product.getName(), product.getPrice(), requiredQuantity, requiredWeight,  product.getExpiryDate().orElse(null));
            items.add(item);
        }

        product.setWeight(product.getWeight().get() - requiredWeight * requiredQuantity );

    }

    public void remove(Product product, int removedQuantity) {

        boolean found = false;

        for (Product item : items) {

            if (item.getName().equals(product.getName())) {

                if (removedQuantity > item.getQuantity()){
                    throw new IllegalArgumentException("You want to remove more than you have");
                }

                found = true;
                item.setQuantity(item.getQuantity() - removedQuantity);
                product.setQuantity(product.getQuantity() + removedQuantity);

                if (item.getQuantity() == 0){
                    items.remove(item);
                }

                break ;

            }

        }

        if (!found) {
            throw new IllegalArgumentException("The item you want to remove isn't exist");
        }
    }

    public void remove(Product product, int removedQuantity, double removedWeight) {

        boolean found = false;

        for (Product item : items) {

            if (item.getName().equals(product.getName()) && item.getWeight().get() == removedWeight) {

                if (removedQuantity > item.getQuantity()){
                    throw new IllegalArgumentException("You want to remove more than you have");
                }

                found = true;
                item.setQuantity(item.getQuantity() - removedQuantity);
                product.setWeight(product.getWeight().get() + removedWeight * removedQuantity );
                if (item.getQuantity() == 0){
                    items.remove(item);
                }
                break;

            }

        }

        if (!found) {
            throw new IllegalArgumentException("The item you want to remove isn't exist");
        }
    }

    public void display(){

        System.out.println();

        if (items.isEmpty()){
            System.out.println("Empty");
        }

        else {
            int i = 1 ;
            for (Product item: items) {
                System.out.println(i + ". Name:" + item.getName() + " ".repeat(15 - item.getName().length()) +  "price:" +  item.getPrice() + " ".repeat(15 - String.valueOf(item.getPrice()).length()) + "Quantity:" + item.getQuantity() + " ".repeat(15 - String.valueOf(item.getQuantity()).length()) + "Weight:" + item.getWeight().map(w -> w + " KG").orElse("No weight"));
                i += 1 ;
            }
        }

    }

}
