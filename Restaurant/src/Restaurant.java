
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
    private  ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();
    private  ArrayList<TableReservation> listOfReservations = new ArrayList<>();
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
    public ArrayList<Restaurant> getListOfRestaurants(){return listOfRestaurants;}
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
                Customer c = new Customer();
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
    }
}

