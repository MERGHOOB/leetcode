package flipkart.conferenceroom.services;

import flipkart.conferenceroom.entities.BaseEntity;
import flipkart.conferenceroom.entities.Building;
import flipkart.conferenceroom.entities.Floor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BuildingService implements IService {
    Map<Long, Building> buildingDB = new HashMap<>();
    Long index = 0L;

    public Building add(String buildingName) {
        Building building = new Building();
        building.setBuildingName(buildingName);
        synchronized (index) {
            index += 1;
            building.setId(index);
            buildingDB.put(index, building);
        }

        return building;
    }

    @Override
    public Building get(String buildingName) {
        Optional<Building> building = getBuilding(buildingName);
        if (building.isEmpty()) {
            return null;
        }
        return building.get();

    }

    private Optional<Building> getBuilding(String building) {
        return buildingDB.values().stream().filter(b -> b.getBuildingName().equalsIgnoreCase(building)).findFirst();
    }

    public void addFloor(Long id, Floor floor) {
        buildingDB.get(id).getFloors().add(floor);
    }
}
