
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public static void main(String[] args) throws FileNotFoundException {
        Restaurant a = new Restaurant(134, 55, 15);
        LocalDate c = LocalDate.now();
        LocalTime d = LocalTime.now();

        TableReservation b = new TableReservation(c, d, "Mark Harrison", 2260882, 3, a.restaurantId, 7 );
    }
    private int restaurantId;
    private int capacity;
    private int numberOfTables;
    private static ArrayList<String> csv = new ArrayList<>();


    public Restaurant(){};
    public Restaurant(int restaurantId, int capacity, int numberOfTables) throws FileNotFoundException {
        this.restaurantId = restaurantId;
        this.capacity = capacity;
        this.numberOfTables = numberOfTables;
        String[] data = {String.valueOf(restaurantId), String.valueOf(capacity), String.valueOf(numberOfTables)};
        CSV("src/restaurant.csv", data);
    }

    public int getRestaurantId(){return  restaurantId;}
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
}

