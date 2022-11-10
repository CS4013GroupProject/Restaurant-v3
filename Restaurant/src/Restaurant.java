
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;


public class Restaurant {
    public static void main(String[] args) throws FileNotFoundException {
        Restaurant a = new Restaurant(134, 55, 15);
        LocalDate c = LocalDate.now();
        LocalTime d = LocalTime.now();

        a.run();
    }
    private  int restaurantId;
    private int capacity;
    private int numberOfTables;
    private  ArrayList<String> csv = new ArrayList<>();
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    private  ArrayList<TableReservation> listOfReservations = new ArrayList<>();
    static int currentRestaurantIndex;
    static boolean quit = false;
    private  ArrayList<Order> currentOrders = new ArrayList<>();
    private  ArrayList<Order> completedOrder = new ArrayList<>();


    public Restaurant(){};
    public Restaurant(ArrayList<Restaurant> listOfRestaurants){
        this.listOfRestaurants = listOfRestaurants;
    }
    public Restaurant(int restaurantId, int capacity, int numberOfTables) throws FileNotFoundException {
        this.restaurantId = restaurantId;
        this.capacity = capacity;
        this.numberOfTables = numberOfTables;
        String[] data = {String.valueOf(restaurantId), String.valueOf(capacity), String.valueOf(numberOfTables)};
        CSV("Restaurant/src/restaurant.csv", data);
    }

    public  int getRestaurantId(){return  restaurantId;}
    public int getCapacity(){return capacity;}
    public int getNumberOfTables(){return numberOfTables;}
    public ArrayList<TableReservation> getListOfReservations(){return listOfReservations;}
    public static ArrayList<Restaurant> getListOfRestaurants(){return listOfRestaurants;}
    public String toString(){
        String s = "" + restaurantId;
                return s;
    }
    public void CSV(String path, String[] columnNames) throws FileNotFoundException {

        FileWriter write = null;
        try {
            write = new FileWriter(path, true);
            for (String s: columnNames) {
                write.write(s);
                write.write(", ");
            }
            write.write("\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Order> getCurrentOrders(){
        return currentOrders;
    }
    public ArrayList<Order> getCompletedOrder(){
        return completedOrder;
    }
    public  void run() throws FileNotFoundException {
        if(listOfRestaurants.size() == 0){
            createRestaurant();
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Menu for Restaurant: " + listOfRestaurants.get(currentRestaurantIndex).getRestaurantId());
        System.out.println("C)ustomer or W)aiter or Ch)ef or A)dministration");
        String role = in.nextLine().toLowerCase().trim();

        switch(role) {
            case "C":
                Customer c = new Customer();
                c.menuForCustomers();
                break;
            case "W":
                Waiter w = new Waiter();
                w.menuForWaiters();
                break;
            case "ch":
                Chef ch = new Chef();
                ch.menuForChefs();
                break;
        }
        if(role.equalsIgnoreCase("C")){
            while(!quit){
                Customer c = new Customer(listOfRestaurants.get(currentRestaurantIndex));
                c.menuForCustomers();
            }
        }else if(role.equalsIgnoreCase("W")) {
            Waiter w = new Waiter(listOfRestaurants.get(currentRestaurantIndex));
            while (!quit) {
                w.menuForWaiters();
            }
        }else if(role.equalsIgnoreCase("Ch")){
            Chef chef = new Chef(listOfRestaurants.get(currentRestaurantIndex));
            while(!quit){
                chef.menuForChef();
            }
        }else if(role.equalsIgnoreCase("A")){
            while(!quit){
            Admin a = new Admin(listOfRestaurants.get(currentRestaurantIndex));
            a.menuForAdmin();
        }
        }


        }







    public void viewMenu(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a time: Morning (1), Noon(2), Evening(3)");
        int time = Integer.parseInt(in.nextLine());
        HashMap<Integer, String> timeToChar = new HashMap<Integer, String>();
        timeToChar.put(1, "Morning");
        timeToChar.put(2, "Noon");
        timeToChar.put(3,"Evening");
        Menu a = new Menu(timeToChar.get(time));
        System.out.println(a);
    }

    public  void createRestaurant() throws FileNotFoundException {
        Scanner in = new Scanner(System.in).useDelimiter("\\s+");
        System.out.println("Create a restaurant");
        System.out.println("Enter Restaurant ID, number of tables and capacity");
        String[] restData = in.nextLine().split(",");
        for(int i = 0; i < restData.length; i++){
            restData[i] = restData[i].trim();
        }
        Restaurant a = new Restaurant(Integer.parseInt(restData[0]), Integer.parseInt(restData[1]), Integer.parseInt(restData[2]));
        listOfRestaurants.add(a);
        currentRestaurantIndex = listOfRestaurants.indexOf(a);


    }
    public  void addToActiveOrders(Order o){
        currentOrders.add(o);
    }
}

