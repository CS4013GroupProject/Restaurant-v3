import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
    static int currentRestaurantIndex;
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();

    public static ArrayList<Restaurant> getListOfRestaurants() {
        return listOfRestaurants;
    }

    public static int getCurrentRestaurantIndex() {
        return currentRestaurantIndex;
    }

    public static void addRestaurant(Restaurant r) {
        listOfRestaurants.add(r);
        currentRestaurantIndex = listOfRestaurants.indexOf(r);
    }

//    public Restaurant getCurrentRestaurant() {
//        return listOfRestaurants.get(currentRestaurantIndex);
//    }
}
