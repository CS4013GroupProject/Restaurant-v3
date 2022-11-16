import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Text text = new Text("Welcome to Yum Restaurants :)");
        text.setFont(new Font(35));
        text.setX(270);
        text.setY(100);


        Group root = new Group(text);


        Text text2 = new Text();
        text2.setText("By Sam Nick Brendan Mark ");
        text2.setX(50);
        text2.setY(100);
        text2.setFont(new Font (20));
        Button toMenu = new Button("Create Restaurant");
        StackPane pane = new StackPane();
        pane.getChildren().add(toMenu);

        Group buttonGroup = new Group(pane);
        buttonGroup.setLayoutX(100);
        buttonGroup.setLayoutY(200);
        Group root2 = new Group(text2);
        Group rootGroup = new Group();
        rootGroup.getChildren().add(root);
        rootGroup.getChildren().add(root2);
        rootGroup.getChildren().add(buttonGroup);
        Scene scene = new Scene(rootGroup, 1000,500);
        scene.setFill(Color.rgb(50,100,0));

        primaryStage.setTitle("Yum Restaurants");
        primaryStage.setScene(scene);
        primaryStage.show();





    }
}
