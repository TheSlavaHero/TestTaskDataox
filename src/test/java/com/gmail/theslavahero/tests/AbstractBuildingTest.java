package com.gmail.theslavahero.tests;

import com.gmail.theslavahero.entity.building.BuildingCreater;
import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.lift.Lift;
import org.junit.Before;

public abstract class AbstractBuildingTest {

    protected Building building;
    protected Lift lift;

    @Before
    public void init() {
        building = BuildingCreater.create();
        lift = new Lift(building);
    }
}
