public class Food {
    private String foodName;
    private double price;
    private String timeOfDay;

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getFoodName() {
        return foodName;
    }

    public String toString() {
        String s = "";
        s += foodName + " ";
        s += price + "\n";
        return s;
    }


}

