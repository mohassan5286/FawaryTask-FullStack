package org.example;

import ecommerce.Cart;
import ecommerce.CheckoutService;
import ecommerce.Store;
import ecommerce.User;
import ecommerce.Product;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean shopping = true;

        User user = new User(5_000);
        Cart cart = new Cart();
        Store store = new Store();
        CheckoutService checkoutService = new CheckoutService(10);

        while (shopping) {

            printMenu();
            System.out.print("Enter option: ");

            int option;
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            option = scanner.nextInt();

            switch (option) {
                case 1 -> store.display();
                case 2 -> AddToCart(scanner, store, cart);
                case 3 -> RemoveFromCart(scanner, store, cart);
                case 4 -> cart.display();
                case 5 -> checkoutService.checkOut(cart, user);
                case 6 -> shopping = false;
                default -> System.out.println("Invalid option. Please choose from 1 to 6.");
            }

            System.out.println();

        }

        System.out.println("Thank you for shopping!");

    }

    private static void printMenu() {

        System.out.println("1. Show Products Available");
        System.out.println("2. Add item to the cart");
        System.out.println("3. Remove item from the cart");
        System.out.println("4. Show Cart Items");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        System.out.println();

    }

    private static Product findProductByName(Store store, String name) {

        for (Product product : store.getProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;

    }

    private static void AddToCart(Scanner scanner, Store store, Cart cart) {

        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        Product product = findProductByName(store, name);
        if (product == null) {
            System.out.println("Product not found.");
            return ;
        }

        System.out.print("Enter quantity (integer): ");
        int quantity = 0 ;
        while (quantity <= 0) {
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.print("Quantity must be integer greater than 0: ");
                }
            }
            else {
                System.out.print("Invalid input. Enter a valid quantity: ");
                scanner.next();
            }
        }

        try {
            if (product.getWeight().isPresent()) {
                System.out.print("Enter weight (double): ");
                double weight = 0.0;
                while (weight <= 0.0) {
                    if (scanner.hasNextDouble()) {
                        weight = scanner.nextDouble();
                        if (weight <= 0.0) {
                            System.out.print("Weight must be greater than 0: ");
                        }
                    } else {
                        System.out.print("Invalid input. Enter a valid weight: ");
                        scanner.next();
                    }
                }
                cart.add(product, quantity, weight);
            }
            else {
                cart.add(product, quantity);
            }

            System.out.println();
            System.out.println("Item added to cart.");
        }
        catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("Error: Item not added to cart. " + e.getMessage());
        }

    }

    private static void RemoveFromCart(Scanner scanner, Store store, Cart cart) {

        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        Product product = findProductByName(store, name);
        if (product == null) {
            System.out.println("Product not found.");
            return ;
        }

        System.out.print("Enter quantity (integer): ");
        int quantity = 0 ;
        while (quantity <= 0) {
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.print("Quantity must be integer greater than 0: ");
                }
            }
            else {
                System.out.print("Invalid input. Enter a valid quantity: ");
                scanner.next();
            }
        }

        try {
            if (product.getWeight().isPresent()) {
                System.out.print("Enter weight (double): ");
                double weight = 0.0;
                while (weight <= 0.0) {
                    if (scanner.hasNextDouble()) {
                        weight = scanner.nextDouble();
                        if (weight <= 0.0) {
                            System.out.print("Weight must be greater than 0: ");
                        }
                    } else {
                        System.out.print("Invalid input. Enter a valid weight: ");
                        scanner.next();
                    }
                }
                cart.remove(product, quantity, weight);
            } else {
                cart.remove(product, quantity);
            }

            System.out.println();
            System.out.println("Item removed from cart.");
        }
        catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("Error: Item not removed from cart. " + e.getMessage());
        }
    }
}