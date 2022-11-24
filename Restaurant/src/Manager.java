import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    static int currentRestaurantIndex;
    private static ArrayList<Restaurant> listOfRestaurants = new ArrayList<>();

    public static ArrayList<Restaurant> getListOfRestaurants() {
        return listOfRestaurants;
    }

    public void startup() throws FileNotFoundException {
        loadRestaurantsFromDisk();
    }

    private void loadRestaurantsFromDisk() {
        try {

            Scanner sc = new Scanner(new File("Restaurant/src/Restaurant.csv"));
            sc.nextLine();
            while(sc.hasNext()) {
                String[] restaurantData = sc.nextLine().split(",");
                Restaurant persistentRestaurant = new Restaurant(this, Integer.parseInt(restaurantData[0].trim()), Integer.parseInt(restaurantData[1].trim()), Integer.parseInt(restaurantData[2].trim()), true);
                addRestaurant(persistentRestaurant);
            }
        } catch (FileNotFoundException f){
            System.out.println("File not found");
        }
    }

    public static int getCurrentRestaurantIndex() {
        return currentRestaurantIndex;
    }

    public static void addRestaurant(Restaurant r) {
        listOfRestaurants.add(r);
        currentRestaurantIndex = listOfRestaurants.indexOf(r);
    }

    public Restaurant getRestaurantByID(int id) {
        Restaurant r;

        for(Restaurant rest : getListOfRestaurants()) {
            if(rest.getRestaurantId() == id) {
                return rest;
            }
        }

        return null;
    }

    public static void setCurrentRestaurantIndex(int nextRestaurant) {
        currentRestaurantIndex = nextRestaurant;
    }

    public Restaurant getCurrentRestaurant() {
        return listOfRestaurants.get(currentRestaurantIndex);
    }
}