package Board.javafx.Impl2D;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell2DImpl extends Group {

    public int size = 100;


    public Cell2DImpl(Color color) {
        final Rectangle cell = new Rectangle(this.size, this.size);
        cell.setFill(color);
        this.getChildren().addAll(cell);
    }


    public void setBox(int stepX, int stepY) {
        this.translateXProperty().set(stepX - 800);
        this.translateYProperty().set(-stepY + 600);
    }


}
