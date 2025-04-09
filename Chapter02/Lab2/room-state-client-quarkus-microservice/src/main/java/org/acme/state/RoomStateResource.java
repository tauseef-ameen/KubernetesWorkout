package org.acme.state;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/state")
@RequiredArgsConstructor
public class RoomStateResource {
    private final RoomStateClient roomStateClient;

    @Path("{roomId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String roomState(@PathParam("roomId") int roomId) {

        Log.infof("Checking state of room for %s ", roomId);
        List<RoomState> rooms = roomStateClient.allRoomStates();
        AtomicReference<String> stateRef = new AtomicReference<>();

        rooms.stream()
                .filter(room -> room.roomId() == roomId)
                .findFirst()
                .ifPresent(room -> stateRef.set(room.state()));

        Log.infof("Found state of roomId %s as %s ", roomId, stateRef.get());
        return stateRef.toString();
    }
}
