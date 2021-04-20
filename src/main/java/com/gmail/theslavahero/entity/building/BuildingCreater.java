package com.gmail.theslavahero.entity.building;

import com.gmail.theslavahero.entity.Floor;
import com.gmail.theslavahero.entity.Passenger;

public class BuildingCreater {

    public static final int MIN_FLOOR_BUILDING = 5;
    public static final int MAX_FLOOR_BUILDING = 20;
    public static final int MIN_PASSENGERS_FLOOR = 0;
    public static final int MAX_PASSENGERS_FLOOR = 10;

    private static int amountOfFloors;

    public static Building create() {
        Floor floor = new Floor();
        Building building = new Building();
        amountOfFloors = getRandomNumber(MIN_FLOOR_BUILDING, MAX_FLOOR_BUILDING);
        for (int currentFloorNumber = 1; currentFloorNumber <= amountOfFloors; currentFloorNumber++) {
            floor = getRandomFloor(currentFloorNumber);
            floor.setNumber(currentFloorNumber);
            building.addFloor(floor);
        }
        return building;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

    private static Floor getRandomFloor(int currentFloorNumber) {
        Floor floor = new Floor();
        int amountOfPassengers = getRandomNumber(MIN_PASSENGERS_FLOOR, MAX_PASSENGERS_FLOOR);
        for (int i = 0; i < amountOfPassengers; i++) {
            floor.addPassenger(getRandomPassenger(currentFloorNumber));
        }
        return floor;
    }

    public static Passenger getRandomPassenger(int currentFloor) {
        int desiredFloor = getRandomNumber(1, amountOfFloors);
        while (desiredFloor == currentFloor) {
            desiredFloor = getRandomNumber(1, amountOfFloors);
        }

        return new Passenger(desiredFloor);
    }
}

