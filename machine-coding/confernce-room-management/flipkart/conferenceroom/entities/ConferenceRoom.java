package flipkart.conferenceroom.entities;

import java.util.ArrayList;
import java.util.List;

public class ConferenceRoom extends BaseEntity {
    Long id;
    String conferenceRoomName;
    List<Slot> slots = new ArrayList<>();


    public ConferenceRoom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConferenceRoomName() {
        return conferenceRoomName;
    }

    public void setConferenceRoomName(String conferenceRoomName) {
        this.conferenceRoomName = conferenceRoomName;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
