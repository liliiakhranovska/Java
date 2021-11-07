package Board.logic.logicpieces;

import Board.logic.*;

import java.util.ArrayList;
import java.util.List;

import static Board.logic.Piece.Color.WHITE;
import static Board.logic.Piece.PieceName.PAWN;

public class WhitePawnImpl extends AbstractPiece {

    public WhitePawnImpl(final Environment environment) {
        super(WHITE, PAWN, environment);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard) {
        final ArrayList<CoordinatesImmutable> posMoves = new ArrayList<>();
        final Piece[][] pieces = piecesOnBoard.getPieces();
        for (Coordinates coordinate : Directions.north(_x, _y)) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            if (pieces[x][y] == null) {
                if (_y == 1 && pieces[x][y + 1] == null) {
                    posMoves.add(coordinate.stepNorth());
                }
                posMoves.add(coordinate);
            }
            break;
        }
        for (final Coordinates coordinate : Directions.northWest(_x, _y)) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            final Color color = piecesOnBoard.getColorOfPiece(x, y);
            if (pieces[x][y] != null) {
                if (!color.equals(WHITE)) {
                    posMoves.add(coordinate);
                }
            } break;
        }
        for (Coordinates coordinate : Directions.northEast(_x, _y)) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            final Color color = piecesOnBoard.getColorOfPiece(x, y);
            if (pieces[x][y] != null) {
                if (!color.equals(WHITE)) {
                    posMoves.add(coordinate);
                }
            } break;
        }
        return posMoves;
    }
}
