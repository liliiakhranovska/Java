package Board.logic;

public interface PiecesBeyondBoard {
    void insertPiecesToBeyondBoardMatrix(Piece piece);
    Piece[][] getPieces();
    int getQuantityWhiteTakenPieces();
    int getQuantityBlackTakenPieces();

    Piece getPiece(int i, int j);

    void insertPiecesToDefaultBoardMatrix();
    void putPiecesToDefaultBoard();

}
