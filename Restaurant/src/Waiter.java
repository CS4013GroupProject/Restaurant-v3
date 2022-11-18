import com.sun.jdi.InvalidTypeException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Waiter extends Restaurant {

    private Restaurant currentRestaurant;

    public Waiter(Restaurant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

//    public void menuForWaiters() throws FileNotFoundException {
//        Scanner in = new Scanner(System.in);
//        Restaurant currentRestaurant = getListOfRestaurants().get(manager.getCurrentRestaurantIndex());
//
//        System.out.println("Menu for restaurant: " + currentRestaurant.getRestaurantId());
//        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, Cr)eate order, V)iew Menu, View Re)servations, Q)uit ");
//        String input = in.nextLine().trim();
//        if (input.equalsIgnoreCase("R")) {
//            System.out.println(currentRestaurant.getRestaurantId());
//        } else if (input.equalsIgnoreCase("N")) {
//            System.out.println(currentRestaurant.getNumberOfTables());
//        } else if (input.equalsIgnoreCase("C")) {
//            System.out.println(currentRestaurant.getCapacity());
//        } else if (input.equalsIgnoreCase("V")) {
//            viewMenu();
//        } else if (input.equalsIgnoreCase("Cr")) {
//            createOrder();
//        } else if (input.equalsIgnoreCase("Re")) {
//            for (TableReservation s : currentRestaurant.getListOfReservations()) {
//                System.out.println(s);
//            }
//        } else if (input.equalsIgnoreCase("Q")) {
//            System.out.println("Goodbye");
//            currentRestaurant.run();
//            return;
//        }
//
//
//    }


    public void createOrder(int tableNumber, int numberOfPeople, Food f) {
        Order order = new Order(tableNumber, currentRestaurant);
        currentRestaurant.viewMenu();
        System.out.println("\nEnter each food one by one:");
        order.addToOrder(f);
        currentRestaurant.addToActiveOrders(order);
    }

}

