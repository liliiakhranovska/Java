package Board.logic.logicpieces;

import Board.logic.*;

import java.util.ArrayList;
import java.util.List;

import static Board.logic.Piece.PieceName.ROOK;

public class RookImpl extends AbstractPiece {

    public RookImpl(final Environment environment, Color color) {
        super(color, ROOK, environment);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard) {
        final ArrayList<CoordinatesImmutable> posMoves = new ArrayList<>();
        final Piece[][] pieces = piecesOnBoard.getPieces();

        final List<List<Coordinates>> directions = List.of (
                Directions.west(_x, _y),
                Directions.east(_x, _y),
                Directions.north(_x, _y),
                Directions.south(_x, _y)
        );

        for (final var coordList : directions) {
            for (Coordinates coordinate : coordList) {
                final int x = coordinate.getX();
                final int y = coordinate.getY();
                final Color colorOtherPiece = piecesOnBoard.getColorOfPiece(x, y);
                final Color colorRook = piecesOnBoard.getColorOfPiece(_x, _y);
                if (pieces[x][y] != null) {
                    if (!colorRook.equals(colorOtherPiece)) {
                        posMoves.add(coordinate);
                    }
                    break;
                }
                if (pieces[x][y] == null) {
                    posMoves.add(coordinate);
                }
            }
        }
        return posMoves;
    }
}

