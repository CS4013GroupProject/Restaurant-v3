import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Mainmenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text a = new Text(" Yum Restaurants\n" +
                "               Created by Sam Nick Mark Brendan");
        Pane titlePane = new Pane(a);
        titlePane.setLayoutY(300);
    Scene titleScene = new Scene(titlePane, 1000, 500);
    primaryStage.setScene(titleScene);
    primaryStage.show();
    }
}
