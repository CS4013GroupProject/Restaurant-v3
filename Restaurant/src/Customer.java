import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    int customerId;
    Restaurant currentRestaurant;

    public Customer(Restaurant restaurant){
        this.customerId = (int) ((Math.random() * 89999999) + 10000000);
        currentRestaurant = restaurant;
    }

    public void menuForCustomers() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Customer Menu");
        System.out.println("M)ake a reservation, V)iew Menu, C)ancel Reservation, S)witch Restaurant, L)ookup available tables");
        String input = in.nextLine();

        if(input.equalsIgnoreCase("M")){
            makeReservation();
        }else if(input.equalsIgnoreCase("V")){
            currentRestaurant.viewMenu();
        }else if(input.equalsIgnoreCase("C")) {
            System.out.println("Choose a reservation to cancel");
            for (TableReservation t : currentRestaurant.getListOfReservations()) {
                System.out.println(t);
                int index = in.nextInt();
                in.nextLine();
                //cancelReservation(index);
            }
        }else if(input.equalsIgnoreCase("S")){
            System.out.println("Choose a restaurant ");
            System.out.println(currentRestaurant.getListOfRestaurants());
            for(int i = 0; i < Restaurant.getListOfRestaurants().size(); i++){
                System.out.println((i+1) + ". " + currentRestaurant.getListOfRestaurants().get(i));
            }
        }else if(input.equalsIgnoreCase("L")){
            System.out.println("Input a date YYYY-MM-DD");
            String s = in.nextLine();
            System.out.println("Input a time HH:MM");
            String t = in.nextLine();
            String[] timeArray = t.split(":");
            int counter = currentRestaurant.getNumberOfTables();
            ArrayList<TableReservation> currentRes = new ArrayList<>();
            System.out.println(currentRestaurant.getListOfReservations());
            ;
            for(TableReservation r : currentRestaurant.getListOfReservations()){
                System.out.println(r.getDate().toString());
                if(r.getDate().toString().equals(s)){
                    if(r.getTime().getHour() <= Integer.parseInt(timeArray[0])){
                        counter--;
                        currentRes.add(r);
                    }
            }
            }
            
            System.out.println("There are " + counter + " tables available at the given date and time. Table(s)\n");
            for(TableReservation r : currentRes){
                System.out.println(r.getTableNumber());
            }
            System.out.println("are taken.");
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
        TableReservation a = new TableReservation(b, c, resData[2],Integer.parseInt(resData[3]), Integer.parseInt(resData[4]), currentRestaurant.getRestaurantId(), Integer.parseInt(resData[5]));
        currentRestaurant.getListOfReservations().add(a);
    }
}
