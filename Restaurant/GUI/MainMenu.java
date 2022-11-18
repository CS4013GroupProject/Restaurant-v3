import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.io.FileNotFoundException;

public class MainMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public Restaurant r;
    private Stage stage;
    private Manager manager;
    private int xHeight = 1440;
    private int yHeight = 900;

    public void start(Stage primaryStage) {
        manager = new Manager();
        //------------MAIN MENU---------------//
        Button bToCreateRestaurant = new Button("Create Restaurant");
        bToCreateRestaurant.setScaleX(2);
        bToCreateRestaurant.setScaleY(2);
        StackPane buttonPane = new StackPane(bToCreateRestaurant);
        buttonPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

        primaryStage.setResizable(false);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(400);

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

    public void displayCreationPage() {
        Text textCreateRestaurant = new Text("First things first.\n" +
                "Create A Restaurant\n");

        textCreateRestaurant.setStyle("-fx-font: 42 Helvetica;");
        textCreateRestaurant.setY(110);
        textCreateRestaurant.setX(110);

        StackPane createTest = new StackPane(textCreateRestaurant);
        createTest.setAlignment(Pos.TOP_LEFT);

        javafx.scene.control.TextField restaurantID = new javafx.scene.control.TextField("1");
        javafx.scene.control.TextField capacity = new javafx.scene.control.TextField("2");
        javafx.scene.control.TextField noOfTables = new TextField("3");
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
                        r = new Restaurant(manager, Integer.parseInt(restaurantID.getText()), Integer.parseInt(capacity.getText()), Integer.parseInt(noOfTables.getText()));
                        manager.addRestaurant(r);
                        displayMainMenu(buttonSubmit);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );
    }

    public Button quitButton() {
        Button quit = new Button("Quit");
        quit.setOnAction(e -> {
            stage.close();
        });
        return quit;
    }

    public Button backButton() {
        Button back = new Button("Back");
        back.setOnAction(e -> {
            Button bToCreateRestaurant = new Button("Create Restaurant");
            bToCreateRestaurant.setScaleX(2);
            bToCreateRestaurant.setScaleY(2);
            displayMainMenu(bToCreateRestaurant);
        });
        return back;
    }

    public void displayMainMenu(Button buttonSubmit) {
        Text title = new Text("Restaurant " + r.getRestaurantId() + " Settings:");
        Text data = new Text("");
        title.setStyle("-fx-font: 36 Helvetica;");
        Button bForCustomer = new Button("Customer ");
        Button bForWaiter = new Button("Waiter");
        Button bForChef = new Button("Chef");
        Button bForAdmin = new Button("Administration");

        HBox originalButtons = new HBox(bForCustomer, bForWaiter, bForChef, bForAdmin, quitButton());
        GridPane rootNodeForMenu = new GridPane();
        rootNodeForMenu.addRow(1,title);
        rootNodeForMenu.addColumn(3, originalButtons);
        rootNodeForMenu.setVgap(10);
        Scene mainMenu = new Scene(rootNodeForMenu, xHeight, yHeight);
        Text response = new Text("");
        data.setStyle("-fx-font: 26 Helvetica;");
        response.setStyle("-fx-font: 16 Helvetica;");
        rootNodeForMenu.addRow(9, data);
        rootNodeForMenu.addRow(10, response);

        bForChef.setOnAction(e -> {
            // "V)iew current orders, U)pdate status, S)ee completed orders, Q)uit
            Button view = new Button("View Current Orders");
            Button update = new Button("Update Status");
            Button seeComplete = new Button("See Completed Orders");

            HBox chefButtons = new HBox(view, update, seeComplete, backButton());
            rootNodeForMenu.getChildren().remove(originalButtons);
            rootNodeForMenu.addColumn(3, chefButtons);
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

            HBox adminButtons = new HBox(getID, getNumberOfTables, getCapacity, viewMenu, createNewRestaurant, switchRestaurant, viewReservations, addToMenu, backButton());
            rootNodeForMenu.getChildren().remove(originalButtons);
            rootNodeForMenu.addColumn(3, adminButtons);

            getID.setOnAction(d -> {
                data.setText("Restaurant ID:");
                response.setText(String.valueOf(r.getRestaurantId()));
            });

            getCapacity.setOnAction(d -> {
                data.setText("Restaurant Capacity:");
                response.setText(String.valueOf(r.getCapacity()));
            });

            getNumberOfTables.setOnAction(d -> {
                data.setText("Number of Tables:");
                response.setText(String.valueOf(r.getNumberOfTables()));
            });

            createNewRestaurant.setOnAction(d -> {
                displayCreationPage();
            });

            viewMenu.setOnAction(d -> {
                String menu = r.getMenu().toString();
                data.setText("Restaurant Menu:");
                response.setText(menu);
            });
        });

        stage.setScene(mainMenu);
    }




}
