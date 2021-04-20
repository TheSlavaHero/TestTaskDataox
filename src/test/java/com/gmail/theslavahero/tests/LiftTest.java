package com.gmail.theslavahero.tests;

import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;
import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.building.BuildingConsoleOutput;
import com.gmail.theslavahero.entity.lift.Direction;
import com.gmail.theslavahero.entity.lift.Lift;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LiftTest extends AbstractLiftTest {

    @Test
    public void liftTest() {
        System.out.println("LiftTest in class LiftTest");
        Assert.assertEquals(0, lift.getPassengersInLift().size());
        lift.start(false);
        Assert.assertEquals(0, lift.getPassengersInLift().size());

        for (Floor floor : building.getFloors()) {
            Assert.assertTrue(floor.getPassengers().isEmpty());
        }
    }

    @Test
    public void correctDecisionTest() {

        lift.setCurrentFloor(2);

        lift.decideDirection();
        Assert.assertEquals(lift.getDirection(), Direction.DOWN);

        floor2.addPassenger(new Passenger(3));
        lift.decideDirection();
        Assert.assertEquals(lift.getDirection(), Direction.UP);

        floor2.addPassenger(new Passenger(1));
        floor2.addPassenger(new Passenger(1));
        lift.decideDirection();
        Assert.assertEquals(lift.getDirection(), Direction.DOWN);

        lift.setCurrentFloor(1);
        lift.decideDirection();
        Assert.assertEquals(lift.getDirection(), Direction.UP);

        lift.setCurrentFloor(3);
        lift.decideDirection();
        Assert.assertEquals(lift.getDirection(), Direction.DOWN);

    }

    @Test
    public void getToNextFloorTest() {
        lift.decideDirection();
        int currentFloorTest;

        for (int i = 0; i  < 10; i++) {

            currentFloorTest = lift.getCurrentFloor();
            lift.getToNextFloor();
            if (lift.getDirection().equals(Direction.DOWN)) {
                Assert.assertEquals(currentFloorTest - 1, lift.getCurrentFloor());
                currentFloorTest = lift.getCurrentFloor();
                lift.decideDirection();
            } else if (lift.getDirection().equals(Direction.UP)) {
                Assert.assertEquals(currentFloorTest + 1, lift.getCurrentFloor());
                currentFloorTest = lift.getCurrentFloor();
                lift.decideDirection();
            }

        }

    }

    @Test
    public void passengersTransferBetweenFloorAndLiftTest() {
        lift.setCurrentFloor(2);

       floor2.addPassenger(new Passenger(3));
        floor2.addPassenger(new Passenger(3));
        lift.decideDirection();

        lift.getPassengersFromFloorToLift();
        Assert.assertTrue(floor2.getPassengers().isEmpty());
        Assert.assertFalse(lift.getPassengersInLift().isEmpty());

        lift.dropPassengersFromLiftToFloor();
        Assert.assertTrue(floor2.getPassengers().isEmpty());
        Assert.assertFalse(lift.getPassengersInLift().isEmpty());

        lift.setCurrentFloor(3);
        lift.dropPassengersFromLiftToFloor();
        Assert.assertTrue(lift.getPassengersInLift().isEmpty());
        lift.decideDirection();
        lift.getPassengersFromFloorToLift();
        Assert.assertFalse(lift.getPassengersInLift().isEmpty());

    }

    @Test(expected = IllegalArgumentException.class)
    public void currentFloorTest() {
        lift.setCurrentFloor(0);
        lift.setCurrentFloor(30);
    }

}
