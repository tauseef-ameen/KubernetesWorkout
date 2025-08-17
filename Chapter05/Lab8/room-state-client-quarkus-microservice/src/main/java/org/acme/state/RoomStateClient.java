package org.acme.state;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class RoomStateClient {
    private static final String AVAILABLE = "Available";
    private static final String MAINTENANCE = "Available";
    private static final String OCCUPIED = "Occupied";
    @Inject
    RoomConfiguration config;

    public List<RoomState> allRoomStates() {
        return List.of(
                new RoomState(config.room1(), AVAILABLE),
                new RoomState(config.room2(), MAINTENANCE),
                new RoomState(config.room3(), AVAILABLE),
                new RoomState(config.room4(), OCCUPIED),
                new RoomState(config.room5(), AVAILABLE)
        );
    }
}
