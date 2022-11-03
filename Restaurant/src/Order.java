import java.util.ArrayList;

public class Order extends Menu {
    private ArrayList<Food> orders = new ArrayList<>();
    private double OrderTotal = 0;

    public Order(String timeOfDay) {
        super(timeOfDay);
    }
    public void addToOrder(Food foods){
        orders.add(foods);
        OrderTotal += foods.getPrice();
    }
}
