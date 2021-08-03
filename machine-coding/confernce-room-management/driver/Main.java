package driver;

import flipkart.conferenceroom.ConferenceRoomManagementSystem;

import java.util.Scanner;

public class Main {

    public static final String ADD = "ADD";
    public static final String BOOK = "BOOK";
    public static final String CANCEL = "CANCEL";
    public static final String LIST = "LIST";
    public static final String SEARCH = "SEARCH";
    public static final String EXIT = "EXIT";
    public static final String BUILDING = "BUILDING";
    public static final String FLOOR = "FLOOR";
    public static final String CONFROOM = "CONFROOM";
    public static final String
            BOOKING = "BOOKING";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase(ADD)) {
                cmd = scanner.next();
                if (cmd.equalsIgnoreCase(BUILDING)) {
                    ConferenceRoomManagementSystem.getInstance().addBuilding(scanner.next());

                } else if (cmd.equalsIgnoreCase(FLOOR)) {
                    String building = scanner.next();
                    String floor = scanner.next();
                    ConferenceRoomManagementSystem.getInstance().addFloor(building, floor);

                } else if (cmd.equalsIgnoreCase(CONFROOM)) {
                    String building = scanner.next();
                    String floor = scanner.next();
                    String conferenceRoom = scanner.next();
                    ConferenceRoomManagementSystem.getInstance().addConferenceRoom(building, floor, conferenceRoom);
                } else {
                    throw new IllegalArgumentException("This is not right argument! \n" +
                            "Please use one of the element from the list" +
                            "[" + BUILDING + ", " + FLOOR + ", " + CONFROOM + " ]");
                }

            } else if (cmd.equalsIgnoreCase(BOOK)) {
                String slot = scanner.next();
                String buildingName = scanner.next();
                String floorName = scanner.next();
                String conferenceRoomName = scanner.next();

                ConferenceRoomManagementSystem.getInstance().bookSlot(buildingName, floorName, conferenceRoomName, slot);

            } else if (cmd.equalsIgnoreCase(CANCEL)) {
                String slot = scanner.next();
                String buildingName = scanner.next();
                String floorName = scanner.next();
                String conferenceRoomName = scanner.next();
                ConferenceRoomManagementSystem.getInstance().cancelSlot(buildingName, floorName, conferenceRoomName, slot);

            } else if (cmd.equalsIgnoreCase(LIST)) {
                cmd = scanner.next();
                if (cmd.equalsIgnoreCase(BOOKING)) {
                    String buildingName = scanner.next();
                    String floorName = scanner.next();
                    ConferenceRoomManagementSystem.getInstance().listBookings(buildingName, floorName);
                }

            } else if (cmd.equalsIgnoreCase(SEARCH)) {
                String slotName = scanner.next();
                String buildingName = scanner.next();
                String floorName = scanner.next();
                ConferenceRoomManagementSystem.getInstance().search(slotName, buildingName, floorName);

            } else if (cmd.equalsIgnoreCase(EXIT)) {
                return;
            }
        }
    }

}
