package Board.logic.logicpieces;

import javafx.scene.canvas.Canvas;
import javafx.scene.shape.MeshView;

public interface PhysicalPiece {
    void placeIt(int x, int y);
    void removeIt(int x, int y);
    Canvas getSelection();
    boolean isMeshViewBlocked (MeshView piece);
}
