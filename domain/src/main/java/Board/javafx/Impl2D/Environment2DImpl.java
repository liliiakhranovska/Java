package Board.javafx.Impl2D;

import Board.logic.*;
import Board.logic.logicpieces.AbstractPiece;
import Board.logic.logicpieces.PhysicalPiece;
import javafx.scene.Group;
import javafx.stage.Stage;

// эту штуку надо переписать, чтобы она относилась именно к ChessBoard, а не к Environment-у
public class Environment2DImpl extends Group implements Environment {

    private final PiecesOnBoard pieces2DOnBoard; // в env, а не в ChessBoard -> надо переместить
//    private final PiecesBeyondBoard piecesBeyondBoard;
    private ChessBoard2DImpl chessBoard;

    private void init() {
        ChessBoard2DImpl chessBoard = new ChessBoard2DImpl();

        chessBoard.insertCellsToMatrix();
        chessBoard.addToGroup();
        chessBoard.setCellsToBoard();
//        chessBoard.addBoardFrame();
        this.getChildren().add(chessBoard);

        pieces2DOnBoard.insertPiecesToDefaultBoardMatrix();
        pieces2DOnBoard.putPiecesToDefaultBoard();
//
//        piecesBeyondBoard.insertPiecesToDefaultBoardMatrix();
//        piecesBeyondBoard.putPiecesToDefaultBoard();
//
        this.addGameBoardPieces(pieces2DOnBoard);
    }

    public Environment2DImpl(Stage stage) {
        this.pieces2DOnBoard = new PiecesOnBoard2DImpl(this);
        this.init();
        Game.getInstance().registerSupplier(() -> { // перенесли сюда с ChessBoard2DImpl, так как функция которая расставляет фигуры на доске - addGameBoardPieces - находится здесь
            Piece[][] pieces = pieces2DOnBoard.getPieces();
            for (int i = 0; i < 8; i++) {
                for (int k = 0; k < 8; k++) {
                    if (pieces[i][k] != null && pieces[i][k].getImpl(this) instanceof Piece2DImpl) {
                        this.getChildren().remove((Piece2DImpl) pieces[i][k].getImpl(this));
                    }
                }
            }
            pieces2DOnBoard.insertPiecesToDefaultBoardMatrix();
            pieces2DOnBoard.putPiecesToDefaultBoard();
            this.addGameBoardPieces(pieces2DOnBoard);
        });
    }

    public void addGameBoardPieces(PiecesOnBoard p) { // не имеет никакого отношения к ChessBoard -> выглядит неправильно -> надо переместить в ChessBoard
        Piece[][] pieces = p.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                if (pieces[i][k] != null && pieces[i][k].getImpl(this) instanceof Piece2DImpl) {
                    this.getChildren().add((Piece2DImpl) pieces[i][k].getImpl(this));
                }
            }
        }
    }

    @Override
    public PiecesOnBoard getPiecesOnBoard() {
        return Game.getInstance().getPiecesOnBoard();
    }

    @Override
    public PhysicalPiece createPiece(Piece.PieceName pieceName, Piece.Color pieceColor, AbstractPiece logicPiece) {
        return new Piece2DImpl(pieceName, pieceColor, this.chessBoard, this, logicPiece);
    }
}
