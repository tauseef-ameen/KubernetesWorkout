package org.acme.state;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class RoomStateClient {

    public List<RoomState> allRoomStates() {
        return List.of(new RoomState(1, "Available"),
                new RoomState(11, "Maintenance"),
                new RoomState(22, "Available"),
                new RoomState(33, "Occupied"),
                new RoomState(44, "Available"));
    }
}
