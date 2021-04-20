package com.gmail.theslavahero.entity.building;

import com.gmail.theslavahero.entity.Floor;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<Floor> floors;

    public Building() {
        this.floors = new ArrayList<Floor>();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

}
