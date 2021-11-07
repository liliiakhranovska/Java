package Board.javafx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Buttons extends Group {
    int width = 1800;
    int height = 400;


    public Buttons() {
        Rectangle buttons = new Rectangle(width, height);
        buttons.setFill(Color.WHITE);
        this.getChildren().add(buttons);
    }

}
