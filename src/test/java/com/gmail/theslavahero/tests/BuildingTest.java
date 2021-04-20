package com.gmail.theslavahero.tests;

import com.gmail.theslavahero.entity.building.BuildingConsoleOutput;
import com.gmail.theslavahero.entity.building.BuildingCreater;
import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;
import org.junit.Assert;
import org.junit.Test;

public class BuildingTest extends AbstractBuildingTest{

    @Test
    public void buildingTest() {
        System.out.println("buildingTest in class BuildingTest");
        BuildingConsoleOutput.print(building, lift);

        int amountOfFloors = building.getFloors().size();
        Assert.assertTrue(amountOfFloors >= BuildingCreater.MIN_FLOOR_BUILDING && amountOfFloors <= BuildingCreater.MAX_FLOOR_BUILDING);

        for (Floor floor : building.getFloors()) {
            Assert.assertTrue(floor.getPassengers().size() <= BuildingCreater.MAX_PASSENGERS_FLOOR);

            for (Passenger passenger : floor.getPassengers()) {
                Assert.assertTrue(passenger.getDesiredFloor() <= amountOfFloors && passenger.getDesiredFloor() > 0);
                Assert.assertTrue(passenger.getDesiredFloor() != floor.getNumber());
            }

        }
    }
}
