package com.gmail.theslavahero.entity.building;

import com.gmail.theslavahero.entity.lift.Lift;

public class BuildingConsoleOutput {

    private static Building building;
    private static Lift lift;

    public static void print(Building building, Lift lift) {
        BuildingConsoleOutput.lift = lift;
        BuildingConsoleOutput.building = building;
        StringBuilder sb = new StringBuilder();
        int amountOfFloors = building.getFloors().size();
        for (int i = amountOfFloors; i > 0; i--) {
            sb.append(i);//number of the floor
            sb.append('\t');//tab space for better view
            sb.append('|').append(printLift(i)).append('|');//lift itself
            sb.append('\t');//tab space for better view
            sb.append(building.getFloors().get(i - 1).getPassengers());//all passengers
            sb.append('\n');//nextline to get building look like a building
        }
        System.out.println(sb.toString());
    }

    private static String printLift(int buildingFloor) {
        StringBuilder sb = new StringBuilder();

        if (lift.getCurrentFloor() == buildingFloor) {
        for (int i = 0; i < Lift.MAX_CAPACITY; i++) {
            if (i < lift.getPassengersInLift().size()) {
                sb.append(lift.getPassengersInLift().get(i).getDesiredFloor());
                sb.append('-');
            } else sb.append('_');
        }
        } else {
            sb.append("*****");
        }




        return sb.toString();
    }
}
