import java.util.ArrayList;

public class Order {
    private ArrayList<Food> orders = new ArrayList<>();
    private double orderTotal = 0;
    private int tableNumber;

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public void addToOrder(String food){
        for(Food f : Menu.getMenu()){
            if(f.getFoodName().equalsIgnoreCase(food)) {
                orders.add(f);
                orderTotal += f.getPrice();
            }
        }

    }

    public double getOrderTotal() {
        return orderTotal;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Food:\n");
        for(Food f : orders){
            s.append(f);
            s.append("\n");
        }
        s.append(orderTotal);
        return s.toString();

    }
}