package dius.test.waleedusman;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class IterationTest {
    @Test
    public void shouldHaveTwoColumnsWhenCreateIteration() {
        Iteration iteration = new Iteration();
        assertThat(iteration.columnCount(), is(equalTo(2)));
        assertThat(iteration.getColumn("starting"), is(notNullValue()));
        assertThat(iteration.getColumn("done"), is(notNullValue()));
    }

    // Assumption: every card must belong to a column
    @Test
    public void shouldPutCardInStartingColumnWhenAddedToIteration() {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        assertThat(iteration.getColumnName(card), is(equalTo("starting")));
    }

    @Test
    public void shouldMoveCard() throws Exception {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "done");
        assertThat(iteration.getColumnName(card), is(equalTo("done")));
    }

    @Test
    public void shouldMoveCardBackWhenUndoLastTransition() throws Exception {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "done");
        iteration.undoLastMove();
        assertThat(iteration.getColumnName(card), is(equalTo("starting")));
    }

    @Test
    public void shouldDoNothingForConsecutiveUndo() throws Exception {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "done");
        iteration.undoLastMove();
        iteration.undoLastMove();
        assertThat(iteration.getColumnName(card), is(equalTo("starting")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenMoveCardToColumnThatDoesNotExist() throws Exception {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "blah");
    }

    @Test
    public void shouldSumAllCardsInDoneColumnWhenCalculatingVelocity() throws Exception {
        Iteration iteration = new Iteration();
        Card card1 = new Card("c1", "first card", 1);
        Card card2 = new Card("c2", "second card", 1);
        Card card3 = new Card("c3", "third card", 1);
        iteration.addCard(card1);
        iteration.addCard(card2);
        iteration.addCard(card3);
        iteration.moveCard(card2, "done");
        iteration.moveCard(card3, "done");
        assertThat(iteration.velocity(), is(equalTo(2)));
    }

    @Test
    public void shouldListAllCardsWhenQueriedByColumnName() {
        Iteration iteration = new Iteration();
        Card card1 = new Card("c1", "first card", 1);
        Card card2 = new Card("c2", "second card", 1);
        Card card3 = new Card("c3", "third card", 1);
        iteration.addCard(card1);
        iteration.addCard(card2);
        iteration.addCard(card3);
        assertThat(iteration.getCardsInColumn("starting").size(), is(equalTo(3)));
        assertThat(iteration.getCardsInColumn("done").size(), is(equalTo(0)));
    }

    @Test(expected = WorkExceededException.class)
    public void shouldThrowExceptionWhenMoveResultsInWorkExceeded() throws Exception {
        Iteration iteration = new Iteration();
        iteration.addColumn(new Column("blah", 1));
        Card card1 = new Card("c1", "first card", 1);
        Card card2 = new Card("c2", "second card", 1);
        iteration.addCard(card1);
        iteration.addCard(card2);
        iteration.moveCard(card1, "blah");
        iteration.moveCard(card2, "blah");
    }

    @Test
    public void shouldNotAddReservedColumn() {
        Iteration iteration = new Iteration();
        iteration.addColumn(new Column("starting", 1));
        assertThat(iteration.getColumn("starting").getWorkInProgressLimit(), is(nullValue()));
    }
}
