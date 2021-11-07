package Board.logic;

import Board.logic.logicpieces.*;
import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

import java.util.List;

public interface Piece {
    PhysicalPiece getImpl(Environment environment);

    void placeIt(int x, int y);

    int get_x();
    int get_y();

    PieceName getNameOfPiece();
    Color getColorOfPiece();

    enum Color {
//        @Value
        WHITE,
//        @Value
        BLACK
    }

    enum PieceName {
        QUEEN,
        KING,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    List<CoordinatesImmutable> getPossibleMoves(PiecesOnBoard piecesOnBoard);
    List<CoordinatesImmutable> getPossibleAttacks(PiecesOnBoard piecesOnBoard);

}
