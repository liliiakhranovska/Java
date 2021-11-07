package Board.logic.logicpieces;

import Board.logic.*;

import java.util.ArrayList;
import java.util.List;

import static Board.logic.Piece.PieceName.KNIGHT;

public class KnightImpl extends AbstractPiece {

    public KnightImpl(final Environment environment, Color color) {
        super(color, KNIGHT, environment);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard) {
        final ArrayList<CoordinatesImmutable> posMoves = new ArrayList<>();
        final Piece[][] pieces = piecesOnBoard.getPieces();
        for (Coordinates coordinate : Directions.knightPosMoves(_x, _y)) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            final Color color = piecesOnBoard.getColorOfPiece(x, y);
            if (pieces[x][y] != null) {
                if (!color.equals(pieces[_x][_y].getColorOfPiece())) {
                    posMoves.add(coordinate);
                }
            } else {
                posMoves.add(coordinate);
            }
        }
        return posMoves;
    }
}

