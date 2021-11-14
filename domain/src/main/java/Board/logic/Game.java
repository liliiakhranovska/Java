package Board.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Board.logic.Piece.Color.BLACK;
import static Board.logic.Piece.Color.WHITE;

public class Game {

    private static Game instance;

    private GameState gameState;

    public Game(GameState gameState) {
        this.setGameState(gameState);
        type = gameState.type;
        side = gameState.side;

    }

    public synchronized static Game getInstance() {
        if (instance == null) {
            instance = new Game(TypeOfGame.DOUBLEBOARD);
        }
        return instance;
    }

    public TypeOfGame type; // 2
    //    List<GameBoard> boards;   // logical
    public Coordinates coordinatesOfSelectedPiece = null; //1
    private Piece.Color side = WHITE; //1
    private PiecesOnBoard piecesOnBoard = null; //1
    private PiecesBeyondBoard piecesBeyondBoard = null; //1

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

    public static void createInstance() {

        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            final InputStream is = new FileInputStream("./game.json");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            final String json = reader.readLine();
            final GameState game = objectMapper.readValue(json, GameState.class);
            instance = new Game(game);
            System.out.println(game.getSide());


        } catch (Exception e) {
            instance = new Game(TypeOfGame.DOUBLEBOARD);
        }

        final SaveStateSupplier saveSupplier = new SaveStateSupplier();
        final SendStateSupplier sendSupplier = new SendStateSupplier();
        instance.registerSupplier(saveSupplier);
        instance.registerSupplier(sendSupplier);
    }

    public TypeOfGame getType() {
        return this.type;
    }


    public void setCoordinatesOfSelectedPiece(Coordinates coordinates) {
        this.coordinatesOfSelectedPiece = coordinates;
    }


    public Coordinates getCoordinatesOfSelectedPiece() {
        return this.coordinatesOfSelectedPiece;
    }

    public Piece.Color getSide() {
        return this.side;
    }

    public void switchSide() {
        side = side.equals(BLACK) ? WHITE : BLACK;
    }

    public PiecesOnBoard getPiecesOnBoard() {
        return this.piecesOnBoard;
    }

    public PiecesBeyondBoard getPieceBeyondBoard() {
        return this.piecesBeyondBoard;
    }

    public void setPiecesOnBoard(PiecesOnBoard piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }

    public void setPiecesBeyondBoard(PiecesBeyondBoard piecesBeyondBoard) {
        this.piecesBeyondBoard = piecesBeyondBoard;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        suppliers.forEach(Supplier::handle);
    }

    public void handleChanges() {
        GameState game = new GameState(getInstance().getId(), getInstance().getType(),
                getInstance().getSide(),
                getInstance().getPiecesOnBoard(),
                getInstance().getPieceBeyondBoard()
        );

        this.setGameState(game);

    }

    @FunctionalInterface
    public interface Supplier {
        void handle();
    }

    private static class SaveStateSupplier implements Supplier {

        @Override
        public void handle() {
            final GameState game = Game.getInstance().getGameState();
            // Save file
            try {
                final String resultJson = new ObjectMapper().writeValueAsString(game);
                try (final FileWriter writer = new FileWriter("./game.json", false)) {
                    writer.write(resultJson);
                    writer.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static class SendStateSupplier implements Supplier {

        @Override
        public void handle() {
            final GameState game = Game.getInstance().getGameState();
            try {
                HttpRequestFactory requestFactory =
                        HTTP_TRANSPORT.createRequestFactory(
                                request -> request.setParser(new JsonObjectParser(JSON_FACTORY))
                        );
                GenericUrl url = new GenericUrl ("http://localhost:8081/hello/game");
                HttpRequest request = requestFactory.buildPostRequest(url, new JsonHttpContent(JSON_FACTORY, game));
                String uid = request.execute().parseAs(String.class);
                game.setId(UUID.fromString(uid));
                System.out.println(uid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Supplier> suppliers = new ArrayList<>();

    public void registerSupplier(Supplier o) {
        suppliers.add(o);
    }


    public enum TypeOfGame {
        SINGLEBOARD, // only 3D Board - one 3D env, one Stage, one Scene
        DOUBLEBOARD, // 3D + 2D Boards - two env 3D+2D, 1 Stage, 2 Scenes
        SWEDISH, // pair of 3D + 2D Boards - pair of two env 3D+2D, 2 Stages, 2 Scenes in each
        FLAT // only 2D board
    }
}
