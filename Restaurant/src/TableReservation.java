
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
    private int reservationID = 0; //ID for reservation
    private int restaurantID; //id of restaurant taking reservation
    private int tableNumber; //table number thats been reserved
    private static ArrayList<String> csv = new ArrayList<>();
    boolean cancelled;
    private int rowNumber;
    private Restaurant currentRestaurant;




    public TableReservation(){}
    public TableReservation(LocalDate date, LocalTime time, String fullName, int phoneNumber, int noOfPeople, int restaurantID, int tableNumber, Restaurant currentRestaurant) throws FileNotFoundException {
        //this constructor makes a reservation
            this(restaurantID);

        this.date = date;
        this.time = time;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.noOfPeople = noOfPeople;
        this.tableNumber = tableNumber;
        cancelled = false;
        this.restaurantID = restaurantID;
        reservationID = (int) ((Math.random() * 89999999) + 10000000);
        String[] data = {fullName, String.valueOf(reservationID), String.valueOf(tableNumber), String.valueOf(date), String.valueOf(time), String.valueOf(restaurantID), String.valueOf(cancelled)};
        this.currentRestaurant = currentRestaurant;
        CSV("Restaurant/src/data.csv", data);
    }

    public LocalDate getDate(){return date;}
    public LocalTime getTime(){return time;}
    public TableReservation(int restaurantID) throws FileNotFoundException {
        //constructor for searching purposes
        this.restaurantID = getRestaurantId();
    }



    public String reminder() {
        if (reservationID != 0) {
            return phoneNumber + ": Reminder! You have a reservation due for " + date + " at " + time + ", at table " + tableNumber + ".";
        }
        //sends a reminder to the
        return "No reservation due.";
    }

    public void cancels(){
        cancelled = true;


    }
@Override
    public String toString(){
    StringBuilder bobTheBuilder = new StringBuilder();
    bobTheBuilder.append("Name: ").append(fullName).append("\n");
    bobTheBuilder.append("Date and time: ").append(date).append(" ").append(time).append("\n");
    bobTheBuilder.append("phone number: ").append(phoneNumber).append("\n");
    bobTheBuilder.append("table number: " ).append(tableNumber).append("\n");
    bobTheBuilder.append("Reservation ID: ").append(reservationID).append("\n");
    bobTheBuilder.append("Restaurant Id: ").append(restaurantID).append("\n");
    bobTheBuilder.append("Number of people: ").append(noOfPeople).append("\n");
    return bobTheBuilder.toString();
    }

    public int getTableNumber() {
        return this.tableNumber;
    }


}
