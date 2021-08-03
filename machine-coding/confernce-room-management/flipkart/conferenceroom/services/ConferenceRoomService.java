package flipkart.conferenceroom.services;

import flipkart.conferenceroom.entities.BaseEntity;
import flipkart.conferenceroom.entities.ConferenceRoom;

import java.util.HashMap;
import java.util.Map;

public class ConferenceRoomService implements IService {

    Map<Long, ConferenceRoom> conferenceRoomDB = new HashMap<>();
    Long index = 0L;

    @Override
    public ConferenceRoom add(String conferenceRoomName) {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomName(conferenceRoomName);
        synchronized (index) {
            index += 1;
            conferenceRoom.setId(index);
            conferenceRoomDB.put(index, conferenceRoom);
        }
        return conferenceRoom;
    }

    @Override
    public BaseEntity get(String serviceObjectName) {
        return null;
    }
}
