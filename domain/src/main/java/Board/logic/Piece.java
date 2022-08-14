package Board.logic;

import Board.logic.logicpieces.*;

import java.util.List;

public interface Piece {
    PhysicalPiece getImpl(Environment environment);

    void placeIt(int x, int y);

    int get_x();
    int get_y();

    PieceName getNameOfPiece();
    Color getColorOfPiece();


    enum Color {
        WHITE,
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
