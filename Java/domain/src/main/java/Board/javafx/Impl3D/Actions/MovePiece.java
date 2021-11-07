package Board.javafx.Impl3D.Actions;

import Board.logic.CoordinatesImmutable;
import Board.logic.Game;
import Board.logic.Piece;
import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MovePiece implements javafx.event.EventHandler<MouseEvent> {

    private final int x;
    private final int y;
    public String color;
    Canvas selection;
    AbstractPiece logicPiece;

    public MovePiece(AbstractPiece logicPiece, int x, int y, Canvas selection) {
        this.logicPiece = logicPiece;
        this.x = x;
        this.y = y;
        this.selection = selection;
    }

    private boolean canMove() {
        final List<CoordinatesImmutable> posMoves = logicPiece.getPossibleMoves(Game.getInstance().getPiecesOnBoard());
        for (CoordinatesImmutable coord : posMoves) {
            if (coord.getX() == this.x && coord.getY() == this.y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handle(MouseEvent event) {

        if (!canMove()) {
            System.out.print("Can't move");
            return;
        }

        int originX = logicPiece.get_x();
        int originY = logicPiece.get_y();
        int targetX = x;
        int targetY = y;
        Piece originPiece = Game.getInstance().getPiecesOnBoard().getPiece(originX, originY);
        Game.getInstance().getPiecesOnBoard().setPiece(targetX, targetY, originPiece);
        Game.getInstance().getPiecesOnBoard().setPiece(originX, originY, null);
        Game.getInstance().getPiecesOnBoard().getPiece(targetX, targetY).placeIt(targetX,targetY);
        Game.getInstance().switchSide();
        Game.getInstance().setCoordinatesOfSelectedPiece(null);
        final var r = new UnHighlightPiece(logicPiece, selection);
        r.handle(event);

        Game.getInstance().handleChanges(); // сообщим ему о том, что произошли изменения

    }
}
