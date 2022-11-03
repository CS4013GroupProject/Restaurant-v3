
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        TableReservation b = new TableReservation(c, d, "Mark Harrison", 2260882, 3, a.restaurantId, 7 );
    }
    private  int restaurantId;
    private int capacity;
    private int numberOfTables;
    private  ArrayList<String> csv = new ArrayList<>();
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    private  ArrayList<TableReservation> listOfReservations = new ArrayList<>();
    static int currentRestaurantIndex;
    static boolean quit = false;


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
    public  void run() throws FileNotFoundException {
        createRestaurant();
        Scanner in = new Scanner(System.in);
        System.out.println("C)ustomer or W)aiter or Ch)ef or A)dministration");
        String role = in.nextLine();
        if(role.equalsIgnoreCase("C")){
            while(!quit){
                Customer c = new Customer(listOfRestaurants.get(currentRestaurantIndex));
                c.menuForCustomers();
            }
        }else if(role.equalsIgnoreCase("W")) {
            Waiter w = new Waiter();
            while (!quit) {
                w.menuForWaiters();
            }
        }else if(role.equalsIgnoreCase("Ch")){
            while(!quit){
                //menuForChef
            }
        }else if(role.equalsIgnoreCase("A")){
            Admin a = new Admin();
            a.menuForAdmin();
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
        Scanner in = new Scanner(System.in);
        System.out.println("Create a restaurant");
        System.out.println("Enter Restaurant ID, number of tables and capacity");
        String[] restData = in.nextLine().split(",");
        Restaurant a = new Restaurant(Integer.parseInt(restData[0]), Integer.parseInt(restData[1]), Integer.parseInt(restData[2]));
        listOfRestaurants.add(a);
        currentRestaurantIndex = listOfRestaurants.indexOf(a);
        System.out.println(currentRestaurantIndex);
        System.out.println(listOfRestaurants.get(0).getListOfRestaurants());

    }
}

