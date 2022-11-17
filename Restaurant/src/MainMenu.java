import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text a = new Text(" \t \t\t   Yum Restaurants\n" +
                "             Created by Sam Nick Mark Brendan");
        a.setStyle("-fx-font: 42 Helvetica;");
        a.setY(0110);
        a.setX(110);
        Button b = new Button("Create Restaurant");


        Pane titlePane = new Pane(a);
        StackPane buttonPane = new StackPane(b);
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(titlePane);
        rootPane.getChildren().add(buttonPane);
        titlePane.setLayoutX(10);
        titlePane.setLayoutY(10);

    Scene titleScene = new Scene(rootPane, 1000, 500);
        titleScene.setFill(Color.web("#cc2b5e"));

    primaryStage.setScene(titleScene);
    primaryStage.show();
    }
}
