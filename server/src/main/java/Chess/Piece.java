package Chess;

import java.util.List;

public interface Piece {

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

}
