import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Restaurant{

    int customerId;

    public Customer(){
        this.customerId = (int) ((Math.random() * 89999999) + 10000000);
    }

    public void menuForCustomers() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);
        System.out.println("Customer Menu");
        System.out.println("M)ake a reservation, V)iew Menu, C)ancel Reservation, S)witch Restaurant, L)ookup available tables");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("M")){
            makeReservation();
        }else if(input.equalsIgnoreCase("V")){
            viewMenu();
        }else if(input.equalsIgnoreCase("C")) {
            System.out.println("Choose a reservation to cancel");
            for (TableReservation t : getListOfReservations()) {
                System.out.println(t);
                int index = in.nextInt();
                in.nextLine();
                //cancelReservation(index);
            }
        }else if(input.equalsIgnoreCase("S")){
            System.out.println("Choose a restaurant ");
            for(int i = 0; i < getListOfRestaurants().size(); i++){
                System.out.println((i+1) + ". " + getListOfReservations().get(i));
            }
        }else if(input.equalsIgnoreCase("L")){
            System.out.println("Input a date YYYY/MM/DD");
            String s = in.nextLine();
            System.out.println("Input a time HH:MM");
            String t = in.nextLine();
            String[] timeArray = t.split(":");
            int counter = currentRestaurant.getNumberOfTables();
            ArrayList<TableReservation> currentRes = new ArrayList<>();
            for(TableReservation r : currentRestaurant.getListOfReservations()){
                if(r.getDate().toString().equals(s)){
                    if(r.getTime().getHour() < Integer.parseInt(timeArray[0])){
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
}
