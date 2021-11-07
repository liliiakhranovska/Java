package Board.javafx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Moves extends Group {
    int width = 1000;
    int height = 1100;

    public Moves() {
        Rectangle moves = new Rectangle(width, height);
        moves.setFill(Color.WHITE);
        this.getChildren().add(moves);
    }

}
