A tracking system for Agile cards.

Caters for the following:
* Be able to create cards. Cards have a title, description and estimate in points
* Cards belong to an iteration
* Assume a board has only one iteration at the moment
* An iteration can have multiple columns. (It should have at least two, one starting and one done)
* There is a column designated as the starting column. And one designated as the done column.
* Columns have a name
* You can undo your last card column transition. So if you moved it to the done column, you can undo that move by calling a method
* You can calculate the velocity of a given iteration. This is defined as the sum of the points of all cards that are in the done column for an iteration.
* You can get all the cards in a particular column
* You can enforce a work in progress limit (expressed in points) for a column. If you try and add a card to a column that goes above the WIP limit, an exception should be thrown

## Pre-requisites
- Java 8
- gradle 3.3

## Build
In a terminal (assuming macOS), type:
```
gradle build
```

This will compile the code and run the unit tests.

See ${projectDir}/build/ for all build output (i.e. *.class, JAR file, junit test report). 