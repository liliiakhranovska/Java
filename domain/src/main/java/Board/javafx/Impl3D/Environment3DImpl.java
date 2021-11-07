package Board.javafx.Impl3D;

import Board.logic.*;
import Board.logic.logicpieces.AbstractPiece;
import Board.logic.logicpieces.PhysicalPiece;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.Group;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Environment3DImpl extends Group implements Environment {

    private final PiecesOnBoard piecesOnBoard;
    private final PiecesBeyondBoard piecesBeyondBoard;

    private ChessBoard3DImpl chessBoard;


    public Environment3DImpl(Stage stage) {

        this.piecesOnBoard = new PiecesOnBoard3DImpl(this);
        Game.getInstance().setPiecesOnBoard(this.piecesOnBoard);
        this.piecesBeyondBoard = new PiecesBeyondBoardImpl(this);
        Game.getInstance().setPiecesBeyondBoard(this.piecesBeyondBoard);
        this.chessBoard = new ChessBoard3DImpl();
        chessBoard.insertCellsToMatrix();
        chessBoard.addToGroup();
        chessBoard.setCellsToBoard();
        chessBoard.addBoardFrame();
        this.getChildren().add(chessBoard);

        piecesOnBoard.insertPiecesToDefaultBoardMatrix(); // logic pieces, not 3D/2D implementation
        piecesOnBoard.putPiecesToDefaultBoard(); // physical piece

//        piecesBeyondBoard.insertPiecesToDefaultBoardMatrix();
//        piecesBeyondBoard.putPiecesToDefaultBoard();

        this.addGameBoardPieces(piecesOnBoard);
        Game.getInstance().setPiecesOnBoard(this.piecesOnBoard);

        stage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
        });
    }

    public void addGameBoardPieces(PiecesOnBoard p) {
        Piece[][] pieces = p.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                if (pieces[i][k] != null && pieces[i][k].getImpl(this) instanceof Piece3DImpl) {
                    this.getChildren().add((Piece3DImpl) pieces[i][k].getImpl(this));
                }
            }
        }
    }

    @Override
    public PiecesOnBoard getPiecesOnBoard() {
        return this.piecesOnBoard;
    }

    @Override
    public PhysicalPiece createPiece(Piece.PieceName pieceName, Piece.Color pieceColor, AbstractPiece logicPiece) {
        return new Piece3DImpl(pieceName, pieceColor, this.chessBoard, this, logicPiece);
    }


}
