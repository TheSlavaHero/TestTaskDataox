package com.gmail.theslavahero.tests;

import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;
import com.gmail.theslavahero.entity.building.BuildingCreater;
import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.lift.Lift;
import org.junit.Before;

public abstract class AbstractLiftTest {

    protected Building building;
    protected Lift lift;
    protected Floor floor1;
    protected Floor floor2;
    protected Floor floor3;

    @Before
    public void init() {
        floor1 = new Floor();
        floor1.addPassenger(new Passenger(3));
        floor1.addPassenger(new Passenger(3));
        floor1.addPassenger(new Passenger(3));

        floor2 = new Floor();

        floor3 = new Floor();
        floor3.addPassenger(new Passenger(1));
        floor3.addPassenger(new Passenger(1));
        floor3.addPassenger(new Passenger(1));

        building = new Building();
        building.addFloor(floor1);
        building.addFloor(floor2);
        building.addFloor(floor3);

        lift = new Lift(building);
    }
}
