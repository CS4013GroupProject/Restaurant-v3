import com.sun.jdi.InvalidTypeException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Waiter extends Restaurant {

    private Restaurant currentRestaurant;

    public Waiter(Restaurant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public void menuForWaiters() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);


        System.out.println("Menu for restaurant: " + currentRestaurant.getRestaurantId());
        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, Cr)eate order, V)iew Menu, View Re)servations, Q)uit ");
        String input = in.nextLine().trim();
        if (input.equalsIgnoreCase("R")) {
            System.out.println(currentRestaurant.getRestaurantId());
        } else if (input.equalsIgnoreCase("N")) {
            System.out.println(currentRestaurant.getNumberOfTables());
        } else if (input.equalsIgnoreCase("C")) {
            System.out.println(currentRestaurant.getCapacity());
        } else if (input.equalsIgnoreCase("V")) {
            viewMenu();
        } else if (input.equalsIgnoreCase("Cr")) {
            createOrder();
        } else if (input.equalsIgnoreCase("Re")) {
            for (TableReservation s : currentRestaurant.getListOfReservations()) {
                System.out.println(s);
            }
        } else if (input.equalsIgnoreCase("Q")) {
            System.out.println("Goodbye");
            currentRestaurant.run();
            return;
        }


    }


    public void createOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table number");
        int tableNumber = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Enter number of people");
        int numberOfPeople = Integer.parseInt(scanner.nextLine().trim());
        Order order = new Order(tableNumber, currentRestaurant);
        System.out.println(order + "order");
        currentRestaurant.viewMenu();
        System.out.println("\nEnter each food one by one:");
        boolean b = true;
        for (int i = 1; i <= numberOfPeople; i++) {
            b = true;
            System.out.println("Press Q to quit.\nFood for person " + i + ":\n");
            while (b) {
                String food = scanner.nextLine().trim();
                if (!food.equalsIgnoreCase("Q")) {
                    order.addToOrder(food);
                    System.out.println(order);
                } else {
                    b = false;
                }
            }

        }
        currentRestaurant.addToActiveOrders(order);
    }

}

