//package chessServer;
//
//import Chess.GameState;
//import org.springframework.web.bind.annotation.*;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.UUID;
//
//import static chessServer.GamesOnServer.parseIt;
//
//
// @Path("/test")
//public class MyController {
//     @GET
//     @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//     public Response TestLily (@PathParam("id") int id) {
//         System.out.println(id);
//         return Response.status(Response.Status.OK.getStatusCode()).build();
//     }
// }
//
////@org.springframework.web.bind.annotation.RestController
////public class MyController {
////    public static class RestResponse {
////        private String param1;
////
////        public String getParam1() {
////            return param1;
////        }
////
////        public void setParam1(String param1) {
////            this.param1 = param1;
////        }
////    }
////
////    @RequestMapping(value = "", method = RequestMethod.POST,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////    public RestResponse postMethod (String gamestate) {
////        RestResponse result = new RestResponse();
////        result.setParam1(gamestate);
////        GameState game = parseIt(gamestate);
////        UUID id;
////        if (game.getId() == null) {
////            id = UUID.randomUUID();
////        } else {
////            id = game.getId();
////        }
////        GamesOnServer.addGame(game, id);
////        return result;
////    }
////
////    @RequestMapping(value = "", method = RequestMethod.GET,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////
////    @ResponseBody
////    public UUID sendResponse(String gamestate) {
////        RestResponse result = new RestResponse();
////        GameState game = parseIt(gamestate);
////        UUID id;
////        if (game.getId() == null) {
////            id = UUID.randomUUID();
////        } else {
////            id = game.getId();
////        }
////        GamesOnServer.addGame(game, id);
////        return id;
////    }
////}
