
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Restaurant {
    public Restaurant() {
    }

    /**
     * Main method, runs the entire program.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Restaurant a = new Restaurant(134, 55, 15);
        LocalDate c = LocalDate.now();
        LocalTime d = LocalTime.now();

        a.run();
    }

    private int restaurantId;
    private int capacity;
    private int numberOfTables;
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    private ArrayList<TableReservation> listOfReservations = new ArrayList<>();
    private Menu menu = new Menu();
    static int currentRestaurantIndex;
    static boolean quit = false;
    private ArrayList<Order> currentOrders = new ArrayList<>();
    private ArrayList<Order> completedOrder = new ArrayList<>();
    private ArrayList<Order> paymentPendingOrders = new ArrayList<>();
    private static ArrayList<Login> listOfCustomers = new ArrayList<>();



 /*
    public Restaurant(ArrayList<Restaurant> listOfRestaurants) {
        this.listOfRestaurants = listOfRestaurants;
    }*/

    /**
     * constructor for restaurant that creates a restaurant
     * writes data to a CSV file
     * @param restaurantId id of restaurant
     * @param capacity number of people it can hold
     * @param numberOfTables the number of tables in restaurant
     * @throws FileNotFoundException
     */
    public Restaurant(int restaurantId, int capacity, int numberOfTables) throws FileNotFoundException {
        this.restaurantId = restaurantId;
        this.capacity = capacity;
        this.numberOfTables = numberOfTables;
        String[] data = {String.valueOf(restaurantId), String.valueOf(capacity), String.valueOf(numberOfTables)};
        CSV("Restaurant/src/restaurant.csv", data);
    }

    public ArrayList<Order> getPaymentPendingOrders() {
        return paymentPendingOrders;
    }

    public static ArrayList<Login> getListOfCustomers() {
        return listOfCustomers;
    }

    /**
     * adds a login object to the list of customers. This ensures a login can only see reservations theyve made
     * @param l the login object
     */
    public static void addToListOfCustomers(Login l){
        listOfCustomers.add(l);
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public ArrayList<TableReservation> getListOfReservations() {
        return listOfReservations;
    }

    public static ArrayList<Restaurant> getListOfRestaurants() {
        return listOfRestaurants;
    }

    /**
     * tostring that returns the restaurant ID
     * @return
     */
    @Override
    public String toString() {
        String s = "" + restaurantId;
        return s;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * a method that writes data to a csv file
     * @param path the path to the csv file
     * @param columnNames the actual data
     * @throws FileNotFoundException incase file is not found
     */
    public void CSV(String path, String[] columnNames) throws FileNotFoundException {

        FileWriter write = null;
        try {
            write = new FileWriter(path, true);
            for (String s : columnNames) {
                write.write(s);
                write.write(", ");
            }
            write.write("\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getCurrentOrders() {
        return currentOrders;
    }

    public ArrayList<Order> getCompletedOrder() {
        return completedOrder;
    }

    public void run() throws FileNotFoundException, InputMismatchException {
        if (listOfRestaurants.size() == 0) {
            createRestaurant();
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Menu for Restaurant: " + listOfRestaurants.get(currentRestaurantIndex).getRestaurantId());
        System.out.println("C)ustomer or W)aiter or Ch)ef or A)dministration.\nQ)uit");
        String role = in.nextLine().toLowerCase().trim();

        if (role.equalsIgnoreCase("C")) {

            while (!quit) {
                Customer c = new Customer(listOfRestaurants.get(currentRestaurantIndex));
                c.login();
            }

        } else if (role.equalsIgnoreCase("W")) {
            Waiter w = new Waiter(listOfRestaurants.get(currentRestaurantIndex));
            while (!quit) {
                w.menuForWaiters();
            }
        } else if (role.equalsIgnoreCase("Ch")) {
            Chef chef = new Chef(listOfRestaurants.get(currentRestaurantIndex));
            while (!quit) {
                chef.menuForChef();
            }
        } else if (role.equalsIgnoreCase("A")) {
            while (!quit) {
                Admin a = new Admin(listOfRestaurants.get(currentRestaurantIndex));
                a.menuForAdmin();
            }
        } else if (role.equalsIgnoreCase("Q")) {
            System.out.println("Goodbye");
            System.exit(0);
        }


    }


    public void viewMenu() throws InputMismatchException {
        System.out.println("Enter time of day: (1)Morning, (2)Afternoon, (3)Evening");
        Scanner in = new Scanner(System.in);
        int timeOfDay = Integer.parseInt(in.nextLine().trim());
        if (timeOfDay == 1) {
            System.out.println(menu.getMenuForMorning());
        } else if (timeOfDay == 2) {
            System.out.println(menu.getMenuForAfterNoon());
        } else if (timeOfDay == 3) {
            System.out.println(menu.getMenuForEvening());
        } else {
            System.out.println("invalid");
        }
    }

    public void createRestaurant() throws FileNotFoundException, InputMismatchException {
        Scanner in = new Scanner(System.in);
        System.out.println("Create a restaurant by entering comma separated integers");
        System.out.println("Enter Restaurant ID, number of tables and capacity");
        String[] restData = in.nextLine().split(",");
        for (int i = 0; i < restData.length; i++) {
            restData[i] = restData[i].trim();
        }
        try {
            Restaurant a = new Restaurant(Integer.parseInt(restData[0]), Integer.parseInt(restData[2]), Integer.parseInt(restData[1]));
            listOfRestaurants.add(a);
            currentRestaurantIndex = listOfRestaurants.indexOf(a);
        } catch (IllegalArgumentException e){
            System.out.println("Please enter comma separated Integers");
            createRestaurant();
        }


    }

    public void addToActiveOrders(Order o) {
        currentOrders.add(o);
    }
}

