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

import java.io.FileNotFoundException;

public class MainMenu extends Application {
    Restaurant r;
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        //------------MAIN MENU---------------//
        Button bToCreateRestaurant = new Button("Create Restaurant");
        bToCreateRestaurant.setScaleX(2);
        bToCreateRestaurant.setScaleY(2);
        StackPane buttonPane = new StackPane(bToCreateRestaurant);
        buttonPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));


        Text titleText1 = new Text("Yum Restaurants\n")
                ;
        titleText1.setStyle("-fx-font: 42 Helvetica;");
        titleText1.setY(60);

        Text titleText2 = new Text("Created by Sam Nick Mark Brendan");
        titleText2.setStyle("-fx-font: 24 Helvetica;");
        titleText2.setY(100);

        Pane titlePane = new Pane(titleText1, titleText2);
        titlePane.setBackground(new Background(new BackgroundFill(Color.LAVENDER,null,null)));

        StackPane rootPane = new StackPane(titlePane,buttonPane);

        Scene titleScene = new Scene(rootPane, 800, 500);
        titleScene.setFill(Color.LAWNGREEN);
        System.out.println(buttonPane.getBackground());

        primaryStage.setScene(titleScene);


        primaryStage.show();
//---------------------------------------------------CREATE RESTAURANT PAGE-----------------------------//

        Text textCreateRestaurant = new Text("First things first.\n" +
                "Create A Restaurant\n");

        textCreateRestaurant.setStyle("-fx-font: 42 Helvetica;");
        textCreateRestaurant.setY(110);
        textCreateRestaurant.setX(110);

        StackPane createTest = new StackPane(textCreateRestaurant);
        createTest.setAlignment(Pos.TOP_LEFT);

        javafx.scene.control.TextField restaurantID = new javafx.scene.control.TextField("Restaurant ID");
        javafx.scene.control.TextField capacity = new javafx.scene.control.TextField("Capacity");
        javafx.scene.control.TextField noOfTables = new TextField("Number of tables");
        Button buttonSubmit = new Button("Submit");



        GridPane textFieldPane = new GridPane();
        textFieldPane.addRow(0, restaurantID, capacity, noOfTables);
        textFieldPane.addRow(1, buttonSubmit);
        textFieldPane.setAlignment(Pos.CENTER);





        GridPane rootNode = new GridPane();
        rootNode.addRow(0,createTest);
        rootNode.addRow(1,textFieldPane);
        rootNode.setAlignment(Pos.CENTER);
        Scene createRestaurantScene = new Scene(rootNode, 800, 500);
        primaryStage.show();
        bToCreateRestaurant.setOnAction(e -> {
                primaryStage.setScene(createRestaurantScene);
                primaryStage.show();
                }
        );
        //-----------MAIN MENU---------------//
            Text t1 = new Text("hey!");
        System.out.println(r.getRestaurantId());
            try {
                t1 = new Text("Menu for Restaurant: " + r.getRestaurantId() + "\nCustomer or W)aiter or Ch)ef or A)dministration.\nQ)uit");
            }catch(NullPointerException e){
                System.out.println("Whoops!");
            }
        Button bForCustomer = new Button("Customer");
        Button bForWaiter = new Button("Waiter");
        Button bForChef = new Button("Chef");
        Button bForAdmin = new Button("Administration");
        Button bQuit = new Button("Quit");

        HBox hbox = new HBox(bForCustomer, bForWaiter, bForChef, bForAdmin, bQuit);
        GridPane rootNodeForMenu = new GridPane();
        rootNodeForMenu.addRow(1,t1);
        rootNodeForMenu.addRow(3, hbox);
        Scene mainMenu = new Scene(rootNodeForMenu, 800, 500);


        buttonSubmit.setOnAction(e -> {
            int ide= Integer.parseInt(restaurantID.getText());

            int capa =Integer.parseInt(capacity.getText());
            int tabl = Integer.parseInt(noOfTables.getText());

            try {
                Restaurant restaurant = new Restaurant(ide, capa, tabl);
                r = restaurant;
                primaryStage.setScene(mainMenu);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }


}
