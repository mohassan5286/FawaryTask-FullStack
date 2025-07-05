package ecommerce;

import java.time.LocalDate;

public class CheckoutService {

    private double weightFee ;

    public CheckoutService(double weightFee) {
        this.weightFee = weightFee;
    }

    public double getWeightFee() {
        return weightFee;
    }

    public void setWeightFee(double weightFee) {
        this.weightFee = weightFee;
    }

    public void checkOut(Cart cart, User user) {

        try {

            System.out.println();

            if (cart.getItems().isEmpty()){
                throw new IllegalArgumentException("cart is empty");
            }

            double totalWeight = 0.0;
            double subtotal = 0.0;

            System.out.println("** Shipment notice ** ");

            for (Product item : cart.getItems()) {
                if (item.getExpiryDate().isPresent()) {
                    LocalDate expiry = item.getExpiryDate().get();
                    if (expiry.isBefore(LocalDate.now()) || expiry.isEqual(LocalDate.now())) {
                        throw new IllegalArgumentException(item.getName() + " is expired");
                    }
                }

                totalWeight += item.getWeight().orElse(0.0) * item.getQuantity();

                String spaces = " ".repeat(Math.max(0, 20 - (item.getName().length() + String.valueOf(item.getQuantity()).length())));

                System.out.println(item.getQuantity() + "x " + item.getName() + spaces + item.getWeight().map(w -> w + " KG").orElse("No weight"));

            }

            System.out.println("Total package weight" + " ".repeat(2) + + totalWeight + " KG");
            System.out.println();

            System.out.println("** Checkout receipt **");

            for (Product item : cart.getItems()) {

                String spaces = " ".repeat(Math.max(0, 20 - (item.getName().length() + String.valueOf(item.getQuantity()).length())));

                double price = item.getQuantity() * item.getWeight().orElse(1.0) * item.getPrice();
                subtotal += price;

                    System.out.println(item.getQuantity() + "x " + item.getName() + spaces + price);

            }

            final double amount = subtotal + weightFee * totalWeight;

            System.out.println("----------------------");
            System.out.println("Subtotal" + " ".repeat(14) + subtotal);
            System.out.println("Shipping" + " ".repeat(14) + weightFee * totalWeight);
            System.out.println("Amount" + " ".repeat(16) + amount);


            if (amount > user.getBalance()) {
                throw new IllegalArgumentException("Your balance is lower than the total cost");
            }

            user.setBalance(user.getBalance() - amount);

        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
        }


    }

}
