package Board.logic;

import Board.logic.logicpieces.AbstractPiece;
import Board.logic.logicpieces.PhysicalPiece;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.util.Collection;

public interface Environment {
    PiecesOnBoard getPiecesOnBoard();
    PhysicalPiece createPiece (Piece.PieceName pieceName, Piece.Color pieceColor, AbstractPiece logicPiece);
}
