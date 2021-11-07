package Chess;

import java.util.List;

public interface PiecesOnBoard {
    void insertPiecesToDefaultBoardMatrix();
    void putPiecesToDefaultBoard();
    Piece[][] getPieces();
    Piece.Color getColorOfPiece(int x, int y);


    Piece createPiece(Piece.Color color, Piece.PieceName name);

    Piece getPiece(int x, int y);

    void setPiece(int x, int y, Piece piece);
}
