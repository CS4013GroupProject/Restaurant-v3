import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Chef extends Restaurant {

    private Restaurant currentRestaurant;

    public Chef(Restaurant a) {
        this.currentRestaurant = a;
    }

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
                if (currentRestaurant.getCurrentOrders().isEmpty()) {
                    System.out.println("No Current Orders");
                    menuForChef();
                } else {
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
                }
                break;
            case "S":
                for (Order o : currentRestaurant.getCompletedOrder()) {
                    System.out.println(o);
                }
                break;
            case "Q":
                System.out.println("Goodbye");
                currentRestaurant.run();
        }
    }

    public void seeCurrentOrders() {
        for (Order o : currentRestaurant.getCurrentOrders()) {
            System.out.println(o);
        }
    }
}