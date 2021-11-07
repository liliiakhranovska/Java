package chessServer;

import Chess.GameState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import static chessServer.GamesOnServer.parseIt;

@org.springframework.web.bind.annotation.RestController
public class MyController {
    public static class RestResponse {
        private String param1;

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse postMethod (String gamestate) {
        RestResponse result = new RestResponse();
        result.setParam1(gamestate);
        GameState game = parseIt(gamestate);
        UUID id;
        if (game.getId() == null) {
            id = UUID.randomUUID();
        } else {
            id = game.getId();
        }
        GamesOnServer.addGame(game, id);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public UUID sendResponse(String gamestate) {
        RestResponse result = new RestResponse();
        GameState game = parseIt(gamestate);
        UUID id;
        if (game.getId() == null) {
            id = UUID.randomUUID();
        } else {
            id = game.getId();
        }
        GamesOnServer.addGame(game, id);
        return id;
    }
}
