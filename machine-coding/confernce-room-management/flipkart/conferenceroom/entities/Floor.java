package flipkart.conferenceroom.entities;

import java.util.ArrayList;
import java.util.List;

public class Floor extends BaseEntity {

    Long id;
    String floorName;
    List<ConferenceRoom> conferenceRooms = new ArrayList<>();

    public Floor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public List<ConferenceRoom> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoom> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }
}
