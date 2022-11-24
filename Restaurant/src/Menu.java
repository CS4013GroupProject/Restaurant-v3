import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    /** Menu class to list food and price for breakfast lunch and dinner
     *
     */

    Restaurant currentRestaurant;
    private ArrayList<Food> menuForMorning = new ArrayList<>();
    private ArrayList<Food> menuForAfterNoon = new ArrayList<>();
    private ArrayList<Food> menuForEvening = new ArrayList<>();

    /** Menu method lists food in an arraylist
     *
     */
    public Menu(Restaurant r) {

        currentRestaurant = r;
        menuForMorning.add(new Food("Full Irish Breakfast", 16.95));
        menuForMorning.add(new Food("Vegetarian Breakfast", 15.95));
        menuForMorning.add(new Food("Eggs Benedict", 12.50));
        menuForMorning.add(new Food("Eggs Royale", 14.50));
        menuForMorning.add(new Food("Avocado Benedict's", 11.50));
        menuForMorning.add(new Food("Hot Buttermilk Pancakes With Bacon", 13.50));
        menuForMorning.add(new Food("Scrambled Eggs And Smoked Salmon", 13.95));
        menuForMorning.add(new Food("Two Hen's Eggs", 8.95));
        menuForMorning.add(new Food("Folded Ham And Cheese Omelette", 11.95));
        menuForMorning.add(new Food("Organic Galway Bay Smoked Salmon", 14.95));
        menuForMorning.add(new Food("Crushed Avocado And Roasted Tomato", 10.95));
        menuForMorning.add(new Food("Non-Gluten Bramley Apple Granola", 6.75));
        menuForMorning.add(new Food("Poached Eggs And Crushed Avocado", 12.95));

        menuForAfterNoon.add(new Food("Smoked Mackerel", 5.00));
        menuForAfterNoon.add(new Food("Mushroom Consomme", 5.00));
        menuForAfterNoon.add(new Food("Ham Hock Croquette", 5.00));

        menuForEvening.add(new Food("Baked Salmon Fillet", 12.95));
        menuForEvening.add(new Food("Sweet Potato Keralan Curry", 12.95));
        menuForEvening.add(new Food("Chargrilled Paillard Of Chicken", 12.95));
        menuForEvening.add(new Food("McChicken Sandwich", 4.95));
        menuForEvening.add(new Food("Steak, Egg And Thick Cut Chips", 12.95));

        try {
            loadMenuFromDisk();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadMenuFromDisk() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Restaurant/src/menu.csv"));

        int firstLine = 0;
        while (sc.hasNext()) {
            if (firstLine == 0) {
                sc.nextLine();
                firstLine++;
                continue;
            }

            String[] data = sc.nextLine().split(",");
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].trim();
            }

            int restaurantID = Integer.parseInt(data[0]);
            String foodName = data[1];
            double price = Double.parseDouble(data[2]);
            int timeOfDay = Integer.parseInt(data[3]);

            if (restaurantID != currentRestaurant.getRestaurantId()) continue;
            Food f = new Food(foodName, price);
            ArrayList<Food> arr = (timeOfDay == 1 ? menuForMorning : timeOfDay == 2 ? menuForAfterNoon : timeOfDay == 3 ? menuForEvening : menuForMorning);

            arr.add(f);
        }
    }

    public String getMenuForAfterNoon() {
        return stringifyMenu(menuForAfterNoon);
    }

    public String getMenuForEvening() {
        return stringifyMenu(menuForEvening);
    }

    public String getMenuForMorning() {
        return stringifyMenu(menuForMorning);
    }

    public ArrayList<Food> getTotalMenu() {
        ArrayList<Food> total = new ArrayList<>();
        total.addAll(menuForEvening);
        total.addAll(menuForAfterNoon);
        total.addAll(menuForMorning);
        return total;
    }

    /**
     * method that turns the array list of food into a string
     * @param menu menu
     * @return m
     */
    public String stringifyMenu(ArrayList<Food> menu) {
        String m = "";
        for(Food f : menu) {
            m += "\n" + f.getFoodName() + "\t\t:\t\t" + f.getPrice();
        }

        return m;
    }
    /**
     * toString method that returns the time of day + the menu for that time of day
     * @return menu for morning, afternoon and evening
     */
    @Override
    public String toString() {
        String morningMenu = stringifyMenu(menuForMorning);
        String afternoonMenu = stringifyMenu(menuForAfterNoon);
        String eveningMenu = stringifyMenu(menuForEvening);

        return "Morning:\n" + morningMenu + "\n\n" + "Afternoon:\n" + afternoonMenu + "\n\n" + "Evening:\n" + eveningMenu;
    }
    /**
     * addToMenu method that allows admin to add items to menu
     * @param timeOfDay takes time of day
     * @param food takes food item to be added
     */
    public void addToMenu(int timeOfDay, Food food) throws FileNotFoundException {
        if (timeOfDay == 1) {
            System.out.println("HRAONRWIPNF");
            menuForMorning.add(food);
            System.out.println(menuForMorning);
        } else if (timeOfDay == 2) {
            menuForAfterNoon.add(food);
        } else if (timeOfDay == 3) {
            menuForEvening.add(food);
        } else {
            System.out.println("Invalid.");
        }

        String[] values = {String.valueOf(currentRestaurant.getRestaurantId()), String.valueOf(food.getFoodName()), String.valueOf(food.getPrice()), String.valueOf(timeOfDay)};
        // restaurantID, name, price, timeOfDay
        CSV("Restaurant/src/menu.csv", values);

    }

    public void CSV(String path, String[] columnNames) throws FileNotFoundException {

        FileWriter write;
        try {
            write = new FileWriter(path, true);
            for (String s : columnNames) {
                write.write(s);
                write.write(", ");
            }
            write.write("\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
//commented out old code
//HashMap<String, Double> breakfast = new HashMap<>();
//        breakfast.put("Full Irish Breakfast", 16.95);
//        breakfast.put("Vegetarian Breakfast", 15.95);
//        breakfast.put("Eggs Benedict", 12.50);
//        breakfast.put("Eggs Royale", 14.50);
//        breakfast.put("Avocado Benedict's", 11.50);
//        breakfast.put("Hot Buttermilk Pancakes With Bacon", 13.50);
//        breakfast.put("Scrambled Eggs And Smoked Salmon", 13.95);
//        breakfast.put("Non-Gluten Buttermilk Pancakes", 11.50);
//        breakfast.put("Two Hen's Eggs",8.95);
//        breakfast.put("Folded Ham And Cheese Omelette",11.95);
//        breakfast.put("Organic Galway Bay Smoked Salmon",14.95);
//        breakfast.put("Crushed Avocado And Roasted Tomato",10.95);
//        breakfast.put("Non-Gluten Bramley Apple Granola",6.75);
//        breakfast.put("Poached Eggs And Crushed Avocado",12.95);
//
//
//        HashMap<String, Double> drinks = new HashMap<>();
//        drinks.put("1917 Breakfast Tea Blend",4.00);
//        drinks.put("Afternoon Tea Blend",4.00);
//        drinks.put("Pot of Coffee And Cream",4.50);
//        drinks.put("Hot Chocolate",4.50);
//        drinks.put("Vanilla Shakerato",4.50);
//        drinks.put("Irish Coffee",11.75);
//
//        HashMap<String, Double> starters = new HashMap<>();
//        starters.put("Smoked Mackerel", 5.00);
//        starters.put("Mushroom Consomme",5.00);
//        starters.put("Ham Hock Croquette",5.00);
//
//
//        HashMap<String, Double> main = new HashMap<>();
//        main.put("Baked Salmon Fillet",12.95);
//        main.put("Sweet Potato Keralan Curry",12.95);
//        main.put("Chargrilled Paillard Of Chicken",12.95);
//        main.put("Steak, Egg And Thick Cut Chips",12.95);
//
//        HashMap<String, Double> sides = new HashMap<>();
//        sides.put("Peas, Mangetout and Baby Shoot",3.95);
//        sides.put("Truffle And Parmesan Chips",5.75);
//        sides.put("Baby Gem Lettuce, Herb Dressing, Cheese And Pine Nuts",3.75);
//        sides.put("Jasmine Rice With Toasted Coconut And Coriander",3.75);
//        sides.put("Thick Cut Chips",4.75);
//        sides.put("San Marzanino Tomato, Yellow Tomato And Basil Salad With Sherry Vinegar Dressing",4.25);
//        sides.put("Creamed Spinach, Pangrattato, Toasted Pine Nuts And Grated Parmesan", 4.75);
//        sides.put("Extra virgin olive oil mashed potato",4.25);
//        sides.put("Sprouting broccoli, lemon oil and sea salt",4.95);
//
//        HashMap<String, Double> dessert = new HashMap<>();
//        dessert.put("VANILLA ICE CREAM - Served with warm salted caramel sauce",5.00);
//        dessert.put("FOURME D’AMBERT - A French blue cheese from the Auvergne region, served with rye crackers, apple and celery",5.00);
//        dessert.put("LEMON POSSET - With poppy seed shortbread",5.00);

//Scanner search = new Scanner(System.in);
//        String food = search.nextLine();
//        if (food != null) {
//            System.out.println("The menu contains " + food + " €" + HashMap.get(food));
//        }
//import java.util.HashMap;
//import java.util.Scanner;
//public class Menu {
//    public static void main(String[] args) {
//        HashMap<String, Double> breakfast = new HashMap<>();
//            breakfast.put("Full Irish Breakfast", 16.95);
//            breakfast.put("Vegetarian Breakfast", 15.95);
//            breakfast.put("Eggs Benedict", 12.50);
//            breakfast.put("Eggs Royale", 14.50);
//            breakfast.put("Avocado Benedict's", 11.50);
//            breakfast.put("Hot Buttermilk Pancakes With Bacon", 13.50);
//            breakfast.put("Scrambled Eggs And Smoked Salmon", 13.95);
//            breakfast.put("Non-Gluten Buttermilk Pancakes", 11.50);
//            breakfast.put("Two Hen's Eggs",8.95);
//            breakfast.put("Folded Ham And Cheese Omelette",11.95);
//            breakfast.put("Organic Galway Bay Smoked Salmon",14.95);
//            breakfast.put("Crushed Avocado And Roasted Tomato",10.95);
//            breakfast.put("Non-Gluten Bramley Apple Granola",6.75);
//            breakfast.put("Poached Eggs And Crushed Avocado",12.95);
//
//
//            HashMap<String, Double> drinks = new HashMap<>();
//            drinks.put("1917 Breakfast Tea Blend",4.00);
//            drinks.put("Afternoon Tea Blend",4.00);
//            drinks.put("Pot of Coffee And Cream",4.50);
//            drinks.put("Hot Chocolate",4.50);
//            drinks.put("Vanilla Shakerato",4.50);
//            drinks.put("Irish Coffee",11.75);
//
//            HashMap<String, Double> starters = new HashMap<>();
//            starters.put("Smoked Mackerel", 5.00);
//            starters.put("Mushroom Consomme",5.00);
//            starters.put("Ham Hock Croquette",5.00);
//
//
//            HashMap<String, Double> main = new HashMap<>();
//            main.put("Baked Salmon Fillet",12.95);
//            main.put("Sweet Potato Keralan Curry",12.95);
//            main.put("Chargrilled Paillard Of Chicken",12.95);
//            main.put("Steak, Egg And Thick Cut Chips",12.95);
//
//            HashMap<String, Double> sides = new HashMap<>();
//            sides.put("Peas, Mangetout and Baby Shoot",3.95);
//            sides.put("Truffle And Parmesan Chips",5.75);
//            sides.put("Baby Gem Lettuce, Herb Dressing, Cheese And Pine Nuts",3.75);
//            sides.put("Jasmine Rice With Toasted Coconut And Coriander",3.75);
//            sides.put("Thick Cut Chips",4.75);
//            sides.put("San Marzanino Tomato, Yellow Tomato And Basil Salad With Sherry Vinegar Dressing",4.25);
//            sides.put("Creamed Spinach, Pangrattato, Toasted Pine Nuts And Grated Parmesan", 4.75);
//            sides.put("Extra virgin olive oil mashed potato",4.25);
//            sides.put("Sprouting broccoli, lemon oil and sea salt",4.95);
//
//            HashMap<String, Double> dessert = new HashMap<>();
//            dessert.put("VANILLA ICE CREAM - Served with warm salted caramel sauce",5.00);
//            dessert.put("FOURME D’AMBERT - A French blue cheese from the Auvergne region, served with rye crackers, apple and celery",5.00);
//            dessert.put("LEMON POSSET - With poppy seed shortbread",5.00);
//
//        Scanner search = new Scanner(System.in);
//        String food = search.nextLine();
//        if (food != null) {
//            System.out.println("The menu contains " + food + " €" + HashMap.get(food));
//        }
//    }
//
//
//}