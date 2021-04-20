This is an implementation of the interview task, given by Data-ox.

# Class Building
This class represents an entity "Building". Represents a building with floors and passengers in it.

Methods:
Usual getters and setters.

# Class BuildingConsoleOutput
This class has all the required methods to print one scope of the building. Prints building every time lift makes a move.

Methods:
print(Building building, Lift lift);
Prints entire building. Example of an output:
```
16	|0|'''''|	[]
15	|0|'''''|	[3, 11, 1, 13, 4, 12, 6, 7, 6]
14	|0|'''''|	[7, 7, 10, 7, 13, 4, 5, 10, 16]
13	|0|'''''|	[]
12	|0|'''''|	[8, 3, 13, 3, 3, 4, 3]
11	|0|'''''|	[]
10	|0|'''''|	[7, 15, 1, 16, 6, 14]
 9	|0|'''''|	[13, 15, 12]
 8	|0|'''''|	[7, 11, 10, 3, 14]
 7	|0|'''''|	[4, 16, 3, 6, 5, 4, 13, 5]
 6	|0|'''''|	[13]
 5	|0|'''''|	[1, 16, 9, 13, 11, 3]
 4	|0|'''''|	[15, 6, 16, 3, 13, 14, 7]
 3	|0|'''''|	[15, 10, 16]
 2	|0|'''''|	[5, 12, 4, 12, 14, 15]
 1	|0|_____|↓|	[5, 6, 11, 13, 6, 7, 15, 15, 5, 8]
[A]     [B] [C] [D]                  [E]
```
 - Column above A - Number of the floor. \n
- Column above B - Calculated last floor lift is going to go; floor, at which lift is going to recalculate it's route
- Column above C - The lift with or without passengers. | ' ' ' ' ' | - lift is not at this floor; _ - empty space in the lift, 1 - desired floor of the passenger
- Column above D - Direction od the lift
- Column above E - Queue on this floor, waiting for the lift.

# Class BuildingCreater
This class creates a random building, with n floors and k passengers on each floor, where 5 <= n <= 20; 0 <= k <= 10.

Methods:
create();
Returns new random building.

getRandomFloor(int currentFloorNumber);
Returns random floor with random passengers. currentFloorNumber is required not to generate passenger on the desired floor.

getRandomPassenger(int currentFloor);
Returns random passenger. currentFloorNumber is required not to generate passenger on the desired floor.

# Enum Direction
An enum for class Lift. Has 2 directions: UP and DOWN.

# Class Lift
This class creates and represents a lift with max capacity of 5 passengers. Works as a "iterator" above call Building, sorting it's passengers and delivering them where they want to be.


Methods:
start(boolean infinite);
Will start the lift. Boolean flag "infinite" corresponds to wether user wants to new passengers appear on new floors as old one's get to their's destination, or not.

**By the given documentation, program was supposed to have an infinite option. To author's opinion, it is easier to watch how lift works if it has limited amount of steps, so both implimintations were added.**

getPassengersFromFloorToLift();
Takes to the lift all suitable passengers (If lift goes up, it will take only passengers who want to get higher than they are).

dropPassengersFromLiftToFloor();
Same as getPassengersFromFloorToLift(), but only in other direction. Works in start(boolean infinite) if infinite is false.

dropPassengersFromLiftToFloorInfinite();
Mostly same as dropPassengersFromLiftToFloor(), modified version of the above method to run code infinitely.Works in start(boolean infinite) if infinite is true.

getToNextFloor();
Puts lift to the next floor, depending on it's direction.

decideDirection();
Decides direction of the lift depending on the passengers inside and outside of the lift.

calculateLastFloorBeforeDecidingDirection();
Calculates the last floor depending on the passengers inside the lift only, where will the lift decideDirection().

# Class Floor
This class represents an entity "Floor" in the "Building". Represents a floor with passengers on it.

Methods:
Usual getters and setters.

# Class Passenger
This class represents an entity "Passenger" on the "Floor". Represents a passenger with random desired floor.

Methods:
Usual getters and setters.

# Classes Main and MainInfinite
Simple entry points to the application; "Main" for the **non-infinite** version, "MainInfinite" is for the **infinite** version.

# Classes AbstactBuildingTest and AbstractLiftTest
Abstract classes for tests, AbstactBuildingTest initializes a random building, AbstractLiftTest - a preset one.



