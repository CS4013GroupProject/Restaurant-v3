import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TableReservation extends Restaurant {
    private LocalDate date; //date of reservation
    private LocalTime time; //time of reservation
    private String fullName; //full name of person making reservation
    private int phoneNumber; //phone number of person making reservation
    private int noOfPeople; //number of poeple for reservation
    private int customerId;
    private int reservationID = 0; //ID for reservation
    private int restaurantID; //id of restaurant taking reservation
    private int tableNumber; //table number thats been reserved
    private static ArrayList<String> csv = new ArrayList<>();
    boolean cancelled;
    private int rowNumber;
    private Restaurant currentRestaurant;


    public TableReservation() {
    }

    public TableReservation(LocalDate date, LocalTime time, int noOfPeople, int restaurantID, Restaurant currentRestaurant, int customerId) throws FileNotFoundException {
        this(restaurantID);
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.noOfPeople = noOfPeople;
        cancelled = false;
        this.restaurantID = restaurantID;
        int counter = currentRestaurant.getNumberOfTables();
        ArrayList<Integer> tables = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            tables.add(i + 1);
        }
        for (TableReservation r : currentRestaurant.getListOfReservations()) {
            if (r.getDate().equals(date)) {
                if (r.getTime().getHour() + 3 >= time.getHour()) {
                    tables.removeIf(i -> i.equals(r.tableNumber));
                    counter--;
                }
            }
        }
        if (tables.isEmpty()) {
            System.out.println("Restaurant is currently fully booked.");
        } else {
            int tableNumberIndex = (int) ((Math.random() * counter));
            tableNumber = tables.get(tableNumberIndex);
            reservationID = (int) ((Math.random() * 89999999) + 10000000);
            String[] data = {"null", String.valueOf(reservationID), String.valueOf(tableNumber), String.valueOf(date), String.valueOf(time), String.valueOf(restaurantID), "0", String.valueOf(customerId), String.valueOf(noOfPeople)};
            this.currentRestaurant = currentRestaurant;
            CSV("Restaurant/src/data.csv", data);
        }
    }

    public TableReservation(LocalDate date, LocalTime time, String fullName, int phoneNumber, int noOfPeople, int restaurantID, int tableNumber, Restaurant currentRestaurant, int customerId, boolean exists) throws FileNotFoundException {
        //this constructor makes a reservation
        this(restaurantID);

        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.noOfPeople = noOfPeople;
        this.tableNumber = tableNumber;
        cancelled = false;
        this.restaurantID = restaurantID;
        reservationID = (int) ((Math.random() * 89999999) + 10000000);
        String[] data = {fullName, String.valueOf(reservationID), String.valueOf(tableNumber), String.valueOf(date), String.valueOf(time), String.valueOf(restaurantID), String.valueOf(phoneNumber), String.valueOf(customerId), String.valueOf(noOfPeople)};
        this.currentRestaurant = currentRestaurant;
        if(!exists) {
            CSV("Restaurant/src/data.csv", data);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public TableReservation(int restaurantID) throws FileNotFoundException {
        //constructor for searching purposes
        this.restaurantID = getRestaurantId();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String reminder() {
        if (reservationID != 0) {
            return phoneNumber + ": Reminder! You have a reservation due for " + date + " at " + time + ", at table " + tableNumber + ".";
        }
        //sends a reminder to the
        return "No reservation due.";
    }

    public void cancels() {
        cancelled = true;


    }

    @Override
    public String toString() {
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Name: ").append(fullName).append("\n");
        bobTheBuilder.append("Date and time: ").append(date).append(" ").append(time).append("\n");
        bobTheBuilder.append("phone number: ").append(phoneNumber).append("\n");
        bobTheBuilder.append("table number: ").append(tableNumber).append("\n");
        bobTheBuilder.append("Reservation ID: ").append(reservationID).append("\n");
        bobTheBuilder.append("Restaurant Id: ").append(restaurantID).append("\n");
        bobTheBuilder.append("Number of people: ").append(noOfPeople).append("\n");
        bobTheBuilder.append("Customer ID: ").append(customerId).append("\n");
        return bobTheBuilder.toString();
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
