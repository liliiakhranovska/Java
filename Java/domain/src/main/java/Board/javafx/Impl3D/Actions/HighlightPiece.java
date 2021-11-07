package Board.javafx.Impl3D.Actions;

import Board.logic.Coordinates;
import Board.logic.CoordinatesImmutable;
import Board.logic.Game;
import Board.logic.Piece;
import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class HighlightPiece implements javafx.event.EventHandler<MouseEvent> {

    Canvas selection;
    Piece piece;

    public HighlightPiece(AbstractPiece logicPiece, Canvas selection) {
        this.piece = logicPiece;
        this.selection = selection;
    }

    @Override
    public void handle(MouseEvent event) {
        selection.setOpacity(1);
//        Game.getInstance().setCoordinatesOfSelectedPiece(new Coordinates(piece.get_x(), piece.get_y()));
//        for (CoordinatesImmutable c: Game.getInstance().getPossibleMovesOfSelectedPiece()) {
//            System.out.println("possibleMoveOfSelectedPiece: " + c.getX() + " " + c.getY());
//        }

    }
}
