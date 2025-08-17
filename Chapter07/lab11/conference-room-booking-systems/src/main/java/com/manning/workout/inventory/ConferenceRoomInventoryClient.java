package com.manning.workout.inventory;

import com.manning.workout.config.ConfigMapConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConferenceRoomInventoryClient implements ConferenceRoomInventory {
    private final ConfigMapConfiguration configMap;
    @Override
    public List<ConferenceRoom> allRooms() {
        return List.of(
                new ConferenceRoom(
                        configMap.getBuilding1(),
                        configMap.getRoom1(),
                        1, "A"),
                new ConferenceRoom(
                        configMap.getBuilding2(),
                        configMap.getRoom2(),
                        3, "D"),
                new ConferenceRoom(
                        configMap.getBuilding3(),
                        configMap.getRoom3(),
                        5, "C"),
                new ConferenceRoom(
                        configMap.getBuilding4(),
                        configMap.getRoom4(),
                        7, "B"),
                new ConferenceRoom(
                        configMap.getBuilding5(),
                        configMap.getRoom5(),
                        9, "E")
        );
    }
}