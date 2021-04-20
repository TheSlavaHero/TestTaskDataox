package com.gmail.theslavahero;

import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.building.BuildingCreater;
import com.gmail.theslavahero.entity.lift.Lift;

public class MainInfinite {
    public static void main(String[] args) {
        Building building = BuildingCreater.create();
        Lift lift = new Lift(building);
        lift.start(true);
    }
}
