package Recall;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        primaryStage.setTitle("first");


        Stage secondStage = new Stage();
        primaryStage.setWidth(200);
        primaryStage.setHeight(300);
        secondStage.setTitle("second");
        secondStage.show();

        Label helloWorldLabel = new Label("Hello world!");
        helloWorldLabel.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(helloWorldLabel);
        primaryStage.setScene(primaryScene);
        primaryStage.show();



//        Application.launch();
    }
}
