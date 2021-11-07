package Board.javafx.windowconfig;

import Board.javafx.Impl3D.Environment3DImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class SINGLEBOARD {
    private static final float WIDTH = 700;
    private static final float HEIGHT = 700;

    private static double anchorX, anchorY;
    private static double anchorAngleX = 0;
    private static double anchorAngleZ = 0;
    private static final DoubleProperty angleX = new SimpleDoubleProperty(-30);
    private static final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private static final DoubleProperty angleZ = new SimpleDoubleProperty(15);

    public static Environment3DImpl create(Stage stage) {
        System.out.println("SINGLEBOARD");

        Environment3DImpl env = new Environment3DImpl(stage);
        final Camera camera = new PerspectiveCamera();
        final Scene scene = new Scene(env, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
        env.translateXProperty().set(WIDTH / 2 + 150);
        env.translateYProperty().set(HEIGHT / 2);
        env.translateZProperty().set(3500);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

        initMouseControl(env, scene, stage);



        return env;
    }

    private static void initMouseControl(Environment3DImpl env, Scene scene, Stage stage) {
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
