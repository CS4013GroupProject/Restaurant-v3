import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    Restaurant currentRestaurant;
    private ArrayList<Food> orders = new ArrayList<>();
    private double orderTotal = 0;
    private int tableNumber;

    public Order(int tableNumber, Restaurant currentRestaurant) {
        this.tableNumber = tableNumber;
        this.currentRestaurant = currentRestaurant;
    }

    public void addToOrder(Food f) {
        orders.add(f);
        orderTotal += f.getPrice();
    }


    public int getTableNumber() {
        return tableNumber;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Food:\n");
        for (Food f : orders) {
            s.append(f);
            s.append("\n");
        }
        s.append(orderTotal);
        return s.toString();

    }
}