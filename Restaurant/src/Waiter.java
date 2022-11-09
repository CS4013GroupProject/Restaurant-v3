import java.io.FileNotFoundException;
import java.util.Scanner;

public class Waiter extends Restaurant {

    public void menuForWaiters() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);


        System.out.println("Menu");
        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, Cr)eate order,V)iew Menu, View Re)servations, Q)uit ");
        String input = in.nextLine();
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
        } else if (input.equalsIgnoreCase("Re")){
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
        int tableNumber = scanner.nextInt();
        Order order = new Order(tableNumber);
        viewMenu();
        System.out.println("Press Q to Quit.\nEnter each food one by one:");
        String food = scanner.nextLine();
        while (!food.equalsIgnoreCase("Q")) {
            order.addToOrder(food);
            food = scanner.nextLine();
        }
        Chef.addToActiveOrders(order);

    }
}
