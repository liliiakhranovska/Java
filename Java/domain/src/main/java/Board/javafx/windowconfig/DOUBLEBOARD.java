package Board.javafx.windowconfig;

import Board.javafx.*;
import Board.javafx.Impl3D.Environment3DImpl;
import Board.logic.Environment;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class DOUBLEBOARD {
    private static final float WIDTH = 1000;
    private static final float HEIGHT = 700;

    private static double anchorX, anchorY;
    private static double anchorAngleX = 0;
    private static double anchorAngleZ = 0;
    private static final DoubleProperty angleX = new SimpleDoubleProperty(-30);
    private static final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private static final DoubleProperty angleZ = new SimpleDoubleProperty(15);

    static Group gameElements = new Group();
//    static TilePane tilePane = new TilePane();


    public static Environment create(Stage stage) throws FileNotFoundException {
        System.out.println("DOUBLEBOARD");

        Environment3DImpl env = new Environment3DImpl(stage);
        Board.javafx.Impl2D.Environment2DImpl flatChess = new Board.javafx.Impl2D.Environment2DImpl(stage);
        Buttons buttons = new Buttons();
        Moves moves = new Moves();

        final Camera camera = new PerspectiveCamera();

        gameElements.getChildren().add(env);
        gameElements.getChildren().add(buttons);
        gameElements.getChildren().add(flatChess);
        gameElements.getChildren().add(moves);

//        tilePane.getChildren().add(env);
//        tilePane.getChildren().add(buttons);
//        tilePane.getChildren().add(flatChess);
//        tilePane.getChildren().add(moves);


        final Scene scene = new Scene(gameElements, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
        env.translateXProperty().set(0);
        env.translateYProperty().set(100);
        env.translateZProperty().set(4500);

        buttons.translateXProperty().set(-1000);
        buttons.translateYProperty().set(1150);
        buttons.translateZProperty().set(3500);

        flatChess.translateXProperty().set(2000);
        flatChess.translateYProperty().set(-600);
        flatChess.translateZProperty().set(3500);

        moves.translateXProperty().set(1100);
        moves.translateYProperty().set(450);
        moves.translateZProperty().set(3500);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

        initMouseControl(env, scene, stage);

        return env;
    }

    private static void initMouseControl(Group env, Scene scene, Stage stage) {
        final Rotate xRotate;
        final Rotate yRotate;
        final Rotate zRotate;
        env.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS),
                zRotate = new Rotate(0, Rotate.Z_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        zRotate.angleProperty().bind(angleZ);


        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleZ = angleZ.get();

        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleZ.set(anchorAngleZ + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            env.translateZProperty().set(env.getTranslateZ() + delta);
        });
    }

}
