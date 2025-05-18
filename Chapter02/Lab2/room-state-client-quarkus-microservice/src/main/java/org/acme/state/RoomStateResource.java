package org.acme.state;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Path("/state")
@RequiredArgsConstructor
public class RoomStateResource {
    private final RoomStateClient roomStateClient;

    @Path("{roomId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response roomState(@PathParam("roomId") int roomId) {
        Log.infof("Checking state of room for %s ", roomId);
        final List<RoomState> rooms = roomStateClient.allRoomStates();

        final String state = rooms.stream()
                .filter(room -> room.roomId() == roomId)
                .map(RoomState::state)
                .findFirst()
                .orElse(null);

        if (state == null) {
            Log.warnf("Room state not found for roomId %s", roomId);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Room state not found for roomId: " + roomId))
                    .build();
        }

        Log.infof("Found state of roomId %s as %s ", roomId, state);
        return Response.ok(new RoomState(roomId, state)).build();
    }
}
