package Board.logic.logicpieces;

import Board.logic.*;

import java.util.ArrayList;
import java.util.List;

import static Board.logic.Piece.PieceName.KING;

public class KingImpl extends AbstractPiece {

    public KingImpl(final Environment environment, Color color) {
        super(color, KING, environment);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard) {
        final ArrayList<CoordinatesImmutable> posMoves = new ArrayList<>();
        final Piece[][] pieces = piecesOnBoard.getPieces();

        final List<List<Coordinates>> directions = List.of(
                Directions.northWest(_x, _y),
                Directions.northEast(_x, _y),
                Directions.southWest(_x, _y),
                Directions.southEast(_x, _y),
                Directions.west(_x, _y),
                Directions.east(_x, _y),
                Directions.south(_x, _y),
                Directions.north(_x, _y));

        for (final var coordList : directions) {
            for (Coordinates coordinate : coordList) {
                final int x = coordinate.getX();
                final int y = coordinate.getY();
                final Color colorOtherPiece = piecesOnBoard.getColorOfPiece(x, y);
                final Color colorQueen = piecesOnBoard.getColorOfPiece(_x, _y);
                if (pieces[x][y] != null) {
                    if (!colorQueen.equals(colorOtherPiece)) {
                        posMoves.add(coordinate);
                    }
                    break;
                }
                if (pieces[x][y] == null) {
                    posMoves.add(coordinate);
                    break;
                }
            }
        }
        return posMoves;
    }
}