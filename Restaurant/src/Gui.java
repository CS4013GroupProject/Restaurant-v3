import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Text text = new Text("Welcome to Yum Restaurants :)");
        text.setFont(new Font(35));
        text.setX(200);
        text.setY(250);


        Group root = new Group(text);

        Text text2 = new Text();
        text2.setText("By Sam Nick Brendan Mark ");
        text2.setX(50);
        text2.setY(100);
        text2.setFont(new Font (20));
        Group root2 = new Group(text2);
        Group rootGroup = new Group();
        rootGroup.getChildren().add(root);
        rootGroup.getChildren().add(root2);
        Scene scene = new Scene(rootGroup, 1000,500);
        scene.setFill(Color.rgb(50,100,0));

        primaryStage.setTitle("Yum Restaurants");
        primaryStage.setScene(scene);
        primaryStage.show();





    }
}
