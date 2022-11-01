import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin extends Restaurant{
    public void menuForAdmin() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Restaurant currentRestaurant = getListOfRestaurants().get(currentRestaurantIndex);

        System.out.println("Menu");
        System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, V)iew Menu, Cr)eate new restaurant, S)witch restaurant, View Re)servations, Q)uit ");
        String input = in.nextLine();
        if(input.equalsIgnoreCase("R")){
            System.out.println(currentRestaurant.getRestaurantId());
        }else if(input.equalsIgnoreCase("N")){
            System.out.println(currentRestaurant.getNumberOfTables());
        }else if(input.equalsIgnoreCase("C")){
            System.out.println(currentRestaurant.getCapacity());
        }else if(input.equalsIgnoreCase("V")){
            viewMenu();
        }else if(input.equalsIgnoreCase("Cr")){
            createRestaurant();
        }else if(input.equalsIgnoreCase("S")){
            System.out.println("Choose a restaurant:");
            for(Restaurant s : getListOfRestaurants()){
                System.out.println(s.getRestaurantId());
            }
            int nextRestaurant = Integer.parseInt(in.nextLine());
            currentRestaurant = getListOfRestaurants().get(nextRestaurant);
        }else if(input.equalsIgnoreCase("Re")){
            for(TableReservation s : currentRestaurant.getListOfReservations()){
                System.out.println(s);
            }
        }else if(input.equalsIgnoreCase("Q")){
            System.out.println("Goodbye");

        }
    }
}
