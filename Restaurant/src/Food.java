public class Food {
    private String foodName;
    private double price;
    private String timeOfDay;

    public Food(String foodName, double price){
        this.foodName = foodName;
        this.price = price;
    }
    public String toString(){
        String s = "";
        s += foodName + " ";
        s += price + "\n";
        return  s;
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }

}

