package chessServer;

import Chess.GameState;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Path("/hello")
public class ChessResource {

    @POST
    @Path("/game")
    @Consumes("application/json")
    public Response addGame(String gameState) throws URISyntaxException
    {
//        if(user.getFirstName() == null || user.getLastName() == null) {
//            return Response.status(400).entity("Please provide all mandatory inputs").build();
//        }
//        user.setId(DB.values().size()+1);
//        user.setUri("/user-management/"+user.getId());
//        DB.put(user.getId(), user);
//        UUID id;
//        if (game.getId() == null) {
//            id = UUID.randomUUID();
//        } else {
//            id = game.getId();
//        }
//        GamesOnServer.addGame(game, id);
        System.out.println("HERE");
        System.out.println(gameState);
//        return Response.status(201).type(MediaType.TEXT_PLAIN).entity(id).build();
        return Response.status(201).build();
    }

    @GET
    @Produces("text/plain")
    public String hello(){
        return "hello world";
    }

}
