
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Restaurant {
    public static void main(String[] args) throws FileNotFoundException {
       run();
        Restaurant a = new Restaurant(134, 55, 15);
        LocalDate c = LocalDate.now();
        LocalTime d = LocalTime.now();

        TableReservation b = new TableReservation(c, d, "Mark Harrison", 2260882, 3, a.restaurantId, 7 );
    }
    private static int restaurantId;
    private int capacity;
    private int numberOfTables;
    private static ArrayList<String> csv = new ArrayList<>();
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    private static ArrayList<TableReservation> listOfReservations = new ArrayList<>();
    static int currentRestaurantIndex;
    static boolean quit = false;


    public Restaurant(){};
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
    public static void run() throws FileNotFoundException {
        createRestaurant();

        while(!quit){
            menu();
        }

         ;

    }
    public static void menu() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Restaurant currentRestaurant = listOfRestaurants.get(currentRestaurantIndex);
        System.out.println("Menu");
        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, M)ake Reservation, V)iew Menu, Cr)eate new restaurant, S)witch restaurant, View Re)servations, Q)uit ");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("R")){
            System.out.println(currentRestaurant.getRestaurantId());
        }else if(input.equalsIgnoreCase("N")){
            System.out.println(currentRestaurant.getNumberOfTables());
        }else if(input.equalsIgnoreCase("C")){
            System.out.println(currentRestaurant.getCapacity());
        }else if(input.equalsIgnoreCase("M")){
                currentRestaurant.makeReservation();
        }else if(input.equalsIgnoreCase("V")){
            System.out.println("Enter a time: Morning (1), Noon(2), Evening(3)");
            int time = Integer.parseInt(in.nextLine());
            HashMap<Integer, String> timeToChar = new HashMap<Integer, String>();
            timeToChar.put(1, "Morning");
            timeToChar.put(2, "Noon");
            timeToChar.put(3,"Evening");
            Menu a = new Menu(timeToChar.get(time));
            System.out.println(a);
        }else if(input.equalsIgnoreCase("Cr")){
            createRestaurant();
        }else if(input.equalsIgnoreCase("S")){
            System.out.println("Choose a restaurant:");
            for(Restaurant s : listOfRestaurants){
                System.out.println(s.getRestaurantId());
            }
            int nextRestaurant = Integer.parseInt(in.nextLine());
            currentRestaurant = listOfRestaurants.get(nextRestaurant);
        }else if(input.equalsIgnoreCase("Re")){
            for(TableReservation s : currentRestaurant.getListOfReservations()){
                System.out.println(s);
            }
        }else if(input.equalsIgnoreCase("Q")){
            System.out.println("Goodbye");
            return;
        }
    }
    public void makeReservation() throws FileNotFoundException {
        System.out.println("Enter date 'DD/MM/YYYY, time 'HH:MM', full name, phone number, number of people, table number ");
        Scanner in = new Scanner(System.in);
        String[] resData = in.nextLine().split(",");
        //format the date string as a LocalDate
        String[] dateAsArray = resData[0].split("/");
        int[] dateAsInts = new int[3];
        for(int i = 0; i < dateAsArray.length; i++) {
            dateAsInts[i] = Integer.parseInt(dateAsArray[i]);
        }
        LocalDate b = LocalDate.of(dateAsInts[2], dateAsInts[1], dateAsInts[0]);
        //format the time string as a LocalTime
        String[] timeArray = resData[1].split(":");
        int[] timeAsInts = new int[2];
        for(int i = 0; i < timeArray.length; i++){
            timeAsInts[i] = Integer.parseInt(timeArray[i]);
        }
        LocalTime c = LocalTime.of(timeAsInts[0], timeAsInts[1]);
        TableReservation a = new TableReservation(b, c, resData[2],Integer.parseInt(resData[3]), Integer.parseInt(resData[4]), getRestaurantId(), Integer.parseInt(resData[5]));
        listOfReservations.add(a);
    }
    public static void createRestaurant() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Create a restaurant");
        System.out.println("Enter Restaurant ID, number of tables and capacity");
        String[] restData = in.nextLine().split(",");
        Restaurant a = new Restaurant(Integer.parseInt(restData[0]), Integer.parseInt(restData[1]), Integer.parseInt(restData[2]));
        listOfRestaurants.add(a);
        currentRestaurantIndex = listOfRestaurants.indexOf(a);
    }
}

