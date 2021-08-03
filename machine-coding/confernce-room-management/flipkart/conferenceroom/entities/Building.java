package flipkart.conferenceroom.entities;

import java.util.ArrayList;
import java.util.List;

public class Building extends BaseEntity {
    String buildingName;
    Long id;
    List<Floor> floors = new ArrayList<>();

    public Building() {
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
