package Board.javafx.Impl3D.Actions;

import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class UnHighlightPiece implements javafx.event.EventHandler<MouseEvent> {

    Canvas selection;
    AbstractPiece logicPiece;

    public UnHighlightPiece(AbstractPiece logicPiece, Canvas selection) {
        this.logicPiece = logicPiece;
        this.selection = selection;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("selection - " + selection);
        selection.setOpacity(0);
    }
}
