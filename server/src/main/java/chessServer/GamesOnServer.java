package chessServer;

import Chess.GameState;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class GamesOnServer {

    final private static HashMap<UUID, GameState> games = new HashMap<>();

    public static void addGame(GameState game, UUID id) {
        games.put(id, game);
    }

    public static UUID getGameId(GameState game) {
        Collection<UUID> collection = games.keySet();
        UUID desiredKey = null;
        for (UUID key : collection) {
            GameState someGame = games.get(key);
            if (someGame.equals(game)) {
                desiredKey = key;
            }
        }
        return desiredKey;
    }

    public static GameState parseIt (String gameStateString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            GameState gameState = objectMapper.readValue(gameStateString, GameState.class);
//

            System.out.println("ID: " + GamesOnServer.getGameId(gameState));
            System.out.println("id from json: "
                    + gameState.getId()
                    + " Side: " + gameState.getSide()
                    + " Type: " + gameState.getType()
                    + " PieceOnBoard: " + gameState.getPiecesOnBoardSer());
            return gameState;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


