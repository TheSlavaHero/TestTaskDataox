package com.gmail.theslavahero.entity.lift;

import com.gmail.theslavahero.entity.building.BuildingConsoleOutput;
import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;

import java.util.ArrayList;
import java.util.List;

public class Lift {

    public static final int MAX_CAPACITY = 5;

    private List<Passenger> passengersInLift;
    private Building building;
    private int currentFloor;
    private Direction direction;
    private int lastFloorBeforeDecisingDirection;


    public Lift(Building building) {
        this.currentFloor = 1;
        this.passengersInLift = new ArrayList<>(5);
        this.building = building;
    }

    public List<Passenger> getPassengersInLift() {
        return passengersInLift;
    }

    public void setPassengersInLift(List<Passenger> passengersInLift) {
        this.passengersInLift = passengersInLift;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void start() {
        direction = Direction.UP;
        for (;;)  {
            dropPassengersFromLiftToFloor();

            if (passengersInLift.isEmpty()) {
                decideDirection();
            }

            getPassengersFromFloorToLift();
            BuildingConsoleOutput.print(building, this);


            boolean proceed = false;
            for (Floor floor : building.getFloors()) {
                if (!floor.getPassengers().isEmpty() || !passengersInLift.isEmpty()) {
                    proceed = true;
                    break;
                }
            }
            if (proceed) { getToNextFloor();} else break;
        }
    }

    public void getPassengersFromFloorToLift() {
        List<Passenger> passengersOnFloor = building.getFloors().get(currentFloor - 1).getPassengers();

        List<Passenger> passengersToDeleteFromFloor = new ArrayList<>();
        for (Passenger passenger : passengersOnFloor) {
            boolean correctPassenger = (direction == Direction.UP      && passenger.getDesiredFloor() > currentFloor) ||
                                       (direction == Direction.DOWN    && passenger.getDesiredFloor() < currentFloor);
            if (correctPassenger && passengersInLift.size() < MAX_CAPACITY) {
                passengersInLift.add(passenger);
                passengersToDeleteFromFloor.add(passenger);
            }
        }
        for (Passenger passenger : passengersToDeleteFromFloor) {
            passengersOnFloor.remove(passenger);
        }
    }

    public void dropPassengersFromLiftToFloor() {
        List<Passenger> passengersToDeleteFromLift = new ArrayList<>();
        for (Passenger passenger : passengersInLift) {
            if (passenger.getDesiredFloor() == currentFloor) {
                passengersToDeleteFromLift.add(passenger);
            }
        }
        for (Passenger passenger : passengersToDeleteFromLift) {
            passengersInLift.remove(passenger);
        }

    }

    public void getToNextFloor() {
        if (direction == Direction.UP) {
            currentFloor++;
        } else currentFloor--;
    }

    public void decideDirection() {
        if (building.getFloors().size() != currentFloor) {
            int up = 0;
            int down = 0;

            for (Passenger passenger : building.getFloors().get(currentFloor - 1).getPassengers()) {
                if (passenger.getDesiredFloor() > currentFloor) {up++;} else down++;
            }

            if (up >= down && up != 0) {
                direction = Direction.UP;
            } else {
                for (Floor floor : building.getFloors()) {
                    if (!floor.getPassengers().isEmpty() && floor.getNumber() <= currentFloor) {direction = Direction.DOWN;}
                    else if (!floor.getPassengers().isEmpty() && floor.getNumber() > currentFloor) {direction = Direction.UP;}
                }
            }
        }  else direction = Direction.DOWN;
    }

}
