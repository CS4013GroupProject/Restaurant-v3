import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Restaurant {

/* Admin class, creates admin for admin to access and alter information
 */
    Restaurant currentRestaurant;

    public Admin(Restaurant restaurant) {
            currentRestaurant = restaurant;
    }
/** menuForAdmin method, creates menu for admin so they can get - Restaurant id,
Number of tables,
Capacity,
View the menu,
Create new restaurant,
Switch restaurant,
view reservations,
add to menu and quit
 *@throws FileNotFoundException throws filenotfoundexception
* @throws InputMismatchException throws inputmismatchexception
 */
    public void menuForAdmin() throws FileNotFoundException, InputMismatchException {

        Scanner in = new Scanner(System.in);

        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);

        System.out.println("Menu for Restaurant: " + currentRestaurant.getRestaurantId());
        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, V)iew Menu, Cr)eate new restaurant, S)witch restaurant, View Re)servations, A)dd to Menu, Q)uit ");
        String input = in.nextLine().trim();
        if (input.equalsIgnoreCase("R")) {
            System.out.println(currentRestaurant.getRestaurantId());
        } else if (input.equalsIgnoreCase("N")) {
            System.out.println(currentRestaurant.getNumberOfTables());
        } else if (input.equalsIgnoreCase("C")) {
            System.out.println(currentRestaurant.getCapacity());
        } else if (input.equalsIgnoreCase("V")) {
            currentRestaurant.viewMenu();
        } else if (input.equalsIgnoreCase("Cr")) {
            createRestaurant();
        } else if (input.equalsIgnoreCase("S")) {
            System.out.println("Choose a restaurant:");
            System.out.println(Restaurant.getListOfRestaurants());
            for (int i = 0; i < getListOfRestaurants().size(); i++) {
                System.out.println((i + 1) + ".");
                System.out.println(getListOfRestaurants().get(i).getRestaurantId());
            }
            int nextRestaurant = Integer.parseInt(in.nextLine().trim()) - 1;
            if (nextRestaurant >= 0 && nextRestaurant <= getListOfRestaurants().size()) {
                currentRestaurant = getListOfRestaurants().get(nextRestaurant);
                currentRestaurantIndex = nextRestaurant;
            } else {
                System.out.println("invalid Restaurant");
                menuForAdmin();
            }
        } else if (input.equalsIgnoreCase("Re")) {
            for (TableReservation s : currentRestaurant.getListOfReservations()) {
                System.out.println(s);
            }
        } else if (input.equalsIgnoreCase("A")) {
            System.out.println("Enter a time of day: (1) Morning, (2) Noon, (3) Evening");
            int timeOfDay = Integer.parseInt(in.nextLine().trim());
            System.out.println("Enter food name:");
            String foodName = in.nextLine().trim();
            System.out.println("Enter a price:");
            double price = Double.parseDouble(in.nextLine().trim());
            Food newFood = new Food(foodName, price);
            Menu newMenu = new Menu();
            newMenu.addToMenu(timeOfDay, newFood);
            currentRestaurant.setMenu(newMenu);
        } else if (input.equalsIgnoreCase("Q")) {
            System.out.println("Goodbye");
            currentRestaurant.run();


            /**
             switch-case to execute selected blocks of code based on admin input
            @param input admin input
             */
            switch (input) {
                case "r":
                    System.out.println(currentRestaurant.getRestaurantId());
                    break;
                case "n":
                    System.out.println(currentRestaurant.getNumberOfTables());
                    break;
                case "c":
                    System.out.println(currentRestaurant.getCapacity());
                    break;
                case "v":
                    viewMenu();
                    break;
                case "cr":
                    createRestaurant();
                    break;
                case "s":
                    System.out.println("Choose a restaurant:");
                    for (Restaurant s : getListOfRestaurants()) {
                        System.out.println(s.getRestaurantId());
                    }
                    int nextRestaurant = Integer.parseInt(in.nextLine());
                    currentRestaurant = getListOfRestaurants().get(nextRestaurant);
                    break;
                case "re":
                    for (TableReservation s : currentRestaurant.getListOfReservations()) {
                        System.out.println(s);
                    }
                    break;
                case "q":
                    System.out.println("Goodbye");
                    quit = true;
                    break;

            }
        }
    }
}

