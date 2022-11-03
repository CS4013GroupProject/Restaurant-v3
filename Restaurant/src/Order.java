import java.util.ArrayList;

public class Order extends Menu {
    private ArrayList<Food> orders = new ArrayList<>();
    private double OrderTotal = 0;

    public Order(String timeOfDay) {
        super(timeOfDay);
    }
    public void addToOrder(Food foods){
        if(this.getMenu().contains(foods)) {
            orders.add(foods);
            OrderTotal += foods.getPrice();
        }
    }

    public double getOrderTotal() {
        return OrderTotal;
    }
}
