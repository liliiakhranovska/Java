package Chess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class GameState {

    public Game.TypeOfGame type;
    public Piece.Color side;
    public PiecesOnBoardSer piecesOnBoardSer;
    public PiecesBeyondBoardSer piecesBeyondBoardSer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private UUID id = null;


    public GameState(Game.TypeOfGame type,
                     Piece.Color side,
                     PiecesOnBoard piecesOnBoard,
                     PiecesBeyondBoard piecesBeyondBoard) {
        this.type = type;
        this.side = side;
        this.piecesOnBoardSer = new PiecesOnBoardSer(piecesOnBoard);
        this.piecesBeyondBoardSer = new PiecesBeyondBoardSer(piecesBeyondBoard);
    }

    public GameState() {
    }

    public Game.TypeOfGame getType() {
        return type;
    }

    public Piece.Color getSide() {
        return side;
    }

    public PiecesOnBoardSer getPiecesOnBoardSer() {
        return piecesOnBoardSer;
    }

    public PiecesBeyondBoardSer getPiecesBeyondBoardSer() {
        return piecesBeyondBoardSer;
    }

    public void setType(Game.TypeOfGame type) {
        this.type = type;
    }

    public void setSide(Piece.Color side) {
        this.side = side;
    }

    public void setPiecesOnBoardSer(PiecesOnBoardSer piecesOnBoardSer) {
        this.piecesOnBoardSer = piecesOnBoardSer;
    }

    public void setPiecesBeyondBoardSer(PiecesBeyondBoardSer piecesBeyondBoardSer) {
        this.piecesBeyondBoardSer = piecesBeyondBoardSer;
    }


    public class PiecesOnBoardSer {
        public PieceSer[][] piecesSer = new PieceSer[8][8];

        PiecesOnBoardSer(PiecesOnBoard piecesOnBoard) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    PieceSer pieceSer = new PieceSer(piecesOnBoard.getPiece(i, j));
                    this.piecesSer[i][j] = pieceSer;
                }
            }
        }

        public PiecesOnBoardSer() {
        }
    }

    public class PiecesBeyondBoardSer {
        public PieceSer[][] piecesSer = new PieceSer[2][8];

        PiecesBeyondBoardSer(PiecesBeyondBoard piecesBeyondBoard) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    PieceSer pieceSer = new PieceSer(piecesBeyondBoard.getPiece(i, j));
                    this.piecesSer[i][j] = pieceSer;
                }
            }
        }

        public PiecesBeyondBoardSer() {
        }
    }

    public static class PieceSer {

        public Piece.PieceName name;
        public Piece.Color color;
        public int x;
        public int y;

        public PieceSer() {
        }

        PieceSer(Piece piece) {
            if (piece == null) {
                return;
            }
            this.name = piece.getNameOfPiece();
            this.color = piece.getColorOfPiece();
            this.x = piece.get_x();
            this.y = piece.get_y();
        }

    }
}
