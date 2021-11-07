package Chess;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;
import java.util.UUID;


import static Chess.Piece.Color.BLACK;
import static Chess.Piece.Color.WHITE;


public class Game {

    private static Game instance;

    private GameState gameState;

    public Game(GameState gameState) {
        this.setGameState(gameState);
        type = gameState.type;
        side = gameState.side;

    }

    public TypeOfGame type;
    public Coordinates coordinatesOfSelectedPiece = null;
    private Piece.Color side = WHITE;
    private PiecesOnBoard piecesOnBoard = null;
    private PiecesBeyondBoard piecesBeyondBoard = null;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private UUID id = null;

    public Game (TypeOfGame type) {
        this.type = type;
    }

    public TypeOfGame getType() {
        return this.type;
    }


    public void setCoordinatesOfSelectedPiece(Coordinates coordinates) {
        this.coordinatesOfSelectedPiece = coordinates;
    }

    public void setGameState(GameState gameState) { // здесь мы сообщаем всем supplier-ам -> setgamestate
        this.gameState = gameState;
    }



    public enum TypeOfGame {
        SINGLEBOARD, // only 3D Board - one 3D env, one Stage, one Scene
        DOUBLEBOARD, // 3D + 2D Boards - two env 3D+2D, 1 Stage, 2 Scenes
        SWEDISH, // pair of 3D + 2D Boards - pair of two env 3D+2D, 2 Stages, 2 Scenes in each
        FLAT // only 2D board
    }
}
