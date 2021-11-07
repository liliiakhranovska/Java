package Recall;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main2 extends Application {

    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        HBox hbox = new HBox();
//        hbox.setSpacing(10);
//        hbox.setPadding(new Insets(10, 10, 10, 10));
//        Button btn1 = new Button("1");
//        Button btn2 = new Button("2");
//        Button btn3 = new Button("3");
//        Button btn4 = new Button("4");
//        hbox.getChildren().addAll(btn1, btn2, btn3, btn4);

        TilePane tilePane = new TilePane();
        tilePane.setOrientation(Orientation.HORIZONTAL);
        tilePane.setVgap(10);
        tilePane.setHgap(10);
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        tilePane.getChildren().addAll(btn1, btn2, btn3, btn4);

//        Sphere sphere = new Sphere(50);
//        Group group = new Group();
//        group.getChildren().add(sphere);



        Scene scene = new Scene(tilePane, WIDTH, HEIGHT);

//        sphere.translateXProperty().set(WIDTH/2);
//        sphere.translateYProperty().set(HEIGHT/2);

        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main (String... args) {
        launch(args);
    }
}
