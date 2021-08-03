package flipkart.conferenceroom.services;

import flipkart.conferenceroom.entities.ConferenceRoom;
import flipkart.conferenceroom.entities.Floor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FloorService implements IService {
    Map<Long, Floor> floorDB = new HashMap<>();
    Long index = 0L;

    @Override
    public Floor add(String floorName) {
        Floor floor = new Floor();
        floor.setFloorName(floorName);
        synchronized (index) {
            index += 1;
            floor.setId(index);
            floorDB.put(index, floor);
        }
        return floor;
    }

    @Override
    public Floor get(String floorName) {

        Optional<Floor> first = floorDB.values().stream().filter(floor -> floor.getFloorName().equalsIgnoreCase(floorName)).findFirst();
        if (first.isEmpty()) {
            return null;
        }
        return first.get();
    }

    public void addConferenceRoom(Long id, ConferenceRoom conferenceRoom) {
        floorDB.get(id).getConferenceRooms().add(conferenceRoom);
    }
}
