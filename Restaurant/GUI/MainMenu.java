import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainMenu extends Application {
    public Restaurant r;
    public Login customer;
    String backgroundColor = "#E5F9E0";
    String navColor = "#222E50";
    String buttonColor = "#A3F7B5";
    private Stage stage;
    private Manager manager;
    private int xHeight = 1920;
    private int yHeight = 1080;

    /**
     * Main menu class that creates the GUI for the program
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start method that creates the GUI and starts it
     *
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
            Button bToCreateRestaurant = generateButton("Create Restaurant");
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
            titlePane.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));

            StackPane rootPane = new StackPane(titlePane, buttonPane);

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
     *
     * @param rootNodeForMenu
     * @param sidebar
     * @param title
     */
    public void displayCustomerPage(GridPane rootNodeForMenu, VBox sidebar, Text title) {

        Button bMakeRes = generateButton("Make A reservation");
        Button bWalkIn = generateButton("Walk-in Reservation");
        Button viewMenu = generateButton("View Menu");
        Button bCancelRes = generateButton("Cancel Reservation");
        Button switchRestaurant = generateButton("Switch Restaurant");
        Button pay = generateButton("Pay");
        Button lookUp = generateButton("Lookup Available Tables");
        Button seeRem = generateButton("See Reminders");

        ArrayList<Button> buttons = new ArrayList<>();

        sidebar.getChildren().clear();

        sidebar.getChildren().add(bMakeRes);
        sidebar.getChildren().add(bWalkIn);
        sidebar.getChildren().add(viewMenu);
        sidebar.getChildren().add(bCancelRes);
        sidebar.getChildren().add(switchRestaurant);
        sidebar.getChildren().add(pay);
        sidebar.getChildren().add(lookUp);
        sidebar.getChildren().add(seeRem);

        StringBuilder s = new StringBuilder();
        for (TableReservation r : r.getListOfReservations()) {
            System.out.println(r.getDate().getDayOfYear() + "Q");
            System.out.println((r.getTime().getHour() * 60) + r.getTime().getMinute());
            System.out.println(LocalDate.now().getDayOfYear());
            System.out.println((LocalTime.now().getHour() * 60) + LocalTime.now().getMinute());
            if (r.getCustomerId() == customer.getCustomerid()) {
                if (r.getDate().getDayOfYear() > LocalDate.now().getDayOfYear()) {
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

            Button submitResData = generateButton("Submit");
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
                    boolean booked = false;
                    for (TableReservation r : r.getListOfReservations()) {
                        if (r.getDate().equals(dateAsLocalDate)) {
                            if (r.getTime().getHour() + 3 >= time.getHour()) {
                                if (r.getTableNumber() == tableNoInt) {

                                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                                    Text text = new Text("Table is booked at that time");
                                    rootNodeForMenu.addRow(2, text);
                                    booked = true;
                                }
                            }
                        }
                    }
                    if (!booked) {
                        r.getListOfReservations().add(res);
                        rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });

        bWalkIn.setOnAction(g -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            Label l2 = new Label("Number of People");
            TextField noOfPpl2 = new TextField("Number of People");
            Button submitResData2 = generateButton("Submit");

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
            Button b = generateButton("Submit");
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
            int i = 0;

            ComboBox<String> orderSelector = new ComboBox<>();

            for (Order o : r.getPaymentPendingOrders()) {
                orderSelector.getItems().add((i + 1) + ". " + o);
                i++;
            }
            TextField getOrder = new TextField("Choose Order To Pay");
            TextField payment = new TextField("Insert Payment");
            TextField tip = new TextField("Insert Tip");
            Button b = generateButton("Submit");


            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "Cash",
                            "Card",
                            "Cheque");

            ComboBox<String> comboBox = new ComboBox<>(options);
            rootNodeForMenu.addRow(2, text);
            rootNodeForMenu.addRow(3, orderSelector);
            rootNodeForMenu.addRow(5, comboBox);
            rootNodeForMenu.addRow(6, payment);
            rootNodeForMenu.addRow(7, tip);
            rootNodeForMenu.addRow(8, b);

            b.setOnAction(g -> {
                String selectedCombo = orderSelector.getValue().split("\\.")[0];
                Order selectedOrder = r.getPaymentPendingOrders().get(Integer.parseInt(selectedCombo)-1);
                double paymentDouble = Double.parseDouble(payment.getText());
                double tipDouble = Double.parseDouble(tip.getText());
                Payment newPayment = null;
                if (paymentDouble < selectedOrder.getOrderTotal()) {
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                    Text notEnough = new Text("Payment not enough!");
                    rootNodeForMenu.addRow(2, notEnough);

                } else {
                    try {
                        rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                        double changeDue = Math.round((paymentDouble - selectedOrder.getOrderTotal()) * 100) / 100;
                        Text change = new Text("Change due: €" + changeDue);
                        Text thanks = new Text("Thank you. Payment has been processed");
                        rootNodeForMenu.addRow(2, thanks);
                        rootNodeForMenu.addRow(3, change);
                        newPayment = new Payment(selectedOrder.getOrderTotal(), LocalDate.now(), tipDouble);
                        newPayment.takePayment();
                        String[] dataPayment = {String.valueOf(selectedOrder.getOrderTotal()), LocalDate.now().toString(), tip.getText(), comboBox.getValue()};
                        r.CSV("Restaurant/src/payments.csv", dataPayment);
                        r.getPaymentPendingOrders().remove(selectedOrder);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        lookUp.setOnAction(f -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
            DatePicker date = new DatePicker();
            TextField time = new TextField("HH:MM");
            Label l = new Label("Choose a date and time");
            Button b = generateButton("Submit");
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
            for (TableReservation r : r.getListOfReservations()) {
                if (r.getCustomerId() == customer.getCustomerid()) {

                    string.append(r).append("\n");

                }
            }

            t.setText(string.toString());

            rootNodeForMenu.addRow(2, t);
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
        Button buttonSubmit = generateButton("Submit");


        GridPane textFieldPane = new GridPane();
        textFieldPane.addRow(0, restaurantID, capacity, noOfTables);
        textFieldPane.addRow(1, buttonSubmit);
        textFieldPane.setAlignment(Pos.CENTER);

        GridPane rootNode = new GridPane();
        rootNode.addRow(0, createTest);
        rootNode.addRow(1, textFieldPane);
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
     *
     * @return Button
     */
    public Button quitButton() {
        Button quit = generateButton("Quit");
        quit.setOnAction(e -> {
            stage.close();
        });
        return quit;
    }

    /**
     * backButton method that creates back button to return to previous screen
     *
     * @return returns back
     */

    public Button backButton() {
        Button back = generateButton("Back");
        back.setOnAction(e -> {
            Button bToCreateRestaurant = generateButton("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            displayMainMenu();
        });
        return back;
    }

    /**
     * backButton method that creates back button to return to previous screen
     *
     * @param o order
     * @return returns back
     */
    public Button backButton(Order o) {
        r.getCurrentOrders().add(o);
        Button back = generateButton("Back");
        back.setOnAction(e -> {
            Button bToCreateRestaurant = generateButton("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            displayMainMenu();
        });
        return back;
    }

    public Button generateButton(String text) {
        Button b = new Button(text);
        b.setPrefSize(200, 60);
        b.setBackground(Background.fill(Paint.valueOf(buttonColor)));
        b.setStyle("-fx-font: 16 Helvetica;");
        return b;
    }

    public ArrayList<Button> generateMenuButtons() {
        Button buttonCustomer = generateButton("Customer");

        Button buttonChef = generateButton("Chef");

        Button buttonAdmin = generateButton("Administration");

        Button buttonWaiter = generateButton("Waiter");

        Button buttonQuit = quitButton();

        ArrayList<Button> buttonList = new ArrayList<>();

        buttonList.add(buttonCustomer);
        buttonList.add(buttonChef);
        buttonList.add(buttonAdmin);
        buttonList.add(buttonWaiter);
        buttonList.add(buttonQuit);

        return buttonList;
    }


    public HBox addMenuHbox(ArrayList<Button> buttonList) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(15);
        hbox.setBackground(Background.fill(Paint.valueOf(navColor)));

        for (Button b : buttonList) {
            hbox.getChildren().add(b);
        }

        return hbox;
    }

    public void setActive(ArrayList<Button> buttons, Button a) {
        for (Button b : buttons) {
            b.setBackground(Background.fill(Paint.valueOf(buttonColor)));
        }

        a.setBackground(Background.fill(Color.DARKGRAY));
    }

    public void displayMainMenu() {
        Text title = new Text("Yum Restaurants | Restaurant " + r.getRestaurantId());
        Text data = new Text("");
        title.setStyle("-fx-font: 36 Helvetica; -fx-border-radius: 25px;");

        GridPane textBox = new GridPane();

        textBox.setBackground(Background.fill(Color.LIGHTSLATEGREY));
        textBox.setPadding(new Insets(5));
        textBox.getChildren().add(title);

        BorderPane nav = new BorderPane();

        ArrayList<Button> originalButtons = generateMenuButtons();
        HBox navbar = addMenuHbox(originalButtons);
        VBox sidebar = new VBox();

        nav.setBackground(Background.fill(Paint.valueOf(backgroundColor)));

        Button bForCustomer = originalButtons.get(0);
        Button bForChef = originalButtons.get(1);
        Button bForAdmin = originalButtons.get(2);
        Button bForWaiter = originalButtons.get(3);

        sidebar.setBackground(Background.fill(Paint.valueOf(navColor)));
        sidebar.setPadding(new Insets(10, 20, 20, 12));
        sidebar.setSpacing(10);

        nav.setTop(navbar);
        GridPane rootNodeForMenu = new GridPane();
        ScrollPane root = new ScrollPane(rootNodeForMenu);
        rootNodeForMenu.addRow(1, textBox);
        rootNodeForMenu.setPadding(new Insets(10));
        rootNodeForMenu.setVgap(10);
        rootNodeForMenu.setAlignment(Pos.CENTER);
        nav.setCenter(rootNodeForMenu);
        nav.setLeft(sidebar);
        Scene mainMenu = new Scene(nav, xHeight, yHeight);
        Text response = new Text("");
        data.setStyle("-fx-font: 26 Helvetica;");
        response.setStyle("-fx-font: 22 Helvetica;");
        rootNodeForMenu.addRow(9, data);
        rootNodeForMenu.addRow(10, response);

        bForChef.setOnAction(e -> {
            // "V)iew current orders, U)pdate status, S)ee completed orders, Q)uit
            setActive(originalButtons, bForChef);
            sidebar.getChildren().clear();

            Button view = generateButton("View Current Orders");
            Button update = generateButton("Mark Orders Complete");
            Button seeComplete = generateButton("See Completed Orders");

            sidebar.getChildren().add(view);
            sidebar.getChildren().add(update);
            sidebar.getChildren().add(seeComplete);

            view.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                StringBuilder s = new StringBuilder();
                for (Order o : r.getCurrentOrders()) {
                    s.append(o.toString()).append("\n");
                }
                Text orders = new Text(s.toString());
                rootNodeForMenu.addRow(2, orders);

            });
            update.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Choose an order to mark");
                String s = "";
                int i = 1;

                ComboBox<String> comboBox = new ComboBox<>();

                for (Order o : r.getCurrentOrders()) {
                    s += i + "\n";
                    s += o.toString() + "\n";
                    comboBox.getItems().add(i + ". " + o);
                    System.out.println("ADDED " + i + ". " + o);
                }
                Button submit = generateButton("Submit");
                rootNodeForMenu.addRow(2, t);
                rootNodeForMenu.addRow(3, comboBox);
                rootNodeForMenu.addRow(5, submit);
                submit.setOnAction(g -> {
                    String comboSelect = comboBox.getValue();
                    System.out.println(Arrays.deepToString(comboSelect.split("\\.")));
                    int selected = Integer.parseInt(comboSelect.split("\\.")[0]) - 1;
                    Order orderToMark = r.getCurrentOrders().get(selected);
                    r.getCompletedOrder().add(orderToMark);
                    r.getPaymentPendingOrders().add(orderToMark);
                    r.getCurrentOrders().remove(selected);
                    comboBox.getItems().remove(comboSelect);
                });
            });
            seeComplete.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for (Order o : r.getCompletedOrder()) {
                    s += o.toString() + "\n";
                }
                Text t = new Text(s);
                rootNodeForMenu.addRow(2, t);
            });

        });

        bForCustomer.setOnAction(e -> {
            rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

            setActive(originalButtons, bForCustomer);
            sidebar.getChildren().clear();

            Button bCreateAcc = generateButton("Create Account");
            Button bSignIn = generateButton("Sign in");
            sidebar.getChildren().add(bCreateAcc);
            sidebar.getChildren().add(bSignIn);
            bCreateAcc.setOnAction(f -> {
                TextField username = new TextField("Username");
                TextField pass = new TextField("Password");
                Button b = generateButton("Submit");
                rootNodeForMenu.addRow(3, username);
                rootNodeForMenu.addRow(4, pass);
                rootNodeForMenu.addRow(5, b);
                b.setOnAction(g -> {
                            rootNodeForMenu.getChildren().removeAll(username, pass, b);
                            Login log = new Login(username.getText(), pass.getText(), (int) ((Math.random() * 89999999) + 10000000));
                            r.addToListOfCustomers(log, false);
                            customer = log;
//                            navbar.getChildren().clear();
//                            navbar.getChildren().add(bForCustomer);
//                            setActive(new ArrayList<Button>(), bForCustomer);
                            displayCustomerPage(rootNodeForMenu, sidebar, title);
                        }
                );

            });
            bSignIn.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                TextField username = new TextField("Username");
                TextField pass = new TextField("Password");
                Button b = generateButton("Submit");
                rootNodeForMenu.addRow(3, username);
                rootNodeForMenu.addRow(4, pass);
                rootNodeForMenu.addRow(5, b);
                b.setOnAction(g -> {
                    for (Login l : Restaurant.getListOfCustomers()) {
                        if (l.getUsername().equalsIgnoreCase(username.getText()) && l.getPassword().equalsIgnoreCase(pass.getText())) {
                            customer = l;
//                            navbar.getChildren().clear();
//                            navbar.getChildren().add(bForCustomer);
//                            setActive(new ArrayList<Button>(), bForCustomer);
                            displayCustomerPage(rootNodeForMenu, sidebar, title);
                            rootNodeForMenu.getChildren().removeAll(username, pass, b);
                            break;
                        }
                    }
                });
            });


        });

        bForAdmin.setOnAction(e -> {
            setActive(originalButtons, bForAdmin);
            sidebar.getChildren().clear();

            Button getID = generateButton("Get Restaurant ID");
            Button getNumberOfTables = generateButton("Get Number of Tables");
            Button getCapacity = generateButton("Get Capacity");
            Button viewMenu = generateButton("View Menu");
            Button createNewRestaurant = generateButton("Create New Restaurant");
            Button switchRestaurant = generateButton("Switch Restaurant");
            Button viewReservations = generateButton("View Reservations");
            Button addToMenu = generateButton("Add to Menu");
            Button getAnalytics = generateButton("Generate Analytics");

            sidebar.getChildren().add(getID);
            sidebar.getChildren().add(getNumberOfTables);
            sidebar.getChildren().add(getCapacity);
            sidebar.getChildren().add(viewMenu);
            sidebar.getChildren().add(createNewRestaurant);
            sidebar.getChildren().add(switchRestaurant);
            sidebar.getChildren().add(viewReservations);
            sidebar.getChildren().add(addToMenu);
            sidebar.getChildren().add(getAnalytics);

            getID.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant ID: " + r.getRestaurantId());
                rootNodeForMenu.addRow(2, t);
            });

            getCapacity.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant Capacity: " + r.getCapacity());
                rootNodeForMenu.addRow(2, t);
            });

            getNumberOfTables.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                Text t = new Text("Restaurant Number Of Tables: " + r.getNumberOfTables());
                rootNodeForMenu.addRow(2, t);
            });
            switchRestaurant.setOnAction(d -> {
                displaySwitch(rootNodeForMenu, data, response);
            });
            viewReservations.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for (TableReservation r : r.getListOfReservations()) {
                    s += r.toString() + "\n";

                }
                Text res = new Text(s);
                ScrollPane scroll = new ScrollPane(res);
                rootNodeForMenu.addRow(2, scroll);
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
                Button b = generateButton("Submit");
                rootNodeForMenu.addRow(5, b);
                rootNodeForMenu.addRow(2, name);
                rootNodeForMenu.addRow(3, price);
                rootNodeForMenu.addRow(4, comboBox);
                b.setOnAction(f -> {
                    Food newFood = new Food(name.getText(), Double.parseDouble(price.getText()));
                    Menu newMenu = new Menu(r);
                    int time = 0;
                    if (comboBox.getValue().equalsIgnoreCase("morning")) {
                        time = 1;
                    } else if (comboBox.getValue().equalsIgnoreCase("noon")) {
                        time = 2;
                    } else if (comboBox.getValue().equalsIgnoreCase("evening")) {
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
                rootNodeForMenu.addRow(2, textMenu);
            });
            getAnalytics.setOnAction(d -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Enter Start Date");
                Text t2 = new Text("Enter End Date");
                DatePicker start = new DatePicker();
                DatePicker end = new DatePicker();
                Button submit = generateButton("Submit");
                rootNodeForMenu.addRow(2, t);
                rootNodeForMenu.addRow(3, start);
                rootNodeForMenu.addRow(4, t2);
                ;
                rootNodeForMenu.addRow(5, end);
                rootNodeForMenu.addRow(6, submit);
                submit.setOnAction(g -> {
                    rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                    LocalDate before = start.getValue();
                    LocalDate after = end.getValue();
                    List<LocalDate> datesBetween = before.datesUntil(after).toList();

                    boolean finished = false;
                    double totalRev = 0;
                    double totalTips = 0;
                    for (int i = 0; i < datesBetween.size(); i++) {
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
                        while (scanPay.hasNext()) {
                            String line = scanPay.nextLine().trim();

                            String[] dataPerLine = line.split(", ");
                            String[] date = dataPerLine[1].split("-");
                            int[] dateFormat = new int[3];
                            for (int j = 0; j < 3; j++) {
                                dateFormat[j] = Integer.parseInt(date[j]);
                            }
                            LocalDate dateOf = LocalDate.of(dateFormat[0], dateFormat[1], dateFormat[2]);
                            System.out.println(r.getRestaurantId());
                            System.out.println(dataPerLine[4].charAt(0));
                            if (dateOf.isBefore(after) && dateOf.isAfter(before) && (r.getRestaurantId() == Integer.parseInt(dataPerLine[4].substring(0, 1)))) {
                                if (dateOf.equals(datesBetween.get(i))) {
                                    totalForDay += Double.parseDouble(dataPerLine[0]);
                                    totalTipsForDay += Double.parseDouble(dataPerLine[2]);
                                    totalRev += Double.parseDouble(dataPerLine[0]);
                                    totalTips += Double.parseDouble(dataPerLine[2]);

                                }
                            }

                        }
                        String textString = "Total For Day " + datesBetween.get(i).toString() + " : " + totalForDay + "\nTips: " + totalTipsForDay;

                        rootNodeForMenu.addRow(i + 1 + 1, new Text(textString));

                    }
                    String s = "Total Revenue for " + r.getRestaurantId() + ": " + totalRev + " \nTips: " + totalTips;
                    int noOfRows = rootNodeForMenu.getRowCount();
                    rootNodeForMenu.addRow(noOfRows + 1, new Text(s));

                });
            });
        });
        bForWaiter.setOnAction(e -> {
            sidebar.getChildren().clear();
            setActive(originalButtons, bForWaiter);

            Button bRestID = generateButton("Get Restaurant ID");
            Button bCap = generateButton("Get Capacity");
            Button bNoOfT = generateButton("Get Num Of Tables");
            Button bCreateOrder = generateButton("Create Order");
            Button bViewMenu = generateButton("View Menu");
            Button bViewRes = generateButton("View Reservations");

            sidebar.getChildren().add(bRestID);
            sidebar.getChildren().add(bCap);
            sidebar.getChildren().add(bNoOfT);
            sidebar.getChildren().add(bCreateOrder);
            sidebar.getChildren().add(bViewMenu);
            sidebar.getChildren().add(bViewRes);

            bRestID.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Restaurant ID: " + r.getRestaurantId());
                rootNodeForMenu.addRow(2, t);

            });
            bCap.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Capacity: " + r.getCapacity());
                rootNodeForMenu.addRow(2, t);

            });
            bNoOfT.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                Text t = new Text("Number of Tables: " + r.getNumberOfTables());
                rootNodeForMenu.addRow(2, t);
            });
            bViewMenu.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                s += r.getMenu().getTotalMenu().toString();
                Text t = new Text(s);
                rootNodeForMenu.addRow(2, t);
            });
            bViewRes.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);
                String s = "";
                for (TableReservation r : r.getListOfReservations()) {
                    s += r.toString() + "\n";

                }
                Text res = new Text(s);
                rootNodeForMenu.addRow(2, res);
            });
            bCreateOrder.setOnAction(f -> {
                rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                GridPane tableButtons = new GridPane();
                tableButtons.setHgap(10);
                tableButtons.setVgap(10);
                int row = 0;
                int column = 0;

                for(int i = 1; i < r.getNumberOfTables(); i++) {
                    Button a = generateButton("Table " + i);
                    int tableNumber = i;
                    a.setOnAction(g -> {
                        rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

                        Order order = new Order(tableNumber, r);
                        Text menu = new Text(r.getMenu().toString());
                        rootNodeForMenu.addRow(2, menu);
                        TextField food = new TextField("Enter Food");
                        rootNodeForMenu.addRow(3, food);
                        Button submitButton = generateButton("Submit Food");

                        rootNodeForMenu.addRow(4, submitButton, backButton(order));
                        submitButton.setOnAction(h -> {

                            for (Food foodString : r.getMenu().getTotalMenu()) {
                                if (foodString.getFoodName().equalsIgnoreCase(food.getText())) {
                                    order.addToOrder(foodString);
                                    food.setText("");
                                    try {
                                        submitButton.setText("Added!");
                                        TimeUnit.SECONDS.sleep(2);
                                        submitButton.setText("Submit Food");
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });
                    });

                    tableButtons.add(a, column, row);
                    column++;
                    if(column > 4) {column = 0; row++;};
                }

                TextField t = new TextField("Enter Table Number");
                Button submit = generateButton("Submit");
                rootNodeForMenu.addRow(2, tableButtons);
            });
        });

        stage.setScene(mainMenu);
    }

    ;

    public void displaySwitch(GridPane rootNodeForMenu, Text data, Text response) {
        rootNodeForMenu.getChildren().removeIf(node -> GridPane.getRowIndex(node) > 1);

        response.setText("");
        TextField f = new TextField("Restaurant ID");
        Button b = generateButton("Submit");
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
            data.setText("Yum Restaurants | Restaurant " + r.getRestaurantId());
            System.out.println(r.getListOfReservations().size());
            data.setStyle("-fx-font: 36 Helvetica;");
            rootNodeForMenu.getChildren().remove(0);
            rootNodeForMenu.addRow(1, data);
            rootNodeForMenu.getChildren().remove(rootNodeForMenu.getChildren().size() - 1);

            rootNodeForMenu.getChildren().add(0, data);

        });
    }
}
