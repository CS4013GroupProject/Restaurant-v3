import java.util.ArrayList;
public class Menu extends TableReservation{

        ArrayList<Food> menu = new ArrayList<>();
        public Menu(String timeOfDay) {
                if(timeOfDay == "Morning"){
                        Food food = new Food("g",5);
                        menu.add(new Food("Full Irish Breakfast",16.95 ));
                        menu.add(new Food("Vegetarian Breakfast", 15.95));
                        menu.add(new Food("Eggs Benedict",12.50 ));
                        menu.add(new Food("Eggs Royale",14.50 ));
                        menu.add(new Food("Avocado Benedict's",11.50 ));
                        menu.add(new Food("Hot Buttermilk Pancakes With Bacon", 13.50));
                        menu.add(new Food("Scrambled Eggs And Smoked Salmon", 13.95));
                        menu.add(new Food("Two Hen's Eggs", 8.95));
                        menu.add(new Food("Folded Ham And Cheese Omelette", 11.95));
                        menu.add(new Food("Organic Galway Bay Smoked Salmon",14.95 ));
                        menu.add(new Food("Crushed Avocado And Roasted Tomato", 10.95));
                        menu.add(new Food("Non-Gluten Bramley Apple Granola",6.75 ));
                        menu.add(new Food("Poached Eggs And Crushed Avocado",12.95 ));
                }
                else if (timeOfDay == "Noon") {
                        menu.add(new Food("Smoked Mackerel",5.00));
                        menu.add(new Food("Mushroom Consomme",5.00));
                        menu.add(new Food("Ham Hock Croquette",5.00));
                }
                else if (timeOfDay == "Evening"){
                        menu.add(new Food("Baked Salmon Fillet",12.95 ));
                        menu.add(new Food("Sweet Potato Keralan Curry",12.95 ));
                        menu.add(new Food("Chargrilled Paillard Of Chicken",12.95 ));
                        menu.add(new Food("McChicken Sandwich", 4.95));
                        menu.add(new Food("Steak, Egg And Thick Cut Chips",12.95 ));
                }
        }

        public ArrayList<Food> getMenu() {
                return menu;
        }

        public String toString(){
                return menu.toString();
        }
        public static void main(String[] args) {
                Menu breakfast = new Menu("Morning");
                System.out.println(breakfast);
        }
}

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