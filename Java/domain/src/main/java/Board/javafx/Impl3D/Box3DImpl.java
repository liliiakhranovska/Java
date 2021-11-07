package Board.javafx.Impl3D;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Box3DImpl extends Group {

    int size = 200;


    public Box3DImpl(String resName) {
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(ChessBoard3DImpl.class.getClassLoader().getResourceAsStream(resName)));
        final Box box = new Box(this.size, this.size, this.size);
        box.setMaterial(material);
        this.getChildren().addAll(box);
    }


    public void setBox(int stepX, int stepY) {
        this.translateXProperty().set(stepX - 800);
        this.translateYProperty().set(-stepY + 600);
    }


}
