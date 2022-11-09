import java.util.ArrayList;
import java.util.Scanner;

public class Chef extends Restaurant{
    private static ArrayList<Order> currentOrders = new ArrayList<>();
    private static ArrayList<Order> completedOrder = new ArrayList<>();
    public void menuForChef(){
        System.out.println("V)iew current orders, U)pdate status, S)ee completed orders");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("V")){
            seeCurrentOrders();
        }else if(input.equalsIgnoreCase("U")){
            System.out.println("Choose an order to update");
            for(int i = 0; i < currentOrders.size(); i++){
                System.out.println(i + " " + currentOrders.get(i));
            }
            Scanner in = new Scanner(System.in);
            int index = in.nextInt();
            if(index >= 0 && index <= currentOrders.size()){
                completedOrder.add(currentOrders.get(index));
                currentOrders.remove(index);

            }
        }else if(input.equalsIgnoreCase("S")){
            for(Order o : completedOrder){
                System.out.println(o);;
            }
        }
    }
    public static void addToActiveOrders(Order o){
        currentOrders.add(o);
    }
    public void seeCurrentOrders(){
        for(Order o : currentOrders){
            System.out.println(o);
        }
    }
}
