package Board.logic;

import java.util.List;

public interface PiecesOnBoard {
//    Environment getEnvironment3D();
    void insertPiecesToDefaultBoardMatrix();
    void putPiecesToDefaultBoard();
    Piece[][] getPieces();
    Piece.Color getColorOfPiece(int x, int y);

//    void switchSide();

//    Piece.Color getSide();

    Piece createPiece(Piece.Color color, Piece.PieceName name);

    Piece getPiece(int x, int y);

    void setPiece(int x, int y, Piece piece);

//    void setCoordinatesOfSelectedPiece(Coordinates coordinates);
//
//    Coordinates getCoordinatesOfSelectedPiece();

//    void setPossibleMovesOfSelectedPiece (List<CoordinatesImmutable> possibleMoves);

//    List<CoordinatesImmutable> getPossibleMovesOfSelectedPiece();
}
