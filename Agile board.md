DiUS are keen on this "Agile" thing. We want to develop a tracking system for Agile cards. 

Your application should cater for the following: 

* Be able to create cards. Cards have a title, description and estimate in points
* Cards belong to an iteration
* Assume a board has only one iteration at the moment
* An iteration can have multiple columns. (It should have at least two, one **starting** and one **done**)
* There is a column designated as the **starting** column. And one designated as the **done** column.
* Columns have a name
* You can undo your last card column transition. So if you moved it to the **done** column, you can undo that move by calling a method
* You can calculate the velocity of a given iteration. This is defined as the sum of the points of all cards that are in the **done** column for an iteration.
* You can get all the cards in a particular column
* You can enforce a work in progress limit (expressed in points) for a column. If you try and add a card to a column that goes above the WIP limit, an exception should be thrown 

Your interface should look something like the folllowing:
```java
board = new Board(columns);
iteration.add(card);
iteration.velocity();
iteration.moveCard(card, toColumn);
iteration.undoLastMove();
```

Notes on implementation:

* use Java, Javascript, Groovy, Scala, Ruby or Swift
* try not to spend more than 2 hours maximum. (We don't want you to lose a weekend over this!)
* don't build guis etc, we're more interested in your approach to solving the given task, not how shiny it looks.
* don't use any frameworks (rails, spring etc), or any external jars/gems (unless it's for testing..)

When you've finished, zip up the solution and send it to bmorrison@dius.com.au. Happy coding :)