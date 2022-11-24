import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MainMenu extends Application {
    /**
     * Main menu class that creates the GUI for the program
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
    public Restaurant r;
    public Login customer;
    private Stage stage;
    private Manager manager;
    private int xHeight = 1440;
    private int yHeight = 900;

    /**
     * start method that creates the GUI and starts it
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage) throws FileNotFoundException {
        manager = new Manager();
        manager.startup();
        System.out.println("Post startup");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(400);

        try {
            Restaurant preload = Manager.getListOfRestaurants().get(0);
            System.out.println(preload);
            r = preload;
            stage = primaryStage;
            primaryStage.show();
            displayMainMenu();
        } catch (Exception f) {
            //------------MAIN MENU---------------//
            Button bToCreateRestaurant = new Button("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            StackPane buttonPane = new StackPane(bToCreateRestaurant);
            buttonPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

            Text titleText1 = new Text("Yum Restaurants\n");
            titleText1.setStyle("-fx-font: 42 Helvetica;");
            titleText1.setY(60);

            Text titleText2 = new Text("Created by Sam Nick Mark Brendan");
            titleText2.setStyle("-fx-font: 24 Helvetica;");
            titleText2.setY(100);

            Pane titlePane = new Pane(titleText1, titleText2);
            titlePane.setBackground(new Background(new BackgroundFill(Color.LAVENDER,null,null)));

            StackPane rootPane = new StackPane(titlePane,buttonPane);

            Scene titleScene = new Scene(rootPane, xHeight, yHeight);
            titleScene.setFill(Color.LAWNGREEN);
            System.out.println(buttonPane.getBackground());

            primaryStage.setScene(titleScene);


            primaryStage.show();
            stage = primaryStage;

            bToCreateRestaurant.setOnAction(e -> {
                displayCreationPage();
            });
        }


    }
    /**
     * display customer method that displays the GUI menu for customers
     * @param rootNodeForMenu
     * @param originalButtons
     * @param title
     */
    public void displayCustomerPage(GridPane rootNodeForMenu, HBox originalButtons, Text title) {

        Button bMakeRes = new Button("Make A reservation");
        Button bWalkIn = new Button("Walk-in Reservation");
        Button viewMenu = new Button("View Menu");
        Button bCancelRes = new Button("Cancel Reservation");
        Button switchRestaurant = new Button("Switch Restaurant");
        Button pay = new Button("Pay");
        Button lookUp = new Button("Lookup Available Tables");
        Button seeRem = new Button("See Reminders");
        Button quit = new Button("Quit");
        HBox customerButtons = new HBox(bMakeRes, bWalkIn, viewMenu, bCancelRes, switchRestaurant, pay, lookUp, seeRem, backButton());
        rootNodeForMenu.getChildren().remove(originalButtons);
        rootNodeForMenu.addColumn(1, customerButtons);
        StringBuilder s = new StringBuilder();
        for(TableReservation r : r.getListOfReservations()){
            System.out.println(r.getDate().getDayOfYear() + "Q");
            System.out.println((r.getTime().getHour() * 60) + r.getTime().getMinute());
            System.out.println(LocalDate.now().getDayOfYear());
            System.out.println((LocalTime.now().getHour() * 60) + LocalTime.now().getMinute());
            if(r.getCustomerId() == customer.getCustomerid()){
                if(r.getDate().getDayOfYear() > LocalDate.now().getDayOfYear()){
                        s.append("Reminder! You have the following reservation upcoming!\n");
                        s.append(r).append("\n");

                }
            }
        }
        Text t = new Text(s.toString());
        rootNodeForMenu.addRow(2, t);

        bMakeRes.setOnAction(f -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Label l = new Label("Enter Date & Time");
            TextField timeField = new TextField("HH:MM");
            TextField name = new TextField("Full Name");
            TextField phoneNo = new TextField("Phone Number");
            TextField noOfPpl = new TextField("Number of People");
            TextField tableNo = new TextField("Table Number");

            Button submitResData = new Button("Submit");
            DatePicker datePick = new DatePicker();
            LocalDate date = datePick.getValue();

            rootNodeForMenu.addRow(2, l);
            rootNodeForMenu.addRow(3, timeField);
            rootNodeForMenu.addRow(4, datePick);
            rootNodeForMenu.addRow(5, name);
            rootNodeForMenu.addRow(6, phoneNo);
            rootNodeForMenu.addRow(7, noOfPpl);
            rootNodeForMenu.addRow(8, tableNo);
            rootNodeForMenu.addRow(9, submitResData);

            submitResData.setOnAction(g -> {

                String[] timeArray = timeField.getText().split(":");
                LocalTime time = LocalTime.of(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
                LocalDate dateAsLocalDate = datePick.getValue();
                String nameString = name.getText();
                String phoneString = phoneNo.getText();
                int noOfPPl = Integer.parseInt(noOfPpl.getText());
                int tableNoInt = Integer.parseInt(tableNo.getText());
                try {
                    TableReservation res = new TableReservation(dateAsLocalDate, time, nameString, Integer.parseInt(phoneString), noOfPPl, r.getRestaurantId(), tableNoInt, r, customer.getCustomerid(), false);
                    r.getListOfReservations().add(res);
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });

        bWalkIn.setOnAction(g -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Label l2 = new Label("Number of People");
            TextField noOfPpl2 = new TextField("Number of People");
            Button submitResData2 = new Button("Submit");

            rootNodeForMenu.addRow(2, l2);
            rootNodeForMenu.addRow(3, noOfPpl2);
            rootNodeForMenu.addRow(4, submitResData2);

            submitResData2.setOnAction(h -> {
                try {
                    TableReservation res = new TableReservation(LocalDate.now(), LocalTime.now(), Integer.parseInt(noOfPpl2.getText()), r.getRestaurantId(), r, customer.getCustomerid());
                    r.getListOfReservations().add(res);
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        viewMenu.setOnAction(e -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            String menu = r.getMenu().toString();
            Text menuHead = new Text("Restaurant Menu:");
            Text response = new Text();
            response.setText(menu);
            rootNodeForMenu.addRow(3, menuHead);
            rootNodeForMenu.addRow(4, response);

        });

        bCancelRes.setOnAction(e -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
            Text listOfRes = new Text();
            StringBuilder st = new StringBuilder();
            int i = 1;
            for (TableReservation r : r.getListOfReservations()) {
                if (r.getCustomerId() == customer.getCustomerid()) {
                    st.append(i + ".").append("\n");
                    st.append(r.toString()).append("\n");
                    i++;
                }
            }

            listOfRes.setText(st.toString());
            TextField choice = new TextField("Which Number");
            Button b = new Button("Submit");
            rootNodeForMenu.addRow(3, listOfRes);
            rootNodeForMenu.addRow(4, choice);
            rootNodeForMenu.addRow(5, b);

            b.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                int choiceIndex = Integer.parseInt(choice.getText());
                r.getListOfReservations().remove(choiceIndex - 1);
                System.out.println(r.getListOfRestaurants().size());
            });
        });
        Text data = new Text();
        Text response = new Text();
        rootNodeForMenu.addRow(8, data);
        rootNodeForMenu.addRow(9, response);
        switchRestaurant.setOnAction(f -> {
            displaySwitch(rootNodeForMenu, data, response);

        });
        pay.setOnAction(f -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Text text = new Text("Choose an Order:");

            Text paymentPending = new Text();
            StringBuilder str = new StringBuilder();
            int i = 0;
            for (Order o : r.getPaymentPendingOrders()) {
                str.append((i+1) + ".\n");
                str.append(o.toString());
                str.append("\n");
                i++;
            }
            paymentPending.setText(str.toString());

            TextField getOrder = new TextField("Choose Order To Pay");
            TextField payment = new TextField("Insert Payment");
            TextField tip = new TextField("Insert Tip");
            Button b = new Button("Submit");


            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "Cash",
                            "Card",
                            "Cheque");

            ComboBox<String> comboBox = new ComboBox<>(options);
            rootNodeForMenu.addRow(2, text);
            rootNodeForMenu.addRow(3, paymentPending);
            rootNodeForMenu.addRow(4,getOrder);
            rootNodeForMenu.addRow(5, comboBox);
            rootNodeForMenu.addRow(6,payment);
            rootNodeForMenu.addRow(7,tip);
            rootNodeForMenu.addRow(8,b);

            b.setOnAction(g -> {
                Double tipActual = Double.parseDouble(payment.getText()) + Double.parseDouble(tip.getText());
                Payment newPayment = null;
                if(Double.parseDouble(payment.getText()) < r.getPaymentPendingOrders().get(Integer.parseInt(getOrder.getText() ) -1).getOrderTotal()){
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                    Text notEnough = new Text("Payment not enough!");
                    rootNodeForMenu.addRow(2,notEnough);


                }else{
                try {
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                    Text change = new Text("Change due: " + (Integer.parseInt(payment.getText()) - r.getPaymentPendingOrders().get(Integer.parseInt(getOrder.getText()) -1).getOrderTotal()));
                   Text thanks = new Text("Thank you. Payment has been processed");
                   rootNodeForMenu.addRow(2, thanks);
                   rootNodeForMenu.addRow(3,change);
                    newPayment = new Payment(r.getPaymentPendingOrders().get(Integer.parseInt(getOrder.getText()) - 1).getOrderTotal(), LocalDate.now(), tipActual);
                    newPayment.takePayment();
                    String[] dataPayment = {String.valueOf(r.getPaymentPendingOrders().get(Integer.parseInt(getOrder.getText()) - 1).getOrderTotal()), LocalDate.now().toString(), tip.getText(),comboBox.getValue()};
                    r.CSV("Restaurant/src/payments.csv", dataPayment);
                    r.getPaymentPendingOrders().remove(Integer.parseInt(getOrder.getText()) - 1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }}
            });
        });
        lookUp.setOnAction(f -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
            DatePicker date = new DatePicker();
            TextField time = new TextField("HH:MM");
            Label l = new Label("Choose a date and time");
            Button b = new Button("Submit");
            rootNodeForMenu.addRow(2, l);
            rootNodeForMenu.addRow(3, date);
            rootNodeForMenu.addRow(4, time);
            rootNodeForMenu.addRow(5, b);

            b.setOnAction(g -> {
                int tableres = r.getNumberOfTables();
                StringBuilder str = new StringBuilder();
                String[] timeString = time.getText().split(":");

                for (TableReservation ta : r.getListOfReservations()) {
                    if (ta.getDate().equals(date.getValue())) {
                        if (ta.getTime().getHour() <= Integer.parseInt(timeString[0])) {
                            tableres--;
                            s.append(ta.getTableNumber()).append("\n");
                        }
                    }
                }
                Text available = new Text("There are " + tableres + "\n Tables not available: " + s.toString());
                rootNodeForMenu.addRow(6, available);
            });
        });
        seeRem.setOnAction(f -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Text text = new Text();
            StringBuilder string = new StringBuilder();
            for(TableReservation r : r.getListOfReservations()){
                if(r.getCustomerId() == customer.getCustomerid()){

                        string.append(r).append("\n");

                    }
                }

            t.setText(string.toString());

            rootNodeForMenu.addRow(2,t);


        });
    }

    /**
     * displayCreationPage that displays the page for creating restaurants
     */
    public void displayCreationPage() {
        Text textCreateRestaurant = new Text("First things first.\n" +
                "Create A Restaurant\n");

        textCreateRestaurant.setStyle("-fx-font: 42 Helvetica;");
        textCreateRestaurant.setY(110);
        textCreateRestaurant.setX(110);

        StackPane createTest = new StackPane(textCreateRestaurant);
        createTest.setAlignment(Pos.TOP_LEFT);

        javafx.scene.control.TextField restaurantID = new javafx.scene.control.TextField("ID");
        javafx.scene.control.TextField capacity = new javafx.scene.control.TextField("Capacity");
        javafx.scene.control.TextField noOfTables = new TextField("Number Of Tables");
        Button buttonSubmit = new Button("Submit");



        GridPane textFieldPane = new GridPane();
        textFieldPane.addRow(0, restaurantID, capacity, noOfTables);
        textFieldPane.addRow(1, buttonSubmit);
        textFieldPane.setAlignment(Pos.CENTER);

        GridPane rootNode = new GridPane();
        rootNode.addRow(0,createTest);
        rootNode.addRow(1,textFieldPane);
        rootNode.setAlignment(Pos.CENTER);
        Scene createRestaurantScene = new Scene(rootNode, xHeight, yHeight);
        stage.setScene(createRestaurantScene);
        stage.show();
        buttonSubmit.setOnAction(e -> {
                    try {
                        r = new Restaurant(manager, Integer.parseInt(restaurantID.getText()), Integer.parseInt(capacity.getText()), Integer.parseInt(noOfTables.getText()), false);
                        Manager.getListOfRestaurants().add(r);
                        displayMainMenu();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );
    }
    /**
     * quit button method, creates quit button
     * @return returns quit
     */
    public Button quitButton() {
        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            stage.close();
        });
        return quit;
    }
    /**
     * backButton method that creates back button to return to previous screen
     * @return returns back
     */

    public Button backButton() {
        Button back = new Button("Back");
        back.setOnAction(e -> {
            Button bToCreateRestaurant = new Button("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            displayMainMenu();
        });
        return back;
    }
    /**
     * backButton method that creates back button to return to previous screen
     * @param o order
     * @return returns back
     */
    public Button backButton(Order o){
        r.getCurrentOrders().add(o);
        Button back = new Button("Back");
        back.setOnAction(e -> {
            Button bToCreateRestaurant = new Button("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            displayMainMenu();
        });
        return back;
    }
    public void displayMainMenu() {
        Text title = new Text("Restaurant " + r.getRestaurantId() + " Settings:");
        Text data = new Text("");
        title.setStyle("-fx-font: 36 Helvetica;");
        Button bForCustomer = new Button("Customer ");
        Button bForWaiter = new Button("Waiter");
        Button bForChef = new Button("Chef");
        Button bForAdmin = new Button("Administration");

        HBox originalButtons = new HBox(bForCustomer, bForWaiter, bForChef, bForAdmin, quitButton());
        GridPane rootNodeForMenu = new GridPane();
        ScrollPane root = new ScrollPane(rootNodeForMenu);
        rootNodeForMenu.addRow(1,title);
        rootNodeForMenu.addColumn(3, originalButtons);
        rootNodeForMenu.setVgap(10);
        Scene mainMenu = new Scene(root, xHeight, yHeight);
        Text response = new Text("");
        data.setStyle("-fx-font: 26 Helvetica;");
        response.setStyle("-fx-font: 16 Helvetica;");
        rootNodeForMenu.addRow(9, data);
        rootNodeForMenu.addRow(10, response);

        bForChef.setOnAction(e -> {
            // "V)iew current orders, U)pdate status, S)ee completed orders, Q)uit
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Button view = new Button("View Current Orders");
            Button update = new Button("Update Status");
            Button seeComplete = new Button("See Completed Orders");

            HBox chefButtons = new HBox(view, update, seeComplete, backButton());
            rootNodeForMenu.getChildren().remove(originalButtons);
            rootNodeForMenu.addColumn(3, chefButtons);

            view.setOnAction(f ->{
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                StringBuilder s = new StringBuilder();
                for(Order o : r.getCurrentOrders()){
                    s.append(o.toString()).append("\n");
                }
                Text orders = new Text(s.toString());
                rootNodeForMenu.addRow(2,orders);

            });
            update.setOnAction(f ->{
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Choose an order to update");
                String s = "";
                int i = 1;
                for(Order o : r.getCurrentOrders()){
                    s += i + "\n" ;
                    s += o.toString() + "\n";
                }
                Text orders = new Text(s);
                TextField index = new TextField("Enter order Number");
                Button submit = new Button("Submit");
                rootNodeForMenu.addRow(2,t);
                rootNodeForMenu.addRow(3,orders );
                rootNodeForMenu.addRow(4,index);
                rootNodeForMenu.addRow(5,submit);
                submit.setOnAction(g -> {
                    r.getCompletedOrder().add(r.getCurrentOrders().get(Integer.parseInt(index.getText()) -1));
                    r.getPaymentPendingOrders().add(r.getCurrentOrders().get(Integer.parseInt(index.getText()) -1));
                    r.getCurrentOrders().remove(Integer.parseInt(index.getText()) -1);
                });
            });
            seeComplete.setOnAction(f ->{
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for(Order o : r.getCompletedOrder()){
                    s += o.toString() + "\n";
                }
                Text t = new Text(s);
                rootNodeForMenu.addRow(2,t);
            });

        });

        bForCustomer.setOnAction( e -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Button bCreateAcc = new Button("Create Account");
            Button bSignIn = new Button("Sign in");
            rootNodeForMenu.addRow(2,bCreateAcc);
            rootNodeForMenu.addRow(3,bSignIn);
            bCreateAcc.setOnAction( f ->{
                rootNodeForMenu.getChildren().remove(bCreateAcc);
                rootNodeForMenu.getChildren().remove(bSignIn);
                TextField username = new TextField("Username");
                TextField pass = new TextField("Password");
                Button b = new Button("Submit");
                rootNodeForMenu.addRow(3, username);
                rootNodeForMenu.addRow(4,pass);
                rootNodeForMenu.addRow(5, b);
                b.setOnAction(g -> {
                    rootNodeForMenu.getChildren().removeAll(username, pass, b);
                    Login log = new Login(username.getText(), pass.getText(), (int) ((Math.random() * 89999999) + 10000000));
                            r.addToListOfCustomers(log, false);
                    customer = log;
                    displayCustomerPage(rootNodeForMenu,originalButtons, title);
                    }
                );

            });
            bSignIn.setOnAction(f -> {

                rootNodeForMenu.getChildren().remove(bCreateAcc);
                rootNodeForMenu.getChildren().remove(bSignIn);
                TextField username = new TextField("Username");
                TextField pass = new TextField("Password");
                Button b = new Button("Submit");
                rootNodeForMenu.addRow(3, username);
                rootNodeForMenu.addRow(4,pass);
                rootNodeForMenu.addRow(5, b);
                b.setOnAction(g -> {
                    for(Login l : Restaurant.getListOfCustomers()){
                        if(l.getUsername().equalsIgnoreCase(username.getText()) && l.getPassword().equalsIgnoreCase(pass.getText())){
                            customer = l;
                            displayCustomerPage(rootNodeForMenu, originalButtons, title);
                            rootNodeForMenu.getChildren().removeAll(username, pass, b);

                            break;
                        }
                    }
                });
            });








        });

        bForAdmin.setOnAction(e -> {
            //         System.out.println("Get R)estaurant ID, get N)umber of Tables, get C)apacity, V)iew Menu, Cr)eate new restaurant, S)witch restaurant, View Re)servations, A)dd to Menu, Q)uit ");
            Button getID = new Button("Get Restaurant ID");
            Button getNumberOfTables = new Button("Get Number of Tables");
            Button getCapacity = new Button("Get Capacity");
            Button viewMenu = new Button("View Menu");
            Button createNewRestaurant = new Button("Create New Restaurant");
            Button switchRestaurant = new Button("Switch Restaurant");
            Button viewReservations = new Button("View Reservations");
            Button addToMenu = new Button("Add to Menu");
            Button getAnalytics = new Button("Generate Analytics");

            HBox adminButtons = new HBox(getID, getNumberOfTables, getCapacity, viewMenu, createNewRestaurant, switchRestaurant, viewReservations, addToMenu, getAnalytics, backButton());
            rootNodeForMenu.getChildren().remove(originalButtons);
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            rootNodeForMenu.addColumn(3, adminButtons);

            getID.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant ID: " + r.getRestaurantId() );
                rootNodeForMenu.addRow(2,t);
            });

            getCapacity.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant Capacity: " + r.getCapacity() );
                rootNodeForMenu.addRow(2,t);
            });

            getNumberOfTables.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant Number Of Tables: " + r.getNumberOfTables() );
                rootNodeForMenu.addRow(2,t);
            });
            switchRestaurant.setOnAction( d ->{
                displaySwitch(rootNodeForMenu, data, response);
            });
            viewReservations.setOnAction( d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for(TableReservation r : r.getListOfReservations()){
                    s += r.toString() + "\n";

                }
                Text res = new Text(s);
                rootNodeForMenu.addRow(2,res);
            });
            addToMenu.setOnAction(d -> {
                data.setText("");
                response.setText("");
                data.setText("");
                response.setText("");
                TextField name = new TextField("Name");
                TextField price = new TextField("Price");

                ObservableList<String> options =
                        FXCollections.observableArrayList(
                                "Morning",
                                "Noon",
                                "Evening"
                        );
                 ComboBox<String> comboBox = new ComboBox<>(options);
                 Button b = new Button("Submit");
                 rootNodeForMenu.addRow(5,b);
                 rootNodeForMenu.addRow(2,name);
                 rootNodeForMenu.addRow(3,price);
                 rootNodeForMenu.addRow(4,comboBox);
                b.setOnAction(f -> {
                    Food newFood = new Food(name.getText(), Double.parseDouble(price.getText()));
                    Menu newMenu = new Menu(r);
                    int time = 0;
                    if (comboBox.getValue().equalsIgnoreCase("morning")){
                        time = 1;
                    }else if(comboBox.getValue().equalsIgnoreCase("noon")){
                        time = 2;
                    }else if (comboBox.getValue().equalsIgnoreCase("evening")){
                        time = 3;
                    }
                    try {
                        newMenu.addToMenu(time, newFood);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    r.setMenu(newMenu);
                    stage.setScene(mainMenu);
                });


            });


            createNewRestaurant.setOnAction(d -> {
                displayCreationPage();
            });

            viewMenu.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                String menu = r.getMenu().toString();
                Text textMenu = new Text("Restaurant Menu:\n" + menu);
                rootNodeForMenu.addRow(2,textMenu);
            });
            getAnalytics.setOnAction(d ->{
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Enter Start Date");
                Text t2 = new Text("Enter End Date");
                DatePicker start = new DatePicker();
                DatePicker end = new DatePicker();
                Button submit = new Button("Submit");
                rootNodeForMenu.addRow(2,t);
                rootNodeForMenu.addRow(3,start);
                rootNodeForMenu.addRow(4,t2);;
                rootNodeForMenu.addRow(5,end);
                rootNodeForMenu.addRow(6,submit);
                submit.setOnAction(g -> {
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                    LocalDate before = start.getValue();
                    LocalDate after = end.getValue();
                    List<LocalDate> datesBetween = before.datesUntil(after).toList();

                    boolean finished = false;
                    double totalRev = 0;
                    double totalTips= 0;
                    for(int i = 0; i < datesBetween.size(); i++){
                        Scanner scanPay = null;
                        try {
                            scanPay = new Scanner(new File("Restaurant//src//payments.csv"));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        assert scanPay != null;
                        scanPay.nextLine();

                        double totalForDay = 0;
                        double totalTipsForDay = 0;
                        while(scanPay.hasNext()){
                            String line =scanPay.nextLine().trim();

                            String[] dataPerLine = line.split(", ");
                            String[] date = dataPerLine[1].split("-");
                            int[] dateFormat = new int[3];
                            for(int j = 0; j < 3; j++){
                                dateFormat[j] = Integer.parseInt(date[j]);
                            }
                            LocalDate dateOf = LocalDate.of(dateFormat[0], dateFormat[1],dateFormat[2]);
                            System.out.println(r.getRestaurantId());
                            System.out.println(dataPerLine[4].charAt(0));
                            if(dateOf.isBefore( after) && dateOf.isAfter(before) && (r.getRestaurantId() == Integer.parseInt(dataPerLine[4].substring(0,1)))){
                                if(dateOf.equals(datesBetween.get(i))){
                                    totalForDay += Double.parseDouble(dataPerLine[0]);
                                    totalTipsForDay += Double.parseDouble(dataPerLine[2]);
                                    totalRev += Double.parseDouble(dataPerLine[0]);
                                    totalTips += Double.parseDouble(dataPerLine[2]);

                                }


                            }

                        }
                        String textString =  "Total For Day "+ datesBetween.get(i).toString() +" : " + totalForDay + "\nTips: " + totalTipsForDay;

                        rootNodeForMenu.addRow(i + 1 + 1, new Text(textString));



                    }
                    String s = "Total Revenue for " + r.getRestaurantId() + ": " +  totalRev + " \nTips: " + totalTips;
                    int noOfRows = rootNodeForMenu.getRowCount();
                    rootNodeForMenu.addRow(noOfRows + 1, new Text(s));



                });
            });
        });
        bForWaiter.setOnAction(e -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Button bRestID = new Button("Get Restaurant ID");
            Button bCap = new Button("Get Capacity");
            Button bNoOfT = new Button("Get Num Of Tables");
            Button bCreateOrder = new Button("Create Order");
            Button bViewMenu = new Button("View Menu");
            Button bViewRes = new Button("View Reservations");
            HBox buttonsForWaiter = new HBox(bRestID, bCap, bNoOfT, bCreateOrder, bViewMenu, bViewRes, backButton());
            rootNodeForMenu.getChildren().remove(originalButtons);
            rootNodeForMenu.addColumn(1, buttonsForWaiter);

            bRestID.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Restaurant ID: " + r.getRestaurantId());
                rootNodeForMenu.addRow(2, t);

            });
            bCap.setOnAction(f ->{
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Capacity: " + r.getCapacity());
                rootNodeForMenu.addRow(2, t);

            });
            bNoOfT.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Number of Tables: " + r.getNumberOfTables());
                rootNodeForMenu.addRow(2,t);
            });
            bViewMenu.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                s += r.getMenu().getTotalMenu().toString();
                Text t = new Text(s);
                rootNodeForMenu.addRow(2,t);
            });
            bViewRes.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for(TableReservation r : r.getListOfReservations()){
                    s+= r.toString() + "\n";

                }
                Text res = new Text(s);
                rootNodeForMenu.addRow(2,res);
            });
            bCreateOrder.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                TextField t = new TextField("Enter Table Number");
                Button submit = new Button("Submit");
                rootNodeForMenu.addRow(2,t);
                rootNodeForMenu.addRow(3,submit);
                submit.setOnAction(g -> {
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                    Order order = new Order(Integer.parseInt(t.getText()), r);
                    Text menu = new Text(r.getMenu().toString());
                    rootNodeForMenu.addRow(2, menu);
                    TextField food = new TextField("Enter Food");
                    rootNodeForMenu.addRow(3,food);
                    Button submitButton = new Button("Submit Food");

                    rootNodeForMenu.addRow(4,submitButton,backButton(order));
                    submitButton.setOnAction(h -> {

                        for(Food foodString : r.getMenu().getTotalMenu()){
                            if(foodString.getFoodName().equalsIgnoreCase(food.getText())){
                                order.addToOrder(foodString);

                            }
                        }});
                    });
                });
            });

        stage.setScene(mainMenu);
    };
    public void displaySwitch(GridPane rootNodeForMenu, Text data, Text response) {
        rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

        response.setText("");
        TextField f = new TextField("Restaurant ID");
        Button b = new Button("Submit");
        GridPane switchRes = new GridPane();
        switchRes.addRow(2, f);
        switchRes.addRow(3, b);
        StringBuilder s = new StringBuilder();
        for (Restaurant r : Manager.getListOfRestaurants()) {
            s.append(r.getRestaurantId()).append("\n");
        }
        Text t = new Text(s.toString());
        switchRes.addRow(1, t);
        rootNodeForMenu.addRow(2, switchRes);

        b.setOnAction(c -> {
            String st = f.getText();
            Restaurant resTemp = null;
            for (Restaurant res : Manager.getListOfRestaurants()) {
                if (String.valueOf(res.getRestaurantId()).equalsIgnoreCase(st)) {

                        resTemp = res;
                        break;


                }
            }
            r = resTemp;
            data.setText("Restaurant " + r.getRestaurantId() + " settings");
            System.out.println(r.getListOfReservations().size());
            data.setStyle("-fx-font: 36 Helvetica;");
            rootNodeForMenu.getChildren().remove(0);
            rootNodeForMenu.addRow(1, data);
            rootNodeForMenu.getChildren().remove(rootNodeForMenu.getChildren().size()-1);

            rootNodeForMenu.getChildren().add(0,data);








        });
    }






}
