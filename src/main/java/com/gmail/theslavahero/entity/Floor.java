package com.gmail.theslavahero.entity;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Passenger> passengers;
    private int number;

    public Floor() {
        this.passengers = new ArrayList<Passenger>();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

}
