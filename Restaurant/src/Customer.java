import javax.print.attribute.standard.PrinterMakeAndModel;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends Restaurant {

    int customerId;
    Restaurant currentRestaurant;
    String username;
    String password;

    public Customer(Restaurant restaurant) {
        currentRestaurant = restaurant;
    }

    public void login() throws FileNotFoundException, InputMismatchException {
        System.out.println("C)reate customer, S)ign in as Customer");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine().trim();

        if (choice.equalsIgnoreCase("C")) {
            int newCustomerId = (int) ((Math.random() * 89999999) + 10000000);
            System.out.println("Enter name");
            username = in.nextLine().trim();

            System.out.println("Enter pass");
            password = in.nextLine().trim();
            Login l = new Login(username, password, newCustomerId);
            Restaurant.addToListOfCustomers(l);
            this.username = l.getUsername();
            this.password = l.getPassword();
            this.customerId = l.getCustomerid();
        } else if (choice.equalsIgnoreCase("S")) {
            System.out.println("Enter username:");
            String user = in.nextLine().trim();
            System.out.println("Enter password:");
            String pass = in.nextLine().trim();
            System.out.println(Restaurant.getListOfRestaurants().size());
            for (Login l : Restaurant.getListOfCustomers()) {

                if (l.getUsername().equalsIgnoreCase(user) && l.getPassword().equals(pass)) {
                    this.username = l.getUsername();
                    this.password = l.getPassword();
                    this.customerId = l.getCustomerid();
                } else {
                }
            }

        }
        menuForCustomers();
    }

    public void menuForCustomers() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Customer Menu for Restaurant: " + currentRestaurant.getRestaurantId());
        System.out.println("Welcome " + username);
        System.out.println("M)ake a reservation, W)alk-in Reservation,V)iew Menu, C)ancel Reservation, S)witch Restaurant, P)ay, L)ookup available tables, Se)e Reservations, See Re)minders Q)uit");
        String input = in.nextLine().trim();

        if (input.equalsIgnoreCase("M")) {
            makeReservation();
            menuForCustomers();
        } else if (input.equalsIgnoreCase("W")) {
            makeWalkInReservation();
            menuForCustomers();
        } else if (input.equalsIgnoreCase("V")) {
            currentRestaurant.viewMenu();
            menuForCustomers();
        } else if (input.equalsIgnoreCase("C")) {
            System.out.println("Choose a reservation to cancel");
            int i = 1;
            for (TableReservation t : currentRestaurant.getListOfReservations()) {
                System.out.println(i);
                System.out.println(t);
                i++;

            }
            int index = Integer.parseInt(in.nextLine().trim()) - 1;
            if (index >= 0 && index < currentRestaurant.getListOfReservations().size()) {

                cancelReservation(currentRestaurant.getListOfReservations(), index);
            } else {
                System.out.println("invalid reservation");
                menuForCustomers();
            }
            menuForCustomers();
        } else if (input.equalsIgnoreCase("P")) {
            System.out.println("Choose an Order");
            for (int i = 0; i < currentRestaurant.getPaymentPendingOrders().size(); i++) {
                System.out.println((i + 1) + ".");
                System.out.println(currentRestaurant.getPaymentPendingOrders().get(i).getOrderTotal());
            }
            int index = Integer.parseInt(in.nextLine().trim()) - 1;
            System.out.println("Cash, Card, or Cheque?");
            String paymethod = in.nextLine().trim();
            System.out.println("How much was paid?");
            double pay = Double.parseDouble(in.nextLine().trim());
            if (pay < currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal()) {
                System.out.println("Payment insufficient.");
                menuForCustomers();
            } else if (pay > currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal()) {
                double change = pay - currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal();
                System.out.printf("Change due: %.2f.%n", change);
            }
            System.out.println("Enter Tip Amount: (0)%, (5)%, (8)%, (10)%, (15)%, (20)%, (Custom)%");
            double tip = Double.parseDouble(in.nextLine().trim());
            double tipActual = currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal() * (tip / 100);
            Payment newPayment = new Payment(currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal(), LocalDate.now(), tipActual);
            newPayment.takePayment();
            String[] data = {String.valueOf(currentRestaurant.getPaymentPendingOrders().get(index).getTableNumber()),
                    String.valueOf(currentRestaurant.getPaymentPendingOrders().get(index).getOrderTotal()),
                    String.valueOf(LocalDate.now()),
                    String.valueOf(tipActual),
                    paymethod
            };
            currentRestaurant.getPaymentPendingOrders().remove(index);
            super.CSV("Restaurant/src/payments.csv", data);
            System.out.println("Payment Processed. Thank you.");
            menuForCustomers();
        } else if (input.equalsIgnoreCase("S")) {
            System.out.println("Choose a restaurant ");
            System.out.println(Restaurant.getListOfRestaurants());
            for (int i = 0; i < Restaurant.getListOfRestaurants().size(); i++) {
                System.out.println((i + 1) + ". " + Restaurant.getListOfRestaurants().get(i));
            }
            int newIndex = Integer.parseInt(in.nextLine().trim()) - 1;
            currentRestaurant = Restaurant.getListOfRestaurants().get(newIndex);
            menuForCustomers();
        } else if (input.equalsIgnoreCase("L")) {
            System.out.println("Input a date YYYY-MM-DD");
            String s = in.nextLine().trim();
            if (s.substring(5, 6) != "-" || s.substring(8, 9) != "-" || s.length() != 10) {
                System.out.println("invalid date.");
                menuForCustomers();
            } else {

                System.out.println("Input a time HH:MM");
                String t = in.nextLine().trim();
                String[] timeArray = t.split(":");
                int counter = currentRestaurant.getNumberOfTables();
                ArrayList<TableReservation> currentRes = new ArrayList<>();
                for (TableReservation r : currentRestaurant.getListOfReservations()) {
                    if (r.getDate().toString().equals(s)) {
                        if (r.getTime().getHour() <= Integer.parseInt(timeArray[0])) {
                            counter--;
                            currentRes.add(r);
                        }
                    }
                }

                System.out.println("There are " + counter + " tables available at the given date and time. Table(s)");
                for (TableReservation r : currentRes) {
                    System.out.println(r.getTableNumber());
                }
                System.out.println("are taken.");
            }
            menuForCustomers();
        } else if (input.equalsIgnoreCase("Se")) {
            for (TableReservation r : currentRestaurant.getListOfReservations()) {

                if (r.getCustomerId() == customerId) {
                    System.out.println(r);
                }
            }
            menuForCustomers();

        } else if (input.equalsIgnoreCase("re")) {
            for (TableReservation r : currentRestaurant.getListOfReservations()) {

                if (r.getCustomerId() == customerId && r.getPhoneNumber() != 0) {
                    System.out.println("Reminder! Reservation upcoming: ");
                    System.out.println(r);
                }
            }
        } else if (input.equalsIgnoreCase("q")) {
            currentRestaurant.run();
        }

    }

    public void makeReservation() throws FileNotFoundException, InputMismatchException {
        System.out.println("Enter date 'YYYY-MM-DD, time 'HH:MM', full name, phone number, number of people, table number ");

        Scanner in = new Scanner(System.in);
            String[] resData = in.nextLine().split(",");
            for (int i = 0; i < resData.length; i++) {
                resData[i] = resData[i].trim();
            }

            if (Integer.parseInt(resData[5]) > currentRestaurant.getNumberOfTables()) {
                System.out.println("Not a valid table number. Number of tables is: " + currentRestaurant.getNumberOfTables());
                menuForCustomers();

                //format the date string as a LocalDate
                String[] dateAsArray = resData[0].split("-");
                int[] dateAsInts = new int[3];
                for (int i = 0; i < dateAsArray.length; i++) {
                    dateAsInts[i] = Integer.parseInt(dateAsArray[i]);
                }
                LocalDate b = LocalDate.of(dateAsInts[0], dateAsInts[1], dateAsInts[2]);

                //format the time string as a LocalTime
                String[] timeArray = resData[1].split(":");
                int[] timeAsInts = new int[2];
                for (int i = 0; i < timeArray.length; i++) {
                    timeAsInts[i] = Integer.parseInt(timeArray[i]);
                }
                LocalTime c = LocalTime.of(timeAsInts[0], timeAsInts[1]);


                int counter = currentRestaurant.getNumberOfTables();
                ArrayList<TableReservation> currentRes = new ArrayList<>();
                for (TableReservation r : currentRestaurant.getListOfReservations()) {
                    if (r.getDate().equals(b)) {
                        if (r.getTime().getHour() + 3 >= c.getHour()) {
                            counter--;
                            currentRes.add(r);
                        }
                    }
                }
                if (currentRes.isEmpty()) {
                    TableReservation a = new TableReservation(b, c, resData[2], Integer.parseInt(resData[3]), Integer.parseInt(resData[4]), currentRestaurant.getRestaurantId(), Integer.parseInt(resData[5]), currentRestaurant, customerId);
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAa");
                    currentRestaurant.getListOfReservations().add(a);
                }
                for (TableReservation r : currentRes) {
                    if (r.getTableNumber() == Integer.parseInt(resData[5])) {
                        System.out.println("Table is booked at this time.");
                    } else {
                        TableReservation a = new TableReservation(b, c, resData[2], Integer.parseInt(resData[3]), Integer.parseInt(resData[4]), currentRestaurant.getRestaurantId(), Integer.parseInt(resData[5]), currentRestaurant, customerId);
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAa");
                        currentRestaurant.getListOfReservations().add(a);
                    }
                }
            }

        }

    public void makeWalkInReservation() throws FileNotFoundException {
        System.out.println("Enter number of people. Table Number is Assigned Randomly. ");
        Scanner in = new Scanner(System.in);
        int noOfPeople = Integer.parseInt(in.nextLine().trim());

        TableReservation a = new TableReservation(LocalDate.now(), LocalTime.now(), noOfPeople, currentRestaurant.getRestaurantId(), currentRestaurant, customerId);
        currentRestaurant.getListOfReservations().add(a);
    }


    public void cancelReservation(ArrayList<TableReservation> a, int index) {
        System.out.println("Table reservation:\n " + a.get(index).toString() + "\n has been cancelled.");
        a.remove(index);

    }
}
