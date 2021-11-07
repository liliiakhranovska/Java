package Board.logic.logicpieces;

import Board.logic.*;

import java.util.ArrayList;
import java.util.List;

import static Board.logic.Piece.Color.BLACK;
import static Board.logic.Piece.PieceName.PAWN;

public class BlackPawnImpl extends AbstractPiece {

    public BlackPawnImpl(final Environment environment) {
        super(BLACK, PAWN, environment);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard) {
        final ArrayList<CoordinatesImmutable> posMoves = new ArrayList<>();
        final Piece[][] pieces = piecesOnBoard.getPieces();
        for (final Coordinates coordinate : Directions.south(_x, _y)) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            if (pieces[x][y] == null) {
                if (_y == 6 && pieces[x][y - 1] == null) {
                    posMoves.add(coordinate.stepSouth());
                }
                posMoves.add(coordinate);
            }
            break;
        }
        extracted(piecesOnBoard, posMoves, pieces, Directions.southWest(_x, _y));
        extracted(piecesOnBoard, posMoves, pieces, Directions.southEast(_x, _y));
        return posMoves;
    }

    private void extracted(
            final PiecesOnBoard piecesOnBoard,
            final List<CoordinatesImmutable> posMoves, //output
            final Piece[][] pieces,
            final List<Coordinates> coordinates
    ) {
        for (final Coordinates coordinate : coordinates) {
            final int x = coordinate.getX();
            final int y = coordinate.getY();
            final Color color = piecesOnBoard.getColorOfPiece(x, y);
            if (pieces[x][y] != null) {
                if (!color.equals(BLACK)) {
                    posMoves.add(coordinate);
                }
            }
            break;
        }
    }
}


