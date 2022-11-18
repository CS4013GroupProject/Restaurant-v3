import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Chef extends Restaurant {

    private Restaurant currentRestaurant;

    public Chef(Restaurant a) {
        this.currentRestaurant = a;
    }
    /**Menu for chef
    * Does not take parameters, displays CLI for "chef" user to interact with.
    * Allows users to see current orders, Update the status of an order, view completed orders and to return to the main restaurant menu
    */
    public void menuForChef() throws FileNotFoundException, InputMismatchException {
        System.out.println("Menu for restaurant: " + currentRestaurant.getRestaurantId());
        System.out.println("V)iew current orders, U)pdate status, S)ee completed orders, Q)uit");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();

        switch (input.toUpperCase()) {
            case "V":
                seeCurrentOrders();
                break;
            case "U":
                System.out.println("Choose an order to update");
                for (int i = 0; i < currentRestaurant.getCurrentOrders().size(); i++) {
                    System.out.println(i + " " + currentRestaurant.getCurrentOrders().get(i));
                }
                Scanner in = new Scanner(System.in);
                int index = Integer.parseInt(in.nextLine().trim());
                if (index >= 0 && index <= currentRestaurant.getCurrentOrders().size()) {
                    currentRestaurant.getCompletedOrder().add(currentRestaurant.getCurrentOrders().get(index));
                    currentRestaurant.getPaymentPendingOrders().add(currentRestaurant.getCurrentOrders().get(index));
                    currentRestaurant.getCurrentOrders().remove(index);

                }
                break;
            case "S":
                for (Order o : currentRestaurant.getCompletedOrder()) {
                    System.out.println(o);
                    ;
                }
                break;
            case "Q":
                System.out.println("Goodbye");
//                currentRestaurant.run();
        }
    }

    public void seeCurrentOrders() {
        for (Order o : currentRestaurant.getCurrentOrders()) {
            System.out.println(o);
        }
    }
}
