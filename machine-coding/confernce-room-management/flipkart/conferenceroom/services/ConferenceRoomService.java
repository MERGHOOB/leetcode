package flipkart.conferenceroom.services;

import flipkart.conferenceroom.entities.ConferenceRoom;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConferenceRoomService implements IService {

    Map<Long, ConferenceRoom> conferenceRoomDB = new HashMap<>();
    Map<Long, Map<String, Boolean>> roomToBookedSlots = new ConcurrentHashMap<>();
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
    public ConferenceRoom get(String conferenceRoomName) {
        Optional<ConferenceRoom> first = conferenceRoomDB.values()
                .stream()
                .filter(conferenceRoom -> conferenceRoom.getConferenceRoomName().equalsIgnoreCase(conferenceRoomName))
                .findFirst();
        if (first.isEmpty())
            return null;

        return first.get();
    }

    public void bookSlot(ConferenceRoom conferenceRoom, String slotString) {
        String key = getKey(slotString);
        if (key.isEmpty()) {
            return;
        }
        if (roomToBookedSlots.get(conferenceRoom.getId()).containsKey(key)) {
            System.out.println("Slot: [ " + key + " ]"
                    + " is already booked");
        }
        roomToBookedSlots.get(conferenceRoom.getId()).put(key, true);
        conferenceRoom.getSlots().add(key);
        System.out.println("Congratulations! your slot: [ " + key + " ]" + "is booked!");
    }

    public void cancelSlot(ConferenceRoom conferenceRoom, String slotString) {

        String key = getKey(slotString);
        if (key.isEmpty()) {
            return;
        }
        if (!roomToBookedSlots.get(conferenceRoom.getId()).containsKey(key)) {
            System.out.println("Slot: [ " + key + " ]"
                    + " is not booked");
        }
        roomToBookedSlots.get(conferenceRoom.getId()).remove(key);
        System.out.println("Success! your meeting: [ " + key + " ]" + "is canceled!");
    }

    private String getKey(String slotString) {
        String[] split = slotString.split(":");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        if (isInvalidTime(start) || isInvalidTime(end)) {
            System.out.println("Invalid Time Range");
            return "";
        }

        return start + "-" + end;
    }

    private boolean isInvalidTime(int time) {
        return time < 0 || time > 23;
    }


}
