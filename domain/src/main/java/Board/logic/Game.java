package Board.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Board.logic.Piece.Color.BLACK;
import static Board.logic.Piece.Color.WHITE;

public class Game {

    private static Game instance;

    private GameState gameState;

    public Game(GameState gameState) {
        this.setGameState(gameState);  // при чтении состояния он у нас только вызывается
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
        final GetStateSupplier getSupplier = new GetStateSupplier();
        instance.registerSupplier(saveSupplier);
        instance.registerSupplier(sendSupplier);
        instance.registerSupplier(getSupplier);

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

    public void setGameState(GameState gameState) { // здесь мы сообщаем всем supplier-ам -> setgamestate
        this.gameState = gameState;
        suppliers.forEach(Supplier::handle);
    }

    public void handleChanges() { // произошли изменения, нам нужно эти изменения привсти к Gamestate
        GameState game = new GameState(getInstance().getId(), getInstance().getType(),  // нужно вынести, к 3D не должно относиться никак
                getInstance().getSide(), // на каждый move у нас будет создаваться новый gameState, этот новый gameState у нас будет сохраняться в файл, отправляться в URL...
                getInstance().getPiecesOnBoard(), // после того как мы сюда его запихнули, мы всем supplier-ам должны об этом сообщить -> строка 109 этого класса
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

    private static class SendStateSupplier implements Supplier {

        @Override
        public void handle() {
            final GameState game = Game.getInstance().getGameState();
            try {
                final HttpClient client = HttpClientBuilder.create().build();
                final String gameState = URLEncoder.encode(game.toJson(), StandardCharsets.UTF_8.toString());
                final HttpPost post = new HttpPost("http://localhost:8080/?gamestate=" + gameState);
                final HttpResponse response = client.execute(post); // response никто на аналиирует, единственное что
//                String id = response.getFirstHeader("GameId").getValue();
//                System.out.println("GameId: " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class GetStateSupplier implements Supplier {

        @Override
        public void handle() {
            final GameState game = Game.getInstance().getGameState();
            try {
                final HttpClient client = HttpClientBuilder.create().build();
                final String gameState = URLEncoder.encode(game.toJson(), StandardCharsets.UTF_8.toString());
                final HttpGet get = new HttpGet("http://localhost:8080/?gamestate=" + gameState);
                final HttpResponse response = client.execute(get); // response никто на аналиирует, единственное что
                BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
                String line = rd.readLine();
                line = line.replace("\"", "");
                UUID id = UUID.fromString(line);
                Game.getInstance().setId(id);
                System.out.println("ID: " + Game.getInstance().getId().toString());

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
