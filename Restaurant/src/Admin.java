import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Restaurant {

    Restaurant currentRestaurant;

    public Admin(Restaurant restaurant) {
        currentRestaurant = restaurant;
    }

//    public void menuForAdmin() throws FileNotFoundException, InputMismatchException {
//
//        Scanner in = new Scanner(System.in);
//
//        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);
//
//        System.out.println("Menu for Restaurant: " + currentRestaurant.getRestaurantId());
//        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, V)iew Menu, Cr)eate new restaurant, S)witch restaurant, View Re)servations, A)dd to Menu, Q)uit ");
//        String input = in.nextLine().trim();
//
//        switch (input.toUpperCase()) {
//            case "R":
//                System.out.println(currentRestaurant.getRestaurantId());
//                break;
//            case "N":
//                System.out.println(currentRestaurant.getNumberOfTables());
//                break;
//            case "C":
//                System.out.println(currentRestaurant.getCapacity());
//                break;
//            case "V":
//                currentRestaurant.viewMenu();
//                break;
//            case "CR":
//                createRestaurant();
//            case "S":
//                System.out.println("Choose a restaurant:");
//                System.out.println(currentRestaurant.getListOfRestaurants());
//                for (int i = 0; i < getListOfRestaurants().size(); i++) {
//                    System.out.println((i + 1) + ".");
//                    System.out.println(getListOfRestaurants().get(i).getRestaurantId());
//                }
//                int nextRestaurant = Integer.parseInt(in.nextLine().trim()) - 1;
//                if (nextRestaurant >= 0 && nextRestaurant <= getListOfRestaurants().size()) {
//                    currentRestaurant = getListOfRestaurants().get(nextRestaurant);
//                    currentRestaurantIndex = nextRestaurant;
//                } else {
//                    System.out.println("invalid Restaurant");
//                    menuForAdmin();
//                }
//                break;
//            case "RE":
//                for (TableReservation s : currentRestaurant.getListOfReservations()) {
//                    System.out.println(s);
//                }
//                break;
//            case "A":
//                System.out.println("Enter a time of day: (1) Morning, (2) Noon, (3) Evening");
//                int timeOfDay = Integer.parseInt(in.nextLine().trim());
//                System.out.println("Enter food name:");
//                String foodName = in.nextLine().trim();
//                System.out.println("Enter a price:");
//                double price = Double.parseDouble(in.nextLine().trim());
//                Food newFood = new Food(foodName, price);
//                Menu newMenu = new Menu();
//                newMenu.addToMenu(timeOfDay, newFood);
//                currentRestaurant.setMenu(newMenu);
//                break;
//            case "Q":
//                System.out.println("Goodbye");
//                currentRestaurant.run();
//                break;
//        }
//    }
}

