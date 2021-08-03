package flipkart.conferenceroom;

import flipkart.conferenceroom.entities.Building;
import flipkart.conferenceroom.entities.ConferenceRoom;
import flipkart.conferenceroom.entities.Floor;
import flipkart.conferenceroom.services.BuildingService;
import flipkart.conferenceroom.services.ConferenceRoomService;
import flipkart.conferenceroom.services.FloorService;

import java.util.ArrayList;
import java.util.List;

public class ConferenceRoomManagementSystem {

    public static final ConferenceRoomManagementSystem conferenceRoomManagementSystem = new ConferenceRoomManagementSystem();

    public static final BuildingService buildingService = new BuildingService();
    public static final FloorService floorService = new FloorService();
    public static final ConferenceRoomService conferenceRoomService = new ConferenceRoomService();

    private ConferenceRoomManagementSystem() {

    }

    public static ConferenceRoomManagementSystem getInstance() {
        return conferenceRoomManagementSystem;
    }


    public void addBuilding(String building) {
        buildingService.add(building);
    }

    public void addFloor(String buildingName, String floor) {
        Building building = getBuilding(buildingName);
        Floor fl = floorService.add(floor);
        buildingService.addFloor(building.getId(), fl);
    }


    public void addConferenceRoom(String buildingName, String floorName, String conferenceRoomName) {
        Building building = getBuilding(buildingName);
        Floor floor = getFloor(floorName);

        checkIfBuildingContains(building, floor);

        ConferenceRoom conferenceRoom = conferenceRoomService.add(conferenceRoomName);
        floorService.addConferenceRoom(floor.getId(), conferenceRoom);
    }

    private void checkIfBuildingContains(Building building, Floor floor) {
        if (building.getFloors().contains(floor)) {
            return;
        }
        throw new RuntimeException("There is no floor of this name: " + building.getBuildingName()
                + " in given building: " + floor.getFloorName());
    }

    private Floor getFloor(String floorName) {
        Floor floor = floorService.get(floorName);
        if (floor == null) {
            throw new RuntimeException("There is no floor of this name: " + floorName);
        }
        return floor;
    }

    private Building getBuilding(String buildingName) {
        Building building = buildingService.get(buildingName);
        if (building == null) {
            throw new RuntimeException("There is no building present for this name: " + buildingName);
        }
        return building;
    }

    public void bookSlot(String buildingName, String floorName, String conferenceRoomName, String slotString) {

        Building building = getBuilding(buildingName);
        Floor floor = getFloor(floorName);
        ConferenceRoom conferenceRoom = getConferenceRoom(conferenceRoomName);

        checkConferenceRoom(building, floor, conferenceRoom);

        conferenceRoomService.bookSlot(conferenceRoom, slotString);

    }

    public void cancelSlot(String buildingName, String floorName, String conferenceRoomName, String slotString) {
        Building building = getBuilding(buildingName);
        Floor floor = getFloor(floorName);
        ConferenceRoom conferenceRoom = getConferenceRoom(conferenceRoomName);

        checkConferenceRoom(building, floor, conferenceRoom);
        conferenceRoomService.cancelSlot(conferenceRoom, slotString);
    }


    public void listBookings(String buildingName, String floorName) {
        Building building = getBuilding(buildingName);
        Floor floor = getFloor(floorName);
        checkIfBuildingContains(building, floor);

        floor.getConferenceRooms().forEach(conferenceRoom -> {
            conferenceRoom.getSlots().forEach(slot -> {
                String replace = slot.replace("-", ":");
                System.out.println(replace + " " + floorName + " " + buildingName + " " + conferenceRoom.getConferenceRoomName());
            });


        });
    }


    public void search(String slotName, String buildingName, String floorName) {
        Building building = getBuilding(buildingName);
        Floor floor = getFloor(floorName);
        checkIfBuildingContains(building, floor);

        String slot = slotName.replace(":", "-");

        List<String> conferenceRooms = new ArrayList<>();
        floor.getConferenceRooms().forEach(conferenceRoom -> {
            if (!conferenceRoom.getSlots().contains(slot)) {
                conferenceRooms.add(conferenceRoom.getConferenceRoomName());
            }
        });

        if (!conferenceRooms.isEmpty()) {
            System.out.println("Search conf rooms available for slot: [ " + slot + " ]");
            System.out.println(conferenceRooms);
        } else {
            System.out.println("No Rooms Available");
        }

    }

    private void checkConferenceRoom(Building building, Floor floor, ConferenceRoom conferenceRoom) {
        checkIfBuildingContains(building, floor);
        if (floor.getConferenceRooms().contains(conferenceRoom)) {
            return;
        }
        throw new RuntimeException("There is no conference room present for this name: " + conferenceRoom.getConferenceRoomName()
                + " in the floor: " + floor.getFloorName() + " of this building : " + building.getBuildingName());
    }

    private ConferenceRoom getConferenceRoom(String conferenceRoomName) {
        ConferenceRoom conferenceRoom = conferenceRoomService.get(conferenceRoomName);
        if (conferenceRoom == null) {
            throw new RuntimeException("There is no conference room present for this name: " + conferenceRoomName);
        }
        return conferenceRoom;
    }

}



/*
// Assumption:
ConferenceRoom are scattered across multiple building and multiple floors
each floor can have multiple conference room.
conference room are uniquely identified by their id;

Booking will be done for slots in hours.
Hour will be taken in 24hrs format: 22:00 (10PM)

Each conference can be booked given that no-one has already booked that for the slot
System will be used only by one user only.
Booking can be done for 1 day only.

// Feature:
user should be able to list-down all the conference room available in the building:
e.g: alpha building has a1,a2,a3 conference room

User should be able to find suitable  rooms to book to given slot.

User should be able to cancel existing booking and rooms should be free to be booked again for that slot

List down all the bookings done by current user

Command:
ADD BUILDING <building_name> // adds building the system
i.e: ADD Building flipkart1

ADD Floor <building_name> <floor_name>

ADD CONFROOM <building> <floor> <conference_roomID>

BOOK SLOT BUILDING FLOOR
i.e SLOT 1:5

CANCEL SLOT BUILDING FLOOR ROOM_ID

LIST BOOKING <BUILDING> <FLOOR>
// should list all the booking made by the current user only

SEARCH SLOT BUILDING FLOOR
// returns possible available conference room for given information

Bonus Functionality:
Existing search will return empty results if no rooms are available for a given slot. Users want a suggestion functionality using which users can get a list of possible future slots [Size limit to 3] which can be booked.
Eg. Assume no room is available for a slot then the search will return an empty result while SUGGEST command will return the next 3 suggestions.
Command: SUGGEST 3:10






 */
