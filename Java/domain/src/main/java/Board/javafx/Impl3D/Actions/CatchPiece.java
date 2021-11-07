package Board.javafx.Impl3D.Actions;

import Board.javafx.MyHandler;
import Board.javafx.Impl3D.Piece3DImpl;
import Board.logic.Coordinates;
import Board.logic.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class CatchPiece implements MyHandler<MouseEvent> {

    private final Piece3DImpl piece3D;
    Canvas selection;

    public CatchPiece(Piece3DImpl piece, Canvas selection) {
        this.piece3D = piece;
        this.selection = selection;
    }

    @Override
    public boolean handle(MouseEvent event) {
        Game.getInstance().setCoordinatesOfSelectedPiece(new Coordinates(piece3D.get_x(), piece3D.get_y()));
        final var r = new HighlightPiece(piece3D.logicPiece, selection);
        r.handle(event);
        return true;
    }
}
