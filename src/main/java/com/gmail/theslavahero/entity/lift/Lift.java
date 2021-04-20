package com.gmail.theslavahero.entity.lift;

import com.gmail.theslavahero.entity.building.BuildingConsoleOutput;
import com.gmail.theslavahero.entity.building.Building;
import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;
import com.gmail.theslavahero.entity.building.BuildingCreater;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.theslavahero.entity.building.BuildingCreater.getRandomNumber;
import static com.gmail.theslavahero.entity.building.BuildingCreater.getRandomPassenger;

public class Lift {

    public static final int MAX_CAPACITY = 5;

    private final Building building;

    private List<Passenger> passengersInLift;
    private Direction direction;
    private int currentFloor;
    private int lastFloorBeforeDecidingDirection;

    public Lift(Building building) {
        this.currentFloor = 1;
        this.passengersInLift = new ArrayList<>(MAX_CAPACITY);
        this.building = building;
    }

    public int getLastFloorBeforeDecidingDirection() {
        return lastFloorBeforeDecidingDirection;
    }

    public void setLastFloorBeforeDecidingDirection(int lastFloorBeforeDecidingDirection) {
        this.lastFloorBeforeDecidingDirection = lastFloorBeforeDecidingDirection;
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
        if (currentFloor < 1 || currentFloor > building.getFloors().size()) {
            throw new IllegalArgumentException("setCurrentFloor argument is invalid. Must be more than 0 and less than amount of the floors in the building.");
        }
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void start(boolean infinite) {
        direction = Direction.UP;
        for (;;)  {

            if (infinite) {
                dropPassengersFromLiftToFloorInfinite();
            } else dropPassengersFromLiftToFloor();


            if (passengersInLift.isEmpty()) {
                decideDirection();
            }
            getPassengersFromFloorToLift();
            calculateLastFloorBeforeDecidingDirection();
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

    public void dropPassengersFromLiftToFloorInfinite() {//modified version of the above method to run code infinitely
        List<Passenger> passengersToDeleteFromLift = new ArrayList<>();
        for (Passenger passenger : passengersInLift) {
            if (passenger.getDesiredFloor() == currentFloor) {
                passengersToDeleteFromLift.add(passenger);
                int buildingSize = building.getFloors().size();
                for (;;) {
                    int randomFloor = getRandomNumber(0, buildingSize - 1);
                    Passenger randomPassenger = getRandomPassenger(currentFloor);
                    if (building.getFloors().get(randomFloor).getPassengers().size() < 10 && randomPassenger.getDesiredFloor() != randomFloor + 1) {
                        building.getFloors().get(randomFloor).addPassenger(randomPassenger);
                        break;
                    }
                }
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

    public void calculateLastFloorBeforeDecidingDirection() {

        if(!passengersInLift.isEmpty()) {

            if (direction.equals(Direction.DOWN)) {
                int min = passengersInLift.get(0).getDesiredFloor();
                for (Passenger passenger : passengersInLift) {
                    if (passenger.getDesiredFloor() < min) {
                        min = passenger.getDesiredFloor();
                    }
                }
                lastFloorBeforeDecidingDirection = min;
            } else if (direction.equals(Direction.UP)) {
                int max = passengersInLift.get(0).getDesiredFloor();
                for (Passenger passenger : passengersInLift) {
                    if (passenger.getDesiredFloor() > max) {
                        max = passenger.getDesiredFloor();
                    }
                }
                lastFloorBeforeDecidingDirection = max;
            }

        }

    }

}
